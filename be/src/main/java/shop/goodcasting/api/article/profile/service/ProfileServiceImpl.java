package shop.goodcasting.api.article.profile.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import shop.goodcasting.api.article.profile.domain.Profile;
import shop.goodcasting.api.article.profile.domain.ProfileDTO;
import shop.goodcasting.api.article.profile.domain.ProfileListDTO;
import shop.goodcasting.api.article.profile.repository.ProfileRepository;

import shop.goodcasting.api.article.profile.repository.SearchProfileRepositoryImpl;
import shop.goodcasting.api.career.domain.Career;
import shop.goodcasting.api.career.domain.CareerDTO;
import shop.goodcasting.api.career.repository.CareerRepository;
import shop.goodcasting.api.career.service.CareerService;
import shop.goodcasting.api.common.domain.PageRequestDTO;
import shop.goodcasting.api.common.domain.PageResultDTO;
import shop.goodcasting.api.file.domain.FileDTO;
import shop.goodcasting.api.file.domain.FileVO;
import shop.goodcasting.api.file.repository.FileRepository;
import shop.goodcasting.api.file.service.FileService;
import shop.goodcasting.api.user.actor.domain.Actor;
import shop.goodcasting.api.user.actor.domain.ActorDTO;
import shop.goodcasting.api.user.actor.service.ActorService;

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
import java.util.function.Function;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepo;
    private final FileRepository fileRepo;
    private final FileService fileService;
    private final ActorService actorService;
    private final CareerService careerService;
    private final CareerRepository careerRepo;

    @Value("${shop.goodcast.upload.path}")
    private String uploadPath;

    @Transactional
    @Override
    public Long register(ProfileDTO profileDTO) {
        ProfileDTO finalProfileDto = entity2DtoAll(profileRepo.save(dto2EntityAll(profileDTO)));

        List<FileDTO> files = profileDTO.getFiles();
        List<CareerDTO> careers = profileDTO.getCareers();

        saveCareer(finalProfileDto, careers);
        return saveFile(finalProfileDto, files);
    }

    @Transactional
    @Override
    public ProfileDTO readProfile(Long profileId) {
        List<Object[]> profileAndFileAndActor = profileRepo.getProfileAndFileAndActorByProfileId(profileId);

        Profile profile = (Profile) profileAndFileAndActor.get(0)[0];
        Actor actor = profile.getActor();

        ProfileDTO profileDTO = entity2Dto(profile);

        ActorDTO actorDTO = actorService.entity2Dto(actor);

        List<FileDTO> fileList = new ArrayList<>();

        profileAndFileAndActor.forEach(objects -> {
            fileList.add(fileService.entity2Dto((FileVO)objects[2]));
        });

        profileDTO.setActor(actorDTO);
        profileDTO.setFiles(fileList);

        return profileDTO;
    }

    @Override
    public PageResultDTO<ProfileListDTO, Object[]> getProfileList(PageRequestDTO pageRequest) {
        Page<Object[]> result = profileRepo.searchPage(pageRequest,
                pageRequest.getPageable(Sort.by(pageRequest.getSort()).descending()));

        Function<Object[], ProfileListDTO> fn = (entity -> entity2DtoFiles((Profile) entity[0],
                (Actor) entity[1], (FileVO) entity[2]));

        return new PageResultDTO<>(result, fn);
    }

    @Transactional
    public Long update(ProfileDTO profileDTO) {
        Long profileId = profileDTO.getProfileId();

        profileRepo.save(dto2EntityAll(profileDTO));

        fileRepo.deleteByProfileId(profileId);

        List<FileDTO> files = profileDTO.getFiles();

        return saveFile(profileDTO, files);
    }

    @Transactional
    public void deleteProfile(Long profileId) {
        fileRepo.deleteByProfileId(profileId);

        profileRepo.deleteById(profileId);
    }

    public String[] extractCelebrity(String photoName) {
        StringBuffer reqStr = new StringBuffer();
        String clientId = "92mep69l88";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "qdbpwHd8pRZPszLr0gLfqKR7OHbdsDriRmOFdwno";//애플리케이션 클라이언트 시크릿값";

        try {
            String paramName = "image"; // 파라미터명은 image로 지정
            String imgFile = photoName;
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

                String confidence = String.valueOf(celebObject.getDouble("confidence"));
                System.out.println("---------------------confidence-----------------" + confidence);

                System.out.println("===================================================================");

                return new String[] {resemble, confidence};
            } else {
                System.out.println("error !!!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Long saveFile(ProfileDTO profileDTO, List<FileDTO> files) {
        if(files != null && files.size() > 0) {
            files.forEach(fileDTO -> {
                fileDTO.setProfile(profileDTO);
                FileVO file = fileService.dto2EntityProfile(fileDTO);
                fileRepo.save(file);

                if (file.isPhotoType() && fileDTO.isFirst()) {
                    String[] arr = extractCelebrity(
                            uploadPath + File.separator + file.getUuid() + "_" + file.getFileName());

                    profileRepo.updateResembleAndConfidenceByProfileId(
                            profileDTO.getProfileId(), arr[0], Double.parseDouble(arr[1]));
                }
            });
            return 1L;
        }
        return 0L;
    }

    public PageResultDTO<ProfileListDTO, Object[]> searchResemble(PageRequestDTO pageRequest, MultipartFile uploadFile) {
        String orgName = uploadFile.getOriginalFilename();
        String fileName = orgName.substring(orgName.lastIndexOf("//") + 1);
        String uuid = UUID.randomUUID().toString();
        String saveName = uploadPath + File.separator + uuid + "_" + fileName;
        Path savePath = Paths.get(saveName);

        try {
            uploadFile.transferTo(savePath);

            log.info("image thumbnail extract");

            String thumbnailSaveName = uploadPath + File.separator + "s_" + uuid + "_" + fileName;

            File thumbnailFile = new File(thumbnailSaveName);

            Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 500, 500);

            String[] arr = extractCelebrity(thumbnailSaveName);

            pageRequest.setRkeyword(arr[0]);

            return getProfileList(pageRequest);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Long saveCareer(ProfileDTO profileDTO, List<CareerDTO> careers) {
        if(careers != null && careers.size() > 0) {
            careers.forEach(careerDTO -> {
                careerDTO.setProfile(profileDTO);
                Career career = careerService.dto2EntityAll(careerDTO);
                careerRepo.save(career);
            });
            return 1L;
        }
        return 0L;
    }
}