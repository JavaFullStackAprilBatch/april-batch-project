package com.example.aprilbatchproject.service;

import java.util.List;

import com.example.aprilbatchproject.dto.DeleteTrainerDTO;
import com.example.aprilbatchproject.dto.TrainerDTO;
import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.entity.Trainers;
import com.example.aprilbatchproject.exception.ConstraintViolationException;
import com.example.aprilbatchproject.exception.ResourceNotFoundException;
import com.example.aprilbatchproject.util.DataConverter;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.aprilbatchproject.repository.BatchRepository;
import com.example.aprilbatchproject.repository.TrainerRepository;
import com.example.aprilbatchproject.response.ApiResponse;

@Service
public class TrainerService {
	@Autowired
	TrainerRepository trainerRepository;
	 @Autowired
	 private BatchRepository batchRepository;

	
	 public List<TrainerDTO>  getAllTrainers(){
		List<String> trainerNames = trainerRepository.findAllDistinctTrainerNames();
		if(trainerNames.isEmpty())
			throw new ResourceNotFoundException("No Trainer found");
		return DataConverter.convertToTrainerDTOs(trainerNames);
	}

	    
	    public DeleteTrainerDTO deleteTrainer(Long id){
	        Trainers trainer = trainerRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found"));
	        DeleteTrainerDTO deletetrainerDTO = new DeleteTrainerDTO(trainer.getName(),trainer.getEmail(),trainer.getPhone(),
					trainer.getSpecialization());
	        List<Batches> batches = batchRepository.findByTrainerId(id);
	        if (batches != null && !batches.isEmpty()) {
	        	throw new ConstraintViolationException(" This Trainer cannot be deleted as trainer is associated with batch.So replace the trainer before delete");
	        }
	        else {
	        trainerRepository.delete(trainer);
	        }
	        return deletetrainerDTO;
	    }
	}

	
	
	
	
	
	
	
	
	
	

