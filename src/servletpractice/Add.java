package servletpractice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Add")
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("reaching out to doGet method");
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>"+
"<html>"+
"<head>"+
"<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>"+
"<title>add</title>"+
"<link rel='stylesheet' href='ems.css'>"+
"</head>"+
"<body>"+
				"<form action='ServerLayerAdd' method='POST' id='addform'>"+				//Form
				"<fieldset>"+
					"<legend>Add An Employee</legend>"+
				"<p id='mandatory'><Strong>Fields Marked with <sup>*</sup> are <em>MANDATORY</em>.</Strong></p>"+
					"<div>"+												//Name
						"<strong>Name<sup>*</sup></strong><br /> "+
						"<input type='text' name='firstName'"+
							"placeholder='First' required></input> "+
						"<input type='text' name='lastName'"+
							"placeholder='Last' required></input>"+
					"</div>"+
					"<div>"+												//Date Of Birth
						"<strong>Birthday<sup>*</sup></strong><br/>"+
						"<select name='date' required>"+
						"<option value=''>DD</option>"
						+getDateOptions(1,31)+
						"</select>"+
						 "<select name='month' required>"+
						 	"<option value=''>MM</option>"+
							"<option value='Jan'>Jan</option>"+
							"<option value='Feb'>Feb</option>"+
							"<option value='Mar'>Mar</option>"+
							"<option value='Apr'>Apr</option>"+
							"<option value='May'>May</option>"+
							"<option value='Jun'>Jun</option>"+
							"<option value='Jul'>Jul</option>"+
							"<option value='Aug'>Aug</option>"+
							"<option value='Sep'>Sep</option>"+
							"<option value='Oct'>Oct</option>"+
							"<option value='Nov'>Nov</option>"+
							"<option value='Dec'>Dec</option>"+
						"</select>"+
						"<select name='year' required>"+
						"<option value=''>YYYY</option>"
						+getDateOptions(1980,2016)+
						"</select>"+	
					"</div>"+
					"<div>"+												//Gender
						"<strong>Gender<sup>*</sup></strong><br /> <input type='radio' value='male' name='gender'"+
							" required>Male</input> <input type='radio' name='gender' value='female'>Female</input>"+
						"<input type='radio' name='gender' value='other'>Other</input>"+
					"</div>"+
					"<div>"+												//Address
						"<strong>Address<sup>*</sup>:</strong><br>"+
						"<textarea name='address' rows='10' cols='40'"+
							"placeholder='Your address goes here .....' required></textarea>"+
					"</div>"+
					"<div>"+												//Phone Number
						"<strong>Phone No.<sup>*</sup>:</strong> <input type='text' name='phone'"+
							"maxlength='10' required></input>"+
					"</div>"+
					"<div>"+
					"<button type='submit' form='addform' value='submit'>Submit</button>"+
					"</div>"+
				"</fieldset>"+
			"</form>"+
		"</tr>"+
"</body>"+
"</html>");}

	private String getDateOptions(int start, int limit) {
		StringBuilder str = new StringBuilder();
		
		for(int i=start; i<=limit; i++){
			str.append("<option value='"+i+"'>"+i+"</option>"); 
		}
		return str.toString();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
