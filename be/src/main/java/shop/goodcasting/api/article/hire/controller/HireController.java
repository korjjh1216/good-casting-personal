package shop.goodcasting.api.article.hire.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.goodcasting.api.article.hire.domain.HireDTO;
import shop.goodcasting.api.article.hire.domain.HireListDTO;
import shop.goodcasting.api.article.hire.service.HireServiceImpl;
import shop.goodcasting.api.common.domain.PageRequestDTO;
import shop.goodcasting.api.common.domain.PageResultDTO;


@Log4j2
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/hires")
public class HireController {
    private final HireServiceImpl hireService;

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody HireDTO hireDTO) {
        System.out.println("Hire DTO: " + hireDTO);
        hireService.register(hireDTO);
        return ResponseEntity.ok(1L);
    }

    @GetMapping("/detail/{hireId}")
    public ResponseEntity<HireDTO> hireDetail(@PathVariable Long hireId) {
        return ResponseEntity.ok(hireService.readHire(hireId));
    }

    @PostMapping("/list")
    public ResponseEntity<PageResultDTO<HireListDTO, Object[]>> hireList(@RequestBody PageRequestDTO pageRequest) {
        log.info("------------------" + pageRequest + "----------------------------------");

        log.info("==================================" + hireService.getHireList(pageRequest));

        return new ResponseEntity<>(hireService.getHireList(pageRequest), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Long> update(@RequestBody HireDTO hireDTO) {
        hireService.update(hireDTO);

        return new ResponseEntity<>(1L, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{hireId}")
    public ResponseEntity<Long> delete(@PathVariable Long hireId) {

        hireService.deleteHire(hireId);

        return new ResponseEntity<>(1L, HttpStatus.OK);
    }

}