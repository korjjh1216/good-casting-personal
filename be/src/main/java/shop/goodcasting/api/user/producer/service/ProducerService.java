package shop.goodcasting.api.user.producer.service;

import shop.goodcasting.api.user.producer.domain.Producer;

import java.util.List;
import java.util.Optional;

public interface ProducerService {
    List<Producer> findAll();
    Optional<Producer> findById(Long producerId);
    Long delete(Producer producer);
}