package shop.goodcasting.api.apply.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.goodcasting.api.apply.domain.ApplyDTO;
import shop.goodcasting.api.apply.service.ApplyServiceImpl;

import java.util.List;

@Log
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/applies")
public class ApplyController {

    private final ApplyServiceImpl service;

    @PostMapping("/doApply")
    public ResponseEntity<ApplyDTO> doApply(@RequestBody ApplyDTO applyDTO){
        return ResponseEntity.ok(service.apply(applyDTO));
    }

    @GetMapping("/{hireId}")
    public ResponseEntity<List<ApplyDTO>> applyList(@PathVariable Long hireId){
        return ResponseEntity.ok(service.findAllByHireId(hireId));
    }
}
