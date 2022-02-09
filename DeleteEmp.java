package com.fss.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DbDeletemp {
public static void main (String[] args) throws Exception {
		
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
		
		
		Class.forName("com.mysql.cj.jdbc.Driver");   
       
        int empId=0;
        
		
		System.out.println("Enter empoloyee Id:");
		empId=Integer.parseInt(br.readLine());
		
		
		

		
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pms","root","40540633@Sai");
		
		PreparedStatement stmt=con.prepareStatement("delete from employee where emp_id="+empId+"");

	    
	    
	   
	    int i=stmt.executeUpdate();
	    System.out.println("Employee Record Deleted Successfully......");
	   
	    
}catch(Exception e) {System.out.println(e);
}
	}


}
