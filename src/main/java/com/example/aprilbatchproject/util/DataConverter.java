package com.example.aprilbatchproject.util;


import com.example.aprilbatchproject.dto.BatchDTO;

import com.example.aprilbatchproject.dto.AddressDTO;

import com.example.aprilbatchproject.dto.CourseDTO;
import com.example.aprilbatchproject.dto.StudentDTO;
import com.example.aprilbatchproject.dto.TrainerDTO;

import com.example.aprilbatchproject.entity.Courses;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.entity.Courses;
import com.example.aprilbatchproject.entity.Trainers;
import com.example.aprilbatchproject.exception.ResourceNotFoundException;

import com.example.aprilbatchproject.entity.Address;

import com.example.aprilbatchproject.entity.Students;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
            BatchDTO batchDTO = new BatchDTO(batch.getBatch_name());
            batchDTO.setBatchName(batch.getBatch_name());
            batchDTO.setBatchStart(batch.getStart_date());
            batchDTO.setBatchEnd(batch.getEnd_date());
            Courses courses = batch.getCourses();
            if(courses !=null){
                batchDTO.setCourseName(courses.getCourseName());
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

    public static StudentDTO convertDTOtoStudents(Students students) {
        // logic to convert StudentsDto to Students

        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setName(students.getName());
        studentDTO.setEmail(students.getEmail());
        studentDTO.setPhone(students.getPhone());

        if (students.getAddress() != null) {
            AddressDTO addressDTO = new AddressDTO();
            Address address = students.getAddress();
            addressDTO.setAddressLine1(address.getAddressLine1());
            addressDTO.setCity(address.getCity());
            addressDTO.setState(address.getState());
            addressDTO.setZipCode(address.getZipCode());
            studentDTO.setAddress(addressDTO);
        }

        if (students.getBatches() != null) {
            List<String> batchNames = students.getBatches().stream()
                    .map(Batches::getBatch_name)
                    .collect(Collectors.toList());
            studentDTO.setBatchNames(batchNames);
        }
        return studentDTO;
    }



}
