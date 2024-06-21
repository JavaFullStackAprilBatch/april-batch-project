package com.example.aprilbatchproject.repository;

import com.example.aprilbatchproject.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(nativeQuery = true, value = "select * from address a where a.address_id= :id ")
    Address findByAddressId(long id);

}