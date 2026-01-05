package com.zenith.firstjobapp.company.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zenith.firstjobapp.company.Company;
import com.zenith.firstjobapp.company.CompanyRepository;
import com.zenith.firstjobapp.company.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean createCompany(Company company) {
        companyRepository.save(company);
        return true;
    }

    @Override
    public boolean deleteCompany(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCompanyById(Long id, Company company) {
        Optional<Company> existingCompanyOpt = companyRepository.findById(id);
        if (existingCompanyOpt.isPresent()) {
            Company existingCompany = existingCompanyOpt.get();
            existingCompany.setName(company.getName());
            existingCompany.setDescription(company.getDescription());
            companyRepository.save(existingCompany);
            return true;
        }
        return false;
    }

    
}
