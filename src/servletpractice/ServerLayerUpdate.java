package servletpractice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServerLayerUpdate")
public class ServerLayerUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String fn,ln,date,day,mon,yr,gen,adr,pn,name,x;
		int id;
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		String doctype = "<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>";
		out.print(doctype+
				"<html>"
				+ "<head><title>Update</title>"
				+ "</head>"
				+ "<body>"
				+req.getParameter("iD")
				+req.getParameter("firstName")
				+req.getParameter("lastName")
				+req.getParameter("date")
				+req.getParameter("month")
				+req.getParameter("year")
				+req.getParameter("gender")
				+req.getParameter("address")
				+req.getParameter("phone")
				+ "</body>"
				+ "</html>");
			
			x  = req.getParameter("iD");
			fn  = req.getParameter("firstName");
			ln  = req.getParameter("lastName");
			day = req.getParameter("date");
			mon = req.getParameter("month");
			yr  = req.getParameter("year");
			gen = req.getParameter("gender");
			adr = req.getParameter("address");
			pn  = req.getParameter("phone");
			
			id = Integer.parseInt(x);
			
			name = fn+" "+ln;
			date = day+" "+mon+" "+yr;
			
//			  	System.out.println(id);	
//				System.out.println(name);
//				System.out.println(date);
//				System.out.println(gen);
//				System.out.println(adr);
//				System.out.println(pn);
//			
			EmployeeData data = new EmployeeData();
			data.setId(id);
			data.setName(name);
			data.setDateOfBirth(date);
			data.setGender(gen);
			data.setAddress(adr);
			data.setPhoneNum(pn);
			
			System.out.println(data.getId());	
			System.out.println(data.getName());
			System.out.println(data.getDateOfBirth());
			System.out.println(data.getGender());
			System.out.println(data.getAddress());
			System.out.println(data.getPhoneNum());
			
			Connection connection = MysqlCon.getDBConnection();
	        PreparedStatement insertPreparedStatement = null;
	        
	        String InsertQuery = "UPDATE employee SET name = ?, date = ?, gender = ?,"
	        		+ " address = ?, phonenumber = ? WHERE id= ?";
	        
	        try {
	            insertPreparedStatement = connection.prepareStatement(InsertQuery);
	            insertPreparedStatement.setString(1, data.getName());
	            insertPreparedStatement.setString(2, data.getDateOfBirth());
	            insertPreparedStatement.setString(3, data.getGender());
	            insertPreparedStatement.setString(4, data.getAddress());
	            insertPreparedStatement.setString(5, data.getPhoneNum());
	            insertPreparedStatement.setInt(6, data.getId());
	            insertPreparedStatement.executeUpdate();
	            insertPreparedStatement.close();
	            
	        } catch (SQLException e) {
	            System.out.println("Exception Message " + e.getLocalizedMessage());
	            e.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
//				
//			System.out.println(name);
//			System.out.println(date);
//			System.out.println(gen);
//			System.out.println(adr);
//			System.out.println(pn);
	        res.sendRedirect(req.getContextPath()+"/index");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
