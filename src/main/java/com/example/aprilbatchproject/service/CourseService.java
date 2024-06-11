package com.example.aprilbatchproject.service;

import java.util.List;

import com.example.aprilbatchproject.dto.CourseDTO;
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
}