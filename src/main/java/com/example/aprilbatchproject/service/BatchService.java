package com.example.aprilbatchproject.service;



import com.example.aprilbatchproject.dto.BatchDTO;
import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.entity.Trainers;
import com.example.aprilbatchproject.entity.Courses;
import com.example.aprilbatchproject.entity.StatusType;
import com.example.aprilbatchproject.entity.StatusType;
import com.example.aprilbatchproject.entity.Trainers;
import com.example.aprilbatchproject.exception.DuplicateResourceFoundException;
import com.example.aprilbatchproject.exception.ResourceNotFoundException;
import com.example.aprilbatchproject.repository.BatchRepository;
import com.example.aprilbatchproject.repository.TrainerRepository;
import com.example.aprilbatchproject.repository.CourseRepository;
import com.example.aprilbatchproject.repository.TrainerRepository;
import com.example.aprilbatchproject.util.DataConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import java.util.List;

@Service
public class BatchService {
    @Autowired
    BatchRepository batchRepository;
    @Autowired
    TrainerRepository trainerRepository;
    @Autowired
    CourseRepository courseRepository;

    public String createStudentsBatch(Batches batches) {
        batchRepository.save(batches);
        return "Data saved";
    }

    public BatchDTO createBatch(BatchDTO dto) {
        Trainers trainer = null;
        Courses course = null;
        Batches newBatch = new Batches();
        try {
            trainer = trainerRepository.getTrainerByName(dto.getTrainerName());
            course = courseRepository.getCourseByName(dto.getCourseName());
            Batches batch = batchRepository.findByBatchName(dto.getBatchName());

            if(batch == null){
                newBatch.setBatch_name(dto.getBatchName());
                newBatch.setStart_date(dto.getBatchStart());
                newBatch.setEnd_date(dto.getBatchEnd());
                newBatch.setCourses(course);
                newBatch.setTrainer(trainer);
                newBatch.setStatus(dto.getBatchStatus());
            }else {
                throw new DuplicateResourceFoundException("Batch name already exist, can not create duplicate batch name");
            }


        }catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

        if(trainer == null){
            throw new ResourceNotFoundException("Trainer Not Found");
        }

        if(course == null){
            throw new ResourceNotFoundException("Course Not Found");
        }

        Batches saveBatch =  batchRepository.save(newBatch);

        return dto;
    }

    public List<BatchDTO>getOngoingBatchs(String status){

       List<Batches> batches =  batchRepository.findByStatusType(status);
        if(batches.isEmpty())
            throw new ResourceNotFoundException("No Batches found");
        return DataConverter.convertToBatchDTOs(batches);
    }

    public List<BatchDTO> getListOfBatchNames() {
        try {
         return  batchRepository.findAll()
                    .stream()
                   .map(batch -> {
                        BatchDTO batchDTO = new BatchDTO(batch.getBatch_name());
                        return batchDTO;
                    }).collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch batch names", e);
        }

    }
    public List<BatchDTO> getListOfBatchNamesByStatus(StatusType statusType) {
       try {
            List<BatchDTO> list = new ArrayList<>();
            if (statusType == null) {
                return list;
            }
            return batchRepository.findByStatus(statusType).stream().map(batch -> {
                BatchDTO batchDTO = new BatchDTO(batch.getBatch_name());
               return batchDTO;
            }).collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch batch names", e);
        }

    }
}
