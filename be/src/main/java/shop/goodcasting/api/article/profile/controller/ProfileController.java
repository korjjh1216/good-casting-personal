package shop.goodcasting.api.article.profile.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.goodcasting.api.article.profile.domain.ProfileDTO;
import shop.goodcasting.api.article.profile.service.ProfileServiceImpl;

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
}
