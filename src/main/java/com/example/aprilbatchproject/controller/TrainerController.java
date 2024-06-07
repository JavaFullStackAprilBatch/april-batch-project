package com.example.aprilbatchproject.controller;


import com.example.aprilbatchproject.entity.Trainers;
import com.example.aprilbatchproject.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trainers")
public class TrainerController {


    @Autowired
    TrainerService trainerService;

    @PostMapping("/createTrainers")
    public String createTrainers(@RequestBody Trainers trainers){
        trainerService.createTrainers(trainers);
        return "sucesfully created";
    }
}
