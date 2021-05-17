package shop.goodcasting.api.user.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.goodcasting.api.user.login.domain.UserVO;

@Repository
public interface UserRepository extends JpaRepository<UserVO, Long> {
}
