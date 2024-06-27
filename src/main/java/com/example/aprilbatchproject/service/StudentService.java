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
import com.example.aprilbatchproject.util.StudentUtil;
import org.springframework.stereotype.Service;
import com.example.aprilbatchproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    BatchRepository batchRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    StudentUtil studentUtil;

//create student details
    public StudentDTO createOrUpdateStudent(Students student,StudentDTO studentDTO) {
        try {
            student.setName(studentDTO.getName());
            student.setEmail(studentDTO.getEmail());
            student.setPhone(studentDTO.getPhone());

            // Handle address
            if (studentDTO.getAddress() != null) {
                Address address=studentUtil.GetStudentAddress(studentDTO,student.getAddress());
                addressRepository.save(address);
                student.setAddress(address);
            }

            // Handle batches

            List<String> batchNames = studentDTO.getBatchNames();
            if (batchNames != null) {
                List<Batches> batches=studentUtil.GetBatchNames(studentDTO);
                 student.setBatches(batches);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create student: " + e.getMessage());
        }

        Students savedStudent = studentRepository.save(student);
        return studentDTO;
    }
//Get all Students
    public List<StudentDTO> getAllStudents() {
//        return studentRepository.findAll().stream()
//                .map(student -> new StudentDTO(student.getStudent_id(), student.getName(), null /* Add batch IDs */))
//                .collect(Collectors.toList());
    	
        return null;
    }

    //Update Student By id

    public StudentDTO updateStudentById(Integer id, StudentDTO studentDTO) {
        Students student = studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student id not found"+ id));
        createOrUpdateStudent(student,studentDTO);
        Students updatedstudent=studentRepository.save(student);
        return DataConverter.convertDTOtoStudents(updatedstudent);
    }




}
