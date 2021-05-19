package shop.goodcasting.api.article.profile.service;

import shop.goodcasting.api.article.profile.domain.Profile;
import shop.goodcasting.api.article.profile.domain.ProfileDTO;
import shop.goodcasting.api.file.domain.FileVO;
import shop.goodcasting.api.file.domain.FileDTO;
import shop.goodcasting.api.user.actor.domain.Actor;
import shop.goodcasting.api.user.actor.domain.ActorDTO;

import java.util.List;

public interface ProfileService {
    Long register(ProfileDTO profileDTO);

    default Profile dto2Entity(ProfileDTO profileDTO) {

        Profile entity = Profile.builder()
                .profileId(profileDTO.getProfileId())
                .career(profileDTO.getCareer())
                .contents(profileDTO.getContents())
                .privacy(profileDTO.isPrivacy())
                .actor(profileDTO.getActor())
                .build();
        return entity;
    }

    default Actor dto2EntityActor(ActorDTO actorDTO) {
        Actor actor = Actor.builder()
                .actorId(actorDTO.getActorId())
                .height(actorDTO.getHeight())
                .phone(actorDTO.getPhone())
                .weight(actorDTO.getWeight())
                .birthday(actorDTO.getBirthday())
                .major(actorDTO.getMajor())
                .agency(actorDTO.getAgency())
                .build();
        return actor;
    }

    default FileVO dto2EntityFile(FileDTO fileDTO) {
        FileVO file = FileVO.builder()
                .fileId(fileDTO.getFileId())
                .fileName(fileDTO.getFileName())
                .uuid(fileDTO.getUuid())
                .first(fileDTO.isFirst())
                .profile(fileDTO.getProfile())
                .build();

        return file;
    }

    ProfileDTO profileDetail(Long profileId);
    List<ProfileDTO> profileList();
}
