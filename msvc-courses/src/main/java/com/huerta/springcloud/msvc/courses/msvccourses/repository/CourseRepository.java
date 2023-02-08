package com.huerta.springcloud.msvc.courses.msvccourses.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huerta.springcloud.msvc.courses.msvccourses.entity.Course;

public interface CourseRepository extends JpaRepository<Course, UUID>{
    
}
