package com.example.aprilbatchproject.dto;

public class CourseDTO {
    private String courseName;
    private String courseContent;
    public String getCourseContent() {
        return courseContent;
    }

    public void setCourseContent(String courseContent) {
        this.courseContent = courseContent;
    }

    public CourseDTO(){};


    //  getters, and setters


    public CourseDTO(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}