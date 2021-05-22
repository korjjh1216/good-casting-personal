package shop.goodcasting.api.actor;

import org.aspectj.util.FileUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class ActorCrawling2 {

    public Elements getLinks(int page)throws Exception{

        List<String> actorPageLink = new ArrayList<>();
        List<String> imgLinkList = new ArrayList<>();

        Document document = connectUrl("https://www.filmmakers.co.kr/actorsProfile/page/1?sort_index=list_order&order_type=asc");
        Elements aTags = document.select(".card a:first-child");
        Elements imgSrcs = document.select(".card img");



        return aTags;

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

    public void getActorPage(String pageURL) throws Exception {

        Document doc = Jsoup.connect("https://www.filmmakers.co.kr"+pageURL).method(Connection.Method.GET).get();

        //System.out.println(doc);

        Element ele = doc.select("table.celled h2 a").get(0);

        String name = ele.text();

        Elements eles = doc.select(".doubling").get(0).select("a");

        if(eles == null || eles.size() == 0){
            return;
        }

        String imgLink = eles.get(0).attr("href");

        System.out.println(imgLink);

        URL url = new URL("https://www.filmmakers.co.kr"+ imgLink);

        InputStream in = url.openStream();

        FileOutputStream fos = new FileOutputStream("/usr/local/goodCasting/" +name+".jpg");

        FileUtil.copyStream(in,fos);


    }

    public static void main(String[] args)throws Exception {

        ActorCrawling2 obj = new ActorCrawling2();

        Elements links = obj.getLinks(1);

        Map<String, String> linkMap = new HashMap<>();

        links.forEach(pageLink -> {
            String linkStr = pageLink.attr("href");

            String[] arr = linkStr.split("\\/");
            //System.out.println(Arrays.toString(arr));
            linkMap.put(arr[2], linkStr);

        });
//
//        System.out.println("----------------");
//        System.out.println(linkMap.size());

//          String pageLink = "/actorsProfile/10414142";

//          obj.getActorPage(pageLink);


        linkMap.values().forEach(pageLink -> {
            try {
                obj.getActorPage(pageLink);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
}
