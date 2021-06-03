package shop.goodcasting.api.article.profile.controller;

import lombok.RequiredArgsConstructor;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.goodcasting.api.article.profile.domain.ProfileDTO;
import shop.goodcasting.api.article.profile.domain.ProfileListDTO;
import shop.goodcasting.api.article.profile.domain.ProfilePageRequestDTO;
import shop.goodcasting.api.article.profile.domain.ProfilePageResultDTO;
import shop.goodcasting.api.article.profile.service.ProfileServiceImpl;

@Log4j2
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins ="*", allowedHeaders = "*")
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
    public ResponseEntity<ProfilePageResultDTO<ProfileListDTO, Object[]>> profileList(@RequestBody ProfilePageRequestDTO pageRequest) {
        log.info("------------------------------" + pageRequest + "----------------------------------------------------");

        return new ResponseEntity<>(profileService.getProfileList(pageRequest), HttpStatus.OK);
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

}

