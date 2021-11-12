package com.JavaMail;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SentEmail
 */
@WebServlet("/SentEmail")
public class SentEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
		try
		{
			String From= request.getParameter("Fmail");
			String sql= "select Tmail,Subject,Message,Dtime from Inbox where Fmail=?";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Email","root","Vinsql@123");
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, From);
		     	
            ResultSet rs = ps.executeQuery();
            PrintWriter out = response.getWriter();
            if (rs.next() == false) 
            { 
            	out.println("Emptyyy boxxx!!!!"); 
            }
            else
            {

                  do{
                      out.print("To: "+rs.getString("Tmail")+", ");
                      out.print("Sub: "+rs.getString("Subject")+", ");
                      out.print("Msg: "+rs.getString("Message")+", ");
                      out.print(rs.getTimestamp("Dtime"));
                      out.println();
                      }while(rs.next());
            }			
		}catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
				
			 	e.printStackTrace();
		}		

	}

}
