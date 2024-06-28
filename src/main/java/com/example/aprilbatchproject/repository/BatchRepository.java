package com.example.aprilbatchproject.repository;


import com.example.aprilbatchproject.dto.BatchDTO;
import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.entity.StatusType;

import java.util.List;

import com.example.aprilbatchproject.entity.StatusType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BatchRepository extends JpaRepository<Batches, Long> {

    @Query(nativeQuery = true, value = "select * from batches b where b.batch_name= :batchName")
    public Batches findByBatchName(String batchName);

	public List<Batches> findByStatus(StatusType status);

    @Query(nativeQuery = true, value = "select * from aprilbatch.batches b where b.status_type= :status")
    public List<Batches> findByStatusType(String status);


    @Query(nativeQuery = true, value = "SELECT * FROM batches b WHERE LOWER(b.batch_name) = LOWER(:batchName)")
    public Batches getBatchByNameIgnoreCase(@Param("batchName") String batchName);

    List<Batches> findByStatus(StatusType statusType);


    @Query("select b from Batches b where b.status= :status")
    public List<Batches> findByBatchStatus(StatusType status );

}
