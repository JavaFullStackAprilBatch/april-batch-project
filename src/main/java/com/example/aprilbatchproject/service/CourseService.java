package com.example.aprilbatchproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aprilbatchproject.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	CourseRepository courseRepo;
	
	public List<String> getAllCourseNames() {
		return courseRepo.getAllCourseNames();
		
	}
}
