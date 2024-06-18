package com.example.aprilbatchproject.service;

import com.example.aprilbatchproject.dto.StudentDTO;
import com.example.aprilbatchproject.entity.Address;
import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.entity.Students;
import com.example.aprilbatchproject.exception.ResourceNotFoundException;
import com.example.aprilbatchproject.repository.AddressRepository;
import com.example.aprilbatchproject.repository.BatchRepository;
import jakarta.validation.constraints.NotNull;
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
    @Autowired
    AddressRepository addressRepository;

    public StudentDTO createStudent(StudentDTO studentDTO) {
        // Convert DTO to entity
        Students student = new Students();
        student.setName(studentDTO.getName());
        student.setAddress(student.getAddress());
        student.setEmail(studentDTO.getEmail());
        student.setPhone(student.getPhone());

        List<String> batchNames = studentDTO.getBatchNames();
        List<Batches> batches = getBatchNames(batchNames);
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
            address = getStudentAddress(studentDTO,existingStudent.getAddress());
            existingStudent.setAddress(address);
            existingStudent.setEmail(studentDTO.getEmail());
            existingStudent.setPhone(studentDTO.getPhone());

            List<String> batchNames = studentDTO.getBatchNames();
            batches = getBatchNames(batchNames);
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

    private List<Batches> getBatchNames(List<String> batchNames){

        List<Batches> batches = new ArrayList();
        for (String batchName : batchNames) {
            Batches batch = batchRepository.findByBatchName(batchName);
            batches.add(batch);
        }
        return batches;
    }

    private Address getStudentAddress( StudentDTO dto, Address address){
        Address changeAddress = null;
       if (address != null) {
           changeAddress = addressRepository.findByAddressId(address.getAddress_id());
           changeAddress.setAddressLine1(dto.getAddress().getAddressLine1());
           changeAddress.setCity(dto.getAddress().getCity());
           changeAddress.setState(dto.getAddress().getState());
           changeAddress.setZipCode(dto.getAddress().getZipCode());
       } else{
            Address newAddress = new Address();
            newAddress.setAddressLine1(dto.getAddress().getAddressLine1());
            newAddress.setCity(dto.getAddress().getCity());
            newAddress.setState(dto.getAddress().getState());
            newAddress.setZipCode(dto.getAddress().getZipCode());
            return newAddress;
        }

    return changeAddress;
    }

}
