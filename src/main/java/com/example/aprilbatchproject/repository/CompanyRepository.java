package com.example.aprilbatchproject.repository;


import com.example.aprilbatchproject.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {


    List<Company> findByName(String name);


}
