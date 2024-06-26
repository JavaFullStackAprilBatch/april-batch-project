package com.example.aprilbatchproject.repository;


import com.example.aprilbatchproject.entity.Batches;

import java.util.List;

import org.hibernate.engine.jdbc.batch.spi.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends JpaRepository<Batches, Long> {


    @Query(nativeQuery = true, value = "select * from batches b where b.batch_name= :batchName")
    public Batches findByBatchName(String batchName);
    @Query(nativeQuery = true, value = "select * from batches b where b.trainer_id= :trainer_id")
    public List<Batches> findByTrainerId(Long trainer_id);
}
