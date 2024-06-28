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


    public List<Batches> GetBatchNames(StudentDTO studentDTO){

        List<String> batchNames = studentDTO.getBatchNames();
        List<Batches> batches = new ArrayList<Batches>();
        for (String batchName : batchNames) {
            Batches batch = batchRepository.findByBatchName(batchName);
            batches.add(batch);
        }
        return batches;
    }

//    public Address GetStudentAddress(StudentDTO dto, Address address){
//        Address changeAddress = null;
//        if (address != null) {
//            changeAddress = addressRepository.findByAddressId(address.getAddress_id());
//           changeAddress = SetAddress(changeAddress,dto);
//        } else{
//        	address = new Address();
//            changeAddress = SetAddress(address,dto);;
//        }
//        return changeAddress;
//    }

    private Address SetAddress(Address address, StudentDTO dto){
        address.setAddressLine1(dto.getAddress().getAddressLine1());
        address.setCity(dto.getAddress().getCity());
        address.setState(dto.getAddress().getState());
        address.setZipCode(dto.getAddress().getZipCode());
        return address;
    }
}