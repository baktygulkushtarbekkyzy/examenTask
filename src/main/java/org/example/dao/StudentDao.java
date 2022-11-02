package org.example.dao;

import org.example.entity.Course;
import org.example.entity.Student;

import java.util.List;

public interface StudentDao {

    void createStudentTable();

    void saveStudent(Student course);

    Student getStudentById(Long id);

    List<Student> getAllStudent();

    Student updateStudent(Long id, Student student);

    void cleanStudentTable();

    void deleteStudentById(Long id);

    void dropTable();
}
