package com.move.telegrambot.repository.userId;

import com.move.telegrambot.model.bd.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserIdRepo extends JpaRepository<UserId, String> {
    
// @Modifying(clearAutomatically = true)
// @Query("update Usuarios uu set uu.session =:session' where uu.user =:userId") 
// Usuarios updateSesion(@Param("userId") String userId, @Param("session") boolean session);
// @Modifying
// @Query(value="update from author a where a.last_name= :lastName", nativeQuery = true)
// void deleteAuthorByLastName(@Param("lastName") String lastName);


// @Query(nativeQuery = true, "update usuarios set session=:session where user:=userId")
// void updateSesion(@Param("userId") String userId, @Param("session") boolean session);


}
