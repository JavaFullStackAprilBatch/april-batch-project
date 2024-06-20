package com.example.aprilbatchproject.controller;
import com.example.aprilbatchproject.dto.BatchDTO;
import com.example.aprilbatchproject.dto.StudentDTO;
import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.response.ApiResponse;
import com.example.aprilbatchproject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/createStuddent")
    public ResponseEntity<ApiResponse<StudentDTO>> createStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO createdStudent = studentService.createStudent(studentDTO);
        return new ResponseEntity<>(new ApiResponse<>(true, "Student created successfully", createdStudent), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentDTO>>> getAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents();
        return new ResponseEntity<>(new ApiResponse<>(true, "Students fetched successfully", students), HttpStatus.OK);
    }

 /*   @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentDTO>> getStudentById(@PathVariable Long id){
        StudentDTO student = studentService.getStudentById(id);
        return new ResponseEntity<>(new ApiResponse<>(true,"Student fethed successfully",student),HttpStatus.OK);
    }*/

}