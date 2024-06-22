package com.example.aprilbatchproject.controller;

import java.util.List;

import com.example.aprilbatchproject.dto.TrainerDTO;
import com.example.aprilbatchproject.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.aprilbatchproject.service.TrainerService;

@RestController
@RequestMapping("/trainers")

public class TrainerController {
	@Autowired
	TrainerService trainerService;
	@GetMapping("/getTrainers")
	public ResponseEntity<ApiResponse<List<TrainerDTO>>> getAllTrainers() {
		List<TrainerDTO> trainerNames = trainerService.getAllTrainers();
		ApiResponse<List<TrainerDTO>> response = new ApiResponse<>(true, "Trainers fetched successfully", trainerNames);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/createTrainers")
	public ResponseEntity<ApiResponse<TrainerDTO>> createTrainers(@RequestBody TrainerDTO trainerDTO) {
		TrainerDTO createdTrainers = trainerService.createTrainers(trainerDTO);
		if (createdTrainers != null)
			return new ResponseEntity<>(new ApiResponse<>(true, "Trainers created successfully", createdTrainers), HttpStatus.CREATED);
		else
			return new ResponseEntity<>(new ApiResponse<>(false, "Failed to insert trainers", createdTrainers), HttpStatus.BAD_REQUEST);

	}

}
