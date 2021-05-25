package shop.goodcasting.api.file.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import shop.goodcasting.api.file.domain.FileDTO;
import shop.goodcasting.api.file.service.FileServiceImpl;

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
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {
    private final FileServiceImpl service;
    @Value("${shop.goodcast.upload.path}")
    private String uploadPath;

    @PostMapping("/register")
    public ResponseEntity<List<FileDTO>> register(MultipartFile[] uploadFiles) {
        log.info("----------------------file register()----------------------------------");
        List<FileDTO> resultDTOList = new ArrayList<>();

        for (MultipartFile uploadFile : uploadFiles) {

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

            try {
                uploadFile.transferTo(savePath);

                if(mimeType.startsWith("image")){
                    log.info("image thumbnail extract");

                    String thumbnailSaveName = uploadPath + File.separator + "s_" + uuid + "_" + fileName;

                    File thumbnailFile = new File(thumbnailSaveName);

                    Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 100, 100);


                }else if(mimeType.startsWith("video")) {
                    log.info("video thumbnail extract");
                    service.extractVideoThumbnail(new File(saveName));
                }

                FileDTO fileDTO = FileDTO.builder()
                        .fileName(fileName)
                        .uuid(uuid)
                        .build();

                resultDTOList.add(fileDTO);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return new ResponseEntity<>(resultDTOList, HttpStatus.OK);
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> display(String fileName) {
        System.out.println("fileName: -----------------------------" + fileName);

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
            return new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        } catch(Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Long> delete(@RequestBody FileDTO fileDTO) {
        service.deleteFile(uploadPath + "\\" + fileDTO.getUuid() + "_" + fileDTO.getFileName());

        return ResponseEntity.ok(null);
    }
}