package com.example.aprilbatchproject.service;


import com.example.aprilbatchproject.dto.BatchDTO;
import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.repository.BatchRepository;
import com.example.aprilbatchproject.util.DataConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BatchService {
    @Autowired
    BatchRepository batchRepository;
    public String createStudentsBatch(Batches batches){
        batchRepository.save(batches);
        return "Data saved";
    }

    public  List<BatchDTO> getCompletedBatchs() {
        List<Batches> batches = batchRepository.findByBatchStatus();
        List<BatchDTO> batchDTOS =  DataConverter.convertBatchsToBatchDto(batches);
        return batchDTOS;
    }
}
