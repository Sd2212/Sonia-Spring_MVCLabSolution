package com.management.student.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.management.student.entity.Student;

@Service
public class StudentServiceImpl implements StudentService {

	private SessionFactory sessionFactory;

	private Session session;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}

	}

	@Override
	public List<Student> findAll() {
		Transaction tx = session.beginTransaction();

		List<Student> students = session.createQuery("from Student").list();

		tx.commit();

		return students;
	}

	@Transactional
	public void save(Student theStudent) {

		Transaction tx = session.beginTransaction();

		session.saveOrUpdate(theStudent);

		tx.commit();

	}

	@Transactional
	public void deleteById(int id) {

		Transaction tx = session.beginTransaction();

		Student Student = session.get(Student.class, id);

		session.delete(Student);

		tx.commit();

	}

	@Transactional
	public void print(List<Student> Student) {

		for (Student s : Student) {
			System.out.println(s);
		}
	}

	@Override
	public Student findById(int id) {
		Student Student = new Student();

		Transaction tx = session.beginTransaction();

		Student = session.get(Student.class, id);

		tx.commit();

		return Student;
	}

}
