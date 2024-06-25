package com.example.aprilbatchproject.service;

import java.util.List;

import com.example.aprilbatchproject.dto.CourseDTO;
<<<<<<< HEAD
import com.example.aprilbatchproject.dto.DeleteCourseDTO;
=======
>>>>>>> 348610940cd70e30a14ccf26ec0c7148a9dd8591
import com.example.aprilbatchproject.entity.Courses;
import com.example.aprilbatchproject.exception.ResourceNotFoundException;
import com.example.aprilbatchproject.util.DataConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aprilbatchproject.repository.CourseRepository;

@Service
public class CourseService {

	private static final Logger logger = LoggerFactory.getLogger(CourseService.class);
	@Autowired
	CourseRepository courseRepo;

	public List<CourseDTO> getAllCourseNames() {
		List<String> courseNames = courseRepo.findAllDistinctCourseNames();
		if (courseNames.isEmpty())
			throw new ResourceNotFoundException("No courses found");
		return DataConverter.convertToCourseDTOs(courseNames);

	}

	public DeleteCourseDTO deleteCourse(Long id) {
		Courses existingCourses = courseRepo.findById(id).get();
		DeleteCourseDTO deleteCourseDTO = new DeleteCourseDTO(existingCourses.getCourse_name(), existingCourses.getCourse_content());

		if (existingCourses == null) {
			throw new ResourceNotFoundException("Course not Found");
		} else {
			courseRepo.delete(existingCourses);
		}
		return deleteCourseDTO;
	}

	//	getCoursedetailsByName
	public CourseDTO getCoursedetaileByName(String name) {

		try {
			Courses courses = courseRepo.getCourseByNameIgnoreCase(name);
			if (courses == null) {
				throw new RuntimeException("Course not found with name: " + name);
			}
			return DataConverter.convertcoursestoDTo(courses);
		} catch (Exception e) {
			// Log the exception for debugging purposes
			logger.error("Error fetching course details by name: {}", name, e);
			throw new RuntimeException("Failed to fetch course details by name: " + name);
		}
	}

	public CourseDTO getCoursedetaileByName(String name) {

		Courses courses=courseRepo.getCourseByName(name);
		if (courses == null) {
			throw new RuntimeException("Course not found with name: " + name);
		}
		return DataConverter.convertcoursestoDTo(courses);

	}
}
