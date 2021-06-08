package shop.goodcasting.api.message.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import shop.goodcasting.api.message.domain.MessageActionType;
import shop.goodcasting.api.message.service.MessageServiceImpl;
import shop.goodcasting.api.message.domain.MessageDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/messages")
public class MessageController {

    private final MessageServiceImpl service;

    @PostMapping("/send")
    public ResponseEntity<MessageDTO> send(@RequestBody MessageDTO messageDTO) {
        return ResponseEntity.ok(service.send(messageDTO));
    }

    @GetMapping("/{messageId}")
    public ResponseEntity<Optional<MessageDTO>> readMessage(@PathVariable Long messageId){
        return ResponseEntity.ok(service.findById(messageId));
    }

    @GetMapping("/list/{receiver}")
    public ResponseEntity<List<MessageDTO>> messageList(@PathVariable Long receiver){
        return ResponseEntity.ok(service.findAllByReceiverId(receiver));
    }

    @GetMapping("/list/{actionType}/{receiver}")
    public ResponseEntity<List<MessageDTO>> messageTypeList(@PathVariable MessageActionType actionType, @PathVariable Long receiver){
        return ResponseEntity.ok(service.findByType(actionType));
    }

    @PutMapping("/update")
    public ResponseEntity<List<MessageDTO>> update(@RequestBody MessageDTO messageDTO) {
        return ResponseEntity.ok(service.update(messageDTO));
    }

    @DeleteMapping("/delete/{messageId}")
    public ResponseEntity<Long> delete(@PathVariable Long messageId){
        return ResponseEntity.ok(service.deleteById(messageId));
    }

}