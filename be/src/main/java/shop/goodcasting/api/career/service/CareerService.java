package shop.goodcasting.api.career.service;

import org.springframework.stereotype.Service;
import shop.goodcasting.api.article.profile.domain.Profile;
import shop.goodcasting.api.article.profile.domain.ProfileDTO;
import shop.goodcasting.api.career.domain.Career;
import shop.goodcasting.api.career.domain.CareerDTO;


public interface CareerService {

    default Career dto2Entity(CareerDTO careerDTO) {
        return Career.builder()
                .careerId(careerDTO.getCareerId())
                .gerne(careerDTO.getGerne())
                .title(careerDTO.getTitle())
                .contents(careerDTO.getContents())
                .year(careerDTO.getYear())
                .build();
    }

    default Career dto2EntityAll(CareerDTO careerDTO) {
        return Career.builder()
                .careerId(careerDTO.getCareerId())
                .gerne(careerDTO.getGerne())
                .title(careerDTO.getTitle())
                .contents(careerDTO.getContents())
                .year(careerDTO.getYear())
                .profile(Profile.builder()
                        .profileId(careerDTO.getProfile().getProfileId())
                        .build())
                .build();
    }

    default CareerDTO entity2Dto(Career career) {
        return CareerDTO.builder()
                .careerId(career.getCareerId())
                .gerne(career.getGerne())
                .title(career.getTitle())
                .contents(career.getContents())
                .year(career.getYear())
                .build();
    }

    default CareerDTO entity2DtoAll(Career career) {
        return CareerDTO.builder()
                .careerId(career.getCareerId())
                .gerne(career.getGerne())
                .title(career.getTitle())
                .contents(career.getContents())
                .year(career.getYear())
                .profile(ProfileDTO.builder()
                        .profileId(career.getProfile().getProfileId())
                        .build())
                .build();
    }
}
