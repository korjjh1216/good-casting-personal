package shop.goodcasting.api.common.crawl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.goodcasting.api.user.actor.domain.Actor;

@Repository
public interface CrawlRepository extends JpaRepository<Actor,Long> {
}