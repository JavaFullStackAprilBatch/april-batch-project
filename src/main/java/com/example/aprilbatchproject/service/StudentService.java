package com.example.aprilbatchproject.service;

import com.example.aprilbatchproject.dto.AddressDTO;
import com.example.aprilbatchproject.dto.StudentDTO;
import com.example.aprilbatchproject.entity.Address;
import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.entity.StatusType;
import com.example.aprilbatchproject.entity.Students;
import com.example.aprilbatchproject.exception.ResourceNotFoundException;
import com.example.aprilbatchproject.repository.AddressRepository;
import com.example.aprilbatchproject.repository.BatchRepository;
import com.example.aprilbatchproject.util.DataConverter;
import com.example.aprilbatchproject.util.StudentUtil;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import com.example.aprilbatchproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public StudentDTO createStudent(StudentDTO studentDTO) {
        // Convert DTO to entity
        Students student = new Students();
        student.setName(studentDTO.getName());
        student.setAddress(student.getAddress());
        student.setEmail(studentDTO.getEmail());
        student.setPhone(student.getPhone());

        List<Batches> batches = studentUtil.GetBatchNames(studentDTO);
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
        if (batches.isEmpty()) {
            throw new ResourceNotFoundException("No Batch details found for this student: " + name);
        }
        if (address == null) {
            throw new ResourceNotFoundException("No Address details found for this student: " + name);
        }
        // Save entity
        Students savedStudent = studentRepository.save(existingStudent);

        return studentDTO;
    }

    public List<StudentDTO> getStudentsByStatus(String status) {

        StatusType statusType = StatusType.valueOf(status);
        List<StudentDTO> studentDTOS = new ArrayList<>();

        List<Batches> batches = batchRepository.findByBatchStatus(statusType);
        for (Batches batches1 : batches) {
            List<Students> students = studentRepository.findByBatch(batches1.getId());
            for (Students s : students) {
                studentDTOS.add(new StudentDTO(s.getName(), DataConverter.convertAddressToAddresssDto(s), s.getEmail(), s.getPhone(), getBatchList(s)));
            }
        }
        return studentDTOS;
    }

    private List<String> getBatchList(Students s) {
        List<String> batchNames = new ArrayList<>();
        batchNames = s.getBatches().stream().map(a -> a.getBatch_name()).collect(Collectors.toList());
        return batchNames;
    }

}