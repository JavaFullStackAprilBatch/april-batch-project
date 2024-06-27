package com.example.aprilbatchproject.dto;

import com.example.aprilbatchproject.entity.Address;

import java.util.List;

public class StudentDTO {



    private int student_id;
    private String name;
    private AddressDTO address;
    private String email;
    private String phone;
    private List<String> batchNames;

    public StudentDTO(String name, AddressDTO address, String email, String phone, List<String> batchNames) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.batchNames = batchNames;
    }

    public  StudentDTO(){};
    public  StudentDTO(String name, List<String> batchNames, String email, String phone){};

    public StudentDTO(int student_id,String name, AddressDTO address, String email, String phone) {
        this.student_id = student_id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
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


    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }
}
