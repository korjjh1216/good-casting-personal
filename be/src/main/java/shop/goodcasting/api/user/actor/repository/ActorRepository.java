package shop.goodcasting.api.user.actor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.goodcasting.api.user.actor.domain.Actor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.goodcasting.api.user.login.domain.UserDTO;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

    @Modifying
    @Query("update UserVO u set u.account = :account where u.id = :user_id")
    void accountUpdate(@Param("user_id") Long userId, @Param("account") boolean account);

    @Modifying
    @Query("update UserVO u set u.password = :password where u.id = :user_id")
    void passwordUpdate(@Param("user_id") Long userId, @Param("password") String password);

    @Query("select u from UserVO u where u.username = :username and u.password = :password")
    UserDTO signin(@Param("username") String username, @Param("password") String password);


}
//    @Query("select a.actorId, p.profileId from Actor a left join Profile p on a.actorId = p.actor.actorId where a.actorId = :actor_id")
//    void getget (@Param("actor_id") Long actor_id);

//    @Query("select p, p.actor, f from Profile p left join FileVO f on f.profile = p where p.profileId = :profileId")
//    void getProfileId(@Param("profileId") Long profileId);
//
//    @Query("SELECT profiles.actor_id,files.file_id FROM profiles LEFT JOIN files ON profiles.profile_id = files.profile_id")
//    void getProfileIdByActorId(@Param("actorId") Long actorId);
//}