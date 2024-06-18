package com.example.aprilbatchproject.util;

import com.example.aprilbatchproject.dto.AddressDTO;
import com.example.aprilbatchproject.dto.CourseDTO;
import com.example.aprilbatchproject.dto.StudentDTO;
import com.example.aprilbatchproject.dto.TrainerDTO;
import com.example.aprilbatchproject.entity.Address;
import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.entity.Students;
import com.example.aprilbatchproject.exception.BatchNotFoundException;
import com.example.aprilbatchproject.repository.AddressRepository;
import org.hibernate.internal.build.AllowNonPortable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
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

    /* Convert studententity back to DTO */
    public static StudentDTO convertDTOtoStudents(Students students) {
        // logic to convert User to UserDTO

//        StudentDTO studentDTO = new StudentDTO(
//                students.getName(),
//
//
//
//                students.getAddress(),
//
//                students.getEmail(),
//                students.getPhone(),
//                students.getBatches().
//                        stream().map(Batches::getBatch_name).collect(Collectors.toList()));
//
//        return studentDTO;


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


    



