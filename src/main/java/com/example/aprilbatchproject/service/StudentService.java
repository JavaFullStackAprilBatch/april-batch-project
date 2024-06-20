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

    public StudentDTO updateStudentById(Long id, StudentDTO studentDTO) {
        Students student = studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student id not found"+ id));
        createOrUpdateStudent(student,studentDTO);
        Students updatedstudent=studentRepository.save(student);
        return DataConverter.convertDTOtoStudents(updatedstudent);
    }

    //

    public StudentDTO getStudentByName(String name, StudentDTO studentDTO) {
        Students existingStudent = null;
        List<Batches> batches;
        Address address = null;

        try {
            existingStudent = studentRepository.findByStudentName(name);
            address = studentUtil.GetStudentAddress(studentDTO, existingStudent.getAddress());
            existingStudent.setAddress(address);
            existingStudent.setEmail(studentDTO.getEmail());
            existingStudent.setPhone(studentDTO.getPhone());

            batches = studentUtil.GetBatchNames(studentDTO);
            existingStudent.setBatches(batches);

        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

        if (existingStudent == null) {
            throw new ResourceNotFoundException("No details found with student name: " + name);
        }
        if(batches.isEmpty()){
            throw new ResourceNotFoundException("No Batch details found for this student: " + name);
        }
        if(address == null){
            throw new ResourceNotFoundException("No Address details found for this student: " + name);
        }
        // Save entity
        Students savedStudent = studentRepository.save(existingStudent);

        return studentDTO;
    }

}
