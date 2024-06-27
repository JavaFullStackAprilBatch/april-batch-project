package com.example.aprilbatchproject.service;

import com.example.aprilbatchproject.dto.AddressDTO;
import com.example.aprilbatchproject.dto.StudentDTO;
import com.example.aprilbatchproject.entity.Address;
import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.entity.StatusType;
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
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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