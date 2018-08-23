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
@WebServlet("/ModifyTestReportServlet1")
public class ModifyTestReportServlet1 extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Userbean user =new Userbean();
		/*String testcaseid[]=request.getParameterValues("testcaseid[]");
		String testdescription[]=request.getParameterValues("testdescription[]");
		String precondition[]=request.getParameterValues("precondition[]");
		String testdesign[]=request.getParameterValues("testdesign[]");
		String expectedresult[]=request.getParameterValues("expectedresult[]");
		String actualresult[]=request.getParameterValues("actualresult[]");
		String status[]=request.getParameterValues("status[]");
		String comment[]=request.getParameterValues("comment[]");*/
		String id1[]=request.getParameterValues("id[]");
		
		try {
			ConnectionSteps steps = new ConnectionSteps();
			Connection conn=steps.connection();
			for(int i=0;i<=id1.length-1;i++)
			{
				
				String insertQuery = "update  testreporttable set report=? where id=?";
				PreparedStatement pstmt = conn.prepareStatement(insertQuery);
				
				int id=Integer.parseInt(id1[i]);
				
				
				
				//int id = Integer.parseInt(testcaseid[i].split("TC-")[1]);
				
				
				pstmt.setString(1, "modified");
				pstmt.setInt(2, id);
				int n =pstmt.executeUpdate();
				if(n>0) {
					
					System.out.println("success");
					user.setValid(true);
				
				}
				else {
					
					System.out.println("fails");
					user.setValid(false);
					response.sendRedirect("ModifyExecutiveTestReportF.jsp");
				}
		
			
			
			
			
			}
		}catch (Exception e) {
	System.out.println(e);
		}
		boolean state = user.isValid();
		if(state) {
			response.sendRedirect("ModifyExecutiveTestReportS.jsp");
		}else {
			response.sendRedirect("ModifyExecutiveTestReportF.jsp");
		}
		
	}

}
