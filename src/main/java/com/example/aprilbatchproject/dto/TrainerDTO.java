package com.example.aprilbatchproject.dto;

public class TrainerDTO {

    private String trainerName;
    private String trainerEmail;
    private String trainerPhone;
    private String trainerSpecialization;

    public TrainerDTO(String trainerName, String trainerEmail, String trainerPhone, String trainerSpecialization) {
        this.trainerName = trainerName;
        this.trainerEmail = trainerEmail;
        this.trainerPhone = trainerPhone;
        this.trainerSpecialization = trainerSpecialization;
    }

    public String getTrainerEmail() {
        return trainerEmail;
    }

    public void setTrainerEmail(String trainerEmail) {
        this.trainerEmail = trainerEmail;
    }

    public String getTrainerPhone() {
        return trainerPhone;
    }

    public void setTrainerPhone(String trainerPhone) {
        this.trainerPhone = trainerPhone;
    }

    public String getTrainerSpecialization() {
        return trainerSpecialization;
    }

    public void setTrainerSpecialization(String trainerSpecialization) {
        this.trainerSpecialization = trainerSpecialization;
    }

    public TrainerDTO(String name) {
        this.trainerName = trainerName;
    }

    // Getter and setter
    public String getName() {
        return trainerName;
    }

    public void setName(String trainerName) {
        this.trainerName = trainerName;
    }
}
