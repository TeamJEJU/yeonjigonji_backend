package com.JEJU.yeonjigonji_backend.repository;

import com.JEJU.yeonjigonji_backend.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email); // 중복 회원 검사를 위해
}