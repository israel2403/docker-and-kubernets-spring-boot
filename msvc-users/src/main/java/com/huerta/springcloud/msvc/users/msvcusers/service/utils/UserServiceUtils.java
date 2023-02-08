package com.huerta.springcloud.msvc.users.msvcusers.service.utils;

import com.huerta.springcloud.msvc.users.msvcusers.dto.UserDTO;
import com.huerta.springcloud.msvc.users.msvcusers.exceptions.UUIDBadRequestException;
import com.huerta.springcloud.msvc.users.msvcusers.models.entity.User;
import com.huerta.springcloud.msvc.users.msvcusers.utils.ValidateUUID;
import java.util.UUID;

public interface UserServiceUtils {
  public static UserDTO fromUserToUserDTO(final User user) {
    return UserDTO
      .builder()
      .id(user.getId())
      .name(user.getName())
      .email(user.getEmail())
      .password(user.getPassword())
      .build();
  }

  public static UUID fromStringToUUID(final String id) {
    if (!ValidateUUID.isValidUUID(id)) throw new UUIDBadRequestException(
      "UUID: " + id + ", is not valid."
    );
    return UUID.fromString(id);
  }
}
