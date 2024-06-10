package com.example.aprilbatchproject.controller;

import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.entity.Courses;
import com.example.aprilbatchproject.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aprilbatchproject.service.CourseService;

@RestController
@RequestMapping("/courses")

public class CourseController {

	@Autowired
	CourseService courseService;

    @PostMapping("/create_course")
    public String createCourse(@RequestBody Courses courses) throws Exception {
        courseService.createCourse(courses);
        return "Course Data Save Successfully";
    }

    @GetMapping
    public List<Courses> getCourse() {
        return courseService.getCourse();
    }
	@GetMapping("/getAllCoursesName")
	public List<String> getCoursesNames() {
		return courseService.getAllCourseNames();

	}
}
