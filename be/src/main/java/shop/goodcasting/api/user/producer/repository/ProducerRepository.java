package shop.goodcasting.api.user.producer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shop.goodcasting.api.user.producer.domain.Producer;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {
    @Query("select h.hireId from Producer p left join Hire h on p.producerId = h.producer.producerId where p.producerId = :producer_id")
    Long getHireId (@Param("producer_id") Long producerId);
}