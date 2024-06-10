package com.example.aprilbatchproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aprilbatchproject.entity.Trainers;
import com.example.aprilbatchproject.repository.TrainerRepository;
import com.example.student.entity.Student;

@Service
public class TrainerService  {
	@Autowired
	TrainerRepository trainerRepository;
	
	public List<String> getAllTrainers(){
		return trainerRepository.getAllTrainers();
	}

	public void deleteTrainer(Long id)  {
		// TODO Auto-generated method stub
		
		Trainers existingTrainers = trainerRepository.findById(id).get();
        trainerRepository.delete(existingTrainers);
		
	}

	public void deleteTrainerByName(String name)  {
		// TODO Auto-generated method stub
		Trainers existingTrainer =  trainerRepository.findByName(name);
		trainerRepository.delete(existingTrainer);
		
		
		
	}

	public void deleteAllTrainer() {
		// TODO Auto-generated method stub
		trainerRepository.deleteAll();
	}
	

	

}
