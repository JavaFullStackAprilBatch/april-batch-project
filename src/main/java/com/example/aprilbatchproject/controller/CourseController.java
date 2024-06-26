package com.example.aprilbatchproject.controller;

import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.entity.Courses;
import com.example.aprilbatchproject.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.example.aprilbatchproject.dto.CourseDTO;
<<<<<<< HEAD
import com.example.aprilbatchproject.entity.Courses;
=======
import com.example.aprilbatchproject.dto.DeleteCourseDTO;
>>>>>>> bc7a0cf1759e6b3eb27ddd60681ccb087a894889
import com.example.aprilbatchproject.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
=======
import org.springframework.web.bind.annotation.*;
>>>>>>> bc7a0cf1759e6b3eb27ddd60681ccb087a894889

import com.example.aprilbatchproject.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {

	@Autowired
	CourseService courseService;

    @PostMapping("/create_course")
    public ResponseEntity<ApiResponse<String>> createCourse(@RequestBody Courses courses) throws Exception {
        courseService.createCourse(courses);
        return ResponseEntity.ok(new ApiResponse<>(true,"Course Data Save Successfully",""));
    }

    @GetMapping
    public List<Courses> getCourse() {
        return courseService.getCourse();
    }
	@GetMapping("/getAllCoursesName")
	public ResponseEntity<ApiResponse<List<CourseDTO>>> getCoursesNames() {
//		return courseService.getAllCourseNames();
		List<CourseDTO> courseNames = courseService.getAllCourseNames();
		ApiResponse<List<CourseDTO>> response = new ApiResponse<>(true, "Courses fetched successfully", courseNames);
		return  ResponseEntity.ok(response);
	}

<<<<<<< HEAD
	}
	
	@GetMapping("/getCourseById/{id}")
	
	public ResponseEntity<ApiResponse<CourseDTO>> getCourseById(@PathVariable Long id)
	{
		Optional<Courses> course = courseService.getCourseById(id);
		if(!course.isPresent()) {
			ApiResponse<CourseDTO> response = new ApiResponse<>(false, "Course not found", null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		
		CourseDTO courseDTO = courseService.convertToCourseDTO(course.get());
		
		ApiResponse<CourseDTO> response = new ApiResponse<>(true,"Courses fetched Successfully using id", courseDTO);
		return new ResponseEntity<>(response,HttpStatus.OK);
=======
	@DeleteMapping("/deleteCourse/{id}")
	public ResponseEntity<ApiResponse<DeleteCourseDTO>> deleteCourseById(@PathVariable Long id){
		DeleteCourseDTO deleteCourseDTO = courseService.deleteCourse(id);

		ApiResponse<DeleteCourseDTO> courseResponce = new ApiResponse<>(true, "Course Deleted Successfully", deleteCourseDTO);
		return ResponseEntity.ok(courseResponce);
>>>>>>> bc7a0cf1759e6b3eb27ddd60681ccb087a894889
	}
}
