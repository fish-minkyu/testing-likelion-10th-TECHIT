package com.example.contents;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// Java로 Spring Boot를 실행했을 때
// Spring container가 정상적으로 작동하는지, Bean 객체가 정상적으로 만들어지나 하는 테스트
// Spring Boot Application이 잘 작동하는지 테스트하는 것이므로 그냥 두면 된다.
@SpringBootTest // Spring Boot가 정상적으로 돌아가는지 확인하는 테스트
class ContentsApplicationTests {

	@Test
	void contextLoads() {
	}
}
