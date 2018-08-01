package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import userbean.Userbean;

public class EmployeeLoginDao {

	public static void insert(Userbean user) {
		try {
			ConnectionSteps steps = new ConnectionSteps();
			Connection conn=steps.connection();
			PreparedStatement pstmt = conn.prepareStatement("select * from registrationtable where username=? and password=?");
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword()); 
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				 user.setValid(true);
				 user.setName(rs.getString("name"));
				 user.setUsername(rs.getString("username"));
				 user.setPassword(rs.getString("password"));
				 user.setConfirmpassword(rs.getString("confirmpassword"));
				 user.setDomain(rs.getString("domain"));
				 user.setDesignation(rs.getString("designation"));
				user.setEmail(rs.getString("email"));
				user.setMobile(Long.parseLong(rs.getString("mobile")));
				user.setId((rs.getString("id")));
				
				
				
				
			}
			else {
				
				user.setValid(false);
			}
			
		} catch (Exception e) {

			System.out.println(e);
		}
		
		
	}

}
