package shop.goodcasting.api.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shop.goodcasting.api.file.domain.FileVO;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileVO, Long> {
    @Query("select f from FileVO f inner join f.profile p where p.profileId = :profileId")
    List<FileVO> findFileListByProfileId(@Param("profileId") Long profileId);

    @Query("select f from FileVO f where f.first = :first")
    List<FileVO> findFileListByFirst(@Param("first") Boolean first);
}
