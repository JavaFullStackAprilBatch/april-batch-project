package com.example.aprilbatchproject.repository;


import com.example.aprilbatchproject.entity.Batches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batches, Long> {
    @Query("select b.batch_name from Batches b")
    public List<String> findListOfBatchNames();
}
