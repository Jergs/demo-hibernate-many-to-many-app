package com.example.demohibernateapp.jdbc;

import com.example.demohibernateapp.entity.Course;
import com.example.demohibernateapp.entity.Instructor;
import com.example.demohibernateapp.entity.InstructorDetail;
import com.example.demohibernateapp.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndReviewDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        try (factory; Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            Course course = new Course("Some C# course");

            course.addReview(new Review("Great course"));
            course.addReview(new Review("Great"));
            course.addReview(new Review("Cool course"));
            course.addReview(new Review("Cool"));

            // Save the course
            session.save(course);

            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
