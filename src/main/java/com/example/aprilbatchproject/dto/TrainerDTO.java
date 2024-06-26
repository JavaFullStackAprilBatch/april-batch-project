package com.example.aprilbatchproject.dto;

public class TrainerDTO {

    private String trainerName;
    

    public TrainerDTO(String name) {
        this.trainerName = name;
    }

    // Getter and setter
    public String getName() {
        return trainerName;
    }

    public void setName(String trainerName) {
        this.trainerName = trainerName;
    }
}
