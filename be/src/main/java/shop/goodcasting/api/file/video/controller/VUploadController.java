package shop.goodcasting.api.file.video.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import shop.goodcasting.api.file.video.domain.VideoDTO;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@CrossOrigin("*")
@RequestMapping("/v_upload")
public class VUploadController {
    @Value("${shop.goodcast.upload.path}")
    private String uploadPath;

    @PostMapping("/register")
    public ResponseEntity<List<VideoDTO>> register(MultipartFile uploadFile){
        log.info("uploadFile()--------------------------------------------------------");
        log.info("uploadFile: " + uploadFile.toString());
        List<VideoDTO> resultDTOList = new ArrayList<>();

        if(!uploadFile.getContentType().startsWith("video")){
            log.warn("this file is not video type");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        String orgName = uploadFile.getOriginalFilename();
        String fileName = orgName.substring(orgName.lastIndexOf("//")+1);

        String uuid = UUID.randomUUID().toString();

        String saveName = uploadPath + File.separator + uuid + "_" + fileName;
        Path savePath = Paths.get(saveName);

        System.out.println("fileName : --------------------------------------" + fileName);

        try {
            uploadFile.transferTo(savePath);

            String thumbnailSaveName = uploadPath + File.separator + "s_" + uuid + "_" + fileName;

            File thumbnailFile = new File(thumbnailSaveName);

            //썸네일


        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(resultDTOList,HttpStatus.OK);
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> getFule(String fileName){
        System.out.println("fileName::::::::::::::" + fileName);

        ResponseEntity<byte[]> result = null;

        try {
            String srcFileName =  URLDecoder.decode(fileName,"UTF-8");

            log.info("srcFileName: " + srcFileName);

            File file = new File(uploadPath + File.separator + srcFileName);

            log.info("file: " + file);

            HttpHeaders header = new HttpHeaders();

            //MIME타입 처리
            header.add("Content-Type", Files.probeContentType(file.toPath()));

            System.out.println("header: " + header);

            //파일 데이터 처리
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);

            System.out.println("result: " + result);

        } catch (Exception e) {
        log.error(e.getMessage());
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }
}
