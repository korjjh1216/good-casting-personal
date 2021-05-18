package shop.goodcasting.api.article.profile.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.goodcasting.api.article.profile.domain.Profile;
import shop.goodcasting.api.article.profile.domain.ProfileDTO;
import shop.goodcasting.api.article.profile.repository.ProfileRepository;
import shop.goodcasting.api.file.photo.domain.Photo;
import shop.goodcasting.api.file.photo.domain.PhotoDTO;
import shop.goodcasting.api.file.photo.repository.PhotoRepository;
import shop.goodcasting.api.user.actor.domain.Actor;
import shop.goodcasting.api.user.actor.repository.ActorRepository;
import shop.goodcasting.api.user.login.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepo;
    private final PhotoRepository photoRepo;
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

        ArrayList<PhotoDTO> photos = profileDTO.getPhotos();

        if(photos != null && photos.size() > 0) {

            photos.forEach(photoDTO -> {
                photoDTO.setProfile(finalProfile);
                Photo photo = dto2EntityPhoto(photoDTO);

                photoRepo.save(photo);
            });
        }

        return null;
    }
}
