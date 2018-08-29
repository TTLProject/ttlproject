<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="dao.ConnectionSteps"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
int  id = Integer.parseInt(request.getParameter("id"));
String color= request.getParameter("color");

String column =request.getParameter("column_name");
String column1;

if(column.equals("testcaseid")){
	 column1="testid";
}
else
{
	column1=column;
}
try {
    
	ConnectionSteps steps = new ConnectionSteps();
	Connection conn=steps.connection();
	System.out.println(column1);
PreparedStatement pstmt = conn.prepareStatement("update duplicatetable set "+column1+"=? where id=?");
pstmt.setString(1, color);

	PreparedStatement pstmt1 = conn.prepareStatement("select * from testreporttable where id=?");
	pstmt1.setInt(1, id);
	ResultSet rs = pstmt1.executeQuery();
	if(rs.next()){
		
	    pstmt.setInt(2, rs.getInt("id"));
		
	
}



int i=pstmt.executeUpdate();

if(i>0) {
	
	System.out.println("update success");
	
}else {
	System.out.println("update failed");
	
}
	
}catch(Exception e) {
	
	System.out.println(e);
}
%>
</body>
</html>