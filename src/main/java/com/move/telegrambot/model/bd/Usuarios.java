package com.move.telegrambot.model.bd;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="usuarios")
public class Usuarios {

    @Id
    protected String user;
    
    @Column(name ="password")
    protected String password;
    
    @Column(name ="status")
    protected String status;
    
    @Column(name ="rol")
    protected String rol;
    
    @Column(name ="ult_session")
    protected Date ult_session;
}
