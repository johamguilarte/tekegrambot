package com.move.telegrambot.model.bd;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "usuarios_sunacrip")
public class SunacripUser {

    @Id
    protected String email;
    @Column(name = "cedula")
    protected String cedula;
    @Column(name = "status")
    protected String status;
    @Column(name = "nombre")
    protected String nombre;
    @Column(name = "telefono")
    protected String telefono;
    @Column(name = "ult_session")
    protected Date ult_session;

}
