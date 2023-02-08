package com.huerta.springcloud.msvc.courses.msvccourses.form;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

/**
 * CourseForm
 */
@Getter
public class CourseForm {

    @NotBlank(message = "The name property must have a value")
    private String name;
}