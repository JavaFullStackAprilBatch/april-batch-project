package com.example.aprilbatchproject.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "students")
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int student_id;
    private String name;
    private String email;
    private String phone;

    @ManyToMany(mappedBy = "students")
    private List<Batches> batches;

    @OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Batches> getBatches() {
        return batches;
    }

    public void setBatches(List<Batches> batches) {
        this.batches = batches;
    }
}
