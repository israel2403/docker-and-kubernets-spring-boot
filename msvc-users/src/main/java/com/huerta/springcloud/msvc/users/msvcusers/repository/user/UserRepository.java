package com.huerta.springcloud.msvc.users.msvcusers.repository.user;

import com.huerta.springcloud.msvc.users.msvcusers.models.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID>, ICustomUser {
  Optional<User> findByEmail(String email);
}
