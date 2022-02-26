package com.move.telegrambot.model.mapper;

import com.move.telegrambot.model.bd.BIlleterasUser;
import com.move.telegrambot.model.bd.DetalleComandoRol;
import com.move.telegrambot.model.bd.RolComandos;
import com.move.telegrambot.model.bd.SunacripUser;
import com.move.telegrambot.model.bd.TasaSunacrip;
import com.move.telegrambot.model.bd.UserId;
import com.move.telegrambot.model.bd.Usuarios;
import com.move.telegrambot.model.dto.BilleterasUserDto;
import com.move.telegrambot.model.dto.DetalleComandoRolDto;
import com.move.telegrambot.model.dto.RolComandosDto;
import com.move.telegrambot.model.dto.SunacripUserDto;
import com.move.telegrambot.model.dto.TasaSunacripDto;
import com.move.telegrambot.model.dto.UserIdDto;
import com.move.telegrambot.model.dto.UsuariosDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GeneralMapper {
    UsuariosDto userToDto(Usuarios equipo);
    Usuarios userToEntity(UsuariosDto equipoDto);
    RolComandosDto rolToDto(RolComandos rolComandos);
    RolComandos rolToEntity(RolComandosDto rolComandosDto);
    BilleterasUserDto billeteraToDto(BIlleterasUser bIlleterasUser);
    BIlleterasUser billeteraToEntity(BilleterasUserDto bIlleterasUser);
    SunacripUserDto sunacripUserToDto(SunacripUser sunacripUser);
    SunacripUser sunacripUserToEntity(SunacripUserDto sunacripUser);
    TasaSunacripDto tasaSunacripToDto(TasaSunacrip tasaSunacrip);
    TasaSunacrip tasaSunacripToEntity(TasaSunacripDto tasaSunacrip);
    UserIdDto userIdToDto(UserId userId);
    UserId userIdToEntity(UserIdDto userId);
    DetalleComandoRolDto detalleComandoToDto(DetalleComandoRol detalleComandoRol);
    DetalleComandoRol detalleComandoToEntity(DetalleComandoRolDto userId);

}
