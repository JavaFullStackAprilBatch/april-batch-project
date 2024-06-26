package com.example.aprilbatchproject.util;

import com.example.aprilbatchproject.dto.StudentDTO;
import com.example.aprilbatchproject.entity.Address;
import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.repository.AddressRepository;
import com.example.aprilbatchproject.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentUtil {

    @Autowired
    BatchRepository batchRepository;

    @Autowired
    AddressRepository addressRepository;


    public List<Batches> GetBatchNames(StudentDTO studentDTO) {

        List<String> batchNames = studentDTO.getBatchNames();
        List<Batches> batches = new ArrayList();
        for (String batchName : batchNames) {
            Batches batch = batchRepository.findByBatchName(batchName);
            batches.add(batch);
        }
        return batches;
    }

    public Address GetStudentAddress(StudentDTO studentDTO, Address address) {
        if (studentDTO.getAddress() != null) {
            if (address == null) {
                address = new Address();
            }

            address.setAddressLine1(studentDTO.getAddress().getAddressLine1());
            address.setCity(studentDTO.getAddress().getCity());
            address.setState(studentDTO.getAddress().getState());
            address.setZipCode(studentDTO.getAddress().getZipCode());

        }
        return address;
    }

}