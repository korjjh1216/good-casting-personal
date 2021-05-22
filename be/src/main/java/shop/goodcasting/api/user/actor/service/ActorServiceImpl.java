package shop.goodcasting.api.user.actor.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import shop.goodcasting.api.article.profile.domain.Profile;
import shop.goodcasting.api.article.profile.domain.ProfileDTO;
import shop.goodcasting.api.article.profile.repository.ProfileRepository;
import shop.goodcasting.api.file.domain.FileVO;
import shop.goodcasting.api.file.repository.FileRepository;
import shop.goodcasting.api.user.actor.domain.Actor;
import shop.goodcasting.api.user.actor.domain.ActorDTO;
import shop.goodcasting.api.user.actor.repository.ActorRepository;
import shop.goodcasting.api.user.login.domain.UserVO;
import shop.goodcasting.api.user.login.repository.UserRepository;
import shop.goodcasting.api.user.login.service.UserServiceImpl;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log
@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {
    private final UserRepository userRepository;
    private final FileRepository fileRepository;
    private final ProfileRepository profileRepository;
    private final ActorRepository actorRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    @Override
    public Optional<Actor> findById(Long actorId) {
        return actorRepository.findById(actorId);
    }

    @Transactional
    @Override
    public Long delete(ActorDTO actorDTO) {
        Actor actor = dto2EntityAll(actorDTO);

        Long profileId = actorRepository.getProfileId(actor.getActorId());

        log.info("profileId : " + profileId);

        if(profileId != null){
            Profile profile = profileRepository.findById(profileId).get();
            List<FileVO> fileList = fileRepository.findFileListByProfileId(profileId);

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

            profileRepository.delete(profile);
        }
        actorRepository.delete(actor);
        userRepository.accountUpdate(actor.getUser().getUserId(), false);
        actorRepository.delete(actor);

        return actorRepository.findById(actor.getActorId()).orElse(null) == null ? 1L : 0L;
    }

    @Override
    @Transactional
    public ActorDTO moreDetail(ActorDTO actorDTO) {
        String passwordUp =  passwordEncoder.encode(actorDTO.getUser().getPassword());
        userRepository.passwordUpdate(actorDTO.getUser().getUserId(), passwordUp);

        Actor actor = dto2EntityAll(actorDTO);
        actorRepository.save(actor);
        return null;
    }
}