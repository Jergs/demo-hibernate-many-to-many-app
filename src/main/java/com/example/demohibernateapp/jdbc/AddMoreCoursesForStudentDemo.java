package com.example.demohibernateapp.jdbc;

import com.example.demohibernateapp.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddMoreCoursesForStudentDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try (factory; Session session = factory.getCurrentSession()) {
            // Start transaction
            session.beginTransaction();

            Student student = session.get(Student.class, 2);

            Course course = new Course("How to solve quizzes");
            Course course2 = new Course("Hibernate course");
            Course course3 = new Course("Spring Course");

            course.addStudent(student);
            course2.addStudent(student);
            course3.addStudent(student);

            session.save(course);
            session.save(course2);
            session.save(course3);

            // Commit transaction
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
