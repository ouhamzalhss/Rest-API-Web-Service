package com.restApi.jersey.restDemo;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("students")
public class StudentResource {
	StudentRepo repo = new StudentRepo();
	
	@GET 
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Student> getStudents() {
		
		return repo.getAll();
	}
	
	@GET 
	@Path("student/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Student getStudent(@PathParam("id") int id) {
		
		return repo.getOne(id);
	}
	
	@POST
	@Path("student")	
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Consumes(MediaType.APPLICATION_JSON)
	public Student createStudent(Student student) {
		repo.create(student);
		return student;
	}
	
	
	@PUT
	@Path("student")	
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Consumes(MediaType.APPLICATION_JSON)
	public Student updateStudent(Student student) {
		if(repo.getOne(student.getId()).getId()==0) {
			repo.create(student);
		}else {
			repo.update(student);
		}
		
		return student;
	}
	
	
	@DELETE
	@Path("student/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Student deleteStudent(@PathParam("id") int id) {
		 Student student = repo.getOne(id);
		 if(student.getId()!=0)  repo.delete(id);
		 return student;
	}
	

}
