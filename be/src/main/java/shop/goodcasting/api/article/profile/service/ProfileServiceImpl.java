package shop.goodcasting.api.article.profile.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.goodcasting.api.article.profile.domain.Profile;
import shop.goodcasting.api.article.profile.domain.ProfileDTO;
import shop.goodcasting.api.article.profile.repository.ProfileRepository;
import shop.goodcasting.api.file.domain.FileVO;
import shop.goodcasting.api.file.domain.FileDTO;
import shop.goodcasting.api.file.repository.FileRepository;
import shop.goodcasting.api.user.actor.repository.ActorRepository;
import shop.goodcasting.api.user.login.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;

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
        Profile profile = dto2Entity(profileDTO);
        System.out.println("service - register - profile: " + profile);
        userRepo.save(profile.getActor().getUserVO());

        actorRepo.save(profile.getActor());

        Profile finalProfile = profileRepo.save(profile);

        ArrayList<FileDTO> files = profileDTO.getFiles();

        if(files != null && files.size() > 0) {

            files.forEach(fileDTO -> {
                fileDTO.setProfile(finalProfile);
                FileVO photo = dto2EntityFile(fileDTO);

                fileRepo.save(photo);
            });
        }

        return null;
    }
}
