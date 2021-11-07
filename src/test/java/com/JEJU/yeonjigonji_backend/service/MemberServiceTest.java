package com.JEJU.yeonjigonji_backend.service;

import com.JEJU.yeonjigonji_backend.constant.Gender;
import com.JEJU.yeonjigonji_backend.dto.MemberFormDto;
import com.JEJU.yeonjigonji_backend.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember() {
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail("test@email.com");
        memberFormDto.setName("홍길동");
        memberFormDto.setPassword("1234");
        memberFormDto.setAge(23);
        memberFormDto.setGender(Gender.FEMALE);
        return Member.createMember(memberFormDto, passwordEncoder);
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveMemberTest() {
        Member member = createMember();
        Member saveMember = memberService.saveMember(member);

        assertEquals(member.getEmail(), saveMember.getEmail());
        assertEquals(member.getName(), saveMember.getName());
        assertEquals(member.getAge(), saveMember.getAge());
        assertEquals(member.getPassword(), saveMember.getPassword());
        assertEquals(member.getRole(), saveMember.getRole());
    }

    @Test
    @DisplayName("중복 회원 테스트")
    public void saveDuplicateMemeberTest() {
        Member m1 = createMember();
        Member m2 = createMember();
        memberService.saveMember(m1);

        Throwable e = assertThrows(IllegalStateException.class, () -> {
            memberService.saveMember(m2);
        });
        assertEquals("이미 가입된 회원입니다", e.getMessage());
    }
}