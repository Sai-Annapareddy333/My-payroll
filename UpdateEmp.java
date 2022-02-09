package com.fss.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUpdateemp {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Empid = 0;
		int response2;
		do {
			System.out.println("\n                         ");
			System.out.println("|--------------------------------------|");
			System.out.println("| Enter Employee ID to Update Details  | ");
			System.out.println("|--------------------------------------|");
			System.out.println(
					"1.Update Employee Name \n2.Update Employee Designation\n3.Update Employee Basic_Salary\n4.Update Employees Address\n5.Update Employee Email\n6.Update Employee Contact info\n7.Logout");
			System.out.println("****Select an Option to Proceed****");
			response2 = Integer.parseInt(br.readLine());
			switch (response2) {
			case 1:
				updatename();
				break;
			case 2:
				updatedesig();
				break;
			case 3:
				updatesalary();
				break;
			case 4:
				updateaddres();
				break;
			case 5:
				updatemail();
				break;
			case 6:
				updatephno();
				break;
			case 7:
				System.out.println("----Logged out Succesfully----");
				break;
			default:
				System.out.println("Invalid Selection...!");
				break;
			}
		} while (response2 != 7);

	}

	private static void updatephno() throws NumberFormatException, Exception,SQLException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Empid = 0;
		String phno;
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pms", "root", "40540633@Sai");
		System.out.println("Connection Established");
		System.out.println("Enter Employee ID:");
		Empid = Integer.parseInt(br.readLine());
		System.out.println("Enter Employee Contact Number to update ");
		phno = br.readLine();

		PreparedStatement stmt = con
				.prepareStatement("update employee set emp_phn=" + phno + "  where emp_id=" + Empid + "");

		stmt.executeLargeUpdate();
		PreparedStatement stmt1 = con.prepareStatement("select * from employee where emp_id=" + Empid + "");

		ResultSet rs = stmt1.executeQuery("select * from employee  where emp_id=" + Empid + "");
		System.out.println(
				"Emp Id  Emo_Name     Emp_Desig         Emp_Salary     Emp_Address     Emp_Email          Emp_Phone");
		while (rs.next())

			System.out.println(rs.getString(1) + "       " + rs.getString(2) + "          " + rs.getString(3)
					+ "         " + rs.getString(4) + "      " + rs.getString(5) + "          " + rs.getString(6)
					+ "       " + rs.getString(7));

		System.out.println("\nEmployee Contact Number Updated Successfully.....");

	}

	private static void updatemail() throws Exception,SQLException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Empid = 0;
		String email;
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pms", "root", "40540633@Sai");
		System.out.println("Connection Established");
		System.out.println("Enter Employee ID:");
		Empid = Integer.parseInt(br.readLine());
		System.out.println("Enter Employee Name to update ");
		email = br.readLine();

		PreparedStatement stmt = con
				.prepareStatement("update employee set emp_email=" + "'" + email + "'" + " where emp_id=" + Empid + "");

		stmt.executeLargeUpdate();
		PreparedStatement stmt1 = con.prepareStatement("select * from employee where emp_id=" + Empid + "");

		ResultSet rs = stmt1.executeQuery("select * from employee  where emp_id=" + Empid + "");
		System.out.println(
				"Emp Id  Emo_Name     Emp_Desig         Emp_Salary     Emp_Address     Emp_Email          Emp_Phone");
		while (rs.next())

			System.out.println(rs.getString(1) + "       " + rs.getString(2) + "          " + rs.getString(3)
					+ "         " + rs.getString(4) + "      " + rs.getString(5) + "          " + rs.getString(6)
					+ "       " + rs.getString(7));

		System.out.println("\nEmployee Email Updated Successfully.....");

	}

	private static void updateaddres() throws Exception,SQLException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Empid = 0;
		String addrs;
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pms", "root", "40540633@Sai");
		System.out.println("Connection Established");
		System.out.println("Enter Employee ID:");
		Empid = Integer.parseInt(br.readLine());
		System.out.println("Enter Employee Name to update ");
		addrs = br.readLine();

		PreparedStatement stmt = con.prepareStatement(
				"update employee set emp_address=" + "'" + addrs + "'" + " where emp_id=" + Empid + "");

		stmt.executeLargeUpdate();
		PreparedStatement stmt1 = con.prepareStatement("select * from employee where emp_id=" + Empid + "");

		ResultSet rs = stmt1.executeQuery("select * from employee  where emp_id=" + Empid + "");
		System.out.println(
				"Emp Id  Emo_Name     Emp_Desig         Emp_Salary     Emp_Address     Emp_Email          Emp_Phone");
		while (rs.next())

			System.out.println(rs.getString(1) + "       " + rs.getString(2) + "          " + rs.getString(3)
					+ "         " + rs.getString(4) + "      " + rs.getString(5) + "          " + rs.getString(6)
					+ "       " + rs.getString(7));

		System.out.println("\nEmployee Address Updated Successfully.....");

	}

	private static void updatesalary() throws Exception,SQLException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Empid = 0;
		float salary;
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pms", "root", "40540633@Sai");
		System.out.println("Connection Established");
		System.out.println("Enter Employee ID:");
		Empid = Integer.parseInt(br.readLine());
		System.out.println("Enter Employee Name to update ");
		salary = Float.parseFloat(br.readLine());
		PreparedStatement stmt = con.prepareStatement(
				"update employee set emp_salary=" + "'" + salary + "'" + " where emp_id=" + Empid + "");

		stmt.executeLargeUpdate();
		PreparedStatement stmt1 = con.prepareStatement("select * from employee where emp_id=" + Empid + "");

		ResultSet rs = stmt1.executeQuery("select * from employee  where emp_id=" + Empid + "");
		System.out.println(
				"Emp Id  Emo_Name     Emp_Desig         Emp_Salary     Emp_Address     Emp_Email          Emp_Phone");
		while (rs.next())

			System.out.println(rs.getString(1) + "       " + rs.getString(2) + "          " + rs.getString(3)
					+ "         " + rs.getString(4) + "      " + rs.getString(5) + "          " + rs.getString(6)
					+ "       " + rs.getString(7));

		System.out.println("\nEmployee Basic Salary  Updated Successfully.....");

	}

	private static void updatedesig() throws Exception, NumberFormatException,SQLException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Empid = 0;
		String desig;
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pms", "root", "40540633@Sai");
		System.out.println("Connection Established");
		System.out.println("Enter Employee ID:");
		Empid = Integer.parseInt(br.readLine());
		System.out.println("Enter Employee Name to update ");
		desig = br.readLine();

		PreparedStatement stmt = con
				.prepareStatement("update employee set emp_desig=" + "'" + desig + "'" + " where emp_id=" + Empid + "");

		stmt.executeLargeUpdate();
		PreparedStatement stmt1 = con.prepareStatement("select * from employee where emp_id=" + Empid + "");

		ResultSet rs = stmt1.executeQuery("select * from employee  where emp_id=" + Empid + "");
		System.out.println(
				"Emp Id  Emo_Name     Emp_Desig         Emp_Salary     Emp_Address     Emp_Email          Emp_Phone");
		while (rs.next())

			System.out.println(rs.getString(1) + "       " + rs.getString(2) + "          " + rs.getString(3)
					+ "         " + rs.getString(4) + "      " + rs.getString(5) + "          " + rs.getString(6)
					+ "       " + rs.getString(7));

		System.out.println("\nEmployee Desination Updated Successfully.....");
	}

	private static void updatename() throws Exception,SQLException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Empid = 0;
		String name;
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pms", "root", "40540633@Sai");
		System.out.println("Connection Established");
		System.out.println("Enter Employee ID:");
		Empid = Integer.parseInt(br.readLine());
		System.out.println("Enter Employee Name to update ");
		name = br.readLine();

		PreparedStatement stmt = con
				.prepareStatement("update employee set emp_name=" + "'" + name + "'" + " where emp_id=" + Empid + "");

		stmt.executeLargeUpdate();
		PreparedStatement stmt1 = con.prepareStatement("select * from employee where emp_id=" + Empid + "");

		ResultSet rs = stmt1.executeQuery("select * from employee  where emp_id=" + Empid + "");
		System.out.println(
				"Emp Id  Emo_Name     Emp_Desig         Emp_Salary     Emp_Address     Emp_Email          Emp_Phone");
		while (rs.next())

			System.out.println(rs.getString(1) + "       " + rs.getString(2) + "          " + rs.getString(3)
					+ "         " + rs.getString(4) + "      " + rs.getString(5) + "          " + rs.getString(6)
					+ "       " + rs.getString(7));

		System.out.println("\nEmployee Name Updated Successfully.....");

	}
}
