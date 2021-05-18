package shop.goodcasting.api.article.hire.service;

import shop.goodcasting.api.article.hire.domain.Hire;
import shop.goodcasting.api.article.hire.domain.HireDTO;
import shop.goodcasting.api.file.domain.FileVO;
import shop.goodcasting.api.file.domain.FileDTO;
import shop.goodcasting.api.user.producer.domain.Producer;
import shop.goodcasting.api.user.producer.domain.ProducerDTO;

public interface HireService {
    Long register(HireDTO hireDTO);

    default Hire dto2Entity(HireDTO hireDTO) {
        Hire entity = Hire.builder()
                .hireId(hireDTO.getHireId())
                .title(hireDTO.getHireTitle())
                .contents(hireDTO.getContents())
                .cast(hireDTO.getCast())
                .filming(hireDTO.getFilming())
                .guarantee(hireDTO.getGuarantee())
                .personnel(hireDTO.getPersonnel())
                .deadline(hireDTO.getDeadline())
                .producer(hireDTO.getProducer())
                .build();
        return entity;
    }

    default Producer dto2EntityProducer(ProducerDTO producerDTO) {
        Producer producer = Producer.builder()
                .producerId(producerDTO.getProducerId())
                .email(producerDTO.getEmail())
                .agency(producerDTO.getAgency())
                .phone(producerDTO.getPhone())
                .position(producerDTO.getPosition())
                .build();
        return producer;
    }

    default FileVO dto2EntityFile(FileDTO fileDTO) {
        FileVO file = FileVO.builder()
                .fileId(fileDTO.getFileId())
                .fileName(fileDTO.getFileName())
                .uuid(fileDTO.getUuid())
                .first(fileDTO.isFirst())
                .hire(fileDTO.getHire())
                .build();
        return file;
    }

}