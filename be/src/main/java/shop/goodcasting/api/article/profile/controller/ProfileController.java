package shop.goodcasting.api.article.profile.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import shop.goodcasting.api.article.profile.service.ProfileServiceImpl;

@RestController
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileServiceImpl service;
}
