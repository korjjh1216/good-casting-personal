package shop.goodcasting.api.article.hire.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shop.goodcasting.api.article.hire.domain.Hire;

import java.util.List;

@Repository
public interface HireRepository extends JpaRepository<Hire, Long>, SearchHireRepository {
    @Query("select h, h.producer, f from Hire h left join FileVO f on f.hire = h where  h.hireId= :hireId")
    List<Object[]> getHireAndFileAndProducerByHireId(@Param("hireId") Long hireId);

    @Query("select h, p from Hire h left join h.producer p where h.hireId = :hireId")
    Object getHireWithProducerByHireId(@Param("hireId") Long hireId);

    @Query(value = "select h, h.producer, f from Hire h left join FileVO f on f.hire = h where f.first = true group by h")
    Page<Object[]> getHireAndFileAndProducerByFirst(Pageable pageable);

    @Query("select h, f from Hire h left join FileVO f on f.hire = h where h.hireId = :hireId")
    List<Object[]> getProfileAndFileByProfileId(@Param("hireId") Long hireId);

}