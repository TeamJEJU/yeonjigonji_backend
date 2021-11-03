package com.JEJU.yeonjigonji_backend.entity;


import com.JEJU.yeonjigonji_backend.constant.Gender;
import com.JEJU.yeonjigonji_backend.constant.Role;
import com.JEJU.yeonjigonji_backend.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members") //밑에서 1:n 관계 매핑 정렬 기준을 "member"으로 지정했기 때문에 복수형 사용
@Getter
@Setter
@ToString
public class Member extends BaseTimeEntity {
    @Id
    @Column(name = "member_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name; //이름

    private String password; //비밀번호

    @Column(unique = true)
    private String email; //이메일(아이디로 사용)

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender; //성별

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member") // member:like = 1:n 관계
    private List<LikeItem> likes = new ArrayList<>();


    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setName(memberFormDto.getName());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setEmail(memberFormDto.getEmail());
        member.setAge(memberFormDto.getAge());
        member.setGender(memberFormDto.getGender());
        member.setRole(Role.USER);
        return member;
    }
}
