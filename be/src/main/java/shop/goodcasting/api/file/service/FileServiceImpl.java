package shop.goodcasting.api.file.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.goodcasting.api.file.repository.FileRepository;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository repo;
}
