package com.move.telegrambot.bussines;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.move.telegrambot.model.bd.RolComandos;
import com.move.telegrambot.model.dto.RolComandosDto;
import com.move.telegrambot.model.mapper.GeneralMapper;
import com.move.telegrambot.repository.rol.RolComandosRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolComandosBusiness {
    
    @Autowired
    GeneralMapper mapper;

    @Autowired
    RolComandosRepo rolRepo;

    private static final Logger logger = LoggerFactory.getLogger(RolComandosBusiness.class);

    @Transactional
    public List<RolComandosDto> listll(){
        return this.rolRepo.findAll().stream()
	        .map(this.mapper::rolToDto).collect(Collectors.toList());
    }

    @Transactional
    public RolComandosDto findByUser(String comandos){
        Optional<RolComandos> rol = this.rolRepo.findById(comandos);
        return this.mapper.rolToDto(rol.orElse(null));
    }
    
}
