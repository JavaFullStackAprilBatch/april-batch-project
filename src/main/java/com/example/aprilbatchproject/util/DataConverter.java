package com.example.aprilbatchproject.util;
import com.example.aprilbatchproject.dto.AddressDTO;
import com.example.aprilbatchproject.dto.BatchDTO;
import com.example.aprilbatchproject.dto.AddressDTO;
import com.example.aprilbatchproject.dto.CourseDTO;
import com.example.aprilbatchproject.dto.StudentDTO;
import com.example.aprilbatchproject.dto.TrainerDTO;
import com.example.aprilbatchproject.entity.Courses;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.entity.Courses;
import com.example.aprilbatchproject.entity.Students;
import com.example.aprilbatchproject.entity.Trainers;
import com.example.aprilbatchproject.exception.ResourceNotFoundException;
import com.example.aprilbatchproject.entity.Address;
import com.example.aprilbatchproject.entity.Students;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class DataConverter {
    public static List<CourseDTO> convertToCourseDTOs(List<String> courseNames) {
        List<CourseDTO> courseDTOs = new ArrayList<>();
        for (String courseName : courseNames) {
            courseDTOs.add(new CourseDTO(courseName));
        }
        return courseDTOs;
    }
    public static List<TrainerDTO> convertToTrainerDTOs(List<String> courseNames) {
        List<TrainerDTO> trainerDTOs = new ArrayList<>();
        for (String courseName : courseNames) {
            trainerDTOs.add(new TrainerDTO(courseName));
        }
        return trainerDTOs;
    }
    public static Trainers convertTrainerDtoToTrainer(TrainerDTO trainerDTO) {
        Trainers trainers = new Trainers();
        trainers.setName(trainerDTO.getTrainerName());
        trainers.setEmail(trainerDTO.getEmail());
        trainers.setPhone(trainerDTO.getPhone());
        trainers.setSpecialization(trainerDTO.getSpecialization());
        return trainers;
    }

    //conver the batches to DTO to fetch the name

    public static BatchDTO convertDTOtoBatches(Batches batches)
    {
        BatchDTO batchDTO=new BatchDTO();

        batchDTO.setBatchName(batches.getBatch_name());
        batchDTO.setBatchStart(batches.getStart_date());
        batchDTO.setBatchEnd(batches.getEnd_date());


        if(batches.getCourses()!=null){
            batchDTO.setCourseName(batches.getCourses().getCourseName());
        }
        else {
            batchDTO.setCourseName(null);
        }
        if(batches.getTrainer()!=null)
        {
            batchDTO.setTrainerName(batches.getTrainer().getName());
        }else {
            batchDTO.setTrainerName(null);
        }


        batchDTO.setBatchStatus(batches.getStatus());
        long studentscount= batches.getStudents().stream().map(
                Students::getName
        ).count();
        batchDTO.setNoofstudents(studentscount);
        return batchDTO;
    }

    //TrinerDto Conversion
    public static TrainerDTO converToTrainerDTO(Trainers trainers)
    {
        TrainerDTO trainerDTO=new TrainerDTO();
        trainerDTO.setTrainerName(trainers.getName());
        //trainerDTO.setName(trainers.getName());changes while resolve the conflict
        trainerDTO.setEmail(trainers.getEmail());
        trainerDTO.setPhone(trainers.getPhone());
        trainerDTO.setSpecialization(trainers.getSpecialization());
        return trainerDTO;
    }


    public static   List<BatchDTO> convertToBatchDTOs(List<Batches> batches){
        List<BatchDTO> batchDTOS = new ArrayList<>();
        for(Batches batch : batches){
            BatchDTO batchDTO = new BatchDTO(batch.getBatch_name());
            batchDTO.setBatchName(batch.getBatch_name());
            batchDTO.setBatchStart(batch.getStart_date());
            batchDTO.setBatchEnd(batch.getEnd_date());
            Courses courses = batch.getCourses();
            if(courses !=null){
                batchDTO.setCourseName(courses.getCourseName());
            }else {
                throw new ResourceNotFoundException("CourseName Not Found");
            }
            Trainers trainers = batch.getTrainer();
            if(trainers != null){
                batchDTO.setTrainerName(trainers.getName());
            }else {
                throw new ResourceNotFoundException("TrainerName Not Found");
            }
            batchDTO.setBatchStatus(batch.getStatus());
            batchDTOS.add(batchDTO);
        }
        return batchDTOS;
    }

    public static AddressDTO convertAddressToAddresssDto(Students s) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressLine1(s.getAddress()== null ?"":s.getAddress().getAddressLine1());
        addressDTO.setCity(s.getAddress()== null ?"":s.getAddress().getCity());
        addressDTO.setState(s.getAddress()== null ?"":s.getAddress().getState());
        addressDTO.setZipCode(s.getAddress()== null ?"":s.getAddress().getZipCode());
        return addressDTO;
    }


    public static StudentDTO convertDTOtoStudents(Students students) {
        // logic to convert StudentsDto to Students

        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setName(students.getName());
        studentDTO.setEmail(students.getEmail());
        studentDTO.setPhone(students.getPhone());

        if (students.getAddress() != null) {
            AddressDTO addressDTO = new AddressDTO();
            Address address = students.getAddress();
            addressDTO.setAddressLine1(address.getAddressLine1());
            addressDTO.setCity(address.getCity());
            addressDTO.setState(address.getState());
            addressDTO.setZipCode(address.getZipCode());
            studentDTO.setAddress(addressDTO);
        }

        if (students.getBatches() != null) {
            List<String> batchNames = students.getBatches().stream()
                    .map(Batches::getBatch_name)
                    .collect(Collectors.toList());
            studentDTO.setBatchNames(batchNames);
        }
        return studentDTO;
    }



    //convertcoursestoDTo
    public static CourseDTO convertcoursestoDTo(Courses courses)
    {
        CourseDTO courseDTO=new CourseDTO();
        courseDTO.setCourseName(courses.getCourseName());
        courseDTO.setCourseContent(courses.getCourseContent());
        return courseDTO;
    }

}
