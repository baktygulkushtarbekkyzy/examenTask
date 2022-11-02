package org.example.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.config.DataBaseConfig;
import org.example.dao.CompanyDao;
import org.example.entity.Company;

import java.util.List;

public class CompanyImpl implements CompanyDao {
    private EntityManagerFactory entityManagerFactory = DataBaseConfig.createEntityManagerFactory();

    public void createCompanyTable() {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery("create table if not exists companies(id serial primary key, name varchar(26) )").executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void saveCompany(Company company) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(company);

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public Company getCompanyById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Company company = entityManager.find(Company.class, id);


        entityManager.getTransaction().commit();
        entityManager.close();
        return company;
    }

    public List<Company> getAllCompany() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Company> companies = entityManager.createQuery("select c from Company c").getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        return companies;
    }

    public Company updateCompany(Long id, Company company) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Company company1 = entityManager.find(Company.class, id);
        company1.setId(id);
        company1.setName(company.getName());
        company1.setCourses(company.getCourses());
        entityManager.merge(company1);

        entityManager.getTransaction().commit();
        entityManager.close();

        return company1;
    }

    public void cleanCompanyTable() {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery("delete from  companies").executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public void dropTable() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery("drop table if exists companies");

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public void deleteCompanyById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Company company = entityManager.find(Company.class, id);
        entityManager.remove(company);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
