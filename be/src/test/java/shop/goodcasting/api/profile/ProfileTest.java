package shop.goodcasting.api.profile;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import shop.goodcasting.api.article.profile.domain.Profile;
import shop.goodcasting.api.article.profile.domain.ProfileDTO;
import shop.goodcasting.api.article.profile.repository.ProfileRepository;
import shop.goodcasting.api.article.profile.service.ProfileService;
import shop.goodcasting.api.file.domain.FileDTO;
import shop.goodcasting.api.file.domain.FileVO;
import shop.goodcasting.api.file.repository.FileRepository;
import shop.goodcasting.api.file.service.FileService;
import shop.goodcasting.api.user.actor.domain.Actor;
import shop.goodcasting.api.user.actor.domain.ActorDTO;
import shop.goodcasting.api.user.actor.repository.ActorRepository;
import shop.goodcasting.api.user.actor.service.ActorService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class ProfileTest {
    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private FileService fileService;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    ActorRepository actorRepository;



    @Transactional
    @Commit
    @Test
    public void testInsert() {
        Actor actor = Actor.builder().actorId(1L).build();

        Profile profile = Profile.builder()
                .actor(actor)
                .career("Career")
                .contents("content...")
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
    public void testRead() {

        List<Object[]> result = profileRepository.getProfileAndFileAndActorByProfileId(2L);

        System.out.println("_--------------------------------------------------");

        Profile p = (Profile) result.get(0)[0];
        System.out.println("-------------------" + p.getClass());
        Actor a = p.getActor();
        System.out.println("aaaaaaaaaaaaaaaaa: " + a);

        ProfileDTO profileDTO = profileService.entity2Dto(p);
        System.out.println("dtodtodtodotdotodtodto: " + profileDTO);

        ActorDTO actorDTO = actorService.entity2Dto(a);
        System.out.println("adto: " + actorDTO);

        List<FileDTO> fileList = new ArrayList<>();

        for (Object[] arr : result) {
            FileVO f = (FileVO) arr[2];

            System.out.println("file: " + arr[2]);
            System.out.println("---------------------------------");
            FileDTO fileDTO = fileService.entity2Dto(f);
            fileList.add(fileDTO);
        }

        for (FileDTO f : fileList) {
            System.out.println("fileLst: " + f);
        }

        profileDTO.setActor(a);
        profileDTO.setFiles(fileList);
//        System.out.println("profile: " + p);
//        System.out.println("actor: " + a);
//
        System.out.println("profile dto: " + profileDTO);

    }

    @Test
    public void testUpdate() {
        Profile profile = profileRepository.findById(2L).get();

        System.out.println(profile);


    }

//    @Test
//    public void test() {
//        List<Object[]> res = profileRepository.getProfileWithFileByProfileId(2L);
//
//        for (Object[] arr : res) {
//            System.out.println(Arrays.toString(arr));
//        }
//    }
}