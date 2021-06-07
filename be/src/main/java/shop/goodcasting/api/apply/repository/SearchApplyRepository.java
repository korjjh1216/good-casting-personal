package shop.goodcasting.api.apply.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.goodcasting.api.apply.domain.ApplyPageRequestDTO;

public interface SearchApplyRepository {
    Page<Object[]> applicantList(ApplyPageRequestDTO pageRequest, Pageable pageable);
    Page<Object[]> applyList(ApplyPageRequestDTO pageRequest, Pageable pageable);
}