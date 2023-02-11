package com.huerta.springcloud.msvc.users.msvcusers.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-courses", url = "msvc-courses:8002/courses")
public interface CourseClientRest {
  @DeleteMapping("users/{id}")
  public void deleteByUserId(@PathVariable(value = "id") final String id);
}
