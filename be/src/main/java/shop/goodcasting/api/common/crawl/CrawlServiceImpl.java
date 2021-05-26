package shop.goodcasting.api.common.crawl;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import shop.goodcasting.api.article.hire.domain.Hire;
import shop.goodcasting.api.article.profile.domain.Profile;
import shop.goodcasting.api.article.profile.domain.ProfileDTO;
import shop.goodcasting.api.file.repository.FileRepository;
import shop.goodcasting.api.user.actor.domain.Actor;
import shop.goodcasting.api.user.actor.domain.ActorDTO;
import shop.goodcasting.api.user.actor.repository.ActorRepository;
import shop.goodcasting.api.user.actor.service.ActorService;
import shop.goodcasting.api.user.login.domain.UserVO;
import shop.goodcasting.api.user.login.repository.UserRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log
@Service
@RequiredArgsConstructor
public class CrawlServiceImpl implements CrawlService {
    private final UserRepository userRepository;
    private final CrawlRepository crawlRepo;
    private final ActorRepository actorRepository;
    private final ActorService actorService;

    @Override
    public List<Actor> actorCrawl() throws IOException {
        Document document = connectUrl("https://www.filmmakers.co.kr/actorsProfile/category/282/page/1");
        Elements link = document.select("div.description>a");

        List<String> list = new ArrayList();
        Document innerDoc = null;

        for (int i = 0; i < link.size(); i++) {

            String a = link.get(i).attr("href");
            list.add(a);
        }

        log.info("list.size : " + list.size());

        List<Actor> actorList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String value = list.get(i);
            innerDoc = connectUrl("https://www.filmmakers.co.kr" + value);
            Elements birthday = innerDoc.select("table.unstackable>tbody>tr:eq(0)>td.three+td");
            log.info("birthday" + birthday);

            Actor actor = new Actor();
            // actor.setBirthday(birthday.get(i).text());
            actorList.add(actor);
            crawlRepo.save(actor);
        }
        log.info("actorList.size() : " + actorList.size());
        return actorList;
    }

    @Override
    public List<Actor> nomalCrawl() throws IOException {
        log.info("save all 접속");
        Document document = connectUrl("https://www.filmmakers.co.kr/actorsProfile/category/282/page/1");

        Elements ttl = document.select("div.extra>p");
        Elements name = document.select("div.description>a");

        List<Actor> hireList = new ArrayList<>();

        for (int i = 0; i < ttl.size(); i++) {
            Actor a = new Actor();
            // a.setBirthday(ttl.get(i).text());
            // a.setName(name.get(i).text());
            hireList.add(a);
            crawlRepo.save(a);
        }
        return hireList;
    }

    @Override
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

    @Override
    public List<Profile> profileCreate() throws IOException {
        List<String> list = new ArrayList();
        List<ActorDTO> actorList = new ArrayList<>();
        List<Profile> profileList = new ArrayList<>();
        Document innerDoc = null;

        for (int j = 1; j < 165; j++) {
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
            Elements profileContents = innerDoc.select("div.rhymix_content xe_content:eq(0)>p");

            ActorDTO actorDTO = new ActorDTO();
            String yeardel = birthday.text().replace("년", ""); //출생년도 "년" 삭제
            Integer cmdel = Integer.valueOf(height.text().replace("Cm", "")); //키 "cm" 삭제
            Integer kgdel = Integer.valueOf(weight.text().replace("Kg", "")); //키 "cm" 삭제
            boolean human = (height.text().contains("Cm") && weight.text().contains("Kg"));

            if (human) {
                // 유저 생성
                UserVO userVO = UserVO.builder()
                        .username("user" + i)
                        .password("1111")
                        .position(true)
                        .build();
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
                actorRepository.save(actor);
                System.out.println("actor" + actor);

                //profile 생성
                ProfileDTO profileDTO = new ProfileDTO();
                profileDTO.setActor(actorDTO);
                // profileDTO.set

            }
        }
        return profileList;
    }
}

