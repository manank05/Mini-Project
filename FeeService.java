package com.example.ons.service;

import com.example.ons.dao.StudentDAO;
import com.example.ons.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class FeeService {
    private final StudentDAO studentDAO;

    @Autowired
    public FeeService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    /**
     * Make payment: deduct amount from student balance.
     * This method is transactional — if any exception happens, it rolls back.
     */
    @Transactional(rollbackFor = Exception.class)
    public void makePayment(Integer studentId, BigDecimal amount) {
        Student s = studentDAO.getStudentById(studentId);
        if (s == null) throw new IllegalArgumentException("Student not found");
        BigDecimal newBalance = s.getBalance().subtract(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalStateException("Insufficient balance for payment");
        }
        s.setBalance(newBalance);
        studentDAO.updateStudent(s);
        // optionally save a payment record (implement Payments DAO similarly)
    }

    @Transactional(rollbackFor = Exception.class)
    public void refund(Integer studentId, BigDecimal amount) {
        Student s = studentDAO.getStudentById(studentId);
        if (s == null) throw new IllegalArgumentException("Student not found");
        s.setBalance(s.getBalance().add(amount));
        studentDAO.updateStudent(s);
        // optionally record refund in payments table as negative or separate record
    }
}
