package com.example.aprilbatchproject.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "trainers")
public class Trainers {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long trainer_id;
    private String name;
    private String email;
    private String phone;
    private String specialization;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    private List<Batches> batches;

    public long getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(long trainer_id) {
        this.trainer_id = trainer_id;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<Batches> getBatches() {
        return batches;
    }

    public void setBatches(List<Batches> batches) {
        this.batches = batches;
    }
}
