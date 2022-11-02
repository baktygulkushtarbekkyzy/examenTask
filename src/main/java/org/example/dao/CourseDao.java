package org.example.dao;

import org.example.entity.Company;
import org.example.entity.Course;

import java.util.List;

public interface CourseDao {

    void createCourseTable();

    void saveCourse(Course course);

    Course getCourseById(Long id);

    List<Course> getAllCourse();

    Course updateCourse(Long id , Course course );

    void cleanCourseTable();

    void dropTable();

    void deleteCourseById(Long id );

    void assignCompanyToCourse(Long StudentId,Long courseId);

}
