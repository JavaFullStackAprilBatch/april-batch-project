package com.example.aprilbatchproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.aprilbatchproject.entity.Trainers;

@Repository
public interface TrainerRepository extends JpaRepository<Trainers, Long>{
	@Query ("Select distinct name from Trainers")
	public List<String> findAllDistinctTrainerNames();

	@Query( nativeQuery = true, value = "select t.id from trainers t where t.name = :trainerName")
	Trainers getTrainerByName(String trainerName);

}
