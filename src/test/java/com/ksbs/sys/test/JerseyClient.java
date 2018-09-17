package com.ksbs.sys.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ksbs.sys.model.Student;

public class JerseyClient {
	public void getAllStudentDetails() {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:8585/jersey-spring-jpa/rest/student");
		WebTarget details = base.path("getAllStudents");
		Response response = details.request(MediaType.APPLICATION_JSON).get();
		
		System.out.println(" getAllStudentDetails ==> "+response.toString());
		
		String students = response.readEntity(String.class);
		System.out.println(" getAllStudentDetails ==> "+students.toString());
		
		/* List<Student> listOfEmployees = employees.getStudentList();
	     System.out.println(Arrays.toString( listOfEmployees.toArray(new Student[listOfEmployees.size()]) ));*/
		
		
	/*	List<Student> list = details.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Student>>() {});*/
		
	   /* list.forEach(student ->  System.out.println(student.getRoll_no()+", "+ student.getStudentName()+", "+ student.getBranch()+", "+student.getMarks());
	    */
	    client.close();
	}
	public void getStudent(int studentId) {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:8585/jersey-spring-jpa/rest/student");
		WebTarget studentById = base.path("student-info/{studentId}").resolveTemplate("studentId", studentId);
		String student = studentById.request(MediaType.APPLICATION_JSON).get(String.class);		
		System.out.println(" getStudent ==> "+student.toString());
	    client.close();
	}
	public void createStudent(Student student) {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:8585/jersey-spring-jpa/rest/student");
		WebTarget add = base.path("create-student");
		Response response = add.request(MediaType.APPLICATION_JSON)	.post(Entity.json(student));
		
		System.out.println("Response Http Status: "+ response.getStatus());
		String std = response.readEntity(String.class);
		System.out.println(" getAllStudentDetails ==> "+std.toString());
        System.out.println(response.getLocation());
        
	    client.close();
	}
	public void updateStudent(Student student) {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:8585/jersey-spring-jpa/rest/student");
		WebTarget update = base.path("updateStudent");
		Response response = update.request(MediaType.APPLICATION_JSON)
				.put(Entity.json(student));
		
		System.out.println("Response Http Status: "+ response.getStatus());
		Student resStudent = response.readEntity(Student.class);
		System.out.println(resStudent.getRoll_no()+", "+ resStudent.getStudentName()+", "+ resStudent.getBranch()+", "+resStudent.getMarks());
        
	    client.close();
	}
	public void deleteStudent(int studentId) {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:8585/jersey-spring-jpa/rest/student");
		WebTarget deleteById = base.path("deleteStudent/{studentId}").resolveTemplate("studentId", studentId);
		Response response = deleteById.request(MediaType.APPLICATION_JSON).delete();
		
		System.out.println("Response Http Status: "+ response.getStatus());
		if(response.getStatus() == 204) {
			System.out.println("Data deleted successfully.");
		}
        
	    client.close();
	}	
	public static void main(String[] args) {
		JerseyClient jerseyClient = new JerseyClient();
	    
		Student student = new Student();
		student.setStudentName("Test 12 Client");
		student.setBranch("Test 12 branch Client");
		student.setMarks(90);
		
		jerseyClient.getAllStudentDetails();
		jerseyClient.getStudent(10);
		
		jerseyClient.createStudent(student);		
		student.setRoll_no(12);
		jerseyClient.updateStudent(student);
		
		jerseyClient.deleteStudent(12);
	}
}
