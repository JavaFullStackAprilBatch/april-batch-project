package com.example.aprilbatchproject.controller;

import java.util.List;

import com.example.aprilbatchproject.dto.CourseDTO;
import com.example.aprilbatchproject.dto.DeleteCourseDTO;
import com.example.aprilbatchproject.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.aprilbatchproject.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	CourseService courseService;

	@GetMapping("/getAllCoursesName")
	public ResponseEntity<ApiResponse<List<CourseDTO>>> getCoursesNames() {
//		return courseService.getAllCourseNames();
		List<CourseDTO> courseNames = courseService.getAllCourseNames();
		ApiResponse<List<CourseDTO>> response = new ApiResponse<>(true, "Courses fetched successfully", courseNames);
		return  ResponseEntity.ok(response);
	}

	//getendpoint course information based on name
	@GetMapping("/getCoursesdetailsByName")
	public ResponseEntity<ApiResponse<CourseDTO>> getCoursedeatilsByName(@RequestParam String name) {
		try {
			CourseDTO coursedetails = courseService.getCoursedetaileByName(name);
			ApiResponse<CourseDTO> response = new ApiResponse<>(true, "Course details retrived for this name", coursedetails);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, e.getMessage(), null));
		}
	}

	@DeleteMapping("/deleteCourse/{id}")
	public ResponseEntity<ApiResponse<DeleteCourseDTO>> deleteCourseById(@PathVariable Long id){
		DeleteCourseDTO deleteCourseDTO = courseService.deleteCourse(id);

		ApiResponse<DeleteCourseDTO> courseResponce = new ApiResponse<>(true, "Course Deleted Successfully", deleteCourseDTO);
		return ResponseEntity.ok(courseResponce);
	}
}
