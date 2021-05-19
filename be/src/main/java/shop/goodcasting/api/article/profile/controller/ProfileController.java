package shop.goodcasting.api.article.profile.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.goodcasting.api.article.profile.domain.Profile;
import shop.goodcasting.api.article.profile.domain.ProfileDTO;
import shop.goodcasting.api.article.profile.service.ProfileServiceImpl;

import java.util.List;

@Log
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileServiceImpl service;

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody ProfileDTO profileDTO) {
        System.out.println("Profile DTO: " + profileDTO);
        System.out.println("Profile DTO actor name: " + profileDTO.getActor());
        System.out.println("Profile DTO user username: " + profileDTO.getActor().getUserVO());

        service.register(profileDTO);

        return ResponseEntity.ok(1L);
    }

    @GetMapping("/profile-detail/{profileId}")
    public ResponseEntity<ProfileDTO> profileDetail(@PathVariable Long profileId) {
        service.profileDetail(profileId);
        log.info(""+service.profileDetail(profileId));
        return ResponseEntity.ok(service.profileDetail(profileId));
    }

    @GetMapping("profile-list")
    public ResponseEntity<List<ProfileDTO>> profileList() {
        service.profileList();
        return ResponseEntity.ok(service.profileList());
    }
}
