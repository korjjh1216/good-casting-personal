package shop.goodcasting.api.file;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shop.goodcasting.api.file.domain.FileDTO;
import shop.goodcasting.api.file.repository.FileRepository;
import shop.goodcasting.api.file.service.FileService;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class FileTest implements FileService {
    @Autowired
    private FileRepository fileRepo;

    @Test
    public void test() {
        fileRepo.findAll().stream().map(entity -> entity2Dto(entity)).collect(Collectors.toList());
    }

    @Override
    public void extractVideoThumbnail(File file) throws Exception {

    }

}