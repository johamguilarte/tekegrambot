package com.move.telegrambot.model.bd;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="comandos_bot")
public class RolComandos {

    @Id
    protected String comando;
    
    @Column(name ="rol")
    protected String rol;
    
}
