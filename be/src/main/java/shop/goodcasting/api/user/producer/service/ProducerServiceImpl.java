package shop.goodcasting.api.user.producer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import shop.goodcasting.api.article.hire.domain.Hire;
import shop.goodcasting.api.article.hire.repository.HireRepository;
import shop.goodcasting.api.article.profile.repository.ProfileRepository;
import shop.goodcasting.api.file.domain.FileVO;
import shop.goodcasting.api.file.repository.FileRepository;
import shop.goodcasting.api.user.login.repository.UserRepository;
import shop.goodcasting.api.user.producer.domain.Producer;
import shop.goodcasting.api.user.producer.domain.ProducerDTO;
import shop.goodcasting.api.user.producer.repository.ProducerRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log
@Service
@RequiredArgsConstructor
public class ProducerServiceImpl implements ProducerService {
    private final UserRepository userRepository;
    private final ProducerRepository producerRepository;
    private final FileRepository fileRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final HireRepository hireRepository;

    @Override
    public List<Producer> findAll() {
        return producerRepository.findAll();
    }

    @Override
    public Optional<Producer> findById(Long producerId) {
        return producerRepository.findById(producerId);
    }

    @Override
    @Transactional
    public Long delete(ProducerDTO producerDTO) {
        Producer producer = dto2EntityAll(producerDTO);

        Long hireId = producerRepository.getHireId(producer.getProducerId());

        log.info("hireId : " + hireId);

        if(hireId != null){
            Hire hire = hireRepository.findById(hireId).get();
            List<FileVO> fileList = fileRepository.findFileListByHireId(hireId);

            log.info("fileList : " + fileList);

            List<Long> fileId = new ArrayList<>();
            fileList.forEach( i -> {
                fileId.add(i.getFileId());
            });
            log.info("fileId : " + fileId);

            fileId.forEach( id -> {
                FileVO test = fileRepository.findById(id).get();
                System.out.println(test);
                fileRepository.delete(test);
            });

            hireRepository.delete(hire);
        }
        userRepository.accountUpdate(producer.getUserVO().getUserId(), false);
        producerRepository.delete(producer);

        return producerRepository.findById(producer.getProducerId()).orElse(null) == null ? 1L : 0L;
    }

    @Override
    @Transactional
    public ProducerDTO moreDetail(ProducerDTO producerDTO) {
        String passwordUp =  passwordEncoder.encode(producerDTO.getUser().getPassword());
        System.out.println("있니?" + producerDTO.getUser().getUserId());
        userRepository.passwordUpdate(producerDTO.getUser().getUserId(), passwordUp);

        Producer producer = dto2EntityAll(producerDTO);
        producerRepository.save(producer);
        return null;
    }
}
