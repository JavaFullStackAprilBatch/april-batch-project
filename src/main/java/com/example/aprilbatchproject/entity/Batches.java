package com.example.aprilbatchproject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "batches")
public class Batches {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "batch_id")
    private long id;
    private String batch_name;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private String start_date;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private String end_date;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(name = "batch_student",
//            joinColumns = @JoinColumn(name = "batch_id"),
//            inverseJoinColumns = @JoinColumn(name = "student_id"))
//    private List<Students> students;

    @ManyToMany(mappedBy = "batches")
    private List<Students> students;

    @Enumerated(EnumType.STRING)
    @Column(name = "statusType")
    private StatusType status;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "courses_id")
    private Courses courses;

    public Batches() {}

    Batches(long id, String batch_name, String start_date, String end_date, List<Students> students, StatusType status, Courses courses, Trainers trainer, List<Recordings> recording) {
        this.id = id;
        this.batch_name = batch_name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.students = students;
        this.status = status;
        this.courses = courses;
        this.trainer = trainer;
        this.recording = recording;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trainer_id")
    private Trainers trainer;

    @OneToMany(targetEntity = Recordings.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "batch_id", referencedColumnName = "batch_id")
    private List<Recordings> recording;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBatch_name() {
        return batch_name;
    }

    public void setBatch_name(String batch_name) {
        this.batch_name = batch_name;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public List<Students> getStudents() {
        return students;
    }

    public void setStudents(List<Students> students) {
        this.students = students;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public Courses getCourses() {
        return courses;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }

    public Trainers getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainers trainer) {
        this.trainer = trainer;
    }

    public List<Recordings> getRecording() {
        return recording;
    }

    public void setRecording(List<Recordings> recording) {
        this.recording = recording;
    }

}
