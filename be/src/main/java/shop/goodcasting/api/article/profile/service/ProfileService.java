package shop.goodcasting.api.article.profile.service;

import shop.goodcasting.api.article.profile.domain.Profile;
import shop.goodcasting.api.article.profile.domain.ProfileDTO;
import shop.goodcasting.api.user.actor.domain.Actor;
import shop.goodcasting.api.user.actor.domain.ActorDTO;

public interface ProfileService {
    Long register(ProfileDTO profileDTO);
    ProfileDTO readProfile(Long profileId);

    default Profile dto2Entity(ProfileDTO profile) {
        return Profile.builder()
                .profileId(profile.getProfileId())
                .career(profile.getCareer())
                .contents(profile.getContents())
                .privacy(profile.isPrivacy())
                .resemble(profile.getResemble())
                .confidence(profile.getConfidence())
                .build();
    }

    default Profile dto2EntityAll(ProfileDTO profile){
        return Profile.builder()
                .profileId(profile.getProfileId())
                .career(profile.getCareer())
                .contents(profile.getContents())
                .privacy(profile.isPrivacy())
                .resemble(profile.getResemble())
                .confidence(profile.getConfidence())
                .actor(Actor.builder()
                        .actorId(profile.getActor().getActorId())
                        .build())
                .build();
    }

    default ProfileDTO entity2Dto(Profile entity) {
        return ProfileDTO.builder()
                .profileId(entity.getProfileId())
                .career(entity.getCareer())
                .contents(entity.getContents())
                .privacy(entity.isPrivacy())
                .resemble(entity.getResemble())
                .confidence(entity.getConfidence())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
    }

    default ProfileDTO entity2DtoAll(Profile profile) {
        return ProfileDTO.builder()
                .profileId(profile.getProfileId())
                .career(profile.getCareer())
                .contents(profile.getContents())
                .privacy(profile.isPrivacy())
                .resemble(profile.getResemble())
                .confidence(profile.getConfidence())
                .regDate(profile.getRegDate())
                .modDate(profile.getModDate())
                .actor(ActorDTO.builder()
                        .actorId(profile.getActor().getActorId())
                        .build())
                .build();
    }
}