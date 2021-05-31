package shop.goodcasting.api.hire;

import net.coobird.thumbnailator.Thumbnailator;
import org.aspectj.util.FileUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import shop.goodcasting.api.article.hire.domain.Hire;
import shop.goodcasting.api.article.hire.domain.HireDTO;
import shop.goodcasting.api.article.hire.repository.HireRepository;
import shop.goodcasting.api.article.hire.service.HireService;
import shop.goodcasting.api.article.profile.service.ProfileService;
import shop.goodcasting.api.file.domain.FileDTO;
import shop.goodcasting.api.file.domain.FileVO;
import shop.goodcasting.api.file.repository.FileRepository;
import shop.goodcasting.api.file.service.FileService;
import shop.goodcasting.api.user.actor.repository.ActorRepository;
import shop.goodcasting.api.user.actor.service.ActorService;
import shop.goodcasting.api.user.login.domain.Role;
import shop.goodcasting.api.user.login.domain.UserVO;
import shop.goodcasting.api.user.login.repository.UserRepository;
import shop.goodcasting.api.user.producer.domain.Producer;
import shop.goodcasting.api.user.producer.domain.ProducerDTO;
import shop.goodcasting.api.user.producer.repository.ProducerRepository;
import shop.goodcasting.api.user.producer.service.ProducerService;

