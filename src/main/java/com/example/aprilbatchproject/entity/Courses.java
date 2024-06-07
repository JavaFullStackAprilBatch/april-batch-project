package com.example.aprilbatchproject.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long course_id;
    private String couser_name;
    private String course_content;

    public long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(long course_id) {
        this.course_id = course_id;
    }

    public String getCouser_name() {
        return couser_name;
    }

    public void setCouser_name(String couser_name) {
        this.couser_name = couser_name;
    }

    public String getCourse_content() {
        return course_content;
    }

    public void setCourse_content(String course_content) {
        this.course_content = course_content;
    }
}
