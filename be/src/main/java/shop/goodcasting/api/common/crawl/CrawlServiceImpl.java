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
import shop.goodcasting.api.common.csv.ConvertToCSV;
import shop.goodcasting.api.user.actor.domain.Actor;
import shop.goodcasting.api.user.actor.domain.ActorDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log
@Service
@RequiredArgsConstructor
public class CrawlServiceImpl implements CrawlService {

    private final CrawlRepository crawlRepo;
    private final HireRepo hireRepo;
    @Override
    public List<Actor> actorCrawl() throws IOException {
        for(int j=1 ;j<5;j++){
            Document document = connectUrl("https://www.filmmakers.co.kr/actorsProfile/page/"+j);
            Elements link = document.select("div.description>a");

            List<String> list = new ArrayList();
            Document innerDoc = null;

            for (int i = 0; i < link.size(); i++) {
                ;
                String a = link.get(i).attr("href");
                list.add(a);
            }

            log.info("list.size : " + list.size());

            List<Actor> actorList = new ArrayList<>();

            for (int i = 0; i < list.size(); i++) {
                String value = list.get(i);
                innerDoc = connectUrl("https://www.filmmakers.co.kr" + value);
                Elements name = innerDoc.select("table.ui>thead>tr>th>h2>a");
                Elements birthday = innerDoc.select("table.unstackable>tbody>tr:eq(0)>td.three+td");
                Elements height = innerDoc.select("table.unstackable>tbody>tr:eq(3)>td.three+td");
                Elements weight = innerDoc.select("table.unstackable>tbody>tr:eq(4)>td.three+td");

                log.info("name" + name);

                Actor actor = new Actor();
                String yeardel= birthday.text().replace("년",""); //출생년도 "년" 삭제
                String cmdel= height.text().replace("Cm",""); //키 "cm" 삭제
                String kgdel= weight.text().replace("Kg",""); //키 "cm" 삭제
                boolean human = (height.text().contains("Cm") &&weight.text().contains("Kg"));

                if(human){
//                    actor.setBirthday(yeardel);
//                    actor.setHeight(cmdel);
//                    actor.setWeight(kgdel);
//                    actor.setName(name.text());
//                    actorList.add(actor);
                    crawlRepo.save(actor);
                }
            }
            log.info("actorList.size() : " + actorList.size());

        }
        return null;
    }


    @Override
    public List<Hire> hireCrawl() throws IOException {
        for (int j = 8; j < 20; j++) {
            Document document = connectUrl("https://www.filmmakers.co.kr/performerWanted/page/" + j);
            Elements link = document.select("a.block");
            //log.info("link" + link);

            List<String> list = new ArrayList();
            Document innerDoc = null;

            for (int i = 0; i < link.size(); i++) {
                ;
                String a = link.get(i).attr("href");
                list.add(a);
                log.info("-----" + list);
            }

            //log.info("list.size : " + list.size());

            List<Hire> hireList = new ArrayList<>();

            for (int i = 0; i < list.size(); i++) {
                String value = list.get(i);
                innerDoc = connectUrl("https://www.filmmakers.co.kr" + value);
                Elements hire_title = innerDoc.select("table.ui>thead>tr>th>h2>a");
                Elements title = innerDoc.select("table.celled>tbody>tr:eq(1)>td:eq(1)");
                Elements cast = innerDoc.select("table.celled>tbody>tr:eq(3)>td:eq(1)");
                Elements filming = innerDoc.select("table.celled>tbody>tr:eq(4)>td:eq(1)");
                Elements guarantee = innerDoc.select("table.celled>tbody>tr:eq(5)>td:eq(1)");
                Elements personnel = innerDoc.select("table.celled>tbody>tr:eq(6)>td:eq(1)");
                Elements deadline = innerDoc.select("table.celled>tbody>tr:eq(10)>td:eq(1)");
                Elements contents = innerDoc.select("div.rhymix_content>p");
                // log.info("title" + contents.text());

                Hire hire = new Hire();
                boolean deadlineTrue = (deadline.text().contains("-"));

                if (deadlineTrue) {
//                    hire.setHireTitle(hire_title.text());
//                    hire.setTitle(title.text());
//                    hire.setCast(cast.text());
//                    hire.setFilming(filming.text());
//                    hire.setGuarantee(guarantee.text());
//                    hire.setPersonnel(personnel.text());
//                    hire.setDeadline(deadline.text());
//                    hire.setContents(contents.text());
                    hireRepo.save(hire);
                }
            }

            log.info("hireList.size() : " + hireList.size());
        }
        return null;
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
    public List<ActorDTO> profileCrawl() throws IOException {
        return null;
    }
}
