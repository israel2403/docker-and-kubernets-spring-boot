package com.huerta.springcloud.msvc.users.msvcusers.repository.user;

import java.util.UUID;

import com.huerta.springcloud.msvc.users.msvcusers.models.entity.User;

public interface ICustomUser {
  User update(UUID id, User user);
}
