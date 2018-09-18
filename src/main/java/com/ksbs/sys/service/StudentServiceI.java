package com.ksbs.sys.service;

import java.util.List;

import com.ksbs.rest.init.modal.StudentModal;
import com.ksbs.sys.model.Student;

public interface StudentServiceI {
	
	public List<Student> getAllStudents();
	public Student getStudent(int id);
	public Student addStudent(Student student);
	public void updateStudent(Student student);
	public void deleteStudent(int id);
	public List<Student> webServiceStudentDetails();
	public StudentModal getWSStudentById(int id);
	public StudentModal wsAddStudent(StudentModal student);
	public void wsUpdateStudent(StudentModal student);
	public void wsDeleteStudent(int id);
	
}
