package com.JavaMail;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();   
		try{
			   
			String email= request.getParameter("Uemail"); 
			String pass= request.getParameter("Upass");
			java.util.Date utilDate = new java.util.Date();
	   	    java.sql.Timestamp Dtime = new Timestamp(utilDate.getTime());
	   	    
			String sql= "select * from User where Uemail=? and Upass=?";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Email","root","Vinsql@123");
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1,email);
            ps.setString(2,pass);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()!=false) 
            {
            	
               out.println(rs.getString("Uname")+ "    In :)");
               out.println(Dtime);}
			   
			} catch (ClassNotFoundException e) {
			    
				e.printStackTrace();
			} catch (SQLException e) {
				
				out.println("Incorrect Password or Email-id!!!  :(");
				e.printStackTrace();
			}		
			
			out.flush();
	}

}
