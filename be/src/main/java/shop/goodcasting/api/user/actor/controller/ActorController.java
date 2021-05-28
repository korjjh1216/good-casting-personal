package shop.goodcasting.api.user.actor.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.goodcasting.api.user.actor.domain.Actor;
import shop.goodcasting.api.user.actor.domain.ActorDTO;
import shop.goodcasting.api.user.actor.repository.ActorRepository;
import shop.goodcasting.api.user.actor.service.ActorServiceImpl;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/actors")
public class ActorController {

    private final ActorServiceImpl service;
    private final ActorRepository repo;

    @GetMapping("/list")
    public ResponseEntity<List<Actor>> actorList(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/myPage/{actorId}")
    public ResponseEntity<ActorDTO> myPage(@PathVariable Long actorId){
        return ResponseEntity.ok(service.findById(actorId));
    }

    @PostMapping("/info")
    public ResponseEntity<ActorDTO> moreDetail(@RequestBody ActorDTO actorDTO){
        return ResponseEntity.ok(service.moreDetail(actorDTO));
    }

    @DeleteMapping("/delete/{actorId}")
    public ResponseEntity<Long> delete(@RequestBody ActorDTO actorDTO){
        return ResponseEntity.ok(service.delete(actorDTO));
    }
}