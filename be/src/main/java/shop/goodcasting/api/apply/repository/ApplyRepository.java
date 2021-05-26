package shop.goodcasting.api.apply.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import shop.goodcasting.api.apply.domain.Apply;
import shop.goodcasting.api.message.domain.Message;

import java.util.List;

@Repository
public interface ApplyRepository extends JpaRepository<Apply, Long> {

    @Query("select a from Apply a where a.hire.hireId = :hireId")
    List<Apply> findAllByHireId(Long hireId);
}
