package shop.goodcasting.api.career.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.goodcasting.api.career.domain.Career;

@Repository
public interface CareerRepository extends JpaRepository<Career, Long> {
}
