package shop.goodcasting.api.common.crawl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.goodcasting.api.article.hire.domain.Hire;

@Repository
public interface HireRepo extends JpaRepository<Hire,Long> {
}
