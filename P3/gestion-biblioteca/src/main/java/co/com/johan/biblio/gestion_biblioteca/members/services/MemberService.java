package co.com.johan.biblio.gestion_biblioteca.members.services;

import co.com.johan.biblio.gestion_biblioteca.members.dtos.request.LoginRequestR;
import co.com.johan.biblio.gestion_biblioteca.members.dtos.request.RegisterMemberR;
import co.com.johan.biblio.gestion_biblioteca.members.entities.MemberEntity;

public interface MemberService {
    
    public MemberEntity registerMember(RegisterMemberR registerMemberR) throws Exception;

    public String login(LoginRequestR loginRequest);

}
