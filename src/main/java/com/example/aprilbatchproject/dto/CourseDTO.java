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