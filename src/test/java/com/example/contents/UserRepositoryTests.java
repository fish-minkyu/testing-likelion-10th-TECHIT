package com.example.contents;

import com.example.contents.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


// UserRepository의 단위 테스트를 위한 테스트들
// : 이미 만들어진 기능이 의도한데로 잘 동작하는지 위한 테스트 코드들을 작성하기 위한 곳

// @DataJpaTest
// : 테스트를 위해 UserRepository를 만들어줘야 하는데
// JPA와 연관된 애들만 Bean으로 만들어주는 어노테이션
@DataJpaTest
public class UserRepositoryTests { // UserRepositoryTests: 어떤 대상을 테스트할지에 대한 것을 이름으로 주는게 일반적
  @Autowired // 필드 기반 DI, Spring Boot가 관리하는 Bean이라고 명시해주는 어노테이션
  private UserRepository userRepository;

  // 사용자를 추가하는 테스트
  @Test
  @DisplayName("새로운 User 추가")
  public void testCreateUser() {
    // given - when - then
    // : 테스트의 가독성을 높이는 패턴 like Spring의 MVC 패턴

    // given - 테스트를 진행하기 위한 기본 조건을 만들어 두는 부분
    // : 내가 만들고자 하는 User 엔티티가 있는 상황에서
    String username = "minkyu";
    User user = new User(username, null, null, null);

    // when - 실제로 테스트를 진행하는 부분
    // userRepository.save(user)를 진행한다.
    User result = userRepository.save(user);

    // then - 내가 기대한대로 동작했는지 검증
    // userRepository.save()의 결과의 username이 본래 User의 username과 일치했는지
    // 13:28 설명 놓침
    assertEquals(username, result.getUsername()); // assertEquals가 실행되었을 때, 이 두 매개변수가 같지 않으면 이상하다.
    // userRepository.save()의 결과의 id가 null이 아닌지
    assertNotNull(result.getId());

    // +) assert문
    // : 테스트를 할 때, 내가 기대하고 있는 결과와 일치하는지 확인하는 코드를 "assert문"이라고 한다.
  }

  // 사용자를 추가하는데 실패하는 테스트
  // 두명의 사용자는 username이 겹치면 안되니까
  // 하나의 username을 가진 사람이 있다고 가정하고
  // 같은 username을 사용해서 user을 생성할 때는 실패해야 한다.
  @Test
  @DisplayName("새로운 User 추가 실패")
  public void testCreateUserFail() {
    // given - 어떤 특정 username을 가진 user가 이미 저장된 상황에서
    String username = "minkyu";
    User userGiven = new User(username, null, null, null);
    userRepository.save(userGiven);

    // when - 동일한 username을 가진 User를 저장하려고 하면
    User newUser = new User(username, null, null, null);

    // then - 실패한다.
    assertThrows(Exception.class, () -> userRepository.save(newUser));
  }

  // 사용자를 조회하는 테스트
  @Test
  @DisplayName("username으로 존재하는 사용자 조회")
  public void testReadUser() {
    // given
    // 내가 읽고자 하는 특정 username의 User가 데이터베이스에 저장된 이후의 상황에서
    String username = "minkyu";
    User userGiven = new User(username, null, null, null);
    userRepository.save(userGiven);

    // when
    // 해당하는 username 가지고 userRepository.findByUsername(username);의 결과를 받아오면
    Optional<User> optionalUser = userRepository.findByUsername(username);

    // then
    // 돌아온 결과 Optional.isPresent() == ture이고, (assertTrue)
    assertTrue(optionalUser.isPresent());
    // 돌아온 결과 Optional.get().getUsername == username이다.
    assertEquals(username, optionalUser.get().getUsername());
  }

  // 존재하지 않는 username을 가지고 조회하면 Optional.empty()가 반환된다.

  // 이미 존재하는 username을 검색해서 존재하는지 확인한다.

  // id를 가지고 User을 삭제한다.

  // (항상 userRepository가 잘 작동한다고 가정하고 작성하자)
}

// +) 테스트 코드가 에러가 나면 테스트 코드 수정이 아닌 소스코드를 수정하러 가야한다.