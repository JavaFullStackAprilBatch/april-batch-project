package com.example.aprilbatchproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.aprilbatchproject.entity.Courses;

@Repository
public interface CourseRepository extends JpaRepository<Courses, Long>{
	
	@Query(value= "select distinct course_name from Courses")
	List<String> findAllDistinctCourseNames();

	@Query( nativeQuery = true, value = "select c.id from courses c where c.course_name = :courseName")
	Courses getCourseByName(String courseName);

}
