package com.move.telegrambot.repository.sunacrip;

import com.move.telegrambot.model.bd.SunacripUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SunacripUserRepo extends JpaRepository<SunacripUser, String> {

}
