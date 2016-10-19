package servletpractice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("reaching out to doGet method");
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>"+
"<html>"+
"<head>"+
"<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>"+
"<title>Update</title>"+
"<link rel='stylesheet' href='ems.css'>"+
"</head>"+
"<body>"
+ req.getParameter("id"));
		String x = req.getParameter("id");
		int i = Integer.parseInt(x);
try{
    Connection conn = MysqlCon.getDBConnection();

     // Execute SQL query
    Statement stmt = conn.createStatement();
    
    String InsertQuery = "SELECT name, date, gender, address, phonenumber FROM employee WHERE (id)=("+i+")";
     ResultSet rs = stmt.executeQuery(InsertQuery);
   
     while(rs.next()){
        String name = rs.getString("name");
        String date = rs.getString("date");
  //      String gen = rs.getString("gender");
        String adr = rs.getString("address");
        String pn = rs.getString("phonenumber");
        
        
//    System.out.println(x);	
//	System.out.println(name);
//	System.out.println(date);
//	System.out.println(gen);
//	System.out.println(adr);
//	System.out.println(pn);
        
        String fn = null,ln=null,day=null,mon=null,yr=null;
        String[] arr = name.split(" ");    
        String[] arr1 = date.split(" ");
        for ( int j=0;j<2;j++) {
        			if(j==0){	
        				fn=arr[j];
        			}
        			else{
        				ln=arr[j];
        			}	
         }
        for ( int j=0;j<3;j++) {
			if(j==0){	
				day=arr1[j];
			}
			else if(j==1){
				mon=arr1[j];
			}	
			else{
				yr=arr1[j];
			}
			
}
        System.out.println(day);
		System.out.println(mon);
		System.out.println(yr);
        
	out.print("<form action='ServerLayerUpdate' method='POST' id='updateform'>"+				//Form
				"<fieldset>"+
					"<legend>Lets Update</legend>"+
				"<p id='mandatory'><Strong>Fields Marked with <sup>*</sup> are <em>MANDATORY</em>.</Strong></p>"+
				"<div>"+												//Name
				"<strong>ID</strong><br /> "+
				"<input type='text' name='iD'"+
					"value="+x+" readonly></input> "+
				"</div>"+	
				"<div>"+												//Name
						"<strong>Name<sup>*</sup></strong><br /> "+
						"<input type='text' name='firstName'"+
							"value="+fn+" required></input> "+
						"<input type='text' name='lastName'"+
							"value="+ln+" required></input>"+
					"</div>"+
					"<div>"+												//Date Of Birth
						"<strong>Birthday<sup>*</sup></strong><br/>"+
						"<select name='date' >"+
						"<option value="+day+">"+day+"</option>"
						+getDateOptions(1,31)+
						"</select>"+
						 "<select name='month' >"+
						 	"<option value="+mon+">"+mon+"</option>"+
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
						"<select name='year' >"+
						"<option value="+yr+">"+yr+"</option>"
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
							"placeholder='Your address goes here .....' value="+adr+" required>"+adr+"</textarea>"+
					"</div>"+
					"<div>"+												//Phone Number
						"<strong>Phone No.<sup>*</sup>:</strong> "
						+ "<input type='number' name='phone'"+
							"maxlength='10' value"+pn+" required></input>"+
					"</div>"+
					"<div>"+
					"<button type='submit' form='updateform' value='submit'>Submit</button>"+
					"</div>"+
				"</fieldset>"+
			"</form>"+
		"</tr>"+
"</body>"+
"</html>");}
	
     rs.close();
     stmt.close();
     conn.close();
   }
    // Clean-up environment
catch(SQLException se){
  //Handle errors for JDBC
  se.printStackTrace();
}catch(Exception e){
  //Handle errors for Class.forName
  e.printStackTrace();
} //end try
}

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
