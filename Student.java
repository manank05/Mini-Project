package com.example.ons.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    private BigDecimal balance = BigDecimal.ZERO;

    public Student() {}
    public Student(String name, Course course, BigDecimal balance) {
        this.name = name;
        this.course = course;
        this.balance = balance;
    }
    // getters / setters ...
}
