package shop.goodcasting.api.article.profile.service;

import shop.goodcasting.api.article.profile.domain.Profile;
import shop.goodcasting.api.article.profile.domain.ProfileDTO;
import shop.goodcasting.api.article.profile.domain.ProfileListDTO;
import shop.goodcasting.api.common.domain.PageRequestDTO;
import shop.goodcasting.api.common.domain.PageResultDTO;
import shop.goodcasting.api.file.domain.FileVO;
import shop.goodcasting.api.user.actor.domain.Actor;
import shop.goodcasting.api.user.actor.domain.ActorDTO;


public interface ProfileService {
    Long register(ProfileDTO profileDTO);
    ProfileDTO readProfile(Long profileId);

    PageResultDTO<ProfileListDTO, Object[]> getProfileList(PageRequestDTO requestDTO);

    default Profile dto2Entity(ProfileDTO profileDTO) {
        return Profile.builder()
                .profileId(profileDTO.getProfileId())
                .contents(profileDTO.getContents())
                .privacy(profileDTO.isPrivacy())
                .resemble(profileDTO.getResemble())
                .confidence(profileDTO.getConfidence())
                .build();
    }

    default Profile dto2EntityAll(ProfileDTO profileDTO){
        return Profile.builder()
                .profileId(profileDTO.getProfileId())
                .contents(profileDTO.getContents())
                .privacy(profileDTO.isPrivacy())
                .resemble(profileDTO.getResemble())
                .confidence(profileDTO.getConfidence())
                .actor(Actor.builder()
                        .actorId(profileDTO.getActor().getActorId())
                        .build())
                .build();
    }

    default ProfileDTO entity2Dto(Profile profile) {
        return ProfileDTO.builder()
                .profileId(profile.getProfileId())
                .contents(profile.getContents())
                .privacy(profile.isPrivacy())
                .resemble(profile.getResemble())
                .confidence(profile.getConfidence())
                .regDate(profile.getRegDate())
                .modDate(profile.getModDate())
                .build();
    }

    default ProfileDTO entity2DtoAll(Profile profile) {
        return ProfileDTO.builder()
                .profileId(profile.getProfileId())
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

    default ProfileListDTO entity2DtoFiles(Profile profile, Actor actor, FileVO file) {
        return ProfileListDTO.builder()
                .profileId(profile.getProfileId())
                .privacy(profile.isPrivacy())
                .resemble(profile.getResemble())
                .confidence(profile.getConfidence())
                .modDate(profile.getModDate())
                .regDate(profile.getRegDate())
                .actorName(actor.getName())
                .fileName(file.getFileName())
                .fileUuid(file.getUuid())
                .build();
    }
}
