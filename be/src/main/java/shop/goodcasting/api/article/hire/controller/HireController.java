package shop.goodcasting.api.article.hire.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.goodcasting.api.article.hire.domain.HireDTO;
import shop.goodcasting.api.article.hire.domain.HireListDTO;
import shop.goodcasting.api.article.hire.domain.HirePageRequestDTO;
import shop.goodcasting.api.article.hire.domain.HirePageResultDTO;
import shop.goodcasting.api.article.hire.service.HireServiceImpl;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins ="*", allowedHeaders = "*")
@RequestMapping("/hires")
public class HireController {
    private final HireServiceImpl hireService;

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody HireDTO hireDTO) {
        hireService.register(hireDTO);
        return ResponseEntity.ok(1L);
    }

    @GetMapping("/detail/{hireId}")
    public ResponseEntity<HireDTO> hireDetail(@PathVariable Long hireId) {
        return ResponseEntity.ok(hireService.readHire(hireId));
    }

    @PostMapping("/list")
    public ResponseEntity<HirePageResultDTO<HireListDTO, Object[]>> hireList(@RequestBody HirePageRequestDTO pageRequest) {
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