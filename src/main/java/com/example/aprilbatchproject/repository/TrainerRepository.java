package com.example.aprilbatchproject.repository;

import com.example.aprilbatchproject.entity.Trainers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<Trainers,Long> {
}
