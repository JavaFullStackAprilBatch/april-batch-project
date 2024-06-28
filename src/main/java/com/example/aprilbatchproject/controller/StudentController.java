package com.example.aprilbatchproject.controller;
import com.example.aprilbatchproject.dto.StudentDTO;
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

    
    @GetMapping("/getStudentByName")
    public ResponseEntity<ApiResponse<List<StudentDTO>>> getStudentsByName(@RequestParam String name){
    	List<StudentDTO> students = studentService.getStudentByName(name);
		return new ResponseEntity<>(new ApiResponse<>(true, "Students fetched successfully", students), HttpStatus.OK); 
    }


    @PutMapping("/updatestudentbyname")
    public ResponseEntity<ApiResponse<StudentDTO>> updateStudentByName(@RequestParam String name, @RequestBody StudentDTO studentDTO){
        StudentDTO updateStudent = studentService.getStudentByName(name, studentDTO);

        return new ResponseEntity<>(new ApiResponse<>(true, "Students Updated successfully", updateStudent), HttpStatus.OK);

    }
    
    @GetMapping("/getstudent/{id}")
    public ResponseEntity<ApiResponse<StudentDTO>> getStudentById(@PathVariable Long id){
    	StudentDTO student = studentService.getStudent(id);

		return new ResponseEntity<>(new ApiResponse<>(true, "Student fetched successfully", student), HttpStatus.OK);
    	
    }
}