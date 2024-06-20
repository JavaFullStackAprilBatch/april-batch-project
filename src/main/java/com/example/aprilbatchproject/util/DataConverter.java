package com.example.aprilbatchproject.util;

import com.example.aprilbatchproject.dto.BatchDTO;
import com.example.aprilbatchproject.dto.CourseDTO;
import com.example.aprilbatchproject.dto.TrainerDTO;
import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.entity.Trainers;

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

    public static List<BatchDTO> convertBatchsToBatchDto(List<Batches> batches) {
        List<BatchDTO> batchDTOS = new ArrayList<BatchDTO>();
        for (Batches batch: batches) {
            batchDTOS.add(new BatchDTO(Long.valueOf(batch.getId()), batch.getBatch_name()));
        }
        return batchDTOS;
    }

    public static Trainers convertTrainerDtoToTrainer(TrainerDTO trainerDTO) {
        Trainers trainers =new Trainers();
        trainers.setName(trainerDTO.getName());
        trainers.setEmail(trainerDTO.getTrainerEmail());
        trainers.setPhone(trainerDTO.getTrainerPhone());
        trainers.setSpecialization(trainerDTO.getTrainerSpecialization());
        return trainers;



    }
}
