package com.move.telegrambot.model.bd;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tasa_sunacrip")
public class TasaSunacrip {

@Id
protected String moneda;
@Column(name = "precio_euro")
protected String precio_euro;
@Column(name = "precio_dolar")
protected String precio_dolar;


}
