package shop.goodcasting.api.apply.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.goodcasting.api.apply.domain.ApplyDTO;
import shop.goodcasting.api.apply.domain.ApplyListDTO;
import shop.goodcasting.api.apply.domain.ApplyPageRequestDTO;
import shop.goodcasting.api.apply.domain.ApplyPageResultDTO;
import shop.goodcasting.api.apply.service.ApplyServiceImpl;

import java.util.List;

@Log
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins ="*", allowedHeaders = "*")
@RequestMapping("/applies")
public class ApplyController {

    private final ApplyServiceImpl applyService;

    @PostMapping("/doApply")
    public ResponseEntity<ApplyDTO> doApply(@RequestBody ApplyDTO applyDTO){
        return ResponseEntity.ok(applyService.apply(applyDTO));
    }

    @GetMapping("/{hireId}")
    public ResponseEntity<List<ApplyDTO>> applyList(@PathVariable Long hireId){
        return ResponseEntity.ok(applyService.findAllByHireId(hireId));
    }

    @PostMapping("/list")
    public ResponseEntity<ApplyPageResultDTO<ApplyListDTO,Object[]>> applicantList(@RequestBody ApplyPageRequestDTO pageRequest){
        log.info("------------------------------" + pageRequest + "----------------------------------------------------");

        return new ResponseEntity<>(applyService.getApplicantList(pageRequest), HttpStatus.OK);
    }
}
