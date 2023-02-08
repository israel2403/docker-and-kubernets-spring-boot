package com.huerta.springcloud.msvc.courses.msvccourses.utility;

import com.huerta.springcloud.msvc.courses.msvccourses.dto.CourseWithUsersDTO;
import com.huerta.springcloud.msvc.courses.msvccourses.entity.Course;
import com.huerta.springcloud.msvc.courses.msvccourses.entity.UsersCourses;
import com.huerta.springcloud.msvc.courses.msvccourses.feing.models.dto.UserDTO;
import java.util.List;

public interface MapUtitliy {
  static String getIDFromUsersCourses(final UsersCourses usersCourses) {
    return usersCourses.getUserId().toString();
  }

  static CourseWithUsersDTO fromCourseToCourseWithUsersDTO(
    final Course course,
    final List<UserDTO> userDtos
  ) {
    return CourseWithUsersDTO
      .builder()
      .id(course.getId())
      .name(course.getName())
      .usersCourses(course.getUsersCourse())
      .userDTOs(userDtos)
      .build();
  }
}
