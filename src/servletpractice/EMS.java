package servletpractice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EMS extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private String message1;
	private String message2;
	
	public void init() throws ServletException{
		message1="Welcome To Employee Management System";
		message2="Choose what you want to do :";
	}

public void doGet(HttpServletRequest req, HttpServletResponse res)
throws IOException,ServletException{
	System.out.println("reaching out to doGet method");
	res.setContentType("text/html");
	PrintWriter out = res.getWriter();
	out.println("<!DOCTYPE html>");
	out.println("<html><body>");
	out.println("<h2>"+message1+"</h2>");
	out.println("<h3>"+message2+"</h3>");
	out.println("<p><a href='/ServletEMS/add'>"
			+ "<button>1. Add</button></a></p>");
	out.println("<p><a href='/ServletEMS/list'>"
			+ "<button>2. List</button></a></p>");
	out.println("</body></html>");
}

public void destroy(){
	System.out.println("Servlet is destroyed.");
}
}