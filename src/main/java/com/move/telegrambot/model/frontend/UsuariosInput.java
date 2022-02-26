package com.move.telegrambot.model.frontend;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuariosInput {
    
    private String usuario;
    private String operation;
    private String user_new;
    private String rol_user_new;
    private String name_lastname_new;
    private String password_new;
    private String status;
    
}
