package com.example.aprilbatchproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aprilbatchproject.repository.TrainerRepository;

@Service
public class TrainerService {
	@Autowired
	TrainerRepository trainerRepository;
	
	public List<String> getAllTrainers(){
		return trainerRepository.getAllTrainers();
	}

}
