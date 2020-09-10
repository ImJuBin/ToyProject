package com.example.toyproject.service;

import com.example.toyproject.domain.Member;
import com.example.toyproject.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository repository ;

    @BeforeEach
    public void beforeEach(){
        repository = new MemoryMemberRepository();
        memberService = new MemberService(repository); // 의존성주입
    }

    @AfterEach
    public void afterEach(){ // 메서드 하나 테스트 끝날때마다 실행됨
        repository.clear();
    }

    @Test
    void join() {
        //given : 어떤데이터가 주어졌을때
        Member member = new Member();
        member.setName("spring");

        //when : ~일떄
        Long saveId = memberService.join(member);
        //then : 이래야된다
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복_회원_예외(){
        //given
        Member member = new Member();
        member.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/*        try {
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.!!");
        }*/
        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}