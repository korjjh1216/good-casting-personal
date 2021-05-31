package shop.goodcasting.api.article.hire.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.goodcasting.api.common.domain.PageRequestDTO;

public interface SearchHireRepository {
    Page<Object[]> searchPage(PageRequestDTO pageRequest, Pageable pageable);
}