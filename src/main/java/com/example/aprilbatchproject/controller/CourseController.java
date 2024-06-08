package com.example.aprilbatchproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aprilbatchproject.service.CourseService;

@RestController

public class CourseController {
	
	@Autowired
	CourseService courseService;

	@GetMapping("/getAllCoursesName")
	public List<String> getCoursesNames() {
		return courseService.getAllCourseNames();
		
	}
}
