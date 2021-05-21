package shop.goodcasting.api.profile;

import org.aspectj.util.FileUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import shop.goodcasting.api.user.login.domain.Role;
import shop.goodcasting.api.user.login.domain.UserVO;
import shop.goodcasting.api.user.login.repository.UserRepository;

import javax.transaction.Transactional;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

        IntStream.rangeClosed(1, 3).forEach(i -> {

            FileVO fileVO = FileVO.builder()
                    .fileName("test" + i + ".jpg")
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

        //profileDTO.setActor(a);
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


    @Test
    public void createprofile() throws Exception {
        List<String> list = new ArrayList();
        List<ActorDTO> actorList = new ArrayList<>();
        List<Profile> profileList = new ArrayList<>();
        List<FileVO> fileVOList = new ArrayList<>();
        Document innerDoc = null;


        for (int j = 1; j < 50; j++) {
            Document document = connectUrl("https://www.filmmakers.co.kr/actorsProfile/page/" + j);
            Elements link = document.select("div.description>a");

            for (int i = 1; i < link.size(); i++) {
                String a = link.get(i).attr("href");
                list.add(a);
            }
        }


        for (int i = 1; i < list.size(); i++) {
            String value = list.get(i);
            innerDoc = connectUrl("https://www.filmmakers.co.kr" + value);
            Elements name = innerDoc.select("table.ui>thead>tr>th>h2>a");
            Elements birthday = innerDoc.select("table.unstackable>tbody>tr:eq(0)>td.three+td");
            Elements height = innerDoc.select("table.unstackable>tbody>tr:eq(3)>td.three+td");
            Elements weight = innerDoc.select("table.unstackable>tbody>tr:eq(4)>td.three+td");

            //profile crawling
            Elements profileContents = innerDoc.select("div#board-content>div.container:eq(0)>div");
            Elements career = innerDoc.select("table.unstackable>tbody>tr:eq(6)>td.three+td");

            ActorDTO actorDTO = new ActorDTO();
            String yeardel = birthday.text().replace("년", ""); //출생년도 "년" 삭제
            String cmdel = height.text().replace("Cm", ""); //키 "cm" 삭제
            String kgdel = weight.text().replace("Kg", ""); //키 "cm" 삭제
            boolean human = (height.text().contains("Cm") && weight.text().contains("Kg")&&(career.text().contains("영화")||career.text().contains("연극")));
            if (human) {
                // 유저 생성
                UserVO userVO = UserVO.builder()
                        .username("user" + i)
                        .position(true)
                        .password(passwordEncoder.encode("1111"))
                        .account(true)
                        .roles(new ArrayList<Role>())
                        .build();

                userVO.addRoles(Role.USER);
                userRepository.save(userVO);

                //actor생성
                actorDTO.setBirthday(yeardel);
                actorDTO.setHeight(cmdel);
                actorDTO.setWeight(kgdel);
                actorDTO.setName(name.text());
                actorDTO.setAgency(i + "컴퍼니");
                actorDTO.setPhone("010-" + i);
                if (i % 2 == 0) {
                    actorDTO.setMajor(true);
                    actorDTO.setGender("female");
                } else {
                    actorDTO.setMajor(false);
                    actorDTO.setGender("male");
                }
                actorList.add(actorDTO);

                Actor actor = actorService.dto2Entity(actorDTO);

                actor.changeUserVO(userVO);
                Actor finalactor =actorRepository.save(actor);
                ActorDTO actorDTO1= actorService.entity2Dto(finalactor);

                //profile 생성
                ProfileDTO profileDTO = new ProfileDTO();
                profileDTO.setCareer(career.text());
                profileDTO.setContents(profileContents.text());
                profileDTO.setActor(actorDTO1);

                Profile profile = profileService.dto2EntityAll(profileDTO);
                Profile finalProfile = profileRepository.save(profile);


                // 크롤링 사진
                Elements eles = innerDoc.select(".doubling").get(0).select("a");
                if(eles == null || eles.size() == 0){return;}
                String imgLink = eles.get(0).attr("href");
                System.out.println(imgLink);
                URL url = new URL("https://www.filmmakers.co.kr"+ imgLink);
                InputStream in = url.openStream();
                FileOutputStream fos = new FileOutputStream("\\\\DESKTOP-F9UL04V\\Users\\bitcamp\\Pictures\\Goodcasting\\" +name.text()+".jpg");
                FileUtil.copyStream(in,fos);


                FileVO fileVO = FileVO.builder()
                        .fileName(name.text()+".jpg")
                        .profile(finalProfile)
                        .uuid("1234")
                        .build();
                fileRepository.save(fileVO);
            }
        }

    }

    public Document connectUrl(String url) throws IOException {
        return Jsoup
                .connect(url)
                .method(Connection.Method.GET)
                .userAgent("Mozilla/5.0 (X11; Linux x86_64; rv:10.0) " +
                        "Gecko/20100101 Firefox/10.0 " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) " +
                        "Chrome/51.0.2704.106 Safari/537.36")
                .execute()
                .parse();
    }

}