package com.example.aprilbatchproject.repository;

import com.example.aprilbatchproject.dto.CourseDTO;
import com.example.aprilbatchproject.entity.Courses;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Courses, Long> {

    List<Courses> findByCourseNameIgnoreCase(String Name);

    Boolean existsByCourseNameIgnoreCase(String Name);


	@Query(nativeQuery = true,value= "select distinct course_name from Courses")
	List<String> findAllDistinctCourseNames();

	@Query("select new com.example.aprilbatchproject.dto.CourseDTO(courseName, courseContent) from Courses")
	List<CourseDTO> findAllCourses();

	//@Query(value = "select * from courses c where c.course-id = id")
	//Courses getCourseById(Long id);

	@Query( nativeQuery = true, value = "select * from courses c where c.course_name = :courseName")
	Courses getCourseByName(String courseName);

	@Query(nativeQuery = true, value = "select * from courses c where lower(c.course_name) = lower(:courseName)")
	Courses getCourseByNameIgnoreCase(@Param("courseName") String courseName);


}
