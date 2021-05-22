package shop.goodcasting.api.user.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shop.goodcasting.api.user.login.domain.UserDTO;
import shop.goodcasting.api.user.login.domain.UserVO;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserVO, Long> {
    boolean existsByUsername(String username);
    Optional<UserVO> findByUsername(String username);

    @Query("select u from UserVO u where u.username = :username and u.password = :password ")
    UserDTO signin(@Param("username") String username, @Param("password") String password);

    @Query("select u.account from UserVO u where u.username = :username")
    boolean checkAccount(@Param("username") String username);
}