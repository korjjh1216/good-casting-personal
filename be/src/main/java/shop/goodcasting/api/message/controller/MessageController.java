package shop.goodcasting.api.message.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import shop.goodcasting.api.message.domain.Message;
import shop.goodcasting.api.message.domain.MessageActionType;
import shop.goodcasting.api.message.domain.MessageDTO;
import shop.goodcasting.api.message.service.MessageServiceImpl;

import java.util.List;

@Log
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/messages")
public class MessageController {

    private final MessageServiceImpl service;

//    @GetMapping("/list/{receiver}")
//    public ResponseEntity<List<MessageDTO>> messageList(@PathVariable Iterable<Long> receiver){
//        log.info("receiver : " + receiver);
//        return ResponseEntity.ok(service.findAllById(receiver));
//    }

    @GetMapping("/list/{receiver}")
    public ResponseEntity<List<MessageDTO>> messageList(@PathVariable Long receiver){
        log.info("receiver : " + receiver);
        return ResponseEntity.ok(service.findAllByReceiverId(receiver));
    }



    @GetMapping("/list/{actionType}/{receiver}")
    public ResponseEntity<List<MessageDTO>> messageTypeList(@PathVariable MessageActionType actionType, @PathVariable Long receiver){
        return ResponseEntity.ok(service.findByType(actionType));
    }

    @PostMapping("/send")
    public ResponseEntity<MessageDTO> send(@RequestBody MessageDTO messageDTO) {
        return ResponseEntity.ok(service.send(messageDTO));
    }

    @DeleteMapping("/delete/{messageId}")
    public ResponseEntity<Long> delete(@PathVariable Long messageId){
        return ResponseEntity.ok(service.deleteById(messageId));
    }

}