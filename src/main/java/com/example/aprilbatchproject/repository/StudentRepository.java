package com.example.aprilbatchproject.repository;

import com.example.aprilbatchproject.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Students, Long> {
}