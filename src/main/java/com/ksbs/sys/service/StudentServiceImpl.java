package com.ksbs.sys.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ksbs.rest.init.modal.StudentModal;
import com.ksbs.rest.init.modal.StudentStatusModal;
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
	
	public List<Student> webServiceStudentDetails() {
		
		List<Student> std_list  =new ArrayList<Student>();
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:2222/jersey-spring-jpa/rest/student");
		WebTarget details = base.path("getAllStudents");
		Response response = details.request(MediaType.APPLICATION_JSON).get();			
		String students = response.readEntity(String.class);		
		   ObjectMapper mapper = new ObjectMapper();
	        try {
				StudentStatusModal std_model = mapper.readValue(students.toString(), StudentStatusModal.class);
				std_list=std_model.getStudentList();
				System.out.println("webServiceStudentDetails : "+std_list);	
				   
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				client.close();
			}
		
		return std_list;
	}
	public StudentModal getWSStudentById(int studentId) {
		// TODO Auto-generated method stub
		StudentModal std_obj=null;
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:2222/jersey-spring-jpa/rest/student");
		WebTarget studentById = base.path("student-info/{studentId}").resolveTemplate("studentId", studentId);
		String student = studentById.request(MediaType.APPLICATION_JSON).get(String.class);		
		System.out.println(" getStudent ==> "+student.toString());
		  ObjectMapper mapper = new ObjectMapper();
	        try {
				StudentStatusModal std_model = mapper.readValue(student.toString(), StudentStatusModal.class);
				std_obj=	 std_model.getStudent();
				System.out.println("getWSStudentById List==> : "+std_obj.toString());	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				 client.close();
			}
		
		
	   
		return std_obj;
	}
	public StudentModal wsAddStudent(StudentModal student) {
		StudentModal std_obj=null;
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:2222/jersey-spring-jpa/rest/student");
		WebTarget add = base.path("create-student");
		Response response = add.request(MediaType.APPLICATION_JSON)	.post(Entity.json(student));
		
		System.out.println("Response Http Status: "+ response.getStatus());
		String std = response.readEntity(String.class);
		System.out.println(" createStudent ==> "+std.toString());
		  ObjectMapper mapper = new ObjectMapper();
	        try {
				StudentStatusModal std_model = mapper.readValue(std.toString(), StudentStatusModal.class);
				std_obj=	 std_model.getStudent();
				   System.out.println("wsAddStudent List==> : "+std_model.getStudent());	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				client.close();
				
			}
        
	    
		
		// TODO Auto-generated method stub
		return std_obj;
	}
	public void wsUpdateStudent(StudentModal student) {
		// TODO Auto-generated method stub
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:2222/jersey-spring-jpa/rest/student");
		WebTarget update = base.path("updateStudent");
		Response response = update.request(MediaType.APPLICATION_JSON).put(Entity.json(student));
		
		System.out.println("Response Http Status: "+ response.getStatus());
		String resStudent = response.readEntity(String.class);
		System.out.println(" updateStudent : "+resStudent.toString());
        
	    client.close();
	}
	public void wsDeleteStudent(int studentId) {
		// TODO Auto-generated method stub
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:2222/jersey-spring-jpa/rest/student");
		WebTarget deleteById = base.path("deleteStudent/{studentId}").resolveTemplate("studentId", studentId);
		Response response = deleteById.request(MediaType.APPLICATION_JSON).delete();
		
		System.out.println("Response Http Status: "+ response.getStatus());
		String resStudent = response.readEntity(String.class);
		System.out.println(" deleteStudent : "+resStudent.toString());
        
	    client.close();
	}
}
