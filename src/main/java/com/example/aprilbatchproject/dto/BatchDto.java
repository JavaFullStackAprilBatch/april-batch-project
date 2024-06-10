package com.example.aprilbatchproject.dto;

public class BatchDto {
    private String batchName;

    private String batchStart;

    private  String batchEnd;

    private String courseName;

    private String trainerName;

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getBatchStart() {
        return batchStart;
    }

    public void setBatchStart(String batchStart) {
        this.batchStart = batchStart;
    }

    public String getBatchEnd() {
        return batchEnd;
    }

    public void setBatchEnd(String batchEnd) {
        this.batchEnd = batchEnd;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        courseName = courseName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }




}
