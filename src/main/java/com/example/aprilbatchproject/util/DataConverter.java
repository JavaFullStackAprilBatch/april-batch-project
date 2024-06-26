package com.example.aprilbatchproject.util;

import com.example.aprilbatchproject.dto.CourseDTO;
import com.example.aprilbatchproject.dto.StudentDTO;
import com.example.aprilbatchproject.dto.TrainerDTO;
import com.example.aprilbatchproject.entity.Courses;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataConverter {
    public static List<CourseDTO> convertToCourseDTOs(List<String> courseNames) {
        List<CourseDTO> courseDTOs = new ArrayList<>();
        for (String courseName : courseNames) {
            courseDTOs.add(new CourseDTO(courseName));
        }
        return courseDTOs;
    }
    public static List<TrainerDTO> convertToTrainerDTOs(List<String> courseNames) {
        List<TrainerDTO> trainerDTOs = new ArrayList<>();
        for (String courseName : courseNames) {
            trainerDTOs.add(new TrainerDTO(courseName));
        }
        return trainerDTOs;
    }
	
	
	
}
