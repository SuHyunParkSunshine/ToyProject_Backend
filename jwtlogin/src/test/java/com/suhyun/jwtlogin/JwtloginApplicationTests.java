package com.suhyun.jwtlogin;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.suhyun.jwtlogin.domain.Member;
import com.suhyun.jwtlogin.persistence.MemberRepository;

@SpringBootTest
class JwtloginApplicationTests {

	@Autowired
	MemberRepository memRepo; 

	@Autowired
	BCryptPasswordEncoder encoder;
	
	//@Test
	void contextLoads() {

		// Member member = memRepo.findByUserEmail("qwer12@gmail.com");
		// System.out.println(member);

		Optional<Member> opt = memRepo.findById("qewre@naver.com");
		if(!opt.isPresent()) {
			System.out.println("No DATA");
			return;
		}
		Member m = opt.get();
		System.out.println("member: " + m);
	}

	@Test
	void test() {

		memRepo.save(Member.builder()
						.userEmail("qewre@naver.com")
						.userPassword(encoder.encode("0000"))
						.userAddress("부산광역시 금정구")
						.userNickname("지은")
						.role("ROLE_USER")
						.userPhoneNumber("010-1234-5678")
						.build());

	}



}
