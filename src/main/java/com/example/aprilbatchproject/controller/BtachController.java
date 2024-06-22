package com.example.aprilbatchproject.controller;


import com.example.aprilbatchproject.dto.BatchDTO;
import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.response.ApiResponse;
import com.example.aprilbatchproject.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/batch")
public class BtachController {

    @Autowired
    BatchService batchService;
    @PostMapping("/newStudent")
    public String createNewStudentBatch(@RequestBody Batches batches){
        batchService.createStudentsBatch(batches);
        return "New Student Data Save Successfully";
    }

    @PostMapping("/registration/newBatch")
    public ResponseEntity<ApiResponse<BatchDTO>> createNewBatch(@RequestBody BatchDTO dto){
        BatchDTO createNewBatch = batchService.createBatch(dto);
        return new ResponseEntity<>(new ApiResponse<>(true, "New Batch created successfully",createNewBatch ), HttpStatus.CREATED);
    }
    //get completed batches name
    @GetMapping("/completedBatches")
    public ResponseEntity<ApiResponse<List<BatchDTO>>> getCompletedBatchesName() {
        List<BatchDTO> completedBatchDTOS = batchService.getCompletedBatchs();
        ApiResponse<List<BatchDTO>> response = new ApiResponse<>(true, "Completed batch fetches succesfully", completedBatchDTOS);
        return ResponseEntity.ok(response);
    }

    }
