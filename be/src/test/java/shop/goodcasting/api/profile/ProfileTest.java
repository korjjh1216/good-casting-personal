package shop.goodcasting.api.profile;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import shop.goodcasting.api.article.profile.domain.Profile;
import shop.goodcasting.api.article.profile.repository.ProfileRepository;
import shop.goodcasting.api.file.domain.FileVO;
import shop.goodcasting.api.file.repository.FileRepository;
import shop.goodcasting.api.user.actor.domain.Actor;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class ProfileTest {
    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    FileRepository fileRepository;

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

        Long profileId = profile.getProfileId();



    }

    @Test
    public void test1() {

        List<Object[]>  result = profileRepository.getProfileWithFileByProfileId(2L);

        System.out.println(result);

        for (Object[] arr : result) {

            Profile p = (Profile) arr[0];

            System.out.println(p);
            System.out.println(p.getActor());
            System.out.println(arr[2]);
            System.out.println("---------------------------------");

        }


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
