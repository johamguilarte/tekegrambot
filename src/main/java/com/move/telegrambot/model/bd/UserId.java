package com.move.telegrambot.model.bd;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="user_id")
public class UserId {


@Id
protected String idTelegram;
@Column(name ="name_lastname")
protected String nombre;
@Column(name ="status")
protected String status;
@Column(name ="rol")
protected String rol;
@Column(name ="ult_session")
protected Date ult_session;
}
