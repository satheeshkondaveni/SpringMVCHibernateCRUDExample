package com.ksbs.sys.dao;

import java.util.List;

import com.ksbs.sys.model.Student;

public interface StudentDAOI {
	public List<Student> getAllStudents();
	public Student getStudent(int id);
	public Student addStudent(Student student);
	public void updateStudent(Student student);
	public void deleteStudent(int id);
	
}
