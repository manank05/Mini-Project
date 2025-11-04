package com.example.ons.service;

import com.example.ons.dao.StudentDAO;
import com.example.ons.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StudentService {
    private final StudentDAO studentDAO;

    @Autowired
    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Transactional
    public Integer addStudent(Student student) {
        return studentDAO.saveStudent(student);
    }

    @Transactional(readOnly = true)
    public Student getStudent(Integer id) {
        return studentDAO.getStudentById(id);
    }

    @Transactional
    public void updateStudent(Student s) {
        studentDAO.updateStudent(s);
    }

    @Transactional
    public void deleteStudent(Integer id) {
        studentDAO.deleteStudent(id);
    }

    @Transactional(readOnly = true)
    public List<Student> listAll() {
        return studentDAO.getAllStudents();
    }
}
