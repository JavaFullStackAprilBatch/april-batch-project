package com.example.aprilbatchproject.service;


import com.example.aprilbatchproject.entity.Company;
import com.example.aprilbatchproject.repository.AddressRepository;
import com.example.aprilbatchproject.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    /*@Autowired
    AddressRepository addressRepository;*/

    public Company crateCompany(Company company) {

       /* if (company.getAddress() != null) {
            company.getAddress().forEach(address -> address.setCompany(company));
        }*/

        return companyRepository.save(company);
    }

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanies(Long id) {
        return companyRepository.findById(id).get();
    }


    public Company updateCompany(Long id, Company company) {

        Company existingRecord = companyRepository.findById(id).get();
//        existingRecord.setCompanyAddress(company.getCompanyAddress());
        existingRecord.setEmail(company.getEmail());
        existingRecord.setName(company.getName());
        companyRepository.save(existingRecord);
        companyRepository.save(company);
        return existingRecord;
    }


    public List<Company> getCompaniesByName(String name) {
        return companyRepository.findByName(name);

    }
}
