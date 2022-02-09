package com.fss.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbViewemp {
public static void main (String[] args) throws Exception {
		
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
		
		
		Class.forName("com.mysql.cj.jdbc.Driver");   
        String ename,edesig,eaddress,eemail;
        int empId=0,phone=0;
        float salary=0;
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pms","root","40540633@Sai");
		
		PreparedStatement stmt=con.prepareStatement("select * from employee");
		
		ResultSet rs = stmt.executeQuery();
		System.out.println("Emp Id  Emo_Name  Emp_Desig       Emp_Salary    Emp_Address   Emp_Email       Emp_Phone");
		while(rs.next()) 
			
			System.out.println(rs.getString(1)+"        "+rs.getString(2)+"       "+rs.getString(3)+"      "+rs.getString(4)+"      "+rs.getString(5)+"     "+rs.getString(6)+"     "+rs.getString(7));
		    
	 
	   
	  
	    System.out.println("Employee List Retrieved Successfully......");
	   
	    
}catch(Exception e) {System.out.println(e);
}
	}

}
