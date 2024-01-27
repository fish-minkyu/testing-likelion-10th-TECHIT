package com.example.contents;

import com.example.contents.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {
  @Mock
  private UserService userService; // 실제 데이터가 아님

  // 단위 테스트에서만 사용할 UserService 객체 만들기
  @InjectMocks
  private UserController userController;

  // MockMvc
  // HTTP Method를 수행했다고 가정
  private MockMvc mockMvc;

  // 이 테스트 클래스의 개별 클래스 전에 실행할 코드
  @BeforeEach
  public void beforeEach() {
    // 그냥 이렇게 쓰는구나 끄덕 끄덕하고 넘어가기
    mockMvc = MockMvcBuilders
      .standaloneSetup(userController)
      .build();
  }

  @Test
  @DisplayName("UserDto를 표현한 JSON 요청을 보내면 UserDto를 표현한 JSON을 응답")
  public void testCreate() throws Exception {
    // given
    // userService.createUser 정의
    String username = "minkyu";
    UserDto requestDto = new UserDto(
      null, username, null, null, null, null
    );
    UserDto responseDto = new UserDto(
      1L, username, null, null,null, null
    );
    when(userService.create(any()))
      .thenReturn(responseDto);

    // when
    // perform: HTTP 요청이 보내졌다고 가정
    ResultActions result = mockMvc.perform(
      // post 요청을 보낸다.
      post("/user")
        // 이 요청의 Body는 requestDto를 JSON으로 변환한 것
        .content(JsonUtil.toJson(requestDto))
        // 이 요청의 Body는 JSON이라고 생각하고 이해할 것
        .contentType(MediaType.APPLICATION_JSON)
    );


    // then
    // 응답의 코드가 2xx
    // 내용이 JSON
    // username이 변화하지 않았다.
    // id가 null이 아니다.
    result.andExpectAll(  // andExpectAll: 내가 기대하는 요구사항을 전부 다 만족시켜야 한다.
      // 2xx번대(성공) 상태코드
      status().is2xxSuccessful(),
      // JSON 응답을 주었다.
      content().contentType(MediaType.APPLICATION_JSON),
      // JSON에 username이라는 값이 전달된 username과 동일하다.
      jsonPath("$.username", is(username)), // $는 나 자신, 오브젝트를 의미한다.
      // id는 null이 아니다.
      jsonPath("$.id", notNullValue())
    );
  }
}
