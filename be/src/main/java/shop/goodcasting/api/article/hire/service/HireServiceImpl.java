package shop.goodcasting.api.article.hire.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.goodcasting.api.article.hire.domain.Hire;
import shop.goodcasting.api.article.hire.domain.HireDTO;
import shop.goodcasting.api.article.hire.repository.HireRepository;
import shop.goodcasting.api.file.photo.domain.Photo;
import shop.goodcasting.api.file.photo.domain.PhotoDTO;
import shop.goodcasting.api.file.photo.repository.PhotoRepository;
import shop.goodcasting.api.file.video.domain.VideoDTO;
import shop.goodcasting.api.file.video.repository.VideoRepository;
import shop.goodcasting.api.user.login.repository.UserRepository;
import shop.goodcasting.api.user.producer.repository.ProducerRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HireServiceImpl implements HireService {
    private final HireRepository hireRepo;
    private final PhotoRepository photoRepo;
    private final ProducerRepository producerRepo;
    private final UserRepository userRepo;
    private final VideoRepository videoRepo;

    @Transactional
    @Override
    public Long register(HireDTO hireDTO) {
        Hire hire = dto2Entity(hireDTO);
        System.out.println("service - register - hire: " +hire);
        userRepo.save(hire.getProducer().getUserVO());


        producerRepo.save(hire.getProducer());

        Hire finalHire= hireRepo.save(hire);

        ArrayList<PhotoDTO> photos = hireDTO.getPhotos();

        if(photos != null && photos.size() > 0) {

            photos.forEach(photoDTO -> {
                photoDTO.setHire(finalHire);
                Photo photo = dto2EntityPhoto(photoDTO);

                photoRepo.save(photo);
            });
        }


        return null;
    }
}
