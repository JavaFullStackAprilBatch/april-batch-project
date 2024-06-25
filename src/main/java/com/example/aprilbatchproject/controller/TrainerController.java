package com.example.aprilbatchproject.controller;

import java.util.List;

import com.example.aprilbatchproject.dto.TrainerDTO;
import com.example.aprilbatchproject.entity.Trainers;
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
	@GetMapping("/getTrainerDetailsByName")
	public ResponseEntity<ApiResponse<List<TrainerDTO>>> getTrainerDetailsByName(@RequestParam String trainerName) {
		List<TrainerDTO> trainerNames = trainerService.getTrainerDetailsByName(trainerName);
		return new ResponseEntity<>(new ApiResponse<>(true, "Trainer Details fetched successfully", trainerNames), HttpStatus.OK);
	}

	@GetMapping("/gettrainerdetailsbyid/{id}")
	public ResponseEntity<ApiResponse<TrainerDTO>> getTrainerDetailsById(@PathVariable Long id)
	{
		TrainerDTO trainerdetailsbyid= trainerService.getTrainerdetailsById(id);
		ApiResponse<TrainerDTO> response=new ApiResponse<>(true,"Trainer details Retrived for this id",trainerdetailsbyid);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
