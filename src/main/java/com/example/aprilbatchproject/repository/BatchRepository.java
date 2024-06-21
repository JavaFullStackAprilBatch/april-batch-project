package com.example.aprilbatchproject.repository;


import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.entity.StatusType;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends JpaRepository<Batches, Long> {


    @Query(nativeQuery = true, value = "select * from batches b where b.batch_name= :batchName")
    public Batches findByBatchName(String batchName);

	public List<Batches> findByStatus(StatusType status);
}
