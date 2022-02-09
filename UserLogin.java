package pms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

	public static void main(String[] args) throws Exception {

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			// login screen
			System.out.println("****************************************************");
			System.out.println("|------------PAYROLL-MANAGEMENT-SYSTEM-------------|");
			System.out.println("****************************************************");
			System.out.println("Enter User Name:");
			String username = br.readLine();
			System.out.println("Enter Password:");
			String password = br.readLine();
			if ("user".equals(username) && "user123".equals(password)) {
				System.out.println("Enter your Role:");
				System.out.println("1.Administrator\n2.Employee");
				int role = Integer.parseInt(br.readLine());
				switch (role) {
				case 1:
					adminlogin();
					break;
				case 2:
					employeeLogin();
					break;
				default:
					System.out.println("!!!!Please Enter a Valid Option!!! ");
					break;
				}

			} else {
				System.out.println("Invalid Username 'or' Password !!");
				System.out.println("!!!!Please Enter a Valid Details!!! ");
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}
