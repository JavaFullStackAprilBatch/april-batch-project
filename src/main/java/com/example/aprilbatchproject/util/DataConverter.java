package com.example.aprilbatchproject.util;

import com.example.aprilbatchproject.dto.BatchDTO;
import com.example.aprilbatchproject.dto.CourseDTO;
import com.example.aprilbatchproject.dto.TrainerDTO;
import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.entity.Courses;
import com.example.aprilbatchproject.entity.Students;
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
    //conver the batches to DTO to fetch the name

    public static BatchDTO convertDTOtoBatches(Batches batches)
    {
        BatchDTO batchDTO=new BatchDTO();

        batchDTO.setBatchName(batches.getBatch_name());
        batchDTO.setBatchStart(batches.getStart_date());
        batchDTO.setBatchEnd(batches.getEnd_date());


        if(batches.getCourses()!=null){
            batchDTO.setCourseName(batches.getCourses().getCourse_name());
        }
        else {
            batchDTO.setCourseName(null);
        }
        if(batches.getTrainer()!=null)
        {
            batchDTO.setTrainerName(batches.getTrainer().getName());
        }else {
            batchDTO.setTrainerName(null);
        }


        batchDTO.setBatchStatus(batches.getStatus());
        long studentscount= batches.getStudents().stream().map(
                Students::getName
        ).count();
        batchDTO.setNoofstudents(studentscount);
        return batchDTO;
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
}
