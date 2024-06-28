package com.example.aprilbatchproject.service;

import com.example.aprilbatchproject.dto.AddressDTO;
import com.example.aprilbatchproject.dto.StudentDTO;
import com.example.aprilbatchproject.entity.Address;
import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.entity.StatusType;
import com.example.aprilbatchproject.entity.Students;
import com.example.aprilbatchproject.exception.ResourceNotFoundException;
import com.example.aprilbatchproject.repository.AddressRepository;
import com.example.aprilbatchproject.repository.BatchRepository;
import com.example.aprilbatchproject.util.DataConverter;
import com.example.aprilbatchproject.util.StudentUtil;
import org.springframework.stereotype.Service;
import com.example.aprilbatchproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    BatchRepository batchRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    StudentUtil studentUtil;


  //create student details
    public StudentDTO createOrUpdateStudent(Students student,StudentDTO studentDTO) {
        try {
            student.setName(studentDTO.getName());
            student.setEmail(studentDTO.getEmail());
            student.setPhone(studentDTO.getPhone());
            // Handle address
            if (studentDTO.getAddress() != null) {
                Address address=studentUtil.GetStudentAddress(studentDTO,student.getAddress());
                addressRepository.save(address);
                student.setAddress(address);
            }
            // Handle batches
            List<String> batchNames = studentDTO.getBatchNames();
            if (batchNames != null) {
                List<Batches> batches=studentUtil.GetBatchNames(studentDTO);
                 student.setBatches(batches);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create student: " + e.getMessage());
        }
        Students savedStudent = studentRepository.save(student);
        return studentDTO;
    }
    
    //Get all Students
    public List<Students> getAllStudents() {
        return studentRepository.findAll();
    	

    }

	public List<StudentDTO> getStudentByName(String name) {
		
		List<Students> studentsByName = studentRepository.findByName(name);
		List<StudentDTO> studentsDTO = new ArrayList<StudentDTO>();
		StudentDTO tempDTO;
		List<Batches> batches;
		for( int i=0; i<studentsByName.size() ; i++) {
			List<String> batchNames = new ArrayList<String>();
			batches = studentsByName.get(i).getBatches();
			for (Batches batch :batches) {
				batchNames.add(batch.getBatch_name());
			}
			tempDTO = new StudentDTO(studentsByName.get(i).getName(), batchNames, studentsByName.get(i).getEmail(), studentsByName.get(i).getPhone());
			studentsDTO.add(tempDTO);
		}
				
		return studentsDTO;
	}

       public StudentDTO getStudentByName(String name, StudentDTO studentDTO) {
        Students existingStudent = null;
        List<Batches> batches;
        Address address = null;

        try {
            existingStudent = studentRepository.findByStudentName(name);
            address = studentUtil.GetStudentAddress(studentDTO, existingStudent.getAddress());
            existingStudent.setAddress(address);
            existingStudent.setEmail(studentDTO.getEmail());
            existingStudent.setPhone(studentDTO.getPhone());

            batches = studentUtil.GetBatchNames(studentDTO);
            existingStudent.setBatches(batches);

        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

        if (existingStudent == null) {
            throw new ResourceNotFoundException("No details found with student name: " + name);
        }
        if(batches.isEmpty()){
            throw new ResourceNotFoundException("No Batch details found for this student: " + name);
        }
        if(address == null){
            throw new ResourceNotFoundException("No Address details found for this student: " + name);
        }
        // Save entity
        Students savedStudent = studentRepository.save(existingStudent);

        return studentDTO;
    }
    //Update Student By id


    public StudentDTO updateStudentById(Long id, StudentDTO studentDTO) {

        Students student = studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student id not found"+ id));
        createOrUpdateStudent(student,studentDTO);
        Students updatedstudent=studentRepository.save(student);
        return DataConverter.convertDTOtoStudents(updatedstudent);
    }


    public List<StudentDTO> getStudentsByStatus(String status) {

        StatusType statusType = StatusType.valueOf(status);
        List<StudentDTO> studentDTOS = new ArrayList<>();

        List<Batches> batches = batchRepository.findByBatchStatus(statusType);
        for (Batches batches1 : batches) {
            List<Students> students = studentRepository.findByBatch(batches1.getId());
            for (Students s : students) {
                studentDTOS.add(new StudentDTO(s.getName(), DataConverter.convertAddressToAddresssDto(s), s.getEmail(), s.getPhone(), getBatchList(s)));
            }
        }
        return studentDTOS;
    }

    private List<String> getBatchList(Students s) {
        List<String> batchNames = new ArrayList<>();
        batchNames = s.getBatches().stream().map(a -> a.getBatch_name()).collect(Collectors.toList());
        return batchNames;
    }

      
	public StudentDTO getStudent(Long id) {
		
		try {
			
		Students student = studentRepository.findById(id).get();
		
		List<String> batchNames =  new ArrayList<String>();
		for (int i=0 ; i< student.getBatches().size() ; i++) {
			batchNames.add(student.getBatches().get(i).getBatch_name());
		}
		
		StudentDTO studentDto = new StudentDTO(student.getName(), new AddressDTO(student.getAddress().getAddressLine1(), student.getAddress().getCity(),
				student.getAddress().getState(), student.getAddress().getZipCode()), student.getEmail(), student.getPhone(), batchNames);
	
		return studentDto;
		}
		
		catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("No Student found for that id");
		}
	}

}

