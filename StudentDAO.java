package com.example.ons.dao;

import com.example.ons.model.Student;
import java.util.List;

public interface StudentDAO {
    Integer saveStudent(Student student);
    void updateStudent(Student student);
    Student getStudentById(Integer id);
    List<Student> getAllStudents();
    void deleteStudent(Integer id);
}
