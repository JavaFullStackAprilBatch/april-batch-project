package com.example.aprilbatchproject.controller;


import com.example.aprilbatchproject.dto.BatchDTO;
import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.response.ApiResponse;
import com.example.aprilbatchproject.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
public class BtachController {

    @Autowired
    BatchService batchService;
    @PostMapping("/newStudent")
    public String createNewStudentBatch(@RequestBody Batches batches){
        batchService.createStudentsBatch(batches);
        return "New Student Data Save Successfully";
    }

    @PostMapping("/newBatch")
    public ResponseEntity<ApiResponse<BatchDTO>> createNewBatch(@RequestBody BatchDTO dto){
        BatchDTO createNewBatch = batchService.createBatch(dto);
        return new ResponseEntity<>(new ApiResponse<>(true, "Student created successfully",createNewBatch ), HttpStatus.CREATED);
    }
}
