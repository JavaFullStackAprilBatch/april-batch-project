package com.example.aprilbatchproject.util;

import com.example.aprilbatchproject.dto.AddressDTO;
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
    public static Trainers convertTrainerDtoToTrainer(TrainerDTO trainerDTO) {
        Trainers trainers = new Trainers();
        trainers.setName(trainerDTO.getTrainerName());
        trainers.setEmail(trainerDTO.getEmail());
        trainers.setPhone(trainerDTO.getPhone());
        trainers.setSpecialization(trainerDTO.getSpecialization());
        return trainers;
    }

    //TrinerDto Conversion
    public static TrainerDTO converToTrainerDTO(Trainers trainers)
    {
        TrainerDTO trainerDTO=new TrainerDTO();
        trainerDTO.setName(trainers.getName());
        trainerDTO.setEmail(trainers.getEmail());
        trainerDTO.setPhone(trainers.getPhone());
        trainerDTO.setSpecialization(trainers.getSpecialization());
        return trainerDTO;
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
    public static AddressDTO convertAddressToAddresssDto(Students s) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressLine1(s.getAddress()== null ?"":s.getAddress().getAddressLine1());
        addressDTO.setCity(s.getAddress()== null ?"":s.getAddress().getCity());
        addressDTO.setState(s.getAddress()== null ?"":s.getAddress().getState());
        addressDTO.setZipCode(s.getAddress()== null ?"":s.getAddress().getZipCode());
        return addressDTO;
    }
}
