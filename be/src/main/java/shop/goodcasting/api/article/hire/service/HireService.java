package shop.goodcasting.api.article.hire.service;

import shop.goodcasting.api.article.hire.domain.Hire;
import shop.goodcasting.api.article.hire.domain.HireDTO;
import shop.goodcasting.api.article.profile.domain.Profile;
import shop.goodcasting.api.article.profile.domain.ProfileDTO;
import shop.goodcasting.api.file.photo.domain.Photo;
import shop.goodcasting.api.file.photo.domain.PhotoDTO;
import shop.goodcasting.api.file.video.domain.Video;
import shop.goodcasting.api.file.video.domain.VideoDTO;
import shop.goodcasting.api.user.actor.domain.Actor;
import shop.goodcasting.api.user.actor.domain.ActorDTO;
import shop.goodcasting.api.user.producer.domain.Producer;
import shop.goodcasting.api.user.producer.domain.ProducerDTO;

import java.util.List;
import java.util.Optional;

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

    default Photo dto2EntityPhoto(PhotoDTO photoDTO) {
        Photo photo = Photo.builder()
                .photoId(photoDTO.getPhotoId())
                .fileName(photoDTO.getFileName())
                .uuid(photoDTO.getUuid())
                .first(photoDTO.isFirst())
                .hire(photoDTO.getHire())
                .build();
        return photo;
    }

    default Video dto2EntityVideo(VideoDTO videoDTO){
        Video video = Video.builder()
                .videoId(videoDTO.getVideoId())
                .fileName(videoDTO.getFileName())
                .uuid(videoDTO.getUuid())
                .first(videoDTO.isFirst())
                .hire(videoDTO.getHire())
                .build();
        return video;
    }
}
