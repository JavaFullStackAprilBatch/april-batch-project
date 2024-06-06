package com.example.aprilbatchproject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Recordings {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long recording_id;

    private String recording_link;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date upload_date;

    public long getRecording_id() {
        return recording_id;
    }

    public void setRecording_id(long recording_id) {
        this.recording_id = recording_id;
    }

    public String getRecording_link() {
        return recording_link;
    }

    public void setRecording_link(String recording_link) {
        this.recording_link = recording_link;
    }

    public Date getUpload_date() {
        return upload_date;
    }

    public void setUpload_date(Date upload_date) {
        this.upload_date = upload_date;
    }
}
