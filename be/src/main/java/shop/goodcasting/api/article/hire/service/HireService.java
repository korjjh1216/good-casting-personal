package shop.goodcasting.api.article.hire.service;

import shop.goodcasting.api.article.hire.domain.Hire;

import java.util.List;
import java.util.Optional;

public interface HireService {
    Long save(Hire hire);

    List<Hire> findAll();

    Optional<Hire> findById(Long id);

    Long delete(Hire id);
}
