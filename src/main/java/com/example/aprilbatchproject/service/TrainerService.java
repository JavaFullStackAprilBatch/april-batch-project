package com.example.aprilbatchproject.service;

import java.util.List;

import com.example.aprilbatchproject.dto.TrainerDTO;
import com.example.aprilbatchproject.entity.Trainers;
import com.example.aprilbatchproject.exception.ResourceNotFoundException;
import com.example.aprilbatchproject.util.DataConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aprilbatchproject.repository.TrainerRepository;

@Service
public class TrainerService {
	@Autowired
	TrainerRepository trainerRepository;


	public TrainerDTO createTrainers(TrainerDTO trainerDTO) {
		Trainers trainers = DataConverter.convertTrainerDtoToTrainer(trainerDTO);
		Trainers trainers1 = trainerRepository.save(trainers);
		if (trainers1.getName() != null)
			return trainerDTO;
		else
			return null;
	}
	
	public List<TrainerDTO>  getAllTrainers(){
		List<String> trainerNames = trainerRepository.findAllDistinctTrainerNames();
		if(trainerNames.isEmpty())
			throw new ResourceNotFoundException("No Trainer found");
		return DataConverter.convertToTrainerDTOs(trainerNames);
	}

	public TrainerDTO getTrainerdetailsById(Long id)
	{
		try {

			Trainers trainers=trainerRepository.findById(id).get();
		return  DataConverter.converToTrainerDTO(trainers);
		}catch (Exception e)
		{
			throw new RuntimeException("Trainer details not found");
		}

	}




}
