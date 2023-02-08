package com.huerta.springcloud.msvc.courses.msvccourses.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "users_courses")
@Getter
public class UsersCourses {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "users_course_id", updatable = false, nullable = false)
  private UUID users_course_id;

  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "user_id", unique = true, updatable = false, nullable = false)
  private UUID userId;

  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(
    name = "course_id",
    unique = true,
    updatable = false,
    nullable = false
  )
  private UUID courseId;

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof UsersCourses)) {
      return false;
    }
    UsersCourses o = (UsersCourses) obj;
    return this.userId != null && this.userId.equals(o.userId);
  }

  public UsersCourses() {}

  public UsersCourses(final UUID userId, final UUID courseId) {
    this.userId = userId;
    this.courseId = courseId;
  }
}
