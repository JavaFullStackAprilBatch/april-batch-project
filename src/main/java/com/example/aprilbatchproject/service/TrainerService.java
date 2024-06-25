package com.example.aprilbatchproject.service;

import com.example.aprilbatchproject.dto.TrainerDTO;
import com.example.aprilbatchproject.entity.Trainers;
import com.example.aprilbatchproject.exception.ResourceNotFoundException;
import com.example.aprilbatchproject.repository.TrainerRepository;
import com.example.aprilbatchproject.util.DataConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerService {
    @Autowired
    TrainerRepository trainerRepository;

    public List<TrainerDTO> getAllTrainers() {
        List<String> trainerNames = trainerRepository.findAllDistinctTrainerNames();
        if (trainerNames.isEmpty())
            throw new ResourceNotFoundException("No Trainer found");
        return DataConverter.convertToTrainerDTOs(trainerNames);
    }

    public List<TrainerDTO> getTrainerDetailsByName(String trainerName) {

        List<TrainerDTO> trainerDTOS = trainerRepository.findTrainerByName(trainerName)
                .stream()
                .map(trainers -> {
                    TrainerDTO trainerDTO = new TrainerDTO(trainers.getName(), trainers.getEmail(), trainers.getPhone(), trainers.getSpecialization());
                    return trainerDTO;
                }).collect(Collectors.toList());

        if (trainerDTOS.isEmpty())
            throw new ResourceNotFoundException("Trainer(s) not found");
        return trainerDTOS;

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



