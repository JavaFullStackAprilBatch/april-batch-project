package com.example.aprilbatchproject.controller;

import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.entity.Courses;
import com.example.aprilbatchproject.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.aprilbatchproject.dto.CourseDTO;
import com.example.aprilbatchproject.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aprilbatchproject.service.CourseService;

@RestController
@RequestMapping("/courses")

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
}
