package com.example.aprilbatchproject.dto;

public class TrainerDTO {
    public String getTrainerName() {
        return trainerName;
    }

    private String trainerName;

    public TrainerDTO(){};
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

    private String email;
    private String phone;
    private String specialization;

    public TrainerDTO(String name) {
        this.trainerName = name;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    /* // Getter and setter
        public String getName() {
            return trainerName;
        }
    */
    public void setName(String trainerName) {
        this.trainerName = trainerName;
    }
}