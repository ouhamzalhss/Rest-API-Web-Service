package com.restApi.jersey.restDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentRepo {
	
	Connection conn = null;

	public StudentRepo() {
		
		String url = "jdbc:mysql://localhost:3306/restdb";
		String user = "root";
		String password = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);

		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public List<Student> getAll(){
		List<Student> students = new ArrayList<Student>();
		String query = "select * from student";
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Student std = new Student();
				std.setId(rs.getInt("id"));
				std.setName(rs.getString("name"));
				std.setPoints(rs.getInt("points"));
				students.add(std);
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return students;
	}
	
	public Student getOne(int id) {

		Student student = new Student();
		String query = "select * from student where id="+id;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) {
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setPoints(rs.getInt("points"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return student;
		
	}

	public void create(Student student) {
		String sql = "insert into student values(?,?,?)";
		try {
			PreparedStatement pr = conn.prepareStatement(sql);
			pr.setInt(1, student.getId());
			pr.setString(2, student.getName());
			pr.setInt(3, student.getPoints());
			pr.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void update(Student student) {
		String sql = "update student set name=?, points=? where id=? ";
		try {
			PreparedStatement pr = conn.prepareStatement(sql);
			pr.setString(1, student.getName());
			pr.setInt(2, student.getPoints());
			pr.setInt(3, student.getId());
			pr.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void delete(int id) {
		String sql = "delete from student where id=? ";
		try {
			PreparedStatement pr = conn.prepareStatement(sql);
			pr.setInt(1, id);
			pr.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	

}
