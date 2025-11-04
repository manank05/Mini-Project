package com.example.ons.dao;

import com.example.ons.model.Student;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public StudentDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer saveStudent(Student student) {
        Session session = sessionFactory.getCurrentSession();
        return (Integer) session.save(student);
    }

    @Override
    public void updateStudent(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.update(student);
    }

    @Override
    public Student getStudentById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Student.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> getAllStudents() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Student").list();
    }

    @Override
    public void deleteStudent(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Student s = session.get(Student.class, id);
        if (s != null) session.delete(s);
    }
}
