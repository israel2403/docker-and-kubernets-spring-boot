package com.huerta.springcloud.msvc.users.msvcusers.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.huerta.springcloud.msvc.users.msvcusers.models.entity.User;
import com.huerta.springcloud.msvc.users.msvcusers.repository.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserEntityRepositoryTests {

  @Autowired
  private UserRepository userRepository;

  // Junit for save employee operation
  @DisplayName("Junit for save employee operation")
  @Test
  public void givenUserEntity_whenSave_thenReturnSavedUser() {
    // given - precondition or setup
    final User user = new User(
      "Israel Huerta",
      "israelhf24@gmail.com",
      "1234567"
    );
    // when action or the behaviour that we are going to test
    final User userSaved = this.userRepository.save(user);
    // then - verify the output
    assertThat(userSaved).isNotNull();
  }
}
