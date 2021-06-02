package shop.goodcasting.api.apply.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.goodcasting.api.apply.domain.ApplyDTO;
import shop.goodcasting.api.apply.domain.ApplyListDTO;
import shop.goodcasting.api.apply.service.ApplyServiceImpl;
import shop.goodcasting.api.common.domain.PageRequestDTO;
import shop.goodcasting.api.common.domain.PageResultDTO;

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
    public ResponseEntity<PageResultDTO<ApplyListDTO,Object[]>> applicantList(@RequestBody PageRequestDTO pageRequest){
        log.info("------------------------------" + pageRequest + "----------------------------------------------------");

        return new ResponseEntity<>(applyService.getApplicantList(pageRequest), HttpStatus.OK);
    }
}
