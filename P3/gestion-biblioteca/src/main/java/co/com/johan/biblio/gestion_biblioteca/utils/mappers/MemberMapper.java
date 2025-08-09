package co.com.johan.biblio.gestion_biblioteca.utils.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;

import co.com.johan.biblio.gestion_biblioteca.constants.RoleEnum;
import co.com.johan.biblio.gestion_biblioteca.exceptions.e4xx.InvalidRolesException;
import co.com.johan.biblio.gestion_biblioteca.members.dtos.request.RegisterMemberR;
import co.com.johan.biblio.gestion_biblioteca.members.entities.MemberEntity;
import co.com.johan.biblio.gestion_biblioteca.members.entities.RoleEntity;
import co.com.johan.biblio.gestion_biblioteca.members.repository.RoleRepository;

@Mapper(componentModel = "spring", uses = {RoleRepository.class})
public abstract class MemberMapper {
    
    @Autowired
    private GrantedAuthorityDefaults grantedAuthorityDefaults;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;



    @Mapping(target = "password",source = "registerMemberR.password",qualifiedByName = "passwordEncoder")
    @Mapping(target = "roles",source = "registerMemberR.roles",qualifiedByName = "StringToRoles")
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "signUpDate",ignore = true)
    @Mapping(target = "updateDate",ignore = true)
    public abstract MemberEntity memberPrepareEntity(RegisterMemberR registerMemberR); 

    @Named("StringToRoles")
    protected List<RoleEntity> SearchRol(List<RoleEnum> roleEntities) {
        String prefix=grantedAuthorityDefaults.getRolePrefix();
        List<String> formattedRoles = roleEntities.stream()
        .map(role -> prefix + role)
        .collect(Collectors.toList());

        List<RoleEntity> roles=roleRepository.findByRolNameIn(formattedRoles);
        
        if(roles.size() != formattedRoles.size()){
            throw new InvalidRolesException("Uno o más de los roles especificados no existen en el sistema.");
        }
        return roles;
    }

    @Named("passwordEncoder")
    protected String passwordEncoder(String password){
        return passwordEncoder.encode(password);
    }

    
}
