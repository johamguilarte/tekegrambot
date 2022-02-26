package com.move.telegrambot.bussines;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.move.telegrambot.model.bd.Usuarios;
import com.move.telegrambot.model.dto.UserIdDto;
import com.move.telegrambot.model.dto.UsuariosDto;
import com.move.telegrambot.model.mapper.GeneralMapper;
import com.move.telegrambot.repository.usuarios.UserRepo;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuariosBusiness {
    
    @Autowired
    GeneralMapper usuariosMapper;

    @Autowired
    UserRepo userRepo;

    // private static final Logger logger = LoggerFactory.getLogger(UsuariosBusinnes.class);

    @Transactional
    public UsuariosDto findByUser(String user){
        Optional<Usuarios> usuario = this.userRepo.findById(user);
        return this.usuariosMapper.userToDto(usuario.orElse(null));
    }

    @Transactional
    public List<UsuariosDto> listAllUser() throws JsonProcessingException{
        return this.userRepo.findAll().stream()
			.map(this.usuariosMapper::userToDto).collect(Collectors.toList());
    }

    @Transactional
    public UsuariosDto saveUser(String id){
        UsuariosDto dto = new UsuariosDto();
        Date date = new Date();
        dto.setUser(id);
        dto.setRol("1200");
        dto.setStatus("ACTIVO");
        dto.setUlt_session(date);
        dto.setPassword("123456");
        Usuarios usuario = this.userRepo.save(this.usuariosMapper.userToEntity(dto));
        return this.usuariosMapper.userToDto(usuario);
    }

    @Transactional
    public UsuariosDto deleteUser(String usuario){
        UsuariosDto usuariosDto = this.findByUser(usuario);
        Usuarios ddd = this.usuariosMapper.userToEntity(usuariosDto);
        this.userRepo.delete(ddd);
        return this.usuariosMapper.userToDto(ddd);
    }

    @Transactional
    public UsuariosDto cambiarRol(String id, String rol){
        UsuariosDto usuariosDto = this.findByUser(id);
        usuariosDto.setRol(rol);
        Usuarios usuario = this.userRepo.save(this.usuariosMapper.userToEntity(usuariosDto));
        return this.usuariosMapper.userToDto(usuario);
    }
}
