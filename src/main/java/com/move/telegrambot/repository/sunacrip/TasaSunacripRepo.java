package com.move.telegrambot.repository.sunacrip;

import com.move.telegrambot.model.bd.TasaSunacrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasaSunacripRepo extends JpaRepository<TasaSunacrip, String> {

}
