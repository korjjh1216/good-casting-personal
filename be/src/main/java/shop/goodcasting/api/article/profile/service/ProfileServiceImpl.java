package shop.goodcasting.api.article.profile.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import shop.goodcasting.api.article.profile.domain.Profile;
import shop.goodcasting.api.article.profile.domain.ProfileDTO;
import shop.goodcasting.api.article.profile.repository.ProfileRepository;
import shop.goodcasting.api.file.domain.FileVO;
import shop.goodcasting.api.file.domain.FileDTO;
import shop.goodcasting.api.file.repository.FileRepository;
import shop.goodcasting.api.file.service.FileService;
import shop.goodcasting.api.user.actor.domain.Actor;
import shop.goodcasting.api.user.actor.domain.ActorDTO;
import shop.goodcasting.api.user.actor.repository.ActorRepository;
import shop.goodcasting.api.user.actor.service.ActorService;
import shop.goodcasting.api.user.login.domain.UserDTO;
import shop.goodcasting.api.user.login.domain.UserVO;
import shop.goodcasting.api.user.login.repository.UserRepository;
import shop.goodcasting.api.user.login.service.UserService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepo;
    private final FileRepository fileRepo;
    private final FileService fileService;
    private final ActorService actorService;
    private final UserRepository userRepo;
    private final ActorRepository actorRepo;
    private final UserService userService;

    @Transactional
    @Override
    public Long register(ProfileDTO profileDTO) {
        ActorDTO actorDTO = profileDTO.getActor();
        UserDTO userDTO = actorDTO.getUser();
        UserVO user = userService.dto2Entity(userDTO);

        System.out.println("111111111111111111111" + user);

        Actor actor = actorService.dto2EntityAll(actorDTO);

        System.out.println("222222222222222222222" + actor);

        // test
        userRepo.save(user);

        // test
        actorRepo.save(actor);

        Profile finalProfile = profileRepo.save(dto2EntityAll(profileDTO));

        System.out.println("final Profile ********************** " + finalProfile);

        ProfileDTO finalProfileDto = entity2DtoAll(finalProfile);

        System.out.println("----------------------after entity to dto-----------------------");

        List<FileDTO> files = profileDTO.getFiles();

        for (FileDTO file : files) {
            System.out.println("file: " + file);
        }

        if(files != null && files.size() > 0) {

            files.forEach(fileDTO -> {
                fileDTO.setProfile(finalProfileDto);
                System.out.println("----------------------after set final dto-----------------------: " + fileDTO);
                FileVO file = fileService.dto2EntityAll(fileDTO);
                System.out.println("----------------------after dto to entity-----------------------: " + file);

                fileRepo.save(file);
            });
        }
        return null;
    }

    @Transactional
    @Override
    public ProfileDTO readProfile(Long profileId) {
        System.out.println("getProfileWithFileByProfileId() entry");

        List<Object[]> profileAndFileAndActor = profileRepo.getProfileAndFileAndActorByProfileId(2L);

        Profile profile = (Profile) profileAndFileAndActor.get(0)[0];
        Actor actor = profile.getActor();
        System.out.println("actor: " + actor);

        ProfileDTO profileDTO = entity2Dto(profile);
        System.out.println("profileDTO: " + profileDTO);

        ActorDTO actorDTO = actorService.entity2Dto(actor);
        System.out.println("actorDTO: " + actorDTO);

        List<FileDTO> fileList = new ArrayList<>();

        profileAndFileAndActor.forEach(objects -> {
            fileList.add(fileService.entity2Dto((FileVO)objects[2]));
        });

        profileDTO.setActor(actorDTO);
        profileDTO.setFiles(fileList);

        System.out.println("profile dto: " + profileDTO);

        return profileDTO;
    }

}