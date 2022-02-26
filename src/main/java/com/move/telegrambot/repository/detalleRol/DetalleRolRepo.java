package com.move.telegrambot.repository.detalleRol;

import com.move.telegrambot.model.bd.DetalleComandoRol;
import com.move.telegrambot.model.bd.RolComandos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleRolRepo extends JpaRepository<DetalleComandoRol, String> {

}
