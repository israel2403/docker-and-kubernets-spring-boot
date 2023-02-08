package com.huerta.springcloud.msvc.courses.msvccourses.dto;

import com.huerta.springcloud.msvc.courses.msvccourses.entity.UsersCourses;
import com.huerta.springcloud.msvc.courses.msvccourses.feing.models.dto.UserDTO;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CourseWithUsersDTO {

  private UUID id;

  private String name;

  private Set<UsersCourses> usersCourses;

  private List<UserDTO> userDTOs;
}
