package com.huerta.springcloud.msvc.users.msvcusers.utils;

import java.util.regex.Pattern;

public class ValidateUUID {

  private static final Pattern UUID_REGEX_PATTERN = Pattern.compile(
    "^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$"
  );

  public static boolean isValidUUID(String str) {
    if (str == null) {
      return false;
    }
    return UUID_REGEX_PATTERN.matcher(str).matches();
  }
}
