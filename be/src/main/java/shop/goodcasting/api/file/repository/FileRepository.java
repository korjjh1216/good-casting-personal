package shop.goodcasting.api.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.goodcasting.api.file.domain.FileVO;

@Repository
public interface FileRepository extends JpaRepository<FileVO, Long> {
}
