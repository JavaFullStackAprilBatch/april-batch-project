package com.example.aprilbatchproject.dto;

public class CourseDTO {
    private String courseName;
    private String courseContent;

    //  getters, and setters


    public String getCourseContent() {
		return courseContent;
	}

	public void setCourseContent(String courseContent) {
		this.courseContent = courseContent;
	}

	public CourseDTO(String courseName, String courseContent) {
		this.courseName = courseName;
		this.courseContent = courseContent;
	}

	public CourseDTO(String courseName) {
        this.courseName = courseName;
    }

	 public CourseDTO() {
			// TODO Auto-generated constructor stub
		}



	public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}