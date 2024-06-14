package com.example.aprilbatchproject.repository;


import com.example.aprilbatchproject.entity.Batches;
import org.hibernate.engine.jdbc.batch.spi.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends JpaRepository<Batches, Long> {


//    @Query("select id from Batches where batch_name=:name")
//    public Batches findByBatchName(String name);

    @Query("SELECT b FROM Batches b WHERE b.batch_name = :name")
    Batches findByBatchName(@Param("name") String name);
}
