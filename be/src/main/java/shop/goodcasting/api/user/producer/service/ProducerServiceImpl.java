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
public class ProducerServiceImpl extends AbstractService<Producer> implements ProducerService {
    private final ProducerRepository repo;
    private final ModelMapper modelMapper;

    @Override
    public Long save(Producer producer) {
        return null;
    }

    @Override
    public Optional<Producer> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<Producer> findAll() {
        return repo.findAll();
    }

    @Override
    public Long count() {
        return repo.count();
    }

    @Override
    public Optional<Producer> getOne(Long id) {
        return Optional.ofNullable(repo.getOne(id));
    }

    @Override
    public Long delete(Producer producer) {
        repo.delete(producer);
        return repo.findById(producer.getProducerId()).orElse(null) == null ? 1L : 0L;
    }

    @Override
    public Boolean existById(Long id) {
        return repo.existsById(id);
    }
}
