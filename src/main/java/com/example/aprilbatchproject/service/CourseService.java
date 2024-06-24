package com.example.aprilbatchproject.service;

import java.util.List;

import com.example.aprilbatchproject.dto.CourseDTO;
import com.example.aprilbatchproject.dto.DeleteCourseDTO;
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

	public DeleteCourseDTO deleteCourse(Long id){
		Courses existingCourses = courseRepo.findById(id).get();
		DeleteCourseDTO deleteCourseDTO = new DeleteCourseDTO(existingCourses.getCourse_name(), existingCourses.getCourse_content());

		if(existingCourses == null){
			throw new ResourceNotFoundException("Course not Found");
		}else {
			courseRepo.delete(existingCourses);
		}
        return deleteCourseDTO;
	}

}
