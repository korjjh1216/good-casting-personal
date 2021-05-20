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
    Profile getProfileWithFileByProfileId(Long profileId);

    default Profile dto2Entity(ProfileDTO profileDTO) {

        Profile entity = Profile.builder()
                .profileId(profileDTO.getProfileId())
                .career(profileDTO.getCareer())
                .contents(profileDTO.getContents())
                .privacy(profileDTO.isPrivacy())
                .actor(profileDTO.getActor())
                .resemble(profileDTO.getResemble())
                .confidence(profileDTO.getConfidence())
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


<<<<<<< HEAD

    ProfileDTO profileDetail(Long profileId);

=======
        return file;
    }

    ProfileDTO profileDetail(Long profileId);
>>>>>>> 8cb7bdc5a69091f7a7160ea72b2de35bb58f0284
    List<ProfileDTO> profileList();
}
