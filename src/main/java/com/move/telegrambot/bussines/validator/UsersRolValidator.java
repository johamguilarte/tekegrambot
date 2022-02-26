package com.move.telegrambot.bussines.validator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.move.telegrambot.bussines.RolComandosBusiness;
import com.move.telegrambot.bussines.UsuariosBusiness;
import com.move.telegrambot.model.dto.RolComandosDto;
import com.move.telegrambot.model.dto.UsuariosDto;
import com.move.telegrambot.model.frontend.UsuarioOutput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersRolValidator {

    @Autowired
    RolComandosBusiness rolComandosBusiness;

    @Autowired
    UsuariosBusiness usuariosBusiness;

    public UsuarioOutput rolToUser(UsuariosDto userDto, String operacion) {
        String rolUser = userDto.getRol();
        RolComandosDto dtoRol = this.rolComandosBusiness.findByUser(operacion);
        UsuarioOutput salida = new UsuarioOutput();
        if (dtoRol != null) {
            // se limpia los roles porque vienen de esta forma 1000,1200,1300
            String[] listaRol = dtoRol.getRol().split(",");
            List<String> rol = new ArrayList<String>();
            for (String string : listaRol) {
                if (string.equals(rolUser)) {
                    rol.add(string);
                }
            }
            // si rol esta vacio significa que no tiene los permisos y pelo fue bola
            if (rol.isEmpty()) {
                salida.setStatus("1001");
                salida.setRespuesta("No tienes permiso para realizar esta accion.");
            } else {
                salida.setStatus("1000");
            }
            return salida;
        }
        return null;
    }

    // // validar que la fecha de ultima session no sea mayor a 5 minutos si lo es
    // // hacer update a false la session
    // public UsuarioOutput timeCloseValidator(UsuariosDto userDto) {
    //     UsuarioOutput salida = new UsuarioOutput();
    //     Boolean active = minutosRestante(userDto.ult_session);
    //     if (!active) {
    //         this.usuariosBusiness.closeSession(userDto);
    //         salida.setStatus("1001");
    //         salida.setRespuesta("DEBES VOLVER A INICIAR SESION PARA REALIZAR ESTA ACCION");
    //     } else {
    //         salida.setStatus("1000");
    //     }
    //     return salida;

    // }
}
