package shop.goodcasting.api.apply.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.goodcasting.api.common.domain.PageRequestDTO;

public interface SearchApplyRepository {
    Page<Object[]> applicantList(PageRequestDTO pageRequest, Pageable pageable);
}