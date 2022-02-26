package com.move.telegrambot.service.sunacrip;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.move.telegrambot.bussines.SunacripBusiness;
import com.move.telegrambot.bussines.UsuariosBusiness;
import com.move.telegrambot.bussines.validator.UsersRolValidator;
import com.move.telegrambot.model.dto.BilleterasUserDto;
import com.move.telegrambot.model.dto.TasaSunacripDto;
import com.move.telegrambot.model.dto.UsuariosDto;
import com.move.telegrambot.model.frontend.UsuarioOutput;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SunacripService {

    Logger log = org.slf4j.LoggerFactory.getLogger(SunacripService.class);

    @Autowired
    UsuariosBusiness usuariosBusinnes;

    @Autowired
    SunacripBusiness sunacripBusiness;

    @Autowired
    UsersRolValidator usersRolValidator;

    ObjectMapper mapper = new ObjectMapper();

    public UsuarioOutput operationPri(String id, String operation) throws JsonProcessingException {
        UsuariosDto userDto = this.usuariosBusinnes.findByUser(id);
        UsuarioOutput validGa = new UsuarioOutput();

        if (userDto == null) {
            validGa.setRespuesta("Usuario inexistente en el bot, para registrarlo usa el comando /crear usuario clave");
            validGa.setStatus("1001");
            validGa.setObjeto(null);
            return validGa;
        }

        if (!userDto.getStatus().equals("ACTIVO")) {
            validGa.setRespuesta("Tu cuenta esta inactiva por favor /reporta usuario mensaje");
            validGa.setStatus("1001");
            validGa.setObjeto(null);
            return validGa;
        }

        UsuarioOutput validar = this.usersRolValidator.rolToUser(userDto, operation);
            if (validar != null) {
                if (!validar.getStatus().equals("1000"))
                    return validar;
            } else {
                validGa.setRespuesta("comando erroneo");
                validGa.setStatus("1001");
                validGa.setObjeto(null);
                return validGa;
            }
        switch (operation) {

            case "/aperturas":
                Long valor = this.sunacripBusiness.countUserRegister();
                return this.mapperParticular("Usuarios total registrados: "+valor, "Usuarios total registrados: " + valor, "200");
            case "/tasa":
                List<TasaSunacripDto> tasas = this.sunacripBusiness.tasaAll();
                String mensaje = this.factoryConsulta(tasas);
                return this.mapperParticular(mensaje, mensaje, "200");
            case "/billeteraAll":
                List<BilleterasUserDto> total = this.sunacripBusiness.countBilleteraAll();
                String message = this.factoryBilleteraAll(total);
                return this.mapperParticular(message, message, "200");
            default:
                return this.mapperParticular(null, "Operacion no valida", "1002");
        }
    }

    private String factoryConsulta(List<TasaSunacripDto> consulta) {
        List<String> list = new ArrayList<String>();
        if (consulta.size() != 0) {
            String listString = "";
            for (TasaSunacripDto string : consulta) {
                String resultado = "moneda: " + string.getMoneda()
                        + " | Dolar : " + string.getPrecio_dolar()
                        + " | Euro : " + string.getPrecio_euro();
                list.add(resultado);
            }
            for (String s : list) {
                listString += s + "\n";
            }
            return listString;

        } else {
            return "Actualmente no hay tasas disponible";
        }
    }

    private String factoryBilleteraAll(List<BilleterasUserDto> consulta) {
        if (consulta.size() != 0) {
            String listString = "";
            List<String> list = new ArrayList<String>();
            List<BilleterasUserDto> petro = consulta.stream().filter(x-> x.getMoneda().equals("PTR")).collect(Collectors.toList());
            List<BilleterasUserDto> btc = consulta.stream().filter(x-> x.getMoneda().equals("BTC")).collect(Collectors.toList());
            List<BilleterasUserDto> ltc = consulta.stream().filter(x-> x.getMoneda().equals("LTC")).collect(Collectors.toList());
            List<BilleterasUserDto> dash = consulta.stream().filter(x-> x.getMoneda().equals("DASH")).collect(Collectors.toList());

            list.add("Total billeteras creadas de PTR "+String.valueOf(petro.size()));
            list.add("Total billeteras creadas de BTC "+String.valueOf(btc.size()));
            list.add("Total billeteras creadas de LTC "+String.valueOf(ltc.size()));
            list.add("Total billeteras creadas de DASH "+String.valueOf(dash.size()));
            for (String s : list) {
                listString += s + "\n";
            }
            return listString;
        } else {
            return "Actualmente no hay billeteras registradas";
        }
    }

    private UsuarioOutput mapperParticular(Object object, String mensaje, String status) {
        UsuarioOutput salida = new UsuarioOutput();
        salida.setObjeto(object);
        salida.setStatus(status);
        salida.setRespuesta(mensaje);
        return salida;
    }

    // private UsuarioOutput userLoged(UsuariosDto usersInput) {
    // UsuarioOutput salida = new UsuarioOutput();
    // if (!usersInput.getSession()) {

    // salida.setRespuesta(
    // "Debes iniciar sesion /login user password");
    // salida.setStatus("1001");
    // return salida;
    // }
    // return salida;

    // }

}
