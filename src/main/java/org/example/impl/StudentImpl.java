package org.example.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.config.DataBaseConfig;
import org.example.dao.StudentDao;
import org.example.entity.Course;
import org.example.entity.Student;

import java.util.List;

public class StudentImpl implements StudentDao {
    EntityManagerFactory entityManagerFactory= DataBaseConfig.createEntityManagerFactory();

    public void createStudentTable() {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery("create table if not exists students(id serial primary key, name varchar(26) )").executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void saveStudent(Student student) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(student);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Student getStudentById(Long id) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.find(Student.class,id);

        entityManager.getTransaction().commit();
        entityManager.close();
        return null;
    }

    public List<Student> getAllStudent() {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Student> students = entityManager.createQuery("select s from Student s ").getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        return students;
    }

    public Student updateStudent(Long id, Student student) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Student student1 = entityManager.find(Student.class, id);
        student1.setId(id);
        student1.setName(student.getName());
        student1.setAge(student.getAge());
        student1.setCourses(student.getCourses());
        entityManager.merge(student1);

        entityManager.getTransaction().commit();
        entityManager.close();
        return student1;
    }

    public void cleanStudentTable() {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery("delete  from students").executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void dropTable() {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery("drop table students ").executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public void deleteStudentById(Long id) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
