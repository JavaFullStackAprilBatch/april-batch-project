package com.example.aprilbatchproject.service;

import com.example.aprilbatchproject.dto.StudentDTO;
import com.example.aprilbatchproject.entity.Address;
import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.entity.Students;
import com.example.aprilbatchproject.exception.BatchNotFoundException;
import com.example.aprilbatchproject.exception.ResourceNotFoundException;
import com.example.aprilbatchproject.repository.AddressRepository;
import com.example.aprilbatchproject.repository.BatchRepository;
import com.example.aprilbatchproject.util.DataConverter;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.example.aprilbatchproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    BatchRepository batchRepository;

    @Autowired
    AddressRepository addressRepository;


    @Transactional
    //postend poitns
    public StudentDTO createOrUpdateStudent(Students student, StudentDTO studentDTO) {
        try {
            student.setName(studentDTO.getName());
            student.setEmail(studentDTO.getEmail());
            student.setPhone(studentDTO.getPhone());

            // Handle address
            if (studentDTO.getAddress() != null) {
                Address address = new Address();
                address.setAddressLine1(studentDTO.getAddress().getAddressLine1());
                address.setCity(studentDTO.getAddress().getCity());
                address.setState(studentDTO.getAddress().getState());
                address.setZipCode(studentDTO.getAddress().getZipCode());
                addressRepository.save(address);
                student.setAddress(address);
            }

            // Handle batches
            List<String> batchNames = studentDTO.getBatchNames();
            if (batchNames != null) {
                List<Batches> batches = new ArrayList<>();
                for (String batchName : batchNames) {
                    Batches batch = batchRepository.findByBatchName(batchName);
                    if (batch != null) {
                        batches.add(batch);
                    } else {
                        throw new BatchNotFoundException("Batch Name not found: " + batchName);
                    }
                }
                student.setBatches(batches);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create student: " + e.getMessage());
        }

        Students savedStudent = studentRepository.save(student);
//return studentDTO; (Directly returs the data from the DTO)
       return DataConverter.convertDTOtoStudents(savedStudent); //(Repo stored the data into the enitiy from there we can direct return the data)
    }


    /*get endpoints to get all studnets details*/

    public List<StudentDTO> getAllStudents() {


        try {
            return studentRepository.findAll().stream().
                    map(DataConverter::convertDTOtoStudents).collect(Collectors.toList());
        } catch (DataAccessException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }
//getStduent details by id

    public Optional<StudentDTO> getStudentById(Long id) {
        try {
            return studentRepository.findById(id).map(DataConverter::convertDTOtoStudents);
        }catch (Exception e)
        {
            throw new ResourceNotFoundException(e.getMessage());
        }

    }



    public StudentDTO updateStudentById(Long id, StudentDTO studentDTO) {
        Students student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        return createOrUpdateStudent(student, studentDTO);
    }
}


