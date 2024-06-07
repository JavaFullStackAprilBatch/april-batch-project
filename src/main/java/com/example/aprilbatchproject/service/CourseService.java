package com.example.aprilbatchproject.service;

import com.example.aprilbatchproject.entity.Courses;
import com.example.aprilbatchproject.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
