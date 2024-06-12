package com.example.aprilbatchproject.controller;


import com.example.aprilbatchproject.dto.BatchDto;
import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/batches")
public class BtachController {

    @Autowired
    BatchService batchService;
//    @PostMapping("/newStudent")
//    public String createNewBatch(@RequestBody BatchDto batches){
//        batchService.createStudentsBatch(batches);
//        return "New Student Data Save Successfully";
//    }

    @DeleteMapping("/batch/{batchId}")
    public String deleteBatch(@PathVariable Long batchId){
        batchService.deleteBatch(batchId);
        return "Batch %d deleted Successfully".formatted(batchId);
    }
}
