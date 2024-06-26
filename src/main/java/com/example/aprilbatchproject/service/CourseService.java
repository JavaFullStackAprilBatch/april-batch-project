package com.example.aprilbatchproject.service;

import java.util.List;
import java.util.Optional;

import com.example.aprilbatchproject.dto.CourseDTO;
import com.example.aprilbatchproject.entity.Courses;
import com.example.aprilbatchproject.exception.ResourceNotFoundException;
import com.example.aprilbatchproject.util.DataConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aprilbatchproject.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	CourseRepository courseRepo;
	
	public List<CourseDTO> getAllCourseNames() {
		List<String> courseNames = courseRepo.findAllDistinctCourseNames();
		if(courseNames.isEmpty())
			throw new ResourceNotFoundException("No courses found");
		return DataConverter.convertToCourseDTOs(courseNames);
		
	}
	


	public Optional<Courses> getCourseById(Long id) {
		// TODO Auto-generated method stub
		return courseRepo.findById(id);
	}



	public CourseDTO convertToCourseDTO(Courses course) {
		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setCourseName(course.getCourse_name());
		courseDTO.setCourseContent(course.getCourse_content());
		
		// TODO Auto-generated method stub
		return courseDTO;
	}
}
