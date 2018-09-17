package com.ksbs.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ksbs.sys.dao.StudentDAOI;
import com.ksbs.sys.model.Student;

@Service("studentService")
public class StudentServiceImpl implements StudentServiceI {
	@Autowired
	StudentDAOI stuentDao;
	
	@Override
	@Transactional
	public List<Student> getAllStudents() {
		return stuentDao.getAllStudents();
	}

	@Override
	@Transactional
	public Student getStudent(int id) {
		// TODO Auto-generated method stub
		return stuentDao.getStudent( id);
	}

	@Override
	@Transactional
	public Student addStudent(Student student) {
		// TODO Auto-generated method stub
		return stuentDao.addStudent( student);
	}

	@Override
	@Transactional
	public void updateStudent(Student student) {
		// TODO Auto-generated method stub
		stuentDao.updateStudent( student);
	}

	@Override
	@Transactional
	public void deleteStudent(int id) {
		// TODO Auto-generated method stub
		stuentDao.deleteStudent(id);
	}
}
