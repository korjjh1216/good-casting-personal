package shop.goodcasting.api.article.profile.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.goodcasting.api.article.profile.repository.ProfileRepository;
import shop.goodcasting.api.file.photo.domain.Photo;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository repo;
//    private final Photo photo;

//    public void a() {
//        photo.getProfile().getContents();
//    }
}
