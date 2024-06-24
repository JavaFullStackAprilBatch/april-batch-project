package com.example.aprilbatchproject.service;


import com.example.aprilbatchproject.dto.BatchDTO;
import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.entity.Courses;
import com.example.aprilbatchproject.entity.StatusType;
import com.example.aprilbatchproject.entity.Trainers;
import com.example.aprilbatchproject.exception.ResourceNotFoundException;
import com.example.aprilbatchproject.repository.BatchRepository;
import com.example.aprilbatchproject.repository.CourseRepository;
import com.example.aprilbatchproject.repository.TrainerRepository;


import java.util.ArrayList;
import java.util.List;

import com.example.aprilbatchproject.util.DataConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BatchService {
    @Autowired
    BatchRepository batchRepository;
    @Autowired
    TrainerRepository trainerRepository;
    @Autowired
    CourseRepository courseRepository;
    public String createStudentsBatch(Batches batches){
        batchRepository.save(batches);
        return "Data saved";
    }

    public BatchDTO createBatch (BatchDTO dto) {
        Trainers trainer = null;
        Courses course = null;
        Batches batch = new Batches();
        try {
            trainer = trainerRepository.getTrainerByName(dto.getTrainerName());
            course = courseRepository.getCourseByName(dto.getCourseName());

            batch.setBatch_name(dto.getBatchName());
            batch.setStart_date(dto.getBatchStart());
            batch.setEnd_date(dto.getBatchEnd());
            batch.setCourses(course);
            batch.setTrainer(trainer);
            batch.setStatus(dto.getBatchStatus());

        }catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

        if(trainer == null){
            throw new ResourceNotFoundException("Trainer Not Found");
        }

        if(course == null){
            throw new ResourceNotFoundException("Course Not Found");
        }

        Batches saveBatch =  batchRepository.save(batch);

        return dto;
    }


	public List<BatchDTO> findAllCompletedBatchDetail() {
		
		List<Batches> batches = new ArrayList<Batches>();
		batches = batchRepository.findByStatus(StatusType.Completed);
		List<BatchDTO> batchDTO = new ArrayList<BatchDTO>();
		
		for (int i=0;i<batches.size();i++) {
			
			BatchDTO tempBatch = new BatchDTO();
			tempBatch.setBatchName(batches.get(i).getBatch_name());
			tempBatch.setBatchStart(batches.get(i).getStart_date());
			tempBatch.setBatchEnd(batches.get(i).getEnd_date());
			tempBatch.setBatchStatus(batches.get(i).getStatus());
			tempBatch.setTrainerName(batches.get(i).getTrainer().getName());
			tempBatch.setCourseName(batches.get(i).getCourses().getCourse_name());
			
			batchDTO.add(tempBatch);
		}
		
		return batchDTO;
	}

    public List<BatchDTO>getOngoingBatchs(String status){

       List<Batches> batches =  batchRepository.findByStatusType(status);
        if(batches.isEmpty())
            throw new ResourceNotFoundException("No Batches found");
        return DataConverter.convertToBatchDTOs(batches);
    }


}
