package com.example.aprilbatchproject.dto;

public class CourseDTO {
    private String courseName;

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