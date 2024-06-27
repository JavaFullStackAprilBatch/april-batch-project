package com.example.aprilbatchproject.repository;

import com.example.aprilbatchproject.entity.Batches;
import com.example.aprilbatchproject.entity.StatusType;
import com.example.aprilbatchproject.entity.Students;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Students, Integer> {

    @Query(nativeQuery = true, value = "select * from students s where s.name = :name")
    Students findByStudentName(String name);



    @Query("select DISTINCT s from Students s left join fetch s.batches bd where bd.id= :id")
   List<Students> findByBatch(long id);


}