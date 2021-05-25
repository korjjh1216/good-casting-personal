package shop.goodcasting.api.article.profile.service;

import shop.goodcasting.api.article.profile.domain.Profile;
import shop.goodcasting.api.article.profile.domain.ProfileDTO;
import shop.goodcasting.api.common.domain.PageRequestDTO;
import shop.goodcasting.api.common.domain.PageResultDTO;
import shop.goodcasting.api.file.domain.FileVO;
import shop.goodcasting.api.user.actor.domain.Actor;
import shop.goodcasting.api.user.actor.domain.ActorDTO;

import java.util.List;

public interface ProfileService {
    Long register(ProfileDTO profileDTO);
    ProfileDTO readProfile(Long profileId);
    PageResultDTO<ProfileDTO, Object[]> getProfileList(PageRequestDTO requestDTO);
    List<ProfileDTO> getImageMatchList(String resemble);

    default Profile dto2Entity(ProfileDTO profileDTO) {
        return Profile.builder()
                .profileId(profileDTO.getProfileId())
                .career(profileDTO.getCareer())
                .contents(profileDTO.getContents())
                .privacy(profileDTO.isPrivacy())
                .resemble(profileDTO.getResemble())
                .confidence(profileDTO.getConfidence())
                .build();
    }

    default Profile dto2EntityAll(ProfileDTO profileDTO){
        return Profile.builder()
                .profileId(profileDTO.getProfileId())
                .career(profileDTO.getCareer())
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
                .career(profile.getCareer())
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
    default ProfileDTO entity2DtoFiles(Profile profile,Actor actor, FileVO file) {
        return ProfileDTO.builder()
                .profileId(profile.getProfileId())
                .career(profile.getCareer())
                .contents(profile.getContents())
                .privacy(profile.isPrivacy())
                .resemble(profile.getResemble())
                .confidence(profile.getConfidence())
                .regDate(profile.getRegDate())
                .modDate(profile.getModDate())
                .fileName(file.getFileName())
                .fileUuid(file.getUuid())
                .actorName(actor.getName())
                .build();
    }
}