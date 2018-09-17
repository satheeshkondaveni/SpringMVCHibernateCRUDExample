package com.ksbs.sys.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ksbs.sys.model.Student;

@Repository("studentDao")
public class StudentDAOImpl implements StudentDAOI {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@SuppressWarnings("unchecked")
	public List<Student> getAllStudents() {
		Session session = this.sessionFactory.getCurrentSession();
		System.out.println(" getAllStudents ===> ");
		//List<Student> studentList = session.createQuery("from Student").list();
		Criteria c = session.createCriteria(com.ksbs.sys.model.Student.class);
		c.addOrder(Order.desc("marks"));
		List<Student> studentList= c.list();
		
		return studentList;
	}
	
	public Student getStudent(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Student student = (Student) session.get(Student.class, new Integer(id));
		return student;
	}

	public Student addStudent(Student student) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(student);
		return student;
	}

	public void updateStudent(Student student) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(student);
	}

	public void deleteStudent(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Student p = (Student) session.load(Student.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}
	}	
}
