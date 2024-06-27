package com.example.aprilbatchproject.controller;



import com.example.aprilbatchproject.dto.BatchDTO;
import com.example.aprilbatchproject.dto.CourseDTO;
import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.entity.StatusType;
import com.example.aprilbatchproject.entity.StatusType;
import com.example.aprilbatchproject.response.ApiResponse;
import com.example.aprilbatchproject.service.BatchService;
import org.hibernate.engine.jdbc.batch.spi.Batch;
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
    public String createNewStudentBatch(@RequestBody Batches batches) {
        batchService.createStudentsBatch(batches);
        return "New Student Data Save Successfully";
    }

    @PostMapping("/newBatch")
    public ResponseEntity<ApiResponse<BatchDTO>> createNewBatch(@RequestBody BatchDTO dto) {
        BatchDTO createNewBatch = batchService.createBatch(dto);
        return new ResponseEntity<>(new ApiResponse<>(true, "New Batch created successfully", createNewBatch), HttpStatus.CREATED);
    }
    @GetMapping("/status")
    public ResponseEntity<ApiResponse<List<BatchDTO>>> retrieveOngingBatchs(@RequestParam String status){
        List<BatchDTO> batches =batchService.getOngoingBatchs(status);
        ApiResponse<List<BatchDTO>> response = new ApiResponse<>(true, "Batches fetched successfully", batches);
        return  ResponseEntity.ok(response);
    }

    //Get the batch detail based on the batch Name
    @GetMapping("/getbatchdetailsbyname")
    public ResponseEntity<ApiResponse<BatchDTO>> getBatchName(@RequestParam String name)
    {
        BatchDTO batchDTOS= batchService.getBatchName(name);
        ApiResponse<BatchDTO> response=new ApiResponse<>(true,"All the batchdetails retrived",batchDTOS);
        return new ResponseEntity<>(response, HttpStatus.OK);


    @GetMapping("/batchNames")
    public ResponseEntity<ApiResponse<List<BatchDTO>>> getListOfBatchNames() {
            List<BatchDTO> batchNames = batchService.getListOfBatchNames();
            return new ResponseEntity<>(new ApiResponse<>(true, "List of batch names fetched successfully", batchNames), HttpStatus.OK);

    }
    @GetMapping("/batchNamesByStatus")
    public ResponseEntity<ApiResponse<List<BatchDTO>>> getListOfBatchNamesByStaus(@RequestParam(required = false) StatusType statusType) {
        List<BatchDTO> batchNames = batchService.getListOfBatchNamesByStatus(statusType);
        return new ResponseEntity<>(new ApiResponse<>(true, "List of batch names fetched successfully", batchNames), HttpStatus.OK);


    }

}
