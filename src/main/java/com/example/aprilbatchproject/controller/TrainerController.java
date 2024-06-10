package com.example.aprilbatchproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.example.aprilbatchproject.service.TrainerService;


@RestController
@RequestMapping("/trainers")

public class TrainerController {
	@Autowired
	TrainerService trainerService;
	@GetMapping("/getTrainers")
	public List<String> getAllTrainers(){
		return trainerService.getAllTrainers();
	}
    
	@DeleteMapping("/deleteTrainer/{id}")
	 public String deleteTrainer(@PathVariable Long id) {
		 trainerService.deleteTrainer(id);
		 return " Trainer Deleted";
	 }
	@DeleteMapping("/deleteTrainerbyname")
	 public String deleteTrainerByNmae(@RequestParam String name){
		 trainerService.deleteTrainerByName(name);
		 return "Trainer Deleted by name";
}
	@DeleteMapping("/deletealltrainer")
	 public String deleteAllTrainer() {
		 trainerService.deleteAllTrainer();
		 return " All Trainers Deleted";
	 }
	
}
