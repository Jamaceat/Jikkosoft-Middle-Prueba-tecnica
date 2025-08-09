package co.com.johan.biblio.gestion_biblioteca.members.controllers;

import java.lang.reflect.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.johan.biblio.gestion_biblioteca.dtos.response.GeneralResponse;
import co.com.johan.biblio.gestion_biblioteca.dtos.response.Response;
import co.com.johan.biblio.gestion_biblioteca.members.dtos.request.LoginRequestR;
import co.com.johan.biblio.gestion_biblioteca.members.dtos.request.RegisterMemberR;
import co.com.johan.biblio.gestion_biblioteca.members.entities.MemberEntity;
import co.com.johan.biblio.gestion_biblioteca.members.services.MemberService;


@RestController
@RequestMapping(value = "/members", produces = MediaType.APPLICATION_JSON_VALUE)
public class MemberController {
    
    @Autowired
    private MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<GeneralResponse> registerMember(@RequestBody RegisterMemberR memberRequest) {
        MemberEntity member=  memberService.registerMember(memberRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response<>(member,HttpStatus.CREATED.toString(),"Miembro creado exitosamente"));
    }
    
    @PostMapping("/login")
    public ResponseEntity<GeneralResponse> memberLogin(@RequestBody LoginRequestR loginRequest) {

        String jwt= memberService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new Response<>(jwt,HttpStatus.OK.toString(),"Miembro logueado exitosamente"));
    }
    



}
