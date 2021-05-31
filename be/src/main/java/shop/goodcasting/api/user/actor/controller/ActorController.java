package shop.goodcasting.api.user.actor.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.goodcasting.api.user.actor.domain.Actor;
import shop.goodcasting.api.user.actor.domain.ActorDTO;
import shop.goodcasting.api.user.actor.repository.ActorRepository;
import shop.goodcasting.api.user.actor.service.ActorServiceImpl;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/actors")
public class ActorController {

    private final ActorServiceImpl actorService;

    @GetMapping("/list")
    public ResponseEntity<List<Actor>> actorList(){
        return ResponseEntity.ok(actorService.findAll());
    }

    @GetMapping("/myPage/{actorId}")
    public ResponseEntity<ActorDTO> myPage(@PathVariable Long actorId){
        return ResponseEntity.ok(actorService.findById(actorId));
    }

    @PostMapping("/info")
    public ResponseEntity<ActorDTO> moreDetail(@RequestBody ActorDTO actorDTO){
        return ResponseEntity.ok(actorService.moreDetail(actorDTO));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Long> delete(@RequestBody ActorDTO actorDTO){
        return ResponseEntity.ok(actorService.delete(actorDTO));
    }
}