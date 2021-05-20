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
import shop.goodcasting.api.user.actor.domain.Actor;
import shop.goodcasting.api.user.actor.repository.ActorRepository;
import shop.goodcasting.api.user.login.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Arrays;
=======
>>>>>>> 8cb7bdc5a69091f7a7160ea72b2de35bb58f0284
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepo;
    private final FileRepository fileRepo;
    private final ActorRepository actorRepo;
    private final UserRepository userRepo;

    @Transactional
    @Override
    public Long register(ProfileDTO profileDTO) {
//        Profile profile = dto2Entity(profileDTO);
//        System.out.println("service - register - profile: " + profile);
//        userRepo.save(profile.getActor().getUserVO());
//
//        actorRepo.save(profile.getActor());
//
//        Profile finalProfile = profileRepo.save(profile);
//
//        ArrayList<FileDTO> files = profileDTO.getFiles();
//
//        if(files != null && files.size() > 0) {
//
//            files.forEach(fileDTO -> {
//                fileDTO.setProfile(finalProfile);
//                FileVO file = dto2EntityFile(fileDTO);
//
//                fileRepo.save(file);
//            });
//        }

        return null;
    }

    @Transactional
    @Override
    public Profile getProfileWithFileByProfileId(Long profileId) {
//        ProfileDTO profileDTO = null;
//
//        List<Object[]> profileAndFile = profileRepo.getProfileWithFileByProfileId(profileId);
//
//        List<FileDTO> fileDTOList = new ArrayList<>();

//        for (Object[] arr : profileAndFile) {
//            Profile profile = (Profile) arr[0];
//            FileVO file = (FileVO) arr[1];
//            fileDTOList.add(file);
//
//            ProfileDTO.builder()
//                    .profileId(profile.getProfileId())
//                    .career(profile.getCareer())
//                    .contents(profile.getContents())
//                    .files(file)
//
//
//
//            log.info("profile: " + profile);
//            log.info("file: " + file);
//
//        }

        Object res = profileRepo.getProfileWithActorByProfileId(profileId);

        Object[] arr = (Object[]) res;

        log.info("profile and actor: " + Arrays.toString(arr));

        Profile profile = (Profile) arr[0];

//        for(Object el : arr) {
//
//            Profile profile = (Profile) arr[0];
//            Actor actor = (Actor) arr[1];
//
//            log.info("userId: " + actor.getUserVO().getUsername());
//
//            log.info("profile: " + profile);
//            log.info("actor: " + actor);
//        }
        return profile;
    }

    @Override
    public ProfileDTO profileDetail(Long profileId) {
        return null;
    }


    @Override
    public List<ProfileDTO> profileList() {
        List<Profile> profileList = profileRepo.findAll();

        return null;
    }

    @Override
    public ProfileDTO profileDetail(Long profileId) {
        profileRepo.findById(profileId);

        return null;
    }

    @Override
    public List<ProfileDTO> profileList() {
        List<Profile> profileList = profileRepo.findAll();

        return null ;
    }
}
