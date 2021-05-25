package shop.goodcasting.api.common.serch.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import shop.goodcasting.api.common.serch.service.SerchServiceImpl;
import shop.goodcasting.api.file.domain.FileDTO;
import shop.goodcasting.api.file.service.FileServiceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
@Log4j2
@RestController
@CrossOrigin("*")
@RequestMapping("/serches")
@RequiredArgsConstructor
public class SerchController {
    private final FileServiceImpl service;
    private final SerchServiceImpl serchService;
    @Value("${shop.goodcast.upload.path}")
    private String uploadPath;


    @PostMapping("/upload")
    public ResponseEntity<List<FileDTO>> serch(MultipartFile uploadFile) {
        log.info("----------------------image serch()----------------------------------");

            String orgName = uploadFile.getOriginalFilename();
            String fileName = orgName.substring(orgName.lastIndexOf("//") + 1);
            String uuid = UUID.randomUUID().toString();
            String saveName = uploadPath + File.separator + uuid + "_" + fileName;
            Path savePath = Paths.get(saveName);

            log.info("register() - fileName: " + fileName);

            try {
                uploadFile.transferTo(savePath);

                log.info("image thumbnail extract");

                String thumbnailSaveName = uploadPath + File.separator + "s_" + uuid + "_" + fileName;

                File thumbnailFile = new File(thumbnailSaveName);

                Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 500, 500);

                serchService.extractCelebrity("s_" + uuid + "_" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
