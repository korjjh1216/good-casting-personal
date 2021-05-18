package shop.goodcasting.api.user.producer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import shop.goodcasting.api.common.service.AbstractService;
import shop.goodcasting.api.user.producer.domain.Producer;
import shop.goodcasting.api.user.producer.repository.ProducerRepository;

import java.util.List;
import java.util.Optional;

@Log
@Service
@RequiredArgsConstructor
public class ProducerServiceImpl  implements ProducerService {
    private final ProducerRepository repo;
    private final ModelMapper modelMapper;

    @Override
    public List<Producer> findAll() {
        return null;
    }

    @Override
    public Optional<Producer> findById(Long actorId) {
        return Optional.empty();
    }

    @Override
    public Long delete(Producer producer) {
        return null;
    }
}
