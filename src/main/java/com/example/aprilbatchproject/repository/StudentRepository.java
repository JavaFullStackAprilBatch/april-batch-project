package com.example.aprilbatchproject.repository;

import com.example.aprilbatchproject.dto.StudentDTO;
import com.example.aprilbatchproject.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Students, Long> {

}