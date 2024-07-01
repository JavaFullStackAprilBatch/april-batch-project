package com.example.aprilbatchproject.controller;
import com.example.aprilbatchproject.dto.StudentDTO;
import com.example.aprilbatchproject.dto.StudentDTOwithoutBatchName;
import com.example.aprilbatchproject.entity.StatusType;
import com.example.aprilbatchproject.dto.TrainerDTO;
import com.example.aprilbatchproject.entity.Students;
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

    @PostMapping("/createstudent")
    public ResponseEntity<ApiResponse<StudentDTO>> createStudent(@RequestBody StudentDTO studentDTO) {
        Students students=new Students();
        StudentDTO createdStudent = studentService.createOrUpdateStudent(students,studentDTO);
        ApiResponse <StudentDTO> response=new ApiResponse<>(true,"Student created successfully",createdStudent);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/all")

    public ResponseEntity<ApiResponse<List<StudentDTOwithoutBatchName>>> getAllStudents() {
       List<StudentDTOwithoutBatchName> allstudents = studentService.getAllStudents();
        return new ResponseEntity<>(new ApiResponse<>(true, "Students fetched successfully", allstudents), HttpStatus.OK);
    	
    }
  
//
//    @GetMapping("/getStudentByName")
//    public ResponseEntity<ApiResponse<List<StudentDTO>>> getStudentsByName(@RequestParam String name){
//    	List<StudentDTO> students = studentService.getStudentByName(name);
//		return new ResponseEntity<>(new ApiResponse<>(true, "Students fetched successfully", students), HttpStatus.OK);
//    }


    @PutMapping("/updatestudentbyname")
    public ResponseEntity<ApiResponse<StudentDTO>> updateStudentByName(@RequestParam String name, @RequestBody StudentDTO studentDTO){
        StudentDTO updateStudent = studentService.getStudentByName(name, studentDTO);

        return new ResponseEntity<>(new ApiResponse<>(true, "Students Updated successfully", updateStudent), HttpStatus.OK);

    }
    
    @GetMapping("/getstudent/{id}")
    public ResponseEntity<ApiResponse<StudentDTO>> getStudentById(@PathVariable Integer id){
    	StudentDTO student = studentService.getStudent(id);

		return new ResponseEntity<>(new ApiResponse<>(true, "Student fetched successfully", student), HttpStatus.OK);
    }
  
  
    @PutMapping("/updatestudentbyid/{id}")
    public ResponseEntity<ApiResponse<StudentDTO>> updateStudentById(@PathVariable Integer id, @RequestBody StudentDTO studentDTO)
    {
        StudentDTO studentDTObyid = studentService.updateStudentById(id,studentDTO);
        ApiResponse<StudentDTO> response = new ApiResponse<>(true,"student data updated",studentDTObyid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/batchStatus")
    public ResponseEntity<ApiResponse<List<StudentDTO>>> getStudentsByStatus(@RequestParam String status){

        List<StudentDTO> students=studentService.getStudentsByStatus(status);
        if (students != null)
            return new ResponseEntity<>(new ApiResponse<>(true, "Students fetched successfully",students ), HttpStatus.OK);
        else
            return new ResponseEntity<>(new ApiResponse<>(false, "Failed to fetched students", students), HttpStatus.BAD_REQUEST);

    }



}