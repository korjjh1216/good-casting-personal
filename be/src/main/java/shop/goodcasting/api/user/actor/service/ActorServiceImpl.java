package shop.goodcasting.api.user.actor.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import shop.goodcasting.api.common.service.AbstractService;
import shop.goodcasting.api.user.actor.domain.Actor;
import shop.goodcasting.api.user.actor.domain.ActorDTO;
import shop.goodcasting.api.user.actor.repository.ActorRepository;

import java.util.List;
import java.util.Optional;

@Log
@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {
    private final ActorRepository repo;
    private final ModelMapper modelMapper;

    @Override
    public List<Actor> findAll() {
        return null;
    }

    @Override
    public Optional<Actor> findById(Long actorId) {
        return Optional.empty();
    }

    @Override
    public Long delete(Actor actor) {
        return null;
    }
}


