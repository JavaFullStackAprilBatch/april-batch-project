package com.example.aprilbatchproject.service;

import com.example.aprilbatchproject.entity.Trainers;
import com.example.aprilbatchproject.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerService {

    @Autowired
    TrainerRepository trainerRepository;

    public String createTrainers(Trainers trainers) {
        trainerRepository.save(trainers);
        return "saved";
    }
}
