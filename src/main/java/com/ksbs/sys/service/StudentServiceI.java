package com.ksbs.sys.service;

import java.util.List;

import com.ksbs.sys.model.Student;

public interface StudentServiceI {
	
	public List<Student> getAllStudents();
	public Student getStudent(int id);
	public Student addStudent(Student student);
	public void updateStudent(Student student);
	public void deleteStudent(int id);
	
}
