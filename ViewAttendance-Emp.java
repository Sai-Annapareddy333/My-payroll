package com.fss.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbVeiwattendance {
public static void main (String[] args) throws Exception {
		
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
		
		
		Class.forName("com.mysql.cj.jdbc.Driver");   
        float emp_wd,emp_wh;
        int emp_id = 0;
		System.out.println("Enter Employee Id :");
		emp_id=Integer.parseInt(br.readLine());
		
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pms","root","40540633@Sai");
		
		PreparedStatement stmt=con.prepareStatement("select * from empatendance where emp_id="+emp_id+"");

	    
	    
	    
		ResultSet rs = stmt.executeQuery();

	    System.out.println("Emp Id     Emp_Working Hours  Emp_Working Days    Leaves_Taken");
		while(rs.next())

		System.out.println(rs.getInt(1)+"          "+rs.getFloat(2)+"               "+rs.getFloat(3)+"                 "+rs.getFloat(4)+"");
		     
	    
		}catch(Exception e) {System.out.println(e);
		}
			}
}
