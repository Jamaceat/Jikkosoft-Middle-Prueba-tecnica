package co.com.johan.biblio.gestion_biblioteca.members.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.johan.biblio.gestion_biblioteca.members.dtos.request.RegisterMemberR;


@RestController
@RequestMapping(value = "/members", produces = MediaType.APPLICATION_JSON_VALUE)
public class MemberController {
    

    @PostMapping("/signup")
    public ResponseEntity<Object> registerMember(@RequestBody RegisterMemberR member) {

        return ResponseEntity.noContent().build();
    }
    




}
