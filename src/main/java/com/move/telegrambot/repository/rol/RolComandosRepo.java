package com.move.telegrambot.repository.rol;

import com.move.telegrambot.model.bd.RolComandos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolComandosRepo extends JpaRepository<RolComandos, String> {

}
