package com.move.telegrambot.model.bd;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "billeteras_creadas")
public class BIlleterasUser {

    @Id
    protected String billetera;
    @Column(name = "moneda")
    protected String moneda;
    @Column(name = "cedula")
    protected String cedula;

}
