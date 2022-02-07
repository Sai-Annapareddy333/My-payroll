package com.fss.beans;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Dbrgemp {
public static void main (String[] args) throws Exception {
		
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
		
		
		Class.forName("com.mysql.cj.jdbc.Driver");   
        String ename,edesig,eaddress,eemail;
        int empId=0,phone=0;
        float salary=0;
		
		System.out.println("Enter empoloyee Id:");
		empId=Integer.parseInt(br.readLine());
		System.out.println("ENter Employee name:");
		ename=br.readLine();
		System.out.println("Enter Employee Designtion :");
		edesig=br.readLine();
		System.out.println("Enter Employee Salary:");
		salary=Float.parseFloat(br.readLine());
		System.out.println("Enter Employee Address:");
		eaddress=br.readLine();
		System.out.println("Enter Employee Email:");
		eemail=br.readLine();
		System.out.println("Enter Contact Num:");
		phone=Integer.parseInt(br.readLine());
		
		

		
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pms","root","40540633@Sai");
		
		PreparedStatement stmt=con.prepareStatement("Insert into employee Values(?,?,?,?,?,?,?)");

	    stmt.setInt(1, empId);
	    stmt.setString(2, ename);
	    stmt.setString(3, edesig);
	    stmt.setFloat(4, salary);
	    stmt.setString(5, eaddress);
	    stmt.setString(6, eemail);
	    stmt.setInt(7, phone);
	    
	   
	    int i=stmt.executeUpdate();
	    System.out.println("Employee Details Added Successfully......");
	   
	    
}catch(Exception e) {System.out.println(e);
}
	}

}
