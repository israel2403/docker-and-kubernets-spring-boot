package com.huerta.springcloud.msvc.users.msvcusers.controller;

import com.huerta.springcloud.msvc.users.msvcusers.dto.UserDTO;
import com.huerta.springcloud.msvc.users.msvcusers.form.UserForm;
import com.huerta.springcloud.msvc.users.msvcusers.service.UserSevice;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

  private final UserSevice userSevice;

  @GetMapping
  public ResponseEntity<List<UserDTO>> getAll() {
    return new ResponseEntity<List<UserDTO>>(
      this.userSevice.getAll(),
      HttpStatus.OK
    );
  }

  @GetMapping(value = "{id}")
  public ResponseEntity<UserDTO> getById(@PathVariable final String id) {
    return new ResponseEntity<UserDTO>(
      this.userSevice.getById(id),
      HttpStatus.OK
    );
  }

  @PostMapping
  public ResponseEntity<UserDTO> save(@RequestBody @Valid final UserForm user) {
    return new ResponseEntity<UserDTO>(
      this.userSevice.save(user),
      HttpStatus.OK
    );
  }

  @PutMapping(value = "{id}")
  public ResponseEntity<UserDTO> update(
    @PathVariable final String id,
    @Valid @RequestBody final UserForm user
  ) {
    return new ResponseEntity<UserDTO>(
      this.userSevice.update(id, user),
      HttpStatus.OK
    );
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteById(
    @PathVariable(value = "id") final String id
  ) {
    this.userSevice.delete(id);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("ids")
  public ResponseEntity<List<UserDTO>> getByIds(
    @RequestParam final List<String> ids
  ) {
    return ResponseEntity.ok(this.userSevice.getByIds(ids));
  }
}
