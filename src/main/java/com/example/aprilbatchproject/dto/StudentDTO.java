package com.example.aprilbatchproject.dto;

import com.example.aprilbatchproject.entity.Address;
import com.example.aprilbatchproject.entity.Batches;

import java.util.List;

public class StudentDTO {
    private String name;

    // private Address address;
    private AddressDTO address;
   private String email;
    private String phone;
    private List<String> batchNames;
    public  StudentDTO(){}
    public StudentDTO(String name, AddressDTO address, String email, String phone, List<String> batchNames) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.batchNames = batchNames;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }



    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getBatchNames() {
        return batchNames;
    }

    public void setBatchNames(List<String> batchNames) {
        this.batchNames = batchNames;
    }




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }


    // Getters and setters

//    public Address getAddress() {
//        return address;
//    }
//
//    public void setAddress(Address address) {
//        this.address = address;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
