package com.example.ons.model;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;

    private String courseName;
    private String duration;

    // constructors, getters, setters
    public Course() {}
    public Course(String courseName, String duration) {
        this.courseName = courseName;
        this.duration = duration;
    }
    // getters and setters...
}
