package shop.goodcasting.api.serch;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import shop.goodcasting.api.article.hire.domain.HireDTO;
import shop.goodcasting.api.article.hire.repository.HireRepository;
import shop.goodcasting.api.article.hire.service.HireService;
import shop.goodcasting.api.article.profile.repository.ProfileRepository;
import shop.goodcasting.api.article.profile.service.ProfileService;
import shop.goodcasting.api.file.domain.FileDTO;
import shop.goodcasting.api.file.domain.FileVO;
import shop.goodcasting.api.file.repository.FileRepository;
import shop.goodcasting.api.file.service.FileService;
import shop.goodcasting.api.user.actor.repository.ActorRepository;
import shop.goodcasting.api.user.actor.service.ActorService;
import shop.goodcasting.api.user.login.repository.UserRepository;
import shop.goodcasting.api.user.producer.repository.ProducerRepository;
import shop.goodcasting.api.user.producer.service.ProducerService;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class ImageSerch {
    @Autowired
    ProfileRepository profileRepository;

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


    public void extractCelebrity(String photoName) {

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

                //profileService.getImageMatchList(resemble);

            } else {
                System.out.println("error !!!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


/*    public Long inputPhoto(File uploadFile) {
        String mimeType = uploadFile.getContentType();

        if (!mimeType.startsWith("image") && !mimeType.startsWith("video")) {
            log.warn("this file is not image type");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        String orgName = uploadFile.getOriginalFilename();
        String fileName = orgName.substring(orgName.lastIndexOf("//") + 1);

        String uuid = UUID.randomUUID().toString();

        String saveName = uploadPath + File.separator + uuid + "_" + fileName;
        Path savePath = Paths.get(saveName);

        log.info("register() - fileName: " + fileName);

        if (file != null) {
            extractCelebrity("s_" + file.getUuid() + "_" + file.getFileName());
        }
        ;
        return 1L;

    }*/

}
