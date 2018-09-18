package com.ksbs.sys.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import com.ksbs.rest.init.modal.StudentStatusModal;
import com.ksbs.sys.model.Student;

public class JerseyClient {
	public void getAllStudentDetails() {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:2222/jersey-spring-jpa/rest/student");
		WebTarget details = base.path("getAllStudents");
		Response response = details.request(MediaType.APPLICATION_JSON).get();
		
		System.out.println(" getAllStudentDetails ==> "+response.toString());
		
		String students = response.readEntity(String.class);
		System.out.println(" getAllStudentDetails ==> "+students.toString());
		  ObjectMapper mapper = new ObjectMapper();
	        try {
				StudentStatusModal std_model = mapper.readValue(students.toString(), StudentStatusModal.class);
				   System.out.println("getStudentList : "+std_model.getStudentList());	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
		
		 HashMap<Object, Object> map = new HashMap<Object, Object>();
		 JSONObject jObject = new JSONObject(students.toString());
	        Iterator<?> keys = jObject.keys();

	        while( keys.hasNext() ){
	            Object key = keys.next();
	            Object value = (Object) jObject.get(key.toString()); 
	            map.put(key.toString(), value);

	        }

	        System.out.println("json : "+jObject);
	        System.out.println("map : "+map.get("studentList"));
	      
	      
	       
		/* List<Student> listOfEmployees = employees.getStudentList();
	     System.out.println(Arrays.toString( listOfEmployees.toArray(new Student[listOfEmployees.size()]) ));*/
		
		
	/*	List<Student> list = details.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Student>>() {});*/
		
	   /* list.forEach(student ->  System.out.println(student.getRoll_no()+", "+ student.getStudentName()+", "+ student.getBranch()+", "+student.getMarks());
	    */
		
		
	    client.close();
	}
	public void getStudent(int studentId) {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:2222/jersey-spring-jpa/rest/student");
		WebTarget studentById = base.path("student-info/{studentId}").resolveTemplate("studentId", studentId);
		String student = studentById.request(MediaType.APPLICATION_JSON).get(String.class);		
		System.out.println(" getStudent ==> "+student.toString());
		  ObjectMapper mapper = new ObjectMapper();
	        try {
				StudentStatusModal std_model = mapper.readValue(student.toString(), StudentStatusModal.class);
				   System.out.println("getStudent List==> : "+std_model.getStudent());	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	    client.close();
	}
	public void createStudent(Student student) {
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
				   System.out.println("getStudent List==> : "+std_model.getStudent());	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
	    client.close();
	}
	public void updateStudent(Student student) {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:2222/jersey-spring-jpa/rest/student");
		WebTarget update = base.path("updateStudent");
		Response response = update.request(MediaType.APPLICATION_JSON).put(Entity.json(student));
		
		System.out.println("Response Http Status: "+ response.getStatus());
		String resStudent = response.readEntity(String.class);
		System.out.println(" updateStudent : "+resStudent.toString());
        
	    client.close();
	}
	public void deleteStudent(int studentId) {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:2222/jersey-spring-jpa/rest/student");
		WebTarget deleteById = base.path("deleteStudent/{studentId}").resolveTemplate("studentId", studentId);
		Response response = deleteById.request(MediaType.APPLICATION_JSON).delete();
		
		System.out.println("Response Http Status: "+ response.getStatus());
		String resStudent = response.readEntity(String.class);
		System.out.println(" deleteStudent : "+resStudent.toString());
        
	    client.close();
	}	
	public static void main(String[] args) {
		JerseyClient jerseyClient = new JerseyClient();
	    
		Student student = new Student();
		student.setStudentName("Test 17 Client");
		student.setBranch("Test 17 branch Client");
		student.setMarks(67);
		
		jerseyClient.getAllStudentDetails();
		jerseyClient.getStudent(5);
		
		jerseyClient.createStudent(student);		
	//	student.setRoll_no(9);
	//	jerseyClient.updateStudent(student);
		
	//	jerseyClient.deleteStudent(6);
	}
}
