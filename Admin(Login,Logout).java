package com.fss.beans;
public class ALogin{
public static void adminlogin() throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter User Name:");
		String username = br.readLine();
		System.out.println("Enter Password:");
		String password = br.readLine();
		if ("admin".equals(username) && "admin123".equals(password))

		{
			int response;
			do {
				System.out.println("\n                         ");
				System.out.println("|-------------------------|");
				System.out.println("| Menu for Administrator  | ");
				System.out.println("|-------------------------|");
				System.out.println(
						"1.Add Employee\n2.Update Employee\n3.Search Employee\n4.Delete Employee\n5.View Employees List\n6.Manage Payroll\n7.Generate Payslip\n8.Record Employee Attendance\n9.Logout");
				System.out.println("****Select an Option to Proceed****");
				response = Integer.parseInt(br.readLine());
				switch (response) {
				case 1:
					addemployee();
					break;
				case 2:
					updateemployee();
					break;
				case 3:
					searchemployee();
					break;
				case 4:
					deleteemployee();
					break;
				case 5:
					viewemployees();
					break;
				case 6:
					managepayroll();
					break;
				case 7:
					generatepayslip();
					break;
				case 8:
					recordattendance();
					break;
				case 9:
					System.out.println("----Logged out Succesfully----");
					break;
				default:
					System.out.println("Invalid Selection...!");
					break;
				}
			} while (response != 9);
		} else {
			System.out.println("Invalid Username 'or' Password !!");
			System.out.println("!!!!Please Enter a Valid Details!!! ");
		}
	}
}
