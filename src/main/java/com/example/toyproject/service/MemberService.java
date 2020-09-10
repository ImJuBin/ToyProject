package com.example.toyproject.service;

import com.example.toyproject.domain.Member;
import com.example.toyproject.repository.MemberRepository;
import com.example.toyproject.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    // 외부에서 MemberRepository객체를 넣어준다, (DI:의존성주입)
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /* 회원가입*/
    public Long join(Member member) {

        duplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void duplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }


    /*전체 회원조회*/
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /*아이디로 멤버 조회*/
    public Optional<Member> findOne(Long memeberId) {
        return memberRepository.findById(memeberId);
    }


}
