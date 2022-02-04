package com.fss.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdminLogin {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// login screen
		System.out.println("enter user name:");
		String username = br.readLine();
		System.out.println("enter password:");
		String password = br.readLine();
		System.out.println("enter your role:");
		System.out.println("1.Administrator\n2.Employee");
		int role = Integer.parseInt(br.readLine());
		switch (role) {
		case 1:
			adminlogin();
			break;
		case 2:
			employeeLogin();
			break;
		}
		br.close();
	}

	public static void adminlogin() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Menu for Administrator");
		System.out.println(
	 "1.Add Employee\n2.Search Employee\n3.Delete Employee\n4.View Employee\n5.Manage Payroll\n6.Generate Payslip\n7.saiRecord Employee Attendance\n8.Quit");
		int response = Integer.parseInt(br.readLine());
	}

	public static void employeeLogin() {
	}
}
