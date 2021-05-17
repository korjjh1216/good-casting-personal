package shop.goodcasting.api.article.hire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.goodcasting.api.article.hire.domain.Hire;

@Repository
public interface HireRepository extends JpaRepository<Hire, Long> {
}
