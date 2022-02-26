package com.move.telegrambot.bussines;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.move.telegrambot.model.bd.UserId;
import com.move.telegrambot.model.dto.DetalleComandoRolDto;
import com.move.telegrambot.model.dto.UserIdDto;
import com.move.telegrambot.model.mapper.GeneralMapper;
import com.move.telegrambot.repository.detalleRol.DetalleRolRepo;
import com.move.telegrambot.repository.userId.UserIdRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTelegramBusiness {
    
    @Autowired
    GeneralMapper mapper;

    @Autowired
    UserIdRepo userIdRepo;

    @Autowired
    DetalleRolRepo detalleRolRepo;

    private static final Logger logger = LoggerFactory.getLogger(UserTelegramBusiness.class);

    @Transactional
    public List<UserIdDto> listll(){
        return this.userIdRepo.findAll().stream()
	        .map(this.mapper::userIdToDto).collect(Collectors.toList());
    }

    @Transactional
    public UserIdDto findByUser(String comandos){
        Optional<UserId> rol = this.userIdRepo.findById(comandos);
        return this.mapper.userIdToDto(rol.orElse(null));
    }
    
    @Transactional
    public UserIdDto guardar(String id, String nombre){
        UserIdDto dto = new UserIdDto();
        Date date = new Date();
        dto.setIdTelegram(id);
        dto.setNombre(nombre);
        dto.setRol("1200");
        dto.setStatus("ACTIVO");
        dto.setUlt_session(date);
        UserId rol = this.userIdRepo.save(this.mapper.userIdToEntity(dto));
        return this.mapper.userIdToDto(rol);
    }

    @Transactional
    public UserIdDto cambiarRol(String id, String rol){
        UserIdDto findByUser = findByUser(id);
        findByUser.setRol(rol);
        UserId entity = this.userIdRepo.save(this.mapper.userIdToEntity(findByUser));
        return this.mapper.userIdToDto(entity);
    }

    @Transactional
    public List<DetalleComandoRolDto> allComandosDescrip(){
        return this.detalleRolRepo.findAll().stream()
	        .map(this.mapper::detalleComandoToDto).collect(Collectors.toList());
    }

    

    
}
