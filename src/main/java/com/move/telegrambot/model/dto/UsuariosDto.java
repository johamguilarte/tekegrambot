package com.move.telegrambot.model.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuariosDto {

public String user;
public String password;
public String status;
public String rol;
public Date ult_session;
}
