package com.move.telegrambot.repository.sunacrip;

import com.move.telegrambot.model.bd.BIlleterasUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BIlleterasUserRepo extends JpaRepository<BIlleterasUser, String> {

}
