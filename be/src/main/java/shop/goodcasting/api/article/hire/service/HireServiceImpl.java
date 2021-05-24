package shop.goodcasting.api.article.hire.service;

import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import shop.goodcasting.api.article.hire.domain.Hire;
import shop.goodcasting.api.article.hire.domain.HireDTO;
import shop.goodcasting.api.article.hire.repository.HireRepository;
import shop.goodcasting.api.common.domain.PageRequestDTO;
import shop.goodcasting.api.common.domain.PageResultDTO;
import shop.goodcasting.api.file.domain.FileVO;
import shop.goodcasting.api.file.domain.FileDTO;
import shop.goodcasting.api.file.repository.FileRepository;
import shop.goodcasting.api.file.service.FileService;
import shop.goodcasting.api.user.producer.domain.Producer;
import shop.goodcasting.api.user.producer.domain.ProducerDTO;
import shop.goodcasting.api.user.producer.service.ProducerService;

import javax.transaction.Transactional;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HireServiceImpl implements HireService {
    private final HireRepository hireRepository;
    private final FileRepository fileRepository;
    private final FileService fileService;
    private final ProducerService producerService;

    @Value("${shop.goodcast.upload.path}")
    private String uploadPath;

    @Transactional
    @Override
    public Long register(HireDTO hireDTO) {
        HireDTO finalHireDto = entity2DtoAll(hireRepository.save(dto2EntityAll(hireDTO)));

        List<FileDTO> files = hireDTO.getFiles();

        return saveFile(finalHireDto, files);

    }
    @Transactional
    @Override
    public HireDTO readHire(Long hireId) {
        List<Object[]> hireAndFileAndProducer = hireRepository.getHireAndFileAndProducerByHireId(hireId);

        Hire hire = (Hire) hireAndFileAndProducer.get(0)[0];
        Producer producer = hire.getProducer();

        HireDTO hireDTO = entity2Dto(hire);

        ProducerDTO producerDTO = producerService.entity2Dto(producer);

        List<FileDTO> fileList = new ArrayList<>();

        hireAndFileAndProducer.forEach(objects -> {
            fileList.add(fileService.entity2Dto((FileVO)objects[2]));
        });

        hireDTO.setProducer(producerDTO);
        hireDTO.setFiles(fileList);

        return hireDTO;
    }

    @Override
    public PageResultDTO<HireDTO, Object[]> getHireList(PageRequestDTO requestDTO) {
        Page<Object[]> result = hireRepository.getHireAndFileAndProducerByFirst(
                requestDTO.getPageable(Sort.by("modDate").descending()));

        Function<Object[], HireDTO> fn = (entity -> entity2DtoFiles((Hire) entity[0],
                (Producer) entity[1], (FileVO) entity[2]));

        return new PageResultDTO<>(result, fn);
    }

    @Transactional
    public Long update(HireDTO hireDTO) {
        Long hireId = hireDTO.getHireId();

        hireRepository.save(dto2EntityAll(hireDTO));

        fileRepository.deleteByHireId(hireId);

        List<FileDTO> files = hireDTO.getFiles();

        return saveFile(hireDTO, files);
    }

    @Transactional
    public void deleteHire(Long hireId) {
        fileRepository.deleteByHireId(hireId);

        hireRepository.deleteById(hireId);
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

                hireRepository.updateResembleAndConfidenceByHireId(hireId, resemble, confidence);

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
                    extractCelebrity(file.getUuid() + "_" + file.getFileName(), hireDTO.getHireId());
                }
            });
            return 1L;
        }
        return 0L;
    }
}