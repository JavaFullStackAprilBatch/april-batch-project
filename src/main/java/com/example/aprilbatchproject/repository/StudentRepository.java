package com.example.aprilbatchproject.repository;

import com.example.aprilbatchproject.entity.Students;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Students, Long> {

	
	 List<Students> findByName(String name);


    @Query(nativeQuery = true, value = "select * from students s where s.name = :name")
    Students findByStudentName(String name);

}