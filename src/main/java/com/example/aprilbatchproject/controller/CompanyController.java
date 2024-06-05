package com.example.aprilbatchproject.controller;


import com.example.aprilbatchproject.entity.Company;
import com.example.aprilbatchproject.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyService service;


    @PostMapping("/create")
    public Company createCompanyData(@RequestBody Company company) {
        return service.crateCompany(company);
    }

    @GetMapping("/getCompanies")
    public List<Company> getCompanies() {
        return service.getCompanies();

    }


    @GetMapping("/getCompanies/{id}")
    public Company getCompanies(@PathVariable Long id) {
        return service.getCompanies(id);

    }

    @PutMapping("/updateCompany/{id}")
    public Company updateCompany(@PathVariable Long id, @RequestBody Company company) {
        return service.updateCompany(id, company);

    }

    @PutMapping("/updateCompany")
    public Company updateCompanyByName(@RequestParam String name, @RequestBody Company company) {
//        return service.updateCompanyByName(name, company);

        return null;


    }

    @GetMapping("/getNames")
    public List<Company> getCompaniesByName(@RequestParam String name) {
        System.out.println("the name is " + name);

        return service.getCompaniesByName(name);


    }

}
