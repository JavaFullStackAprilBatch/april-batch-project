package com.example.aprilbatchproject.repository;


import com.example.aprilbatchproject.entity.Batches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batches, Long> {


}
