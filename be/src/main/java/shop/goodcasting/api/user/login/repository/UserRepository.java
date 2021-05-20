package shop.goodcasting.api.user.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.goodcasting.api.user.login.domain.UserDTO;
import shop.goodcasting.api.user.login.domain.UserVO;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserVO, Long> {
    boolean existsByUsername(String username);
    Optional<UserVO> findByUsername(String username);

    @Query("select u from UserVO u where u.username = :username and u.password = :password ")
    UserDTO signin(@Param("username") String username, @Param("password") String password);
}