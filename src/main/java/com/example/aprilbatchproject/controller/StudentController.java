package com.example.aprilbatchproject.controller;
import com.example.aprilbatchproject.dto.StudentDTO;
import com.example.aprilbatchproject.entity.Students;
import com.example.aprilbatchproject.response.ApiResponse;
import com.example.aprilbatchproject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/getallstudents")
   public ResponseEntity<ApiResponse<List<StudentDTO>>> getAllStudents()
    {
        List<StudentDTO> getAllStudent= studentService.getAllStudents();
       ApiResponse<List<StudentDTO>> response = new ApiResponse<>(true,"Students data Retrived", getAllStudent);
       return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getstudent/{id}")
    public ResponseEntity<ApiResponse<StudentDTO>> getStudentById(@PathVariable Long id) {
        Optional<StudentDTO> studentById = studentService.getStudentById(id);

        if (studentById.isPresent()) {
            ApiResponse<StudentDTO> response = new ApiResponse<>(true, "Student data retrieved", studentById.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            ApiResponse<StudentDTO> response = new ApiResponse<>(false, "Student not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getstudentbyname")
    public ResponseEntity<ApiResponse<List<StudentDTO>>> getStudentByName(@RequestParam String name)
    {
        List<StudentDTO> studentDTOByName= studentService.getStudentsByname(name);
        if(studentDTOByName.isEmpty()){
            ApiResponse<List<StudentDTO>> response = new ApiResponse<>(false,"Students name not found", studentDTOByName);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }else {
            ApiResponse<List<StudentDTO>> response = new ApiResponse<>(true,"Students data Retrived based on the name", studentDTOByName);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }


    }


    @PutMapping("/updatestudentbyid/{id}")
    public ResponseEntity<ApiResponse<StudentDTO>> updateStudentById(@PathVariable Long id, @RequestBody StudentDTO studentDTO)
    {
        StudentDTO studentDTObyid=studentService.updateStudentById(id,studentDTO);
       ApiResponse<StudentDTO> response=new ApiResponse<>(true,"student data updated",studentDTObyid);
       return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/updatestudentbyname")
    public ResponseEntity<ApiResponse<StudentDTO>> updateStudentByName(@RequestParam String name,@RequestBody StudentDTO studentDTO)
    {
        StudentDTO studentDTObyName=studentService.updateStudentByName(name,studentDTO);
        ApiResponse<StudentDTO> response=new ApiResponse<>(true,"student data updated",studentDTObyName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Delete By id
    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteById(@PathVariable Long id)
    {

         studentService.deleteById(id);
        ApiResponse<Void> response=new ApiResponse<>(true,"Student detail deleted succesfully",null);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @DeleteMapping("/deletebyname")
    public ResponseEntity<ApiResponse<Void>> deleteByName(@RequestParam String name)
    {
        studentService.deleteByName(name);
        ApiResponse<Void> response=new ApiResponse<>(true,"Student detail deleted succesfully",null);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

}