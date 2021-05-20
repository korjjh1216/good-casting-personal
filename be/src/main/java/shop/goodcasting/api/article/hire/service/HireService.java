package shop.goodcasting.api.article.hire.service;

import shop.goodcasting.api.article.hire.domain.Hire;
import shop.goodcasting.api.article.hire.domain.HireDTO;
import shop.goodcasting.api.file.domain.FileVO;
import shop.goodcasting.api.file.domain.FileDTO;
import shop.goodcasting.api.user.producer.domain.Producer;
import shop.goodcasting.api.user.producer.domain.ProducerDTO;

public interface HireService {
    Long register(HireDTO hireDTO);

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
                .producer(dto.getProducer())
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
                .producer(entity.getProducer())
                .build();
    }
}