package com.huerta.springcloud.msvc.courses.msvccourses.controller;

import com.huerta.springcloud.msvc.courses.msvccourses.dto.CourseDTO;
import com.huerta.springcloud.msvc.courses.msvccourses.dto.CourseWithUsersDTO;
import com.huerta.springcloud.msvc.courses.msvccourses.feing.models.dto.UserDTO;
import com.huerta.springcloud.msvc.courses.msvccourses.feing.models.form.UserForm;
import com.huerta.springcloud.msvc.courses.msvccourses.form.CourseForm;
import com.huerta.springcloud.msvc.courses.msvccourses.service.CourseService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("courses")
@RequiredArgsConstructor
public class CourseController {

  private final CourseService courseService;

  @GetMapping
  public ResponseEntity<List<CourseDTO>> getAll() {
    return new ResponseEntity<List<CourseDTO>>(
      courseService.getAll(),
      HttpStatus.OK
    );
  }

  @GetMapping(value = "{id}")
  public ResponseEntity<CourseWithUsersDTO> getById(
    @PathVariable final String id
  ) {
    return new ResponseEntity<>(
      this.courseService.byIdWithUsers(id),
      HttpStatus.OK
    );
  }

  @PostMapping
  public ResponseEntity<CourseDTO> save(
    @Valid @RequestBody final CourseForm course
  ) {
    return new ResponseEntity<CourseDTO>(
      this.courseService.save(course),
      HttpStatus.OK
    );
  }

  @PutMapping("{id}")
  ResponseEntity<CourseDTO> updateById(
    @PathVariable final String id,
    @Valid @RequestBody final CourseForm courseForm
  ) {
    return new ResponseEntity<CourseDTO>(
      this.courseService.updateById(id, courseForm),
      HttpStatus.OK
    );
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteById(
    @PathVariable(value = "id") final String id
  ) {
    this.courseService.delete(id);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("users/{id}")
  public ResponseEntity<Void> deleteByUserId(
    @PathVariable(value = "id") final String id
  ) {
    this.courseService.deleteUserById(id);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{courseId}/add-user/{userId}")
  public ResponseEntity<UserDTO> addUserToCourse(
    @PathVariable(value = "courseId") final String CourseId,
    @PathVariable(value = "userId") final String userId
  ) {
    final UserDTO userDTO =
      this.courseService.addUserToCourse(userId, CourseId);
    return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
  }

  @PostMapping("/{courseId}/create-user")
  public ResponseEntity<UserDTO> createUserAndAddToCourse(
    @PathVariable(value = "courseId") final String courseId,
    @RequestBody final UserForm userForm
  ) {
    final UserDTO userDTO =
      this.courseService.createUserAndAsignToCourse(userForm, courseId);
    return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
  }

  @DeleteMapping("/{courseId}/delete-user/{userId}")
  public ResponseEntity<UserDTO> deleteUserFromCourse(
    @PathVariable(value = "courseId") final String CourseId,
    @PathVariable(value = "userId") final String userId
  ) {
    final UserDTO userDTO =
      this.courseService.dissociateAUserFromACourse(userId, CourseId);
    return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
  }
}
