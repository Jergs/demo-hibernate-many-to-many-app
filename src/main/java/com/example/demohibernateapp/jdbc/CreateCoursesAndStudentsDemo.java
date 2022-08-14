package com.example.demohibernateapp.jdbc;

import com.example.demohibernateapp.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesAndStudentsDemo {

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
            session.beginTransaction();

            Course course = new Course("Some C# course");
            // Save the course
            session.save(course);

            Student student1 = new Student("John", "Doe", "test@gmail.com");
            Student student2 = new Student("Marry", "Poppins ", "test2@gmail.com");

            course.addStudent(student1);
            course.addStudent(student2);

            // Save students
            session.save(student1);
            session.save(student2);

            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
