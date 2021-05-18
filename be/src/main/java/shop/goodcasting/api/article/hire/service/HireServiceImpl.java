package shop.goodcasting.api.article.hire.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.goodcasting.api.article.hire.domain.Hire;
import shop.goodcasting.api.article.hire.domain.HireDTO;
import shop.goodcasting.api.article.hire.repository.HireRepository;
import shop.goodcasting.api.file.domain.FileVO;
import shop.goodcasting.api.file.domain.FileDTO;
import shop.goodcasting.api.file.repository.FileRepository;
import shop.goodcasting.api.user.login.repository.UserRepository;
import shop.goodcasting.api.user.producer.repository.ProducerRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class HireServiceImpl implements HireService {
    private final HireRepository hireRepo;
    private final FileRepository fileRepo;
    private final ProducerRepository producerRepo;
    private final UserRepository userRepo;

    @Transactional
    @Override
    public Long register(HireDTO hireDTO) {
        Hire hire = dto2Entity(hireDTO);
        System.out.println("service - register - hire: " +hire);
        userRepo.save(hire.getProducer().getUserVO());


        producerRepo.save(hire.getProducer());

        Hire finalHire= hireRepo.save(hire);

        ArrayList<FileDTO> files = hireDTO.getFiles();

        if(files != null && files.size() > 0) {

            files.forEach(fileDTO -> {
                fileDTO.setHire(finalHire);
                FileVO file = dto2EntityFile(fileDTO);

                fileRepo.save(file);
            });
        }
        return null;
    }
}