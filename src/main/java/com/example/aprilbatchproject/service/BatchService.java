package com.example.aprilbatchproject.service;


import com.example.aprilbatchproject.dto.BatchDto;
import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.entity.Trainers;
import com.example.aprilbatchproject.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatchService {
    @Autowired
    BatchRepository batchRepository;
//    public String createStudentsBatch(Batches batches){
//        batchRepository.save(batches);
//        return "Data saved";
//    }
    @Autowired
    TrainerRepository trainerRepository

    public String createStudentsBatch(BatchDto batchDto){
        Trainers trainer = trainerRepository.getTrainerByName(batchDto.getTrainerName());
                batchRepopsitory.save(batches);
        return "Data saved";
    }
}
