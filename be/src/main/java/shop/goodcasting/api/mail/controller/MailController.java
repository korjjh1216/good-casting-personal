package shop.goodcasting.api.mail.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.goodcasting.api.mail.domain.MailDTO;
import shop.goodcasting.api.mail.service.MailServiceImpl;

@Log
@RestController
@RequestMapping("/mails")
@RequiredArgsConstructor
public class MailController {

    private final MailServiceImpl service;

    @PostMapping("/send")
    public ResponseEntity<MailDTO> sendMail(@RequestBody MailDTO mailDTO){
        log.info("mail");
        return ResponseEntity.ok(service.txtMailSend(mailDTO));
    }
}