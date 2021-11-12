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

@WebServlet("/SendEmail")
public class SendEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			
			String From= request.getParameter("Fmail"); 
			String To= request.getParameter("Tmail"); 
		    String Sub= request.getParameter("Subject");
	   	    String Cont= request.getParameter("Message");
	   	    int Sender =1;
	   	    int  receiver = 1;
	   	    java.util.Date utilDate = new java.util.Date();
	   	    java.sql.Timestamp Dtime = new Timestamp(utilDate.getTime());
	   	    
	   	    String SQL="select * from User where Uemail=?";
	   	    String sql= "insert into Inbox(Fmail,Tmail,Subject,Message,Dtime,Sender,Receiver) values(?,?,?,?,?,?,?)";
	   	    
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Email","root","Vinsql@123");
            
            PreparedStatement Ps = conn.prepareStatement(SQL);
            Ps.setString(1,To);
            ResultSet Rs = Ps.executeQuery();
            PrintWriter out = response.getWriter();
            
            if(Rs.next()!=false)
            {
            	PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1,From);
                ps.setString(2,To);
                ps.setString(3,Sub);
                ps.setString(4,Cont);
                ps.setTimestamp(5,Dtime);
                ps.setInt(6,Sender);
                ps.setInt(7,receiver);
                ps.executeUpdate();
                
                out.print("Email-sent-successfullyy!!!!");
            }
            else
            {
            	out.print("Resend !!!!");
            }
            
            out.flush();
            
			} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}		
	}

}
