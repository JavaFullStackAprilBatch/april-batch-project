package com.example.aprilbatchproject.service;

import com.example.aprilbatchproject.dto.StudentDTO;
import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.entity.Students;
import com.example.aprilbatchproject.repository.BatchRepository;
import org.springframework.stereotype.Service;
import com.example.aprilbatchproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    BatchRepository batchRepository;

    public StudentDTO createStudent(StudentDTO studentDTO) {
        // Convert DTO to entity
        Students student = new Students();
        student.setName(studentDTO.getName());
        student.setAddress(student.getAddress());
        student.setEmail(studentDTO.getEmail());
        student.setPhone(student.getPhone());

        List<String> batchNames = studentDTO.getBatchNames();

        List<Batches> batches = new ArrayList();
        for (String batchName : batchNames) {
            Batches batch = batchRepository.findByBatchName(batchName);
            batches.add(batch);
        }
        student.setBatches(batches);

        // Save entity
        Students savedStudent = studentRepository.save(student);

        // Convert entity back to DTO
        return studentDTO;
    }

    public List<StudentDTO> getAllStudents() {
//        return studentRepository.findAll().stream()
//                .map(student -> new StudentDTO(student.getStudent_id(), student.getName(), null /* Add batch IDs */))
//                .collect(Collectors.toList());
        return null;
    }

  /*  public StudentDTO getStudentById(Long id) {
      *//*  Students student = studentRepository.findById(id).get();
        List<Batches> batches =student.getBatches();

       // (String name, List<String> batchNames, String email, String phone)
        return new StudentDTO(student.getName(),student.getBatches(),student.getEmail(), student.getPhone() );*//*
        return null;
    }*/
}
