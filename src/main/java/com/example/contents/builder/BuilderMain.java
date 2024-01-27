package com.example.contents.builder;

public class BuilderMain {
  public static void main(String[] args) {
    // User에는 총 8개의 필드가 있고,
    // 전체 필드를 받아서 생성해주는 생성자가 있을 때,
    // username, email, firstName, lastName만 넣어서 초기화를 하고 싶다면?
    // fishMinkyu, e951219@naver.com, minkyu, eo
/*    User newUser = new User(
      null,
      "fishMinkyu",
      null,
      "e951219@naver.com",
      null,
      "minkyu",
      "eo",
      "null"
    );
*/

/*    // javaBean
    User newUser2 = new User();
    newUser2.setUsername("fishMinkyu");
    newUser2.setEmail("e951219@naver.com");
    // ...
*/

/*    User.UserBuilder userBuilder = new User.UserBuilder();
    // UserBuilder
    User.UserBuilder a = userBuilder.id(1L);
    User.UserBuilder b = a.username("fishMinkyu");
*/

    // 10:14분 설명 놓침
    // Builder pattern은 메서드 체이닝으로 동작한다.
    User newUser = User.builder()
      .username("fishMinkyu")
      .email("e951219@naver.com")
      .firstName("minkyu")
      .lastName("Eo")
      .build();

  }
}
