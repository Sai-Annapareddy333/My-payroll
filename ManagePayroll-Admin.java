package com.fss.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DbManagePayroll {

public static void main(String[] args) {
	try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
	
	
	Class.forName("com.mysql.cj.jdbc.Driver");   
    
    int EmpId=0;
    String Emp_Dept;
    float Basic_pay,Hra,Da,Bonus,Pf;
	
	System.out.println("Enter empoloyee Id:");
	EmpId=Integer.parseInt(br.readLine());
	System.out.println("ENter Employee Departmnt:");
	Emp_Dept=br.readLine();
	System.out.println("Enter Employee Bsic Pay :");
	Basic_pay=Float.parseFloat(br.readLine());
	System.out.println("Enter Employee Hose Rent Allowance :");
	Hra=Float.parseFloat(br.readLine());
	System.out.println("Enter Employee Dearness Aloowance :");
	Da=Float.parseFloat(br.readLine());
	System.out.println("Enter Employee Bonus :");
	Bonus=Float.parseFloat(br.readLine());
	System.out.println("Enter Employee Privident Fund :");
	Pf=Float.parseFloat(br.readLine());
	
	

	
	
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pms","root","40540633@Sai");
	
	PreparedStatement stmt=con.prepareStatement("Insert into managepayroll Values(?,?,?,?,?,?,?,null)");

    stmt.setInt(1, EmpId);
    stmt.setString(2, Emp_Dept);
    stmt.setFloat(3,Basic_pay);
    stmt.setFloat(4, Hra);
    stmt.setFloat(5, Da);
    stmt.setFloat(6, Bonus);
    stmt.setFloat(7, Pf);
    
   
    int i=stmt.executeUpdate();
    System.out.println("Employee Payroll Details Registered Successfully......");
   
    
}catch(Exception e) {System.out.println(e);
}
}
}
