package com.example.aprilbatchproject.dto;

import com.example.aprilbatchproject.entity.StatusType;

public class BatchDTO {

    private String batchName;
    private String batchStart;
    private String batchEnd;
    private String courseName;
    private String trainerName;
    private StatusType batchStatus;

   public long getNoofstudents() {
        return Noofstudents;
    }

    public void setNoofstudents(long noofstudents) {
        Noofstudents = noofstudents;
    }

    private long Noofstudents;

    public BatchDTO() {

    public BatchDTO(String batchName) {
    }

    public BatchDTO(String batchName, String batchStart, String batchEnd, String courseName, String trainerName, StatusType batchStatus) {
        this.batchName = batchName;
        this.batchStart = batchStart;
        this.batchEnd = batchEnd;
        this.courseName = courseName;
        this.trainerName = trainerName;
        this.batchStatus = batchStatus;

    }

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
        this.courseName = courseName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public StatusType getBatchStatus() {
        return batchStatus;
    }

    public void setBatchStatus(StatusType batchStatus) {
        this.batchStatus = batchStatus;
    }
}
