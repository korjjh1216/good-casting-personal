package shop.goodcasting.api.common.crawl;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.goodcasting.api.article.hire.domain.Hire;
import shop.goodcasting.api.user.actor.domain.Actor;

import java.io.IOException;
import java.util.List;

@RestController
@Log
@RequiredArgsConstructor
@RequestMapping("/crawl")
public class CrawlController {
    private final CrawlServiceImpl service;

    @GetMapping("/actor")
    public ResponseEntity<List<Actor>> bugs() throws IOException {
        return ResponseEntity.ok(service.nomalCrawl());
    }

}