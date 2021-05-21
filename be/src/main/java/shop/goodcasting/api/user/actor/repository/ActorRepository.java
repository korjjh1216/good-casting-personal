package shop.goodcasting.api.user.actor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.goodcasting.api.user.actor.domain.Actor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.goodcasting.api.user.login.domain.UserDTO;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

    @Modifying
    @Query("update UserVO u set u.account = :account where u.id = :user_id")
    void accountupdate(@Param("user_id") Long userId, @Param("account") Boolean account);

    @Modifying
    @Query("update UserVO u set u.password = :password where u.id = :user_id")
    void passwordUpdate(@Param("user_id") Long userId, @Param("password") String password);

    @Query("select u from UserVO u where u.username = :username and u.password = :password")
    UserDTO signin(@Param("username") String username, @Param("password") String password);
}