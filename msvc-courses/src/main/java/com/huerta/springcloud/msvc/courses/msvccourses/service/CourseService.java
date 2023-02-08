package com.huerta.springcloud.msvc.courses.msvccourses.service;

import com.huerta.springcloud.msvc.courses.msvccourses.dto.CourseDTO;
import com.huerta.springcloud.msvc.courses.msvccourses.dto.CourseWithUsersDTO;
import com.huerta.springcloud.msvc.courses.msvccourses.entity.Course;
import com.huerta.springcloud.msvc.courses.msvccourses.entity.UsersCourses;
import com.huerta.springcloud.msvc.courses.msvccourses.exeptions.NotFoundException;
import com.huerta.springcloud.msvc.courses.msvccourses.feing.UserClientRest;
import com.huerta.springcloud.msvc.courses.msvccourses.feing.models.dto.UserDTO;
import com.huerta.springcloud.msvc.courses.msvccourses.feing.models.form.UserForm;
import com.huerta.springcloud.msvc.courses.msvccourses.form.CourseForm;
import com.huerta.springcloud.msvc.courses.msvccourses.repository.CourseRepository;
import com.huerta.springcloud.msvc.courses.msvccourses.repository.UsersCoursesRepository;
import com.huerta.springcloud.msvc.courses.msvccourses.utility.MapUtitliy;
import com.huerta.springcloud.msvc.courses.msvccourses.utility.UUIDUtility;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CourseService {

  private final CourseRepository courseRepository;

  private final UserClientRest userClientRest;

  private final UsersCoursesRepository usersCoursesRepository;

  @Transactional(readOnly = true)
  public List<CourseDTO> getAll() {
    return this.courseRepository.findAll()
      .stream()
      .map(CourseService::fromCourseToCourseDTO)
      .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public CourseDTO getById(final String id) {
    final UUID courseUUID = UUIDUtility.createUUUID(id);
    final Course courseById = getCourseById(courseUUID);
    return fromCourseToCourseDTO(courseById);
  }

  @Transactional
  public CourseDTO save(final CourseForm courseForm) {
    final Course courseToSave = new Course(courseForm.getName());
    this.courseRepository.save(courseToSave);
    return fromCourseToCourseDTO(courseToSave);
  }

  @Transactional
  public CourseDTO updateById(final String id, final CourseForm courseForm) {
    final UUID courseUUID = UUIDUtility.createUUUID(id);
    final Course courseById = getCourseById(courseUUID);

    courseById.setName(courseForm.getName());
    this.courseRepository.save(courseById);
    return fromCourseToCourseDTO(courseById);
  }

  @Transactional
  public void delete(final String id) {
    final UUID courseUUID = UUIDUtility.createUUUID(id);
    final Course courseById = getCourseById(courseUUID);
    this.courseRepository.delete(courseById);
  }

  @Transactional
  public UserDTO addUserToCourse(final String userId, final String courseId) {
    final UUID courseUUID = UUIDUtility.createUUUID(courseId);
    final Course courseById = this.getCourseById(courseUUID);
    final UserDTO userDTOById = this.userClientRest.getById(userId);
    final UsersCourses usersCourses = new UsersCourses(
      userDTOById.getId(),
      courseById.getId()
    );
    courseById.getUsersCourse().add(usersCourses);
    this.courseRepository.save(courseById);
    return userDTOById;
  }

  @Transactional
  public UserDTO createUserAndAsignToCourse(
    final UserForm userForm,
    final String courseId
  ) {
    final UUID courseUUID = UUIDUtility.createUUUID(courseId);
    final Course courseById = this.getCourseById(courseUUID);
    final UserDTO userDTOById = this.userClientRest.save(userForm);
    final UsersCourses usersCourses = new UsersCourses(
      userDTOById.getId(),
      courseById.getId()
    );
    courseById.getUsersCourse().add(usersCourses);
    this.courseRepository.save(courseById);
    return userDTOById;
  }

  @Transactional
  public UserDTO dissociateAUserFromACourse(
    final String userId,
    final String courseId
  ) {
    final UUID couUuid = UUIDUtility.createUUUID(courseId);
    // Verify that course exists
    final Course courseById = this.getCourseById(couUuid);
    // Verify that user exists
    final UserDTO userDTOById = this.userClientRest.getById(userId);
    final Set<UsersCourses> usersCourses =
      this.usersCoursesRepository.findByUserIdAndCourseId(
          userDTOById.getId(),
          courseById.getId()
        );
    this.usersCoursesRepository.deleteAll(usersCourses);
    return userDTOById;
  }

  @Transactional(readOnly = true)
  public CourseWithUsersDTO byIdWithUsers(final String courseId) {
    final UUID courseUUID = UUIDUtility.createUUUID(courseId);
    final Course courseById = getCourseById(courseUUID);
    final List<String> ids = courseById
      .getUsersCourse()
      .stream()
      .map(MapUtitliy::getIDFromUsersCourses)
      .collect(Collectors.toList());
    final List<UserDTO> userDTOs = userClientRest.getByIds(ids);
    return MapUtitliy.fromCourseToCourseWithUsersDTO(courseById, userDTOs);
  }

  @Transactional
  public void deleteUserById(final String userId) {
    final UUID userUUID = UUIDUtility.createUUUID(userId);
    this.usersCoursesRepository.deleteByUserId(userUUID);
  }

  private static CourseDTO fromCourseToCourseDTO(final Course course) {
    return CourseDTO
      .builder()
      .id(course.getId())
      .name(course.getName())
      .usersCourses(course.getUsersCourse())
      .build();
  }

  private Course getCourseById(final UUID id) {
    return this.courseRepository.findById(id)
      .orElseThrow(() ->
        new NotFoundException("Course with id: " + id + " not found.")
      );
  }
}
