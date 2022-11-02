package org.example.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.example.config.DataBaseConfig;
import org.example.dao.CourseDao;
import org.example.entity.Company;
import org.example.entity.Course;
import org.example.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class CourseImpl implements CourseDao {
    EntityManagerFactory entityManagerFactory = DataBaseConfig.createEntityManagerFactory();

    public void createCourseTable() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery("create table if not exists courses(id serial primary key, name varchar(26) )").executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void saveCourse(Course course) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(course);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Course getCourseById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Course course = entityManager.find(Course.class, id);

        entityManager.getTransaction().commit();
        entityManager.close();
        return course;
    }

    public List<Course> getAllCourse() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Course> courses = entityManager.createQuery("select c from Course c").getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        return courses;
    }

    public Course updateCourse(Long id, Course course) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Course course1 = entityManager.find(Course.class, id);
        course1.setId(id);
        course1.setName(course.getName());
        course1.setCompany(course.getCompany());
        course1.setStudents(course.getStudents());
        entityManager.merge(course1);

        entityManager.getTransaction().commit();
        entityManager.close();
        return course1;
    }

    public void cleanCourseTable() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery("delete from courses").executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public void dropTable() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery("drop table courses").executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public void deleteCourseById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public void assignCompanyToCourse(Long studentId, Long courseId) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Student student = entityManager.find(Student.class, studentId);
        Course course = entityManager.find(Course.class, courseId);


        List<Student> students=new ArrayList<>();
        students.add(student);

       course.setStudents(students);
       student.addCourse(course);

       entityManager.persist(course);
        entityManager.getTransaction().commit();
        entityManager.close();
    }


//    public void assignInstructorToCourse(Long instructorId, Long courseId) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//
//        Course course = entityManager.find(Course.class, courseId);
//        Instructor instructor1 = entityManager.find(Instructor.class, instructorId);
//
//        List<Course> courses = new ArrayList<>();
//        courses.add(course);
//
//        instructor1.setCourses(courses);
//        course.addInstructor(instructor1);
//
//        entityManager.persist(instructor1);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//    }



}
