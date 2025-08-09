package co.com.johan.biblio.gestion_biblioteca.members.dtos.request;

import java.util.List;

import co.com.johan.biblio.gestion_biblioteca.constants.RoleEnum;

public record RegisterMemberR(String name,String email,String phone,String password, List<RoleEnum> roles) {
    

}
