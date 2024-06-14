package com.example.aprilbatchproject.service;


import com.example.aprilbatchproject.dto.BatchDTO;
import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.entity.Courses;
import com.example.aprilbatchproject.entity.Trainers;
import com.example.aprilbatchproject.exception.ResourceNotFoundException;
import com.example.aprilbatchproject.repository.BatchRepository;
import com.example.aprilbatchproject.repository.CourseRepository;
import com.example.aprilbatchproject.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatchService {
    @Autowired
    BatchRepository batchRepository;
    @Autowired
    TrainerRepository trainerRepository;
    @Autowired
    CourseRepository courseRepository;
    public String createStudentsBatch(Batches batches){
        batchRepository.save(batches);
        return "Data saved";
    }

    public BatchDTO createBatch (BatchDTO dto) {
        Trainers trainer = null;
        Courses course = null;
        Batches batch = new Batches();
        try {
            trainer = trainerRepository.getTrainerByName(dto.getTrainerName());
            course = courseRepository.getCourseByName(dto.getCourseName());

            batch.setBatch_name(dto.getBatchName());
            batch.setStart_date(dto.getBatchStart());
            batch.setEnd_date(dto.getBatchEnd());
            batch.setCourses(course);
            batch.setTrainer(trainer);

        }catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

        if(trainer == null){
            throw new ResourceNotFoundException("Trainer Not Found");
        }

        if(course == null){
            throw new ResourceNotFoundException("Course Not Found");
        }

        Batches saveBatch =  batchRepository.save(batch);

        return dto;
    }


}
