package org.example.dao;

import org.example.entity.Company;

import java.util.List;

public interface CompanyDao {

    void createCompanyTable();

    void saveCompany(Company company);

    Company getCompanyById(Long id);

    List<Company> getAllCompany();

    Company updateCompany(Long id , Company company);

    void cleanCompanyTable();

    void dropTable();

    void deleteCompanyById(Long id );



}
