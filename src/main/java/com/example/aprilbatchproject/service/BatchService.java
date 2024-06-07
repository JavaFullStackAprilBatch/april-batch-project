package com.example.aprilbatchproject.service;


import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BatchService {
    @Autowired
    BatchRepository batchRepository;
    public String createStudentsBatch(Batches batches){
        batchRepository.save(batches);
        return "Data saved";
    }

}
