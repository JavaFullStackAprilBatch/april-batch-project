package com.example.aprilbatchproject.service;

import com.example.aprilbatchproject.entity.Courses;
import com.example.aprilbatchproject.repository.CourseRepository;
import java.util.List;
import java.util.Optional;

import com.example.aprilbatchproject.dto.CourseDTO;
import com.example.aprilbatchproject.dto.DeleteCourseDTO;
import com.example.aprilbatchproject.exception.ResourceNotFoundException;
import com.example.aprilbatchproject.util.DataConverter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public Courses createCourse(CourseDTO courseDto) throws Exception {
        if (courseRepository.existsByCourseNameIgnoreCase(courseDto.getCourseName())) {
            throw new Exception("Course Name Already exist");
        }
		Courses course=new Courses();
		course.setCourseName(courseDto.getCourseName());
		course.setCourseContent(courseDto.getCourseContent());

        return courseRepository.save(course);
    }

    public List<CourseDTO> getCourses() {
        return courseRepository.findAllCourses();
    }



	//private static final Logger logger = LoggerFactory.getLogger(CourseService.class);
	@Autowired
	CourseRepository courseRepo;

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
		}else {
			courseRepository.delete(existingCourses);
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
		//	logger.error("Error fetching course details by name: {}", name, e);
			throw new RuntimeException("Failed to fetch course details by name: " + name);
		}
	}


}
