package com.example.aprilbatchproject.dto;

public class StudentDTOwithoutBatchName {
    private int student_id;
    private String name;
    private AddressDTO address;
      private String email;

      public  StudentDTOwithoutBatchName(){}
    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
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

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String phone;

    public StudentDTOwithoutBatchName(int student_id, String name, AddressDTO address, String email, String phone) {
        this.student_id = student_id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }
}