import javax.transaction.Transactional;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class hireTest {

    @Autowired
    ProducerRepository producerRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private HireService hireService;
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

    @Autowired
    private HireRepository hireRepository;

    @Autowired
    private ProducerService producerService;

    @Value("${shop.goodcast.upload.path}")
    private String uploadPath;
//<------------------------------------------------------------------------------------------------------------------>
@Transactional
@Commit
@Test
public void creatHire() throws Exception {
    List<String> list = new ArrayList();
    List<ProducerDTO> producerList = new ArrayList<>();
    List<HireDTO> hireList = new ArrayList<>();
    List<FileVO> fileVOList = new ArrayList<>();
    Document innerDoc = null;
    int k =0;


    for (int j = 1; j < 50; j++) {
        Document document = connectUrl("https://www.filmmakers.co.kr/performerWanted/page/" + j);
        Elements link = document.select("table.table>tbody>tr>td>a");
        for (int i = 1; i < link.size(); i++) {
            String a = link.get(i).attr("href");
            list.add(a);
        }
    }


    for (int i = 1; i < list.size(); i++) {
        //hire crawling
        String value = list.get(i);
        innerDoc = connectUrl("https://www.filmmakers.co.kr" + value);
        Elements hire_title = innerDoc.select("table.ui>thead>tr>th>h2>a");
        Elements project = innerDoc.select("table.celled>tbody>tr:eq(1)>td:eq(1)");
        Elements cast = innerDoc.select("table.celled>tbody>tr:eq(3)>td:eq(1)");
        Elements filming = innerDoc.select("table.celled>tbody>tr:eq(4)>td:eq(1)");
        Elements guarantee = innerDoc.select("table.celled>tbody>tr:eq(5)>td:eq(1)");
        Elements personnel = innerDoc.select("table.celled>tbody>tr:eq(6)>td:eq(1)");
        Elements deadline = innerDoc.select("table.celled>tbody>tr:eq(10)>td:eq(1)");
        Elements contents = innerDoc.select("div.rhymix_content>p");
        ProducerDTO producerDTO = new ProducerDTO();
        boolean deadlineTrue = (deadline.text().contains("-"));


        if (deadlineTrue) {
            // 유저 생성
            UserVO userVO = UserVO.builder()
                    .username("userP" + i)
                    .position(false)
                    .password(passwordEncoder.encode("1111"))
                    .account(true)
                    .roles(new ArrayList<Role>())
                    .build();

            userVO.addRoles(Role.USER);
            userRepository.save(userVO);

            //producer 생성
            producerDTO.setName("producer" + i);
            producerDTO.setAgency("agency" + i);
            producerDTO.setPosition("manager");
            producerDTO.setPhone("010" + i);
            producerDTO.setEmail("pro@" + i + ".com");
            producerList.add(producerDTO);

            Producer producer = producerService.dto2Entity(producerDTO);
            producer.changeUserVO(userVO);
            Producer finalProducer = producerRepository.save(producer);
            ProducerDTO producerDTO1 = producerService.entity2Dto(finalProducer);

            //Hire 생성
            HireDTO hireDTO = new HireDTO();
            hireDTO.setTitle(hire_title.text());
            hireDTO.setProject(project.text());
            hireDTO.setCast(cast.text());
            hireDTO.setPersonnel(personnel.text());
            hireDTO.setContents(contents.text());
            hireDTO.setProducer(producerDTO1);
            hireList.add(hireDTO);
            System.out.println("hire 다 가져오니?" + hireDTO);

            Hire hire = hireService.dto2EntityAll(hireDTO);
            Hire finalHire = hireRepository.save(hire);
            hireDTO.setHireId(finalHire.getHireId());

            // 크롤링 사진
            Document namuEnt = connectUrl("http://www.namooactors.com/");
            Elements eles = namuEnt.select(".actor_small").get(k).select("img");
            if (eles == null || eles.size() == 0) { return;}
            String imgLink = eles.attr("src");
            URL url = new URL("http://www.namooactors.com/" + imgLink);
            System.out.println("완벽한 url" + url);
            InputStream in = url.openStream();
            FileOutputStream fos = new FileOutputStream("\\\\DESKTOP-F9UL04V\\Users\\bitcamp\\Pictures\\Goodcasting\\" + producerDTO1.getName() + ".jpg");
            FileUtil.copyStream(in, fos);
            k++;

            // 썸네일 생성
            String uuid = UUID.randomUUID().toString();
            FileVO fileVO = FileVO.builder()
                    .fileName(producerDTO1.getName() + ".jpg")
                    .hire(finalHire)
                    .uuid(uuid)
                    .first(true)
                    .photoType(true)
                    .build();
            fileRepository.save(fileVO);

            String saveName = uploadPath + File.separator + producerDTO1.getName() + ".jpg";
            Path savePath = Paths.get(saveName);
            System.out.println("image thumbnail extract");
            String thumbnailSaveName = uploadPath + File.separator + "s_" + uuid + "_" + producerDTO1.getName() + ".jpg";
            File thumbnailFile = new File(thumbnailSaveName);
            Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 500, 500);

            List<FileDTO> files = new ArrayList<>();
            files.add(fileService.entity2Dto(fileVO));
            saveFile(hireDTO, files);


        }
    }

}

    public void extractCelebrity(String photoName, Long hireId) {

        StringBuffer reqStr = new StringBuffer();
        String clientId = "92mep69l88";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "qdbpwHd8pRZPszLr0gLfqKR7OHbdsDriRmOFdwno";//애플리케이션 클라이언트 시크릿값";

        try {
            String paramName = "image"; // 파라미터명은 image로 지정
            String imgFile = uploadPath + "\\" + photoName;
            System.out.println("##################"+ imgFile +"################################3");
            File uploadFile = new File(imgFile);
            String apiURL = "https://naveropenapi.apigw.ntruss.com/vision/v1/celebrity"; // 유명인 얼굴 인식
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            // multipart request
            String boundary = "---" + System.currentTimeMillis() + "---";
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            OutputStream outputStream = con.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
            String LINE_FEED = "\r\n";
            // file 추가
            String fileName = uploadFile.getName();
            writer.append("--" + boundary).append(LINE_FEED);
            writer.append("Content-Disposition: form-data; name=\"" + paramName + "\"; filename=\"" + fileName + "\"").append(LINE_FEED);
            writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
            writer.append(LINE_FEED);
            writer.flush();
            FileInputStream inputStream = new FileInputStream(uploadFile);
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            inputStream.close();
            writer.append(LINE_FEED).flush();
            writer.append("--" + boundary + "--").append(LINE_FEED);
            writer.close();
            BufferedReader br = null;
            int responseCode = con.getResponseCode();
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 오류 발생
                System.out.println("error!!!!!!! responseCode= " + responseCode);
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            }
            String inputLine;
            if (br != null) {
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                System.out.println("response: " + response.toString());

                JSONObject jsonObject = new JSONObject(response.toString());
                JSONArray facesArr = jsonObject.getJSONArray("faces");
                System.out.println("---------------------facesArr-----------------" + facesArr);

                JSONObject elem = facesArr.getJSONObject(0);
                System.out.println("---------------------elem-----------------" + elem);

                JSONObject celebObject = elem.getJSONObject("celebrity");
                System.out.println("---------------------celebObject-----------------" + celebObject);

                String resemble = celebObject.getString("value");
                System.out.println("---------------------resemble-----------------" + resemble);

                Double confidence = celebObject.getDouble("confidence");
                System.out.println("---------------------confidence-----------------" + confidence);

                System.out.println("===================================================================");

                //hireRepository.updateResembleAndConfidenceByHireId(hireId, resemble, confidence);

            } else {
                System.out.println("error !!!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public Long saveFile(HireDTO hireDTO, List<FileDTO> files) {
        if(files != null && files.size() > 0) {
            files.forEach(fileDTO -> {
                fileDTO.setHire(hireDTO);
                FileVO file = fileService.dto2EntityHire(fileDTO);
                fileRepository.save(file);

                if (file.isPhotoType() && fileDTO.isFirst()) {
                    extractCelebrity("s_"+file.getUuid() + "_" + file.getFileName(), hireDTO.getHireId());
                }
            });
            return 1L;
        }
        return 0L;
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
