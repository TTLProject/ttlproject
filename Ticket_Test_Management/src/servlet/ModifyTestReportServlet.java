package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ConnectionSteps;
import userbean.Userbean;

/**
 * Servlet implementation class ModifyTestReportServlet
 */
@WebServlet("/ModifyTestReportServlet")
public class ModifyTestReportServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Userbean user =new Userbean();
		String testcaseid[]=request.getParameterValues("testcaseid[]");
		String testdescription[]=request.getParameterValues("testdescription[]");
		String precondition[]=request.getParameterValues("precondition[]");
		String testdesign[]=request.getParameterValues("testdesign[]");
		String expectedresult[]=request.getParameterValues("expectedresult[]");
		String actualresult[]=request.getParameterValues("actualresult[]");
		String status[]=request.getParameterValues("status[]");
		String comment[]=request.getParameterValues("comment[]");
		String id1[]=request.getParameterValues("id1[]");
		
		try {
			ConnectionSteps steps = new ConnectionSteps();
			Connection conn=steps.connection();
			for(int i=0;i<=testcaseid.length-1;i++)
			{
				
				String insertQuery = "update  testreporttable set testcaseid=?,testdescription=?,precondition=?,testdesign=?,expectedresult=?,actualresult=?,status=?,comments=?,report=? where id=?";
				PreparedStatement pstmt = conn.prepareStatement(insertQuery);
				
				
				
				System.out.println("testcaseid[i]  "+testcaseid[i]);
				System.out.println("testdescription[i]  "+testdescription[i]);
				System.out.println("precondition[i]  "+precondition[i]);
				System.out.println("testdesign[i]  "+testdesign[i]);
				System.out.println("expectedresult[i]  "+expectedresult[i]);
				System.out.println("actualresult[i]  "+actualresult[i]);
				System.out.println("status[i]  "+status[i]);
				System.out.println("comment[i]  "+comment[i]);
				System.out.println("id"+id1[i]);
				
				//int id = Integer.parseInt(testcaseid[i].split("TC-")[1]);
				
				pstmt.setString(1, testcaseid[i]); 
				pstmt.setString(2, testdescription[i]);
				pstmt.setString(3, precondition[i]); 
				pstmt.setString(4, testdesign[i]);
				pstmt.setString(5, expectedresult[i]); 
				pstmt.setString(6, actualresult[i]);
				pstmt.setString(7, status[i]); 
				pstmt.setString(8, comment[i]);
				pstmt.setString(9, "modify");
				pstmt.setString(10, id1[i]);
				int n =pstmt.executeUpdate();
				if(n>0) {
					
					System.out.println("success");
					user.setValid(true);
				
				}
				else {
					
					System.out.println("fails");
					user.setValid(false);
					
				}
		
			
			
			
			
			}
		}catch (Exception e) {
	System.out.println(e);
		}
		boolean state = user.isValid();
		if(state) {
			response.sendRedirect("ModifyExecutiveTestReport.jsp");
		}else {
			response.sendRedirect("ModifyExecutiveTestReport.jsp");
		}
		
	}

}
