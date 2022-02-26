package com.move.telegrambot.model.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import lombok.Data;

@Data
public class UserIdDto {

private String idTelegram;
private String nombre;
private String status;
private String rol;
private Date ult_session;
}
