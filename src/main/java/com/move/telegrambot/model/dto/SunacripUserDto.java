package com.move.telegrambot.model.dto;

import java.util.Date;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SunacripUserDto {

    
    private String email;
    
    private String cedula;
    
    private String status;
    
    private String nombre;
    
    private String telefono;
    
    private Date ult_session;

}
