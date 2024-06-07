package com.example.aprilbatchproject.repository;

import com.example.aprilbatchproject.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Courses, Long> {

    List<Courses> findByCourseNameIgnoreCase(String Name);

    Boolean existsByCourseNameIgnoreCase(String Name);


}
