package co.com.johan.biblio.gestion_biblioteca.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import co.com.johan.biblio.gestion_biblioteca.members.entities.MemberEntity;
import co.com.johan.biblio.gestion_biblioteca.members.entities.RoleEntity;
import co.com.johan.biblio.gestion_biblioteca.members.repository.MemberRepository;

public class BibGestUserDetailService  implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName, password;
        List<GrantedAuthority> authorities;
        Optional<MemberEntity> customerOptional = memberRepository.findByEmail(username);
        if (customerOptional.isEmpty()) {
            throw new UsernameNotFoundException("User details not found for the user : " + username);
        } else {
            MemberEntity customer = customerOptional.get();
            userName = customer.getEmail();
            password = customer.getPassword();
            authorities = new ArrayList<>(customer.getRoles().stream().map(RoleEntity::getRolName).map(SimpleGrantedAuthority::new).toList());

        }
        return new User(userName, password, authorities);
    }
}

