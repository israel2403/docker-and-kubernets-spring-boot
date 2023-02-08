package com.huerta.springcloud.msvc.courses.msvccourses.utility;

import com.huerta.springcloud.msvc.courses.msvccourses.exeptions.UUIDBadRequestException;
import java.util.UUID;
import java.util.regex.Pattern;

public interface UUIDUtility {
  public static final Pattern UUID_REGEX_PATTERN = Pattern.compile(
    "^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$"
  );

  static boolean isValidUUID(String str) {
    if (str == null) {
      return false;
    }
    return UUID_REGEX_PATTERN.matcher(str).matches();
  }

  static UUID createUUUID(final String id) {
    if (!UUIDUtility.isValidUUID(id)) {
      throw new UUIDBadRequestException("UUID:" + id + ", is not valid.");
    }
    return UUID.fromString(id);
  }
}
