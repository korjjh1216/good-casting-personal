package shop.goodcasting.api.article.profile.controller;

import lombok.RequiredArgsConstructor;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import shop.goodcasting.api.article.hire.domain.HireDTO;
import shop.goodcasting.api.article.profile.domain.ProfileDTO;
import shop.goodcasting.api.article.profile.domain.ProfileListDTO;
import shop.goodcasting.api.article.profile.service.ProfileServiceImpl;
import shop.goodcasting.api.common.domain.PageRequestDTO;
import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/profiles")
public class ProfileController {
    private final ProfileServiceImpl profileService;


    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody ProfileDTO profileDTO) {
        System.out.println("Profile DTO: " + profileDTO);

        profileService.register(profileDTO);

        return ResponseEntity.ok(1L);
    }

    @GetMapping("/detail/{profileId}")
    public ResponseEntity<ProfileDTO> profileDetail(@PathVariable Long profileId) {
        return ResponseEntity.ok(profileService.readProfile(profileId));
    }


    @PostMapping("/list")
    public ResponseEntity<List<ProfileListDTO>> profileList(@RequestBody PageRequestDTO pageRequest) {
        log.info("------------------------------" + pageRequest + "----------------------------------------------------");

        return new ResponseEntity<>(profileService.getProfileList(pageRequest).getDtoList(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Long> update(@RequestBody ProfileDTO profileDTO) {
        profileService.update(profileDTO);

        return new ResponseEntity<>(1L, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{profileId}")
    public ResponseEntity<Long> delete(@PathVariable Long profileId) {

        profileService.deleteProfile(profileId);

        return new ResponseEntity<>(1L, HttpStatus.OK);
    }

    @PostMapping("/search-resemble")
    public ResponseEntity<List<ProfileListDTO>> searchResemble(@RequestBody PageRequestDTO pageRequest, MultipartFile uploadFile) {
        log.info("----------------------image search()----------------------------------");

        return new ResponseEntity<>(profileService.searchResemble(pageRequest, uploadFile).getDtoList(), HttpStatus.OK);
    }
}