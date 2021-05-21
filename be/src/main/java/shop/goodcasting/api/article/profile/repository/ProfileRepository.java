
package shop.goodcasting.api.article.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shop.goodcasting.api.article.profile.domain.Profile;
import shop.goodcasting.api.article.profile.domain.ProfileDTO;
import shop.goodcasting.api.file.domain.FileVO;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query("select p, p.actor, f from Profile p left join FileVO f on f.profile = p where p.profileId = :profileId")
    List<Object[]> getProfileAndFileAndActorByProfileId(@Param("profileId") Long profileId);

    @Query("select p, a from Profile p left join p.actor a where p.profileId = :profileId")
    Object getProfileWithActorByProfileId(@Param("profileId") Long profileId);

    @Query("select p, p.actor, f from Profile p left join FileVO f on f.profile = p where f.first = :first")
    List<Object[]> getProfileAndFileAndActorByFirst(@Param("first") boolean first);
}