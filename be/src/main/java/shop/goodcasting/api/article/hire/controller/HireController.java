package shop.goodcasting.api.article.hire.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.goodcasting.api.article.hire.domain.Hire;
import shop.goodcasting.api.article.hire.domain.HireDTO;
import shop.goodcasting.api.article.hire.service.HireServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/hire")
public class HireController {
    private final HireServiceImpl service;

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody HireDTO hireDTO) {
        System.out.println("Hire DTO: " + hireDTO);
        System.out.println("Hire DTO actor name: " + hireDTO.getProducer());
        System.out.println("Hire DTO user username: " + hireDTO.getProducer().getUserVO());

        service.register(hireDTO);
        return ResponseEntity.ok(1L);
    }


}