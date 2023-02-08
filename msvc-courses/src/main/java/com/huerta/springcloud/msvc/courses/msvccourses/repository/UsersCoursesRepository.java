package com.huerta.springcloud.msvc.courses.msvccourses.repository;

import com.huerta.springcloud.msvc.courses.msvccourses.entity.UsersCourses;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersCoursesRepository
  extends JpaRepository<UsersCourses, UUID> {
  Set<UsersCourses> findByUserIdAndCourseId(UUID userId, UUID courseId);

  List<UsersCourses> deleteByUserId(final UUID userId);
}
