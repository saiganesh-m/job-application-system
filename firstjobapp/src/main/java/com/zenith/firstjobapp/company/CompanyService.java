package com.zenith.firstjobapp.company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    Company getCompanyById(Long id);
    boolean createCompany(Company company);
    boolean updateCompanyById(Long id, Company company);
    boolean deleteCompany(Long id);
}
