package com.ksbs.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ksbs.rest.init.modal.StudentModal;
import com.ksbs.sys.model.Student;
import com.ksbs.sys.service.StudentServiceI;


@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentServiceI studnetService;
	
	@RequestMapping(value = "/getAllStudents", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getStudents(Model model) {
		List<Student> listOfStudents = studnetService.getAllStudents();
		model.addAttribute("student", new Student());
		model.addAttribute("listOfStudents", listOfStudents);
		return "studentDetails";
	}

	@RequestMapping(value = "/getStudent/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Student getStudentById(@PathVariable int id) {
		return studnetService.getStudent(id);
	}

	@RequestMapping(value = "/addStudent", method = RequestMethod.POST, headers = "Accept=application/json")
	public String addStudent(@ModelAttribute("student") Student student) {	
		if(student.getRoll_no()==0)
		{
			studnetService.addStudent(student);
		}
		else
		{	
			studnetService.updateStudent(student);
		}
		return "redirect:/student/getAllStudents.do";
	}

	@RequestMapping(value = "/{id}/updateStudent", method = RequestMethod.GET, headers = "Accept=application/json")
	public String updateStudent(@PathVariable("id") int id,Model model) {
		 model.addAttribute("student", this.studnetService.getStudent(id));
	        model.addAttribute("listOfStudents", this.studnetService.getAllStudents());
	        return "studentDetails";
	}

	@RequestMapping(value = "/{id}/deleteStudent", method = RequestMethod.GET, headers = "Accept=application/json")
	public String deleteStudent(@PathVariable("id") int id) {
		studnetService.deleteStudent(id);
		 return "redirect:/student/getAllStudents.do";

	}	
	
	@RequestMapping(value = "/webServiceStudentDetails", method = RequestMethod.GET, headers = "Accept=application/json")
	public String webServiceStudentDetails(Model model) {
		
		List<Student> listOfStudents = studnetService.webServiceStudentDetails();
		model.addAttribute("student", new Student());
		model.addAttribute("listOfStudents", listOfStudents);
		return "webServiceStudentDetails";
	}
	
	@RequestMapping(value = "/getWSStudentById/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public StudentModal getWSStudentById(@PathVariable int id) {
		return studnetService.getWSStudentById(id);
	}

	@RequestMapping(value = "/wsAddStudent", method = RequestMethod.POST, headers = "Accept=application/json")
	public String wsAddStudent(@ModelAttribute("student") StudentModal student) {	
		if(student.getRoll_no()==0)
		{
			studnetService.wsAddStudent(student);
		}
		else
		{	
			studnetService.wsUpdateStudent(student);
		}
		
		return "redirect:/student/webServiceStudentDetails.do";
	}

	@RequestMapping(value = "/{id}/wsUpdateStudent", method = RequestMethod.GET, headers = "Accept=application/json")
	public String wsUpdateStudent(@PathVariable("id") int id,Model model) {
		 model.addAttribute("student", this.studnetService.getWSStudentById(id));
	        model.addAttribute("listOfStudents", this.studnetService.webServiceStudentDetails());
	        return "webServiceStudentDetails";
	}

	@RequestMapping(value = "/{id}/wsDeleteStudent", method = RequestMethod.GET, headers = "Accept=application/json")
	public String wsDeleteStudent(@PathVariable("id") int id) {
		studnetService.wsDeleteStudent(id);
		 return "redirect:/student/webServiceStudentDetails.do";

	}	
	
}
