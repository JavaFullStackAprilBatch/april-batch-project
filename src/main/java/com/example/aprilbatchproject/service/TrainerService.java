package com.example.aprilbatchproject.service;

import java.util.List;

import com.example.aprilbatchproject.dto.TrainerDTO;
import com.example.aprilbatchproject.exception.ResourceNotFoundException;
import com.example.aprilbatchproject.util.DataConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aprilbatchproject.repository.TrainerRepository;

@Service
public class TrainerService {
	@Autowired
	TrainerRepository trainerRepository;
	
	public List<TrainerDTO>  getAllTrainers(){
		List<String> trainerNames = trainerRepository.findAllDistinctTrainerNames();
		if(trainerNames.isEmpty())
			throw new ResourceNotFoundException("No Trainer found");
		return DataConverter.convertToTrainerDTOs(trainerNames);
	}

}
