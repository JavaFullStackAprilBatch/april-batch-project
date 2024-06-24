package com.example.aprilbatchproject.dto;

public class DeleteCourseDTO {

    private String courseName;
    private String courseContent;

    public DeleteCourseDTO(String courseName, String courseContent) {
        this.courseName = courseName;
        this.courseContent = courseContent;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseContent() {
        return courseContent;
    }

    public void setCourseContent(String courseContent) {
        this.courseContent = courseContent;
    }
}
