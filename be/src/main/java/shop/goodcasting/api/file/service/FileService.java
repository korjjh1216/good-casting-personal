package shop.goodcasting.api.file.service;

import shop.goodcasting.api.article.profile.domain.Profile;
import shop.goodcasting.api.article.profile.domain.ProfileDTO;
import shop.goodcasting.api.file.domain.FileDTO;
import shop.goodcasting.api.file.domain.FileVO;

import java.io.File;
import java.util.List;

public interface FileService {
    void extractVideoThumbnail(File file) throws Exception;

//    List<FileDTO> findFileListByProfileId(Long profileId);

    default FileDTO entity2Dto(FileVO entity) {
        return FileDTO.builder()
                .fileId(entity.getFileId())
                .fileName(entity.getFileName())
                .uuid(entity.getUuid())
                .first(entity.isFirst())
                .build();
    }

    default FileDTO entity2DtoAll(FileVO entity) {
        return FileDTO.builder()
                .fileId(entity.getFileId())
                .fileName(entity.getFileName())
                .uuid(entity.getUuid())
                .first(entity.isFirst())
                .profile(ProfileDTO.builder()
                        .profileId(entity.getProfile().getProfileId())
                        .build())
                .build();
    }

    default FileVO dto2Entity(FileDTO dto) {
        return FileVO.builder()
                .fileId(dto.getFileId())
                .fileName(dto.getFileName())
                .uuid(dto.getUuid())
                .first(dto.isFirst())
                .build();
    }

    default FileVO dto2EntityAll(FileDTO dto) {
        return FileVO.builder()
                .fileId(dto.getFileId())
                .fileName(dto.getFileName())
                .uuid(dto.getUuid())
                .first(dto.isFirst())
                .profile(Profile.builder()
                        .profileId(dto.getProfile().getProfileId())
                        .build())
                .build();
    }
}