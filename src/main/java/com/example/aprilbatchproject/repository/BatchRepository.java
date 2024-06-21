package com.example.aprilbatchproject.repository;


import com.example.aprilbatchproject.entity.Batches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends JpaRepository<Batches, Long> {

    @Query(nativeQuery = true, value = "select * from batches b where b.batch_name= :batchName")
    Batches findByBatchName(String batchName);

}
