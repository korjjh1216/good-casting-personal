package shop.goodcasting.api.file.video.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.goodcasting.api.file.video.domain.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
}
