package shop.goodcasting.api.career.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import shop.goodcasting.api.career.domain.Career;

@Repository
public interface CareerRepository extends JpaRepository<Career, Long> {

    @Query("select c.careerId from Career c where c.profile.profileId = :profileId")
    Long getCareerIdByProfileId (Long profileId);
}
