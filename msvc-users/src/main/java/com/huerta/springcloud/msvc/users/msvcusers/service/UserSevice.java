package com.huerta.springcloud.msvc.users.msvcusers.service;

import com.huerta.springcloud.msvc.users.msvcusers.dto.UserDTO;
import com.huerta.springcloud.msvc.users.msvcusers.exceptions.DuplicatedEmailException;
import com.huerta.springcloud.msvc.users.msvcusers.exceptions.NotFoundException;
import com.huerta.springcloud.msvc.users.msvcusers.exceptions.UUIDBadRequestException;
import com.huerta.springcloud.msvc.users.msvcusers.feign.CourseClientRest;
import com.huerta.springcloud.msvc.users.msvcusers.form.UserForm;
import com.huerta.springcloud.msvc.users.msvcusers.models.entity.User;
import com.huerta.springcloud.msvc.users.msvcusers.repository.user.UserRepository;
import com.huerta.springcloud.msvc.users.msvcusers.service.utils.UserServiceUtils;
import com.huerta.springcloud.msvc.users.msvcusers.utils.ValidateUUID;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserSevice {

  private final UserRepository userRepository;

  private final CourseClientRest courseClientRest;

  @Transactional(readOnly = true)
  public List<UserDTO> getAll() {
    return this.userRepository.findAll()
      .stream()
      .map(UserServiceUtils::fromUserToUserDTO)
      .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public UserDTO getById(final String id) {
    if (!ValidateUUID.isValidUUID(id)) throw new UUIDBadRequestException(
      "UUID: " + id + ", is not valid."
    );
    return UserServiceUtils.fromUserToUserDTO(
      findUserById(UUID.fromString(id))
    );
  }

  public UserDTO save(final UserForm userForm) {
    if (findByEmail(userForm.getEmail()).isPresent()) {
      throw new DuplicatedEmailException("Duplicated Email");
    }
    User userToSave = new User(
      userForm.getName(),
      userForm.getEmail(),
      userForm.getPassword()
    );
    final User user = this.userRepository.save(userToSave);

    return UserServiceUtils.fromUserToUserDTO(user);
  }

  @Transactional
  public UserDTO update(final String id, final UserForm userForm) {
    if (!ValidateUUID.isValidUUID(id)) throw new UUIDBadRequestException(
      "UUID: " + id + ", is not valid."
    );

    if (findByEmail(userForm.getEmail()).isPresent()) {
      throw new DuplicatedEmailException("Duplicated Email");
    }
    final User userByID = findUserById(UUID.fromString(id));
    userByID.setName(userForm.getName());
    userByID.setPassword(userForm.getPassword());
    userByID.setEmail(userForm.getEmail());
    this.userRepository.update(UUID.fromString(id), userByID);
    return UserServiceUtils.fromUserToUserDTO(userByID);
  }

  @Transactional
  public void delete(final String id) {
    if (!ValidateUUID.isValidUUID(id)) throw new UUIDBadRequestException(
      "UUID: " + id + ", is not valid."
    );
    User findUserById = findUserById(UUID.fromString(id));
    this.courseClientRest.deleteByUserId(id);
    this.userRepository.delete(findUserById);
  }

  @Transactional(readOnly = true)
  public List<UserDTO> getByIds(final List<String> ids) {
    final Set<UUID> uuids = ids
      .stream()
      .map(UserServiceUtils::fromStringToUUID)
      .collect(Collectors.toSet());
    return this.userRepository.findAllById(uuids)
      .stream()
      .map(UserServiceUtils::fromUserToUserDTO)
      .collect(Collectors.toList());
  }

  private User findUserById(UUID userId) {
    return this.userRepository.findById(userId)
      .orElseThrow(() ->
        new NotFoundException("User with id: " + userId + " not found!")
      );
  }

  private Optional<User> findByEmail(final String email) {
    return this.userRepository.findByEmail(email);
  }
}
