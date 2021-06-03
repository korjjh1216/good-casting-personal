package shop.goodcasting.api.article.profile.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.goodcasting.api.article.profile.domain.ProfilePageRequestDTO;

public interface SearchProfileRepository {
    Page<Object[]> searchPage(ProfilePageRequestDTO pageRequest, Pageable pageable);
    Page<Object[]> myProfilePage(ProfilePageRequestDTO pageRequest, Pageable pageable);
}