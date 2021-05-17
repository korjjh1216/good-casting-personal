package shop.goodcasting.api.file.video.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import shop.goodcasting.api.file.video.service.VideoServiceImpl;

@RestController
@RequiredArgsConstructor
public class VideoController {
    private final VideoServiceImpl service;
}
