package co.com.johan.biblio.gestion_biblioteca.members.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.johan.biblio.gestion_biblioteca.members.dtos.request.RegisterMemberR;
import co.com.johan.biblio.gestion_biblioteca.members.entities.MemberEntity;
import co.com.johan.biblio.gestion_biblioteca.members.repository.MemberRepository;
import co.com.johan.biblio.gestion_biblioteca.members.services.MemberService;
import co.com.johan.biblio.gestion_biblioteca.utils.mappers.MemberMapper;
import jakarta.transaction.Transactional;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private MemberRepository memberRepository;


    @Transactional
    @Override
    public MemberEntity registerMember(RegisterMemberR registerMemberR) {

        MemberEntity member=memberMapper.memberPrepareEntity(registerMemberR);
        member=memberRepository.save(member);
        return member;
    }
    
}
