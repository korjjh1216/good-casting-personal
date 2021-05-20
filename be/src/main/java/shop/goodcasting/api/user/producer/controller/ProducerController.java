package shop.goodcasting.api.user.producer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.goodcasting.api.user.producer.domain.Producer;
import shop.goodcasting.api.user.producer.domain.ProducerDTO;
import shop.goodcasting.api.user.producer.repository.ProducerRepository;
import shop.goodcasting.api.user.producer.service.ProducerServiceImpl;


@Log
@RestController
@RequiredArgsConstructor
@RequestMapping("/producers")
public class ProducerController {
    private final ProducerServiceImpl service;
    private final ProducerRepository repo;

    @PutMapping("/update")
    public ResponseEntity<Producer> update(@RequestBody Producer producer){
        return ResponseEntity.ok(repo.save(producer));
    }


}
