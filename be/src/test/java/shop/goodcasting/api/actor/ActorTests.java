package shop.goodcasting.api.actor;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import shop.goodcasting.api.user.actor.domain.Actor;
import shop.goodcasting.api.user.actor.domain.ActorDTO;
import shop.goodcasting.api.user.actor.repository.ActorRepository;
import shop.goodcasting.api.user.actor.service.ActorService;
import shop.goodcasting.api.user.login.domain.Role;
import shop.goodcasting.api.user.login.domain.UserVO;
import shop.goodcasting.api.user.login.repository.UserRepository;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ActorTests {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActorService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummyActors() throws IOException {
        List<String> list = new ArrayList();
        List<ActorDTO> actorList = new ArrayList<>();
        Document innerDoc = null;

        for(int j = 1; j < 165; j++){
            Document document = connectUrl("https://www.filmmakers.co.kr/actorsProfile/page/"+j);
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

            ActorDTO actorDTO = new ActorDTO();
            String yeardel= birthday.text().replace("년",""); //출생년도 "년" 삭제
            Integer cmdel= Integer.valueOf(height.text().replace("Cm","")); //키 "cm" 삭제
            Integer kgdel= Integer.valueOf(weight.text().replace("Kg","")); //키 "cm" 삭제
            boolean human = (height.text().contains("Cm") &&weight.text().contains("Kg"));

            if(human){

                UserVO userVO = UserVO.builder()
                        .username("user" + i)
                        .position(true)
                        .password(passwordEncoder.encode("1111"))
                        .account(true)
                        .roles(new ArrayList<Role>())
                        .build();

                userVO.addRoles(Role.USER);
                userRepository.save(userVO);

                actorDTO.setBirthday(yeardel);
                actorDTO.setHeight(cmdel);
                actorDTO.setWeight(kgdel);
                actorDTO.setName(name.text());
                actorDTO.setAgency(i + "컴퍼니");
                actorDTO.setPhone("010-" + i);
                if( i % 2 == 0){
                    actorDTO.setMajor(true);
                    actorDTO.setGender("female");
                } else {
                    actorDTO.setMajor(false);
                    actorDTO.setGender("male");
                }
                actorList.add(actorDTO);

                Actor actor = service.dto2Entity(actorDTO);

                actor.changeUserVO(userVO);
                actorRepository.save(actor);
                System.out.println("actor" + actor);
            }
        }
    }

    @Test
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
