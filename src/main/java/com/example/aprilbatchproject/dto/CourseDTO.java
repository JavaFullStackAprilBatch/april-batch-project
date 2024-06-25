package com.example.aprilbatchproject.dto;

public class CourseDTO {
    private String courseName;

    public CourseDTO(){}
    public String getCourse_content() {
        return course_content;
    }

    public void setCourse_content(String course_content) {
        this.course_content = course_content;
    }

    private String course_content;

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