package com.ksbs.rest.init.modal;

import java.util.List;

import com.ksbs.sys.model.Student;

public class StudentStatusModal {
	private int status;
	private String message;
	private StudentModal student;
	private List<Student> studentList;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public StudentModal getStudent() {
		return student;
	}
	public void setStudent(StudentModal student) {
		this.student = student;
	}
	public List<Student> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	
	
	
	
	
}
