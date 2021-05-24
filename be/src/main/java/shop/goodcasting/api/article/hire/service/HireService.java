package shop.goodcasting.api.article.hire.service;

import shop.goodcasting.api.article.hire.domain.Hire;
import shop.goodcasting.api.article.hire.domain.HireDTO;
import shop.goodcasting.api.article.profile.domain.Profile;
import shop.goodcasting.api.article.profile.domain.ProfileDTO;
import shop.goodcasting.api.common.domain.PageRequestDTO;
import shop.goodcasting.api.common.domain.PageResultDTO;
import shop.goodcasting.api.file.domain.FileVO;
import shop.goodcasting.api.file.domain.FileDTO;
import shop.goodcasting.api.user.actor.domain.Actor;
import shop.goodcasting.api.user.producer.domain.Producer;
import shop.goodcasting.api.user.producer.domain.ProducerDTO;

import java.util.List;

public interface HireService {
    Long register(HireDTO hireDTO);
    HireDTO readHire(Long hireId);
    PageResultDTO<HireDTO, Object[]> getHireList(PageRequestDTO requestDTO);
    default Hire dto2Entity(HireDTO dto) {
        return Hire.builder()
                .hireId(dto.getHireId())
                .title(dto.getTitle())
                .project(dto.getProject())
                .contents(dto.getContents())
                .cast(dto.getCast())
                .filming(dto.getFilming())
                .guarantee(dto.getGuarantee())
                .personnel(dto.getPersonnel())
                .deadline(dto.getDeadline())
                .build();
    }

    default Hire dto2EntityAll(HireDTO hireDTO){
        return Hire.builder()
                .hireId(hireDTO.getHireId())
                .title(hireDTO.getTitle())
                .project(hireDTO.getProject())
                .contents(hireDTO.getContents())
                .cast(hireDTO.getCast())
                .filming(hireDTO.getFilming())
                .guarantee(hireDTO.getGuarantee())
                .personnel(hireDTO.getPersonnel())
                .deadline(hireDTO.getDeadline())
                .producer(Producer.builder()
                        .producerId(hireDTO.getProducer().getProducerId())
                        .build())
                .build();
    }

    default HireDTO entity2Dto(Hire entity) {
        return HireDTO.builder()
                .hireId(entity.getHireId())
                .title(entity.getTitle())
                .project(entity.getProject())
                .contents(entity.getContents())
                .cast(entity.getCast())
                .filming(entity.getFilming())
                .guarantee(entity.getGuarantee())
                .personnel(entity.getPersonnel())
                .deadline(entity.getDeadline())
                .build();
    }

    default HireDTO entity2DtoAll(Hire hire) {
        return HireDTO.builder()
                .hireId(hire.getHireId())
                .title(hire.getTitle())
                .project(hire.getProject())
                .contents(hire.getContents())
                .cast(hire.getCast())
                .filming(hire.getFilming())
                .guarantee(hire.getGuarantee())
                .personnel(hire.getPersonnel())
                .deadline(hire.getDeadline())
                .producer(ProducerDTO.builder()
                        .producerId(hire.getProducer().getProducerId())
                        .build())
                .build();
    }
    default HireDTO entity2DtoFiles(Hire hire,Producer producer, FileVO file) {
        return HireDTO.builder()
                .hireId(hire.getHireId())
                .title(hire.getTitle())
                .project(hire.getProject())
                .contents(hire.getContents())
                .cast(hire.getCast())
                .filming(hire.getFilming())
                .guarantee(hire.getGuarantee())
                .personnel(hire.getPersonnel())
                .deadline(hire.getDeadline())
                .fileName(file.getFileName())
                .fileUuid(file.getUuid())
                .producerName(producer.getName())
                .build();
    }
}