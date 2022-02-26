package com.move.telegrambot.model.bd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="descripcion_bot")
public class DetalleComandoRol {

    @Id
    protected String comando;
    
    @Column(name ="rol")
    protected String rol;

    @Column(name ="descripcion_bot")
    protected String descripcionBot;
    
}
