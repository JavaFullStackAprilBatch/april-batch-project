package com.example.aprilbatchproject.repository;


import com.example.aprilbatchproject.entity.Batches;
import org.hibernate.engine.jdbc.batch.spi.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batches, Long> {



    @Query("select id from Batches where batch_name=:name")
    public Batches findByBatchName(String name);

    @Query("select id,batch_name from Batches where status=Completed")
    public List<Batches> findByBatchStatus();

}
