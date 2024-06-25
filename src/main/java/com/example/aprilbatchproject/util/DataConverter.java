package com.example.aprilbatchproject.util;

import com.example.aprilbatchproject.dto.BatchDTO;
import com.example.aprilbatchproject.dto.CourseDTO;
import com.example.aprilbatchproject.dto.TrainerDTO;
import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.entity.Courses;
import com.example.aprilbatchproject.entity.Trainers;
import com.example.aprilbatchproject.exception.ResourceNotFoundException;
import org.hibernate.engine.jdbc.batch.spi.Batch;

import java.util.ArrayList;
import java.util.List;

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

    public static   List<BatchDTO> convertToBatchDTOs(List<Batches> batches){
        List<BatchDTO> batchDTOS = new ArrayList<>();
        for(Batches batch : batches){
            BatchDTO batchDTO = new BatchDTO();
            batchDTO.setBatchName(batch.getBatch_name());
            batchDTO.setBatchStart(batch.getStart_date());
            batchDTO.setBatchEnd(batch.getEnd_date());
            Courses courses = batch.getCourses();
            if(courses !=null){
                batchDTO.setCourseName(courses.getCourse_name());
            }else {
                throw new ResourceNotFoundException("CourseName Not Found");
            }
            Trainers trainers = batch.getTrainer();
            if(trainers != null){
                batchDTO.setTrainerName(trainers.getName());
            }else {
                throw new ResourceNotFoundException("TrainerName Not Found");
            }
            batchDTO.setBatchStatus(batch.getStatus());
            batchDTOS.add(batchDTO);
        }
        return batchDTOS;
    }

<<<<<<< HEAD
    //convertcoursestoDTo
    public static CourseDTO convertcoursestoDTo(Courses courses)
    {
        CourseDTO courseDTO=new CourseDTO();
        courseDTO.setCourseName(courses.getCourse_name());
        courseDTO.setCourse_content(courses.getCourse_content());
        return courseDTO;
    }
=======
    //convert course enityty to DTo
    public static CourseDTO convertcoursestoDTo(Courses course)
    {
        CourseDTO courseDTO=new CourseDTO();
        courseDTO.setCourseName(course.getCourse_name());
        courseDTO.setCourseContent(course.getCourse_content());

        return courseDTO;
    }

>>>>>>> 348610940cd70e30a14ccf26ec0c7148a9dd8591
}
