package shop.goodcasting.api.article.hire;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shop.goodcasting.api.article.hire.repository.HireRepository;
import shop.goodcasting.api.article.hire.service.HireService;
import shop.goodcasting.api.user.producer.repository.ProducerRepository;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class HireTests {

    @Autowired
    private HireRepository hireRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private HireService service;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

//    @Test
//    public List<Hire> hireCrawl() throws IOException {
//        for (int j = 8; j < 20; j++) {
//            Document document = connectUrl("https://www.filmmakers.co.kr/performerWanted/page/" + j);
//            Elements link = document.select("a.block");
//            //log.info("link" + link);
//
//            List<String> list = new ArrayList();
//            Document innerDoc = null;
//
//            for (int i = 0; i < link.size(); i++) {
//                String a = link.get(i).attr("href");
//                list.add(a);
//            }
//
//            List<HireDTO> hireList = new ArrayList<>();
//
//            for (int i = 0; i < list.size(); i++) {
//                String value = list.get(i);
//                innerDoc = connectUrl("https://www.filmmakers.co.kr" + value);
//                Elements hire_title = innerDoc.select("table.ui>thead>tr>th>h2>a");
//                Elements title = innerDoc.select("table.celled>tbody>tr:eq(1)>td:eq(1)");
//                Elements cast = innerDoc.select("table.celled>tbody>tr:eq(3)>td:eq(1)");
//                Elements filming = innerDoc.select("table.celled>tbody>tr:eq(4)>td:eq(1)");
//                Elements guarantee = innerDoc.select("table.celled>tbody>tr:eq(5)>td:eq(1)");
//                Elements personnel = innerDoc.select("table.celled>tbody>tr:eq(6)>td:eq(1)");
//                Elements deadline = innerDoc.select("table.celled>tbody>tr:eq(10)>td:eq(1)");
//                Elements contents = innerDoc.select("div.rhymix_content>p");
//
//                HireDTO hireDTO = new HireDTO();
//                boolean deadlineTrue = (deadline.text().contains("-"));
//
//                if (deadlineTrue) {
//
//                    Producer producer = Producer.builder()
//                            .username("producer" + i)
//                            .password("1111")
//                            .build();
//                    producerRepository.save(producer);
//
//                    hireDTO.setHireTitle(hire_title.text());
//                    hireDTO.setHireTitle(hireDTO.getHireTitle());
//                    hireDTO.setCast(cast.text());
//                    hireDTO.setFilming(filming.text());
//                    hireDTO.setGuarantee(guarantee.text());
//                    hireDTO.setPersonnel(personnel.text());
//                    hireDTO.setDeadline(deadline.text());
//                    hireDTO.setContents(contents.text());
//                    hireList.add(hireDTO);
//
//                    Hire hire = service.dto2Entity(hireDTO);
//
//                    hire.changeProducer(producer);
//                    hireRepository.save(hire);
//                }
//            }
//        }
//        return null;
//    }

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
