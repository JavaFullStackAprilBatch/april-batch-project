package com.example.aprilbatchproject.repository;


import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.entity.StatusType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.hibernate.engine.jdbc.batch.spi.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batches, Long> {


    @Query(nativeQuery = true, value = "select * from batches b where b.batch_name= :batchName")
    public Batches findByBatchName(String batchName);


    @Query("select b from Batches b where b.status= :status")
    public List<Batches> findByBatchStatus(StatusType status );
}
