package com.huerta.springcloud.msvc.courses.msvccourses.feing;

import com.huerta.springcloud.msvc.courses.msvccourses.feing.models.dto.UserDTO;
import com.huerta.springcloud.msvc.courses.msvccourses.feing.models.form.UserForm;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "msvc-users", url = "${msvc.users.url}")
public interface UserClientRest {
  @GetMapping(value = "{id}")
  UserDTO getById(@PathVariable final String id);

  @PostMapping
  UserDTO save(@RequestBody final UserForm user);

  @GetMapping("ids")
  List<UserDTO> getByIds(@RequestParam Iterable<String> ids);
}
