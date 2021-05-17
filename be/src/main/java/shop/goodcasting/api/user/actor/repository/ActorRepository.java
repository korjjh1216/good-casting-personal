package shop.goodcasting.api.user.actor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.goodcasting.api.user.actor.domain.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
}