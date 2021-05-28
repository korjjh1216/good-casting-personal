package shop.goodcasting.api.user.actor.service;

import lombok.Builder;
import lombok.extern.java.Log;
import shop.goodcasting.api.user.actor.domain.Actor;
import shop.goodcasting.api.user.actor.domain.ActorDTO;
import shop.goodcasting.api.user.login.domain.UserDTO;
import shop.goodcasting.api.user.login.domain.UserVO;

import java.util.List;
import java.util.Optional;

public interface ActorService {
    List<Actor> findAll();
    ActorDTO findById(Long actorId);
    Long delete(ActorDTO actorDTO);

    ActorDTO moreDetail(ActorDTO actorDTO);

    default Actor dto2Entity(ActorDTO actorDTO){
        return Actor.builder()
                .actorId(actorDTO.getActorId())
                .height(actorDTO.getHeight())
                .phone(actorDTO.getPhone())
                .weight(actorDTO.getWeight())
                .birthday(actorDTO.getBirthday())
                .major(actorDTO.isMajor())
                .agency(actorDTO.getAgency())
                .gender(actorDTO.getGender())
                .name(actorDTO.getName())
                .build();
    }

    default Actor dto2EntityAll(ActorDTO actorDTO){
        return Actor.builder()
                .actorId(actorDTO.getActorId())
                .height(actorDTO.getHeight())
                .phone(actorDTO.getPhone())
                .weight(actorDTO.getWeight())
                .birthday(actorDTO.getBirthday())
                .major(actorDTO.isMajor())
                .agency(actorDTO.getAgency())
                .gender(actorDTO.getGender())
                .name(actorDTO.getName())
                .user(UserVO.builder()
                        .userId(actorDTO.getUser().getUserId())
                        .build())
                .build();
    }

    default ActorDTO entity2Dto(Actor actor){
        return ActorDTO.builder()
                .actorId(actor.getActorId())
                .name(actor.getName())
                .height(actor.getHeight())
                .phone(actor.getPhone())
                .weight(actor.getWeight())
                .birthday(actor.getBirthday())
                .major(actor.getMajor())
                .gender(actor.getGender())
                .agency(actor.getAgency())
                .build();
    }

    default ActorDTO entity2DtoAll(Actor actor){
        return ActorDTO.builder()
                .actorId(actor.getActorId())
                .name(actor.getName())
                .height(actor.getHeight())
                .phone(actor.getPhone())
                .weight(actor.getWeight())
                .birthday(actor.getBirthday())
                .major(actor.getMajor())
                .gender(actor.getGender())
                .agency(actor.getAgency())
                .user(UserDTO.builder()
                        .userId(actor.getUser().getUserId())
                        .build())
                .build();
    }
}