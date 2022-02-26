package com.move.telegrambot.service.usuarios;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.move.telegrambot.bussines.UserTelegramBusiness;
import com.move.telegrambot.bussines.UsuariosBusiness;
import com.move.telegrambot.bussines.validator.UsersRolValidator;
import com.move.telegrambot.model.dto.DetalleComandoRolDto;
import com.move.telegrambot.model.dto.UserIdDto;
import com.move.telegrambot.model.dto.UsuariosDto;
import com.move.telegrambot.model.frontend.UsuarioOutput;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuariosService {

    Logger log = org.slf4j.LoggerFactory.getLogger(UsuariosService.class);

    @Autowired
    UsuariosBusiness usuariosBusinnes;

    @Autowired
    UserTelegramBusiness userTelegramBusiness;

    @Autowired
    UsersRolValidator usersRolValidator;

    ObjectMapper mapper = new ObjectMapper();

    public UsuarioOutput operationPri(String operation, String id) throws JsonProcessingException {
        UsuariosDto userDto = this.usuariosBusinnes.findByUser(id);
        UsuarioOutput validGa = new UsuarioOutput();
        

        if (!operation.equals("/crear")) {

            if (userDto == null) {
                validGa.setRespuesta("Usuario inexistente en el bot, para registrarlo usa el comando /crear");
                validGa.setStatus("1001");
                validGa.setObjeto(null);
                return validGa;
            }

            if (!userDto.getStatus().equals("ACTIVO")) {
                validGa.setRespuesta("Tu cuenta esta inactiva.");
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
        }

        switch (operation) {
            case "/usuariosbot":
                List<UserIdDto> consulta = this.userTelegramBusiness.listll();
                String resultado = factoryConsulta(consulta);
                return this.mapperParticular(resultado, "Consulta exitosa", "200");
            
            case "/help":
                List<DetalleComandoRolDto> helplist = this.userTelegramBusiness.allComandosDescrip();
                String result = factoryConsultaHelp(helplist, userDto);
                return this.mapperParticular(result, "Consulta exitosa", "200");

            default:
                return this.mapperParticular(null, "Operacion no valida", "1002");
        }
    }

    private String factoryConsulta(List<UserIdDto> consulta) {
        List<String> list = new ArrayList<String>();
        if (consulta.size() != 0) {
            String listString = "";
            for (UserIdDto string : consulta) {
                String resultado = "id: " + string.getIdTelegram()+"||"
                        + " Nombre: " + string.getNombre()+"||"
                        + " Rol: " + string.getRol()+"||"
                        + " Status: " + string.getStatus();
                list.add(resultado);
            }
            for (String s : list) {
                listString += s + "\n";
            }
            return listString;

        } else {
            return "Actualmente no hay usuarios registrados";
        }
    }

    private String factoryConsultaHelp(List<DetalleComandoRolDto> consulta, UsuariosDto userDto) {
        List<String> salida = new ArrayList<String>();
        log.info(String.valueOf(consulta.size()));
        String listString = "";
        if (consulta.size() != 0) {
            
                List<DetalleComandoRolDto> listaComandos = this.userTelegramBusiness.allComandosDescrip();
                if(listaComandos.size()!=0){
                    for (DetalleComandoRolDto detalle : consulta) {
                        String[] listaRol = detalle.getRol().split(",");
                        for (String rolGeneral : listaRol) {
                            if (rolGeneral.equals(userDto.getRol())) {
                                String resultado = detalle.getComando()+" => "
                                + detalle.getDescripcionBot();
                                salida.add(resultado);
                            }
                        }
                    }
                }else{
                    return "actualmente no existe comandos que mostrar.";
                }
            
            for (String s : salida) {
                listString += s + "\n";
            }
            return listString;

        } else {
            return "Actualmente no hay usuarios registrados";
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
    //     UsuarioOutput salida = new UsuarioOutput();
    //     if (!usersInput.getSession()) {

    //         salida.setRespuesta(
    //                 "Debes iniciar sesion /login user password");
    //         salida.setStatus("1001");
    //         return salida;
    //     }
    //     return salida;

    // }

}
