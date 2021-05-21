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
import java.util.List;
import java.util.Optional;

@Log
@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {
    private final UserRepository userRepository;
    private final ActorRepository actorRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProfileRepository profileRepository;
    private final FileRepository fileRepository;

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
        //actorRepository.getProfileIdByActorId(actorDTO.getActorId());
//        fileRepository.deleteById();
//        profileRepository.deleteById();
//        actorRepository.accountUpdate(actorDTO.getUser().getUserId(),false);
//        actorRepository.deleteById(actorDTO.getActorId());
        Actor actor = dto2EntityAll(actorDTO);
        return actorRepository.findById(actor.getActorId()).orElse(null) == null ? 1L : 0L;
    }

    @Override
    @Transactional
    public ActorDTO moreDetail(ActorDTO actorDTO) {

        String passwordUp =  passwordEncoder.encode(actorDTO.getUser().getPassword());
        actorRepository.passwordUpdate(actorDTO.getUser().getUserId(),passwordUp);
        Actor actor = dto2EntityAll(actorDTO);
        actorRepository.save(actor);
        return null;
    }
}