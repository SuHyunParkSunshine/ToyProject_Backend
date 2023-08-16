package com.suhyun.jwtlogin.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suhyun.jwtlogin.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {   
}
