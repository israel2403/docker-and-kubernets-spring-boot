package com.huerta.springcloud.msvc.courses.msvccourses.dto;

import com.huerta.springcloud.msvc.courses.msvccourses.entity.UsersCourses;
import java.util.Set;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CourseDTO {

  private UUID id;

  private String name;

  private Set<UsersCourses> usersCourses;
}
