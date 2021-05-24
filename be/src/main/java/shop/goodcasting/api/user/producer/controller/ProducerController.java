package shop.goodcasting.api.user.producer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.goodcasting.api.user.producer.domain.Producer;
import shop.goodcasting.api.user.producer.domain.ProducerDTO;
import shop.goodcasting.api.user.producer.repository.ProducerRepository;
import shop.goodcasting.api.user.producer.service.ProducerServiceImpl;

import java.util.List;
import java.util.Optional;


@Log
@RestController
@RequiredArgsConstructor
@RequestMapping("/producers")
public class ProducerController {
    private final ProducerServiceImpl service;

    @GetMapping("/list")
    public ResponseEntity<List<Producer>> actorList(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/myPage")
    public ResponseEntity<Optional<Producer>> myPage(@RequestBody Producer producer){
        return ResponseEntity.ok(service.findById(producer.getProducerId()));
    }

    @PostMapping("/info")
    public ResponseEntity<ProducerDTO> moreDetail(@RequestBody ProducerDTO producerDTO){
        return ResponseEntity.ok(service.moreDetail(producerDTO));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Long> delete(@RequestBody ProducerDTO producerDTO){
        return ResponseEntity.ok(service.delete(producerDTO));
    }

}