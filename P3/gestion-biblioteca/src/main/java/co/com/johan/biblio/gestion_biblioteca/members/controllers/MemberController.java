package co.com.johan.biblio.gestion_biblioteca.members.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.johan.biblio.gestion_biblioteca.dtos.response.GeneralResponse;
import co.com.johan.biblio.gestion_biblioteca.members.dtos.request.LoginRequestR;
import co.com.johan.biblio.gestion_biblioteca.members.dtos.request.RegisterMemberR;
import co.com.johan.biblio.gestion_biblioteca.members.entities.MemberEntity;
import co.com.johan.biblio.gestion_biblioteca.members.services.MemberService;
import co.com.johan.biblio.gestion_biblioteca.utils.response.ResponseBuilder;


@RestController
@RequestMapping(value = "/members", produces = MediaType.APPLICATION_JSON_VALUE)
public class MemberController {
    
    @Autowired
    private MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<GeneralResponse> registerMember(@RequestBody RegisterMemberR memberRequest) throws Exception {
        MemberEntity member=  memberService.registerMember(memberRequest);
        return ResponseBuilder.of(member, HttpStatus.CREATED, "Miembro creado exitosamente");
    }
    
    @PostMapping("/login")
    public ResponseEntity<GeneralResponse> memberLogin(@RequestBody LoginRequestR loginRequest) {

        String jwt= memberService.login(loginRequest);
        return ResponseBuilder.of(jwt, HttpStatus.OK, "Miembro logueado exitosamente");
    }
    



}
