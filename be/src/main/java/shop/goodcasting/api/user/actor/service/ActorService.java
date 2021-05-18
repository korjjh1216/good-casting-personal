package shop.goodcasting.api.user.actor.service;

import shop.goodcasting.api.user.actor.domain.Actor;
import shop.goodcasting.api.user.actor.domain.ActorDTO;

import java.util.List;
import java.util.Optional;

public interface ActorService {

    List<Actor> findAll();

    Optional<Actor> findById(Long actorId);

    Long delete(Actor actor);

    default Actor dto2Entity(ActorDTO actorDTO){
        Actor actor = Actor.builder()
                .actorId(actorDTO.getActorId())
                .height(actorDTO.getHeight())
                .phone(actorDTO.getPhone())
                .weight(actorDTO.getWeight())
                .birthday(actorDTO.getBirthday())
                .major(actorDTO.getMajor())
                .agency(actorDTO.getAgency())
                .gender(actorDTO.getGender())
                .name(actorDTO.getName())
                .build();

        return actor;
    }

    default ActorDTO entity2Dto(Actor actor){
        ActorDTO actorDTO = ActorDTO.builder()
                .actorId(actor.getActorId())
                .height(actor.getHeight())
                .phone(actor.getPhone())
                .weight(actor.getWeight())
                .birthday(actor.getBirthday())
                .major(actor.getMajor())
                .gender(actor.getGender())
                .agency(actor.getAgency())
                .build();

        return actorDTO;
    }
}