package shop.goodcasting.api.file.service;

import shop.goodcasting.api.article.profile.domain.Profile;
import shop.goodcasting.api.article.profile.domain.ProfileDTO;
import shop.goodcasting.api.file.domain.FileDTO;
import shop.goodcasting.api.file.domain.FileVO;

import java.io.File;
import java.util.List;

public interface FileService {
    void extractVideoThumbnail(File file) throws Exception;

    List<FileDTO> findFileListByProfileId(Long profileId);

    default FileDTO entity2DtoFile(FileVO file) {
        return FileDTO.builder()
                .fileId(file.getFileId())
                .fileName(file.getFileName())
                .uuid(file.getUuid())
                .first(file.isFirst())
                .build();
    }

    default ProfileDTO entity2DtoProfile(Profile profile) {
        return ProfileDTO.builder()
                .profileId(profile.getProfileId())
                .confidence(profile.getConfidence())
                .contents(profile.getContents())
                .career(profile.getCareer())
                .resemble(profile.getResemble())
                .modDate(profile.getModDate())
                .regDate(profile.getRegDate())
                .build();
    }
}
