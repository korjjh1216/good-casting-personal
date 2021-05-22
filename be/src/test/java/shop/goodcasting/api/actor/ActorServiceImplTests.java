package shop.goodcasting.api.actor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shop.goodcasting.api.article.profile.domain.Profile;
import shop.goodcasting.api.article.profile.repository.ProfileRepository;
import shop.goodcasting.api.article.profile.service.ProfileService;
import shop.goodcasting.api.file.domain.FileVO;
import shop.goodcasting.api.file.repository.FileRepository;
import shop.goodcasting.api.user.actor.domain.Actor;
import shop.goodcasting.api.user.actor.repository.ActorRepository;
import shop.goodcasting.api.user.actor.service.ActorServiceImpl;
import shop.goodcasting.api.user.login.domain.UserVO;
import shop.goodcasting.api.user.login.repository.UserRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class ActorServiceImplTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActorServiceImpl actorService;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private FileRepository fileRepository;

    @Test
    public void insertTests() {
        UserVO user = UserVO.builder()
                .userId(255L)
                .username("test")
                .password("test")
                .account(true)
                .build();
        userRepository.save(user);

        Actor actor = Actor.builder().user(user).actorId(252L).build();

        actorRepository.save(actor);

        Profile profile = Profile.builder()
                .actor(actor)
                .career("커리어")
                .contents("콘텐츠")
                .build();
        profileRepository.save(profile);

        IntStream.rangeClosed(1,3).forEach(i -> {
            FileVO fileVO = FileVO.builder()
                    .fileName("test" + i +".jpg")
                    .uuid(UUID.randomUUID().toString())
                    .profile(profile)
                    .build();
            fileRepository.save(fileVO);
        });
    }

    @Test
    public void actorDeleteTest() {

        Actor actor = actorRepository.findById(255L).get();
        Long actorJoin = actorRepository.getProfileId(actor.getActorId());
        System.out.println(actorJoin);  // 4

        Profile profile = profileRepository.findById(actorJoin).get();

        List<FileVO> fileList = fileRepository.findFileListByProfileId(actorJoin);
        System.out.println(fileList);

        List<Long> fileId = new ArrayList<>();

        fileList.forEach( i -> {
            fileId.add(i.getFileId());
        });
        System.out.println(fileId);

        fileId.forEach( id -> {
            FileVO test = fileRepository.findById(id).get();
            System.out.println(test);
            fileRepository.delete(test);
        });
        profileRepository.delete(profile);
        actorRepository.delete(actor);
    }
}
