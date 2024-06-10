package com.example.aprilbatchproject.repository;

import com.example.aprilbatchproject.entity.Courses;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Courses, Long> {

    List<Courses> findByCourseNameIgnoreCase(String Name);

    Boolean existsByCourseNameIgnoreCase(String Name);


	@Query(value= "select distinct course_name from Courses")
	List<String> getAllCourseNames();

}
