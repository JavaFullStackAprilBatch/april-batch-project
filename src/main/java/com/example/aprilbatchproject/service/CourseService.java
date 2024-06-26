package com.example.aprilbatchproject.service;

import com.example.aprilbatchproject.entity.Courses;
import com.example.aprilbatchproject.repository.CourseRepository;
import java.util.List;
import java.util.Optional;

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
    CourseRepository courseRepository;

    public String createCourse(Courses courses) throws Exception {
        if (courseRepository.existsByCourseNameIgnoreCase(courses.getCourseName())) {
            throw new Exception("Course Name Already exist");
        }
        courseRepository.save(courses);
        return " ";
    }

    public List<Courses> getCourse() {
        return courseRepository.findAll();
    }



	public List<CourseDTO> getAllCourseNames() {
		List<String> courseNames = courseRepository.findAllDistinctCourseNames();
		if(courseNames.isEmpty())
			throw new ResourceNotFoundException("No courses found");
		return DataConverter.convertToCourseDTOs(courseNames);

	}

	


	public Optional<Courses> getCourseById(Long id) {
		// TODO Auto-generated method stub
		return courseRepository.findById(id);
	}



	public CourseDTO convertToCourseDTO(Courses course) {
		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setCourseName(course.getCourseName());
		courseDTO.setCourseContent(course.getCourseContent());
		
		// TODO Auto-generated method stub
		return courseDTO;
	}


	public DeleteCourseDTO deleteCourse(Long id){
		Courses existingCourses = courseRepository.findById(id).get();
		DeleteCourseDTO deleteCourseDTO = new DeleteCourseDTO(existingCourses.getCourseName(), existingCourses.getCourseContent());

		if(existingCourses == null){
			throw new ResourceNotFoundException("Course not Found");
		}
		else {
			courseRepository.delete(existingCourses);
		}
		return deleteCourseDTO;
	}
}
