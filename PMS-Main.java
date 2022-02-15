package pms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class Login {
	// Declaring Buffered Reader as Static to use further
	static BufferedReader br;

	public static void main(String[] args) throws IOException { // Throwing IOException
		// reading values from user by Input Stream using Buffered Reader
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			// login screen
			System.out.println("****************************************************");
			System.out.println("|------------PAYROLL-MANAGEMENT-SYSTEM-------------|");
			System.out.println("****************************************************");
			System.out.println("Enter User Name:");
			String username = br.readLine();
			System.out.println("Enter Password:");
			String password = br.readLine();

			if ("user".equals(username) && "user123".equals(password)) { // Checking the user is Authenticated or not by
																			// giving inbuilt
				System.out.println("Enter your Role:");
				System.out.println("1.Administrator\n2.Employee");
				int role = Integer.parseInt(br.readLine());
				switch (role) {
				case 1:
					adminlogin(); // Creating Methods
					break;
				case 2:
					employeeLogin();
					break;
				default:
					System.out.println("!!!!Please Enter a Valid Option!!! ");
					break;
				}

			} else {
				System.out.println("Invalid Username 'or' Password !!"); // Displays this message if user not is
																			// authorized
				System.out.println("!!!!Please Enter a Valid Details!!! ");
			}

		} catch (Exception e) {
			System.out.println(e); // Catching thrown Exception with a message
		}

	}

	private static void employeeLogin() throws Exception, IOException {
		// reading values from user by Input Stream using Buffered Reader
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			// Employee Login page
			int response1;
			do {
				System.out.println("\n                         ");
				System.out.println("|-------------------------|");
				System.out.println("| Menu for Employee       | ");
				System.out.println("|-------------------------|");
				System.out.println("1.View Profile \n2.View Working days and Working Hours\n3.View Payslip\n4.Logout");
				System.out.println("****Select an Option to Proceed****");
				response1 = Integer.parseInt(br.readLine());
				
				switch (response1) {
			    case 1:
					viewprofile();
					break;
				case 2: // Creating Methods to use features
					viewattendance();
					break;
				case 3:
					viewpayslip();
					break;
				case 4:
					System.out.println("----Logged out Succesfully----");
					break;
				default:
					System.out.println("Invalid Selection...!");
					break;
				}
			} while (response1 != 4);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private static void viewpayslip() {
		// TODO Auto-generated method stub
		try {
			br = new BufferedReader(new InputStreamReader(System.in));

			Class.forName("com.mysql.cj.jdbc.Driver");

			int EmpId = 0;
			String Emp_Dept;
			float Basic_pay, Hra, Da, Bonus, Pf;
			// Creating Connection using Driver Manager to establish connection to the
			// Database
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pms", "root", "40540633@Sai");
			System.out.println("Connection Established");
			System.out.println("Enter your Employee ID to View Payslip:");
			EmpId = Integer.parseInt(br.readLine());
			// Preparing statement that to be execute in MYSQL Database
			PreparedStatement stmt1 = con.prepareStatement(
					"update managepayroll set Gross=(basic_pay + hra + da + bonus) -pf where emp_id=" + EmpId + "");
			PreparedStatement stmt2 = con.prepareStatement("select * from managepayroll where emp_id=" + EmpId + "");
			stmt1.executeLargeUpdate();
			System.out.println("*************************************************");
			System.out.println("|------------PAY SLIP FOR EMPLOYEE--------------|");
			System.out.println("************************************************");
			ResultSet rs = stmt2.executeQuery("select * from managepayroll where emp_id=" + EmpId + "");
			// Using next() we can retrieve the result set
			while (rs.next()) {
				System.out.printf("\nEmployeee ID           :" + rs.getInt(1));
				System.out.printf("\nEmployeee Department   :" + rs.getString(2));
				System.out.printf("\nEmployeee Basic Pay    :" + rs.getFloat(3));
				System.out.printf("\nEmployeee HRA          :" + rs.getFloat(4));
				System.out.printf("\nEmployeee DA           :" + rs.getFloat(5));
				System.out.printf("\nEmployeee Bonus        :" + rs.getFloat(6));
				System.out.printf("\nEmployeee PF           :" + rs.getFloat(7));
				System.out.printf("\nEmployeee Net Payable  :" + rs.getFloat(8));
				System.out.println("(In Hand Amount)");
				System.out.println("\n**************************************************");
				System.out.println("|------------******FSS-Corp*******---------------|");
				System.out.println("**************************************************");
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private static void viewattendance() {
		// TODO Auto-generated method stub
		// reading values from user by Input Stream using Buffered Reader
		try {
			br = new BufferedReader(new InputStreamReader(System.in));

			Class.forName("com.mysql.cj.jdbc.Driver");
			float emp_wd, emp_wh; // Declaring variables
			int emp_id = 0;
			System.out.println("Enter Employee Id :");
			emp_id = Integer.parseInt(br.readLine());
			// Creating Connection using Driver Manager to establish connection to the
			// Database
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pms", "root", "40540633@Sai");
			// Preparing statement that to be execute in MYSQL Database
			PreparedStatement stmt = con.prepareStatement("select * from empatendance where emp_id=" + emp_id + "");
//ResultSet is the Output of Prepared Statement
			ResultSet rs = stmt.executeQuery();

			System.out.println("Emp Id     Emp_Working Hours  Emp_Working Days    Leaves_Taken");
			while (rs.next())
				// Getting the Data from Database using get()
				System.out.println(rs.getInt(1) + "          " + rs.getFloat(2) + "               " + rs.getFloat(3) // Getting
																														// the
																														// Data
																														// from
																														// Database
																														// using
																														// get()
						+ "                 " + rs.getFloat(4) + "");

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private static void viewprofile() {
		// TODO Auto-generated method stub
		// reading values from user by Input Stream using Buffered Reader
		try {
			br = new BufferedReader(new InputStreamReader(System.in));

			Class.forName("com.mysql.cj.jdbc.Driver");
			String ename, edesig, eaddress, eemail;
			int empId = 0;
			float phone = 0;
			// Creating Connection using Driver Manager to establish connection to the
			// Database

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pms", "root", "40540633@Sai");
			System.out.println("Connection Established");
			System.out.println("Enter your Employee ID:");
			empId = Integer.parseInt(br.readLine());
			PreparedStatement stmt = con.prepareStatement("select * from employee where emp_id = " + empId + "");
			// ResultSet is the Output of Prepared Statement
			ResultSet rs = stmt.executeQuery();

			System.out.println("|---------------Employee Profile-----------------|");
			System.out
					.println("Emp Id  Emo_Name  Emp_Desig       Emp_Salary    Emp_Address   Emp_Email       Emp_Phone");
			while (rs.next()) {
				System.out.printf(rs.getInt(1) + "        " + rs.getString(2) + "       " + rs.getString(3) + "      "
						+ rs.getFloat(4) + "      " + rs.getString(5) + "     " + rs.getString(6) + "     "
						+ rs.getLong(7));
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void adminlogin() throws Exception {
		// reading values from user by Input Stream using Buffered Reader
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
//Used do while to iterate Menu
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
				// Switch Case is used to switch between different methods
				switch (response) {
				case 1:
					addemployee();
					break;
				case 2:
					updateemployee(); // Creating Methods
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

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private static void updateemployee() throws Exception, IOException {
		try {
			// reading values from user by Input Stream using Buffered Reader
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int Empid = 0;
			int response2; // Declaring Variables
			do {
				System.out.println("\n                         ");
				System.out.println("|--------------------------------------|");
				System.out.println("| Enter Employee ID to Update Details  | ");
				System.out.println("|--------------------------------------|");
				System.out.println(
						"1.Update Employee Name \n2.Update Employee Designation\n3.Update Employee Basic_Salary\n4.Update Employees Address\n5.Update Employee Email\n6.Update Employee Contact info\n7.Exit");
				System.out.println("****Select an Option to Proceed****");
				response2 = Integer.parseInt(br.readLine());
				
				switch (response2) {
				case 1:
					updatename();
					break;
				case 2:
					updatedesig();
					break;
				case 3: // Creating Methods
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
					System.out.println("Logged out Successfully...");
					break;
				default:
					System.out.println("Invalid Selection...!");
					break;
				}
				
			} while (response2!=7);
			
		} catch (Exception e) {
			System.out.println(e); // Catching Exception by printing Message
		}
}

	private static void updatephno() throws NumberFormatException, Exception, SQLException {
		// TODO Auto-generated method stub
		// reading values from user by Input Stream using Buffered Reader
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			int Empid = 0;
			Long phno;
			// Creating Connection using Driver Manager to establish connection to the
			// Database
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pms", "root", "40540633@Sai");
			System.out.println("Connection Established");
			System.out.println("Enter Employee ID:");
			Empid = Integer.parseInt(br.readLine());
			System.out.println("Enter Employee Contact Number to update ");
			phno =Long.parseLong( br.readLine());
//Multiple Prepared Statements are used here to execute queries in SQl Database
			PreparedStatement stmt = con
					.prepareStatement("update employee set emp_phn=" + phno + "  where emp_id=" + Empid + "");

			int rows=stmt.executeUpdate();
			//Validating If employee exists or not!
			if(rows==0) {
				System.out.println("|--Employee Id Does not Found!--|\n Please Enter Valid Employee Id");
			}
			PreparedStatement stmt1 = con.prepareStatement("select * from employee where emp_id=" + Empid + "");

			ResultSet rs = stmt1.executeQuery("select * from employee  where emp_id=" + Empid + "");
			System.out.println(
					"Emp Id  Emo_Name     Emp_Desig         Emp_Salary     Emp_Address     Emp_Email          Emp_Phone");
			while (rs.next())

				System.out.println(rs.getInt(1) + "       " + rs.getString(2) + "          " + rs.getString(3)
						+ "         " + rs.getString(4) + "      " + rs.getString(5) + "          " + rs.getString(6)
						+ "       " + rs.getLong(7));

			System.out.println("\nEmployee Contact Number Updated Successfully.....");

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private static void updatemail() throws Exception, SQLException {
		// TODO Auto-generated method stub
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			int Empid = 0;
			String email;
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pms", "root", "40540633@Sai");
			System.out.println("Connection Established");
			System.out.println("Enter Employee ID:");
			Empid = Integer.parseInt(br.readLine());
			System.out.println("Enter Employee Email to update ");
			email = br.readLine();

			PreparedStatement stmt = con.prepareStatement(
					"update employee set emp_emial=" + "'" + email + "'" + " where emp_id=" + Empid + "");

			int rows=stmt.executeUpdate();
			if(rows==0) {
				System.out.println("|--Employee Id Does not Found!--|\n Please Enter Valid Employee Id");
			}
			else {
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
			} catch (Exception e) {
			System.out.println(e);
		}
	}

	private static void updateaddres() throws Exception, SQLException {
		// TODO Auto-generated method stub
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			String addrs;
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pms", "root", "40540633@Sai");
			System.out.println("Connection Established");
			System.out.println("Enter Employee ID:");
			int Empid = Integer.parseInt(br.readLine());
			System.out.println("Enter Employee Address to update ");
			addrs = br.readLine();

			PreparedStatement stmt = con.prepareStatement(
					"update employee set emp_address=" + "'" + addrs + "'" + " where emp_id=" + Empid + "");

			int rows=stmt.executeUpdate();
			//Validating whether Employee Id exists or not!2
			if(rows==0) {
				System.out.println("|--Employee Id Does not Found!--|\n Please Enter Valid Employee Id");
			}
			else {
			PreparedStatement stmt1 = con.prepareStatement("select * from employee where emp_id=" + Empid + "");

			ResultSet rs = stmt1.executeQuery("select * from employee  where emp_id=" + Empid + "");
			System.out.println(
					"Emp Id  Emo_Name     Emp_Desig         Emp_Salary     Emp_Address     Emp_Email          Emp_Phone");
			while (rs.next())

				System.out.printf(rs.getString(1) + "       " + rs.getString(2) + "          " + rs.getString(3)
						+ "         " + rs.getString(4) + "      " + rs.getString(5) + "          " + rs.getString(6)
						+ "       " + rs.getString(7));

			System.out.println("\nEmployee Address Updated Successfully.....");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private static void updatesalary() throws Exception, SQLException {
		// TODO Auto-generated method stub
		try {BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int Empid = 0;
			float salary;
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pms", "root", "40540633@Sai");
			System.out.println("Connection Established");
			System.out.println("Enter Employee ID:");
			Empid = Integer.parseInt(br.readLine());
			System.out.println("Enter Employee Basic Salary to update ");
			salary = Float.parseFloat(br.readLine());
			PreparedStatement stmt = con.prepareStatement(
					"update employee set emp_salary=" + "'" + salary + "'" + " where emp_id=" + Empid + "");

			int rows=stmt.executeUpdate();
			if(rows==0) {
				System.out.println("|--Employee Id Does not Found!--|\n Please Enter Valid Employee Id");
			}
			else {
			PreparedStatement stmt1 = con.prepareStatement("select * from employee where emp_id=" + Empid + "");

			ResultSet rs = stmt1.executeQuery("select * from employee  where emp_id=" + Empid + "");
			System.out.println(
					"Emp Id  Emo_Name     Emp_Desig         Emp_Salary     Emp_Address     Emp_Email          Emp_Phone");
			while (rs.next())

				System.out.println(rs.getInt(1) + "       " + rs.getString(2) + "          " + rs.getString(3)
						+ "        " + rs.getString(4) + "     " + rs.getString(5) + "          " + rs.getString(6)
						+ "       " + rs.getLong(7));

			System.out.println("\nEmployee Basic Salary  Updated Successfully.....");
			}
			} catch (Exception e) {
			System.out.println(e);
		}
	}

	private static void updatedesig() throws Exception, NumberFormatException, SQLException {
		// TODO Auto-generated method stub
		try { br = new BufferedReader(new InputStreamReader(System.in));
			int Empid = 0;
			String desig;
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pms", "root", "40540633@Sai");
			System.out.println("Connection Established");
			System.out.println("Enter Employee ID:");
			Empid = Integer.parseInt(br.readLine());
			System.out.println("Enter Employee Designation to update ");
			desig = br.readLine();

			PreparedStatement stmt = con.prepareStatement(
					"update employee set emp_desig=" + "'" + desig + "'" + " where emp_id=" + Empid + "");

			int rows=stmt.executeUpdate();
			if(rows==0) {
				System.out.println("|--Employee Id Does not Found!--|\n Please Enter Valid Employee Id");
			}
			else {
			PreparedStatement stmt1 = con.prepareStatement("select * from employee where emp_id=" + Empid + "");

			ResultSet rs = stmt1.executeQuery("select * from employee  where emp_id=" + Empid + "");
			System.out.println(
					"Emp Id  Emo_Name     Emp_Desig         Emp_Salary     Emp_Address     Emp_Email          Emp_Phone");
			while (rs.next())

				System.out.println(rs.getString(1) + "       " + rs.getString(2) + "          " + rs.getString(3)
						+ "         " + rs.getString(4) + "      " + rs.getString(5) + "          " + rs.getString(6)
						+ "       " + rs.getLong(7));

			System.out.println("\nEmployee Designation Updated Successfully.....");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private static void updatename() throws Exception, SQLException {
		try { 
			br = new BufferedReader(new InputStreamReader(System.in));
			int Empid = 0;
			String name;
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pms", "root", "40540633@Sai");
			System.out.println("Connection Established");
			System.out.println("Enter Employee ID:");
			Empid = Integer.parseInt(br.readLine());
			System.out.println("Enter Employee Name to update ");
			name = br.readLine();

			PreparedStatement stmt = con.prepareStatement(
					"update employee set emp_name=" + "'" + name + "'" + " where emp_id=" + Empid + "");

			int rows=stmt.executeUpdate();
			if(rows==0) {
				System.out.println("|--Employee Id Does not Found!--|\n Please Enter Valid Employee Id");
			}
			else {
			PreparedStatement stmt1 = con.prepareStatement("select * from employee where emp_id=" + Empid + "");

			ResultSet rs = stmt1.executeQuery("select * from employee  where emp_id=" + Empid + "");
			System.out.println(
					"Emp Id  Emo_Name     Emp_Desig         Emp_Salary     Emp_Address     Emp_Email          Emp_Phone");
			while (rs.next())
			{
				System.out.println(rs.getInt(1) + "       " + rs.getString(2) + "          " + rs.getString(3)
						+ "         " + rs.getString(4) + "      " + rs.getString(5) + "          " + rs.getString(6)
						+ "       " + rs.getLong(7));

			System.out.println("\nEmployee Name Updated Successfully.....");
		}
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	

	private static void addemployee() throws Exception {
		try {
			// reading values from user by Input Stream using Buffered Reader
			br = new BufferedReader(new InputStreamReader(System.in));
			Class.forName("com.mysql.cj.jdbc.Driver");
			String ename, edesig, eaddress, eemail;
			int empId = 0;
			long phone = 0;
			float salary = 0;

			System.out.println("Enter empoloyee Id:");
			empId = Integer.parseInt(br.readLine());
			if (empId == 0) {
				System.out.println("|--Employee Id Shouldn't be '0'--|\n Please Enter Valid Employee Id");
			} else {
				System.out.println("ENter Employee name:");
				ename = br.readLine();
				System.out.println("Enter Employee Designtion :");
				edesig = br.readLine();
				System.out.println("Enter Employee Salary:");
				salary = Float.parseFloat(br.readLine());
				System.out.println("Enter Employee Address:");
				eaddress = br.readLine();
				System.out.println("Enter Employee Email:");
				eemail = br.readLine();
				System.out.println("Enter Contact Num:");
				phone = Long.parseLong(br.readLine());

				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pms", "root", "40540633@Sai");

				PreparedStatement stmt = con.prepareStatement("Insert into employee Values(?,?,?,?,?,?,?)");

				stmt.setInt(1, empId);
				stmt.setString(2, ename);
				stmt.setString(3, edesig);
				stmt.setFloat(4, salary);
				stmt.setString(5, eaddress);
				stmt.setString(6, eemail);
				stmt.setLong(7, phone);

				int i = stmt.executeUpdate();
				System.out.println("Employee Details are Added Successfully......");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private static void searchemployee() throws Exception, IOException {
		// TODO Auto-generated method stub
		try {
			br = new BufferedReader(new InputStreamReader(System.in));

			Class.forName("com.mysql.cj.jdbc.Driver");
			String ename, edesig, eaddress, eemail;
			int empId = 0;
			long phone = 0;
			float salary = 0;

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pms", "root", "40540633@Sai");
			System.out.println("Connection Established");
			System.out.println("Enter Employee ID:");
			empId = Integer.parseInt(br.readLine());
			PreparedStatement stmt = con.prepareStatement("select * from employee where emp_id = " + empId + "");

			ResultSet rs = stmt.executeQuery();

			System.out.println("|---------------Your Search Result-----------------|");
			System.out
					.println("Emp Id  Emo_Name  Emp_Desig       Emp_Salary    Emp_Address   Emp_Email       Emp_Phone");
			while (rs.next()) {
				System.out.printf(rs.getInt(1) + "        " + rs.getString(2) + "       " + rs.getString(3) + "      "
						+ rs.getFloat(4) + "      " + rs.getString(5) + "     " + rs.getString(6) + "     "
						+ rs.getLong(7));

			}

		} catch (Exception e) {
			System.out.println("Enter Valid Details!");
		}

	}

	private static void deleteemployee() {
		// TODO Auto-generated method stub

		try {
			// reading values from user by Input Stream using Buffered Reader
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			Class.forName("com.mysql.cj.jdbc.Driver");

			int empId = 0;

			System.out.println("Enter empoloyee Id:");
			empId = Integer.parseInt(br.readLine());
			System.out.println("Are you Sure to delete the Record ?");
			System.out.println("1.Yes\n2.No\n(Enter your Choice)");
			int n = Integer.parseInt(br.readLine());
			// Prompting user to take Confirmation...
			switch (n) {
			case 1:

				// Creating Connection using Driver Manager to establish connection to the
				// Database

				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pms", "root", "40540633@Sai");
				PreparedStatement stmt = con.prepareStatement("set foreign_key_checks=0");
				PreparedStatement stmt1 = con.prepareStatement("delete from employee where emp_id=" + empId + "");

				int i = stmt.executeUpdate();
				int rs = stmt1.executeUpdate();
				if (rs == 0) {
					System.out.println("EMployee Details Not Found!!!\n*Please Enter Valid Employee Id*");
				} else {
					System.out.println("Employee Record Deleted Successfully......");
				}
				break;
			case 2:
				System.out.println("You Choose Not to Delete!\nEmployee Recorded was not Deleted...");
				break;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private static void viewemployees() {

		try {
			br = new BufferedReader(new InputStreamReader(System.in));

			Class.forName("com.mysql.cj.jdbc.Driver");
			String ename, edesig, eaddress, eemail;
			int empId = 0;
			long phone = 0;
			float salary = 0;
			// Creating Connection using Driver Manager to establish connection to the
			// Database
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pms", "root", "40540633@Sai");

			PreparedStatement stmt = con.prepareStatement("select * from employee");

			ResultSet rs = stmt.executeQuery();

			System.out.println("|---------------List of all Employee's-----------------|");
			System.out
					.println("Emp Id  Emo_Name  Emp_Desig       Emp_Salary    Emp_Address   Emp_Email       Emp_Phone");
			while (rs.next())

				System.out.println(rs.getInt(1) + "        " + rs.getString(2) + "       " + rs.getString(3) + "      "
						+ rs.getFloat(4) + "      " + rs.getString(5) + "     " + rs.getString(6) + "     "
						+ rs.getLong(7));

			System.out.println("Employees list retrieved Successfully......");

		} catch (Exception e) {
			System.out.println(e);
		}
		// TODO Auto-generated method stub

	}

	public static void managepayroll() throws IOException {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));

			Class.forName("com.mysql.cj.jdbc.Driver");

			int EmpId = 0;
			String Emp_Dept;
			float Basic_pay, Hra, Da, Bonus, Pf;

			System.out.println("Enter empoloyee Id:");
			EmpId = Integer.parseInt(br.readLine());
			System.out.println("ENter Employee Departmnt:");
			Emp_Dept = br.readLine();
			System.out.println("Enter Employee Bsic Pay :");
			Basic_pay = Float.parseFloat(br.readLine());
			System.out.println("Enter Employee Hose Rent Allowance :");
			Hra = Float.parseFloat(br.readLine());
			System.out.println("Enter Employee Dearness Aloowance :");
			Da = Float.parseFloat(br.readLine());
			System.out.println("Enter Employee Bonus :");
			Bonus = Float.parseFloat(br.readLine());
			System.out.println("Enter Employee Privident Fund :");
			Pf = Float.parseFloat(br.readLine());

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pms", "root", "40540633@Sai");

			PreparedStatement stmt = con.prepareStatement("Insert into managepayroll Values(?,?,?,?,?,?,?,null)");

			stmt.setInt(1, EmpId);
			stmt.setString(2, Emp_Dept);
			stmt.setFloat(3, Basic_pay);
			stmt.setFloat(4, Hra);
			stmt.setFloat(5, Da);
			stmt.setFloat(6, Bonus);
			stmt.setFloat(7, Pf);

			int i = stmt.executeUpdate();

			System.out.println("Employee Payroll Details Registered Successfully......");

		} catch (Exception e) {
			System.out.println("|-----Employee Doesn't Exist-----|\nPlease Enter Valid Employee ID!!");
		}

	}

	private static void generatepayslip() {
		// TODO Auto-generated method stub
		try {
			br = new BufferedReader(new InputStreamReader(System.in));

			Class.forName("com.mysql.cj.jdbc.Driver");

			int EmpId = 0;
			String Emp_Dept;
			float Basic_pay, Hra, Da, Bonus, Pf;

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pms", "root", "40540633@Sai");
			System.out.println("Connection Established");
			System.out.println("Enter Employee ID:");
			EmpId = Integer.parseInt(br.readLine());

			PreparedStatement stmt1 = con.prepareStatement(
					"update managepayroll set Gross=(basic_pay + hra + da + bonus) -pf where emp_id=" + EmpId + "");
			PreparedStatement stmt2 = con.prepareStatement("select * from managepayroll where emp_id=" + EmpId + "");
			int rows = stmt1.executeUpdate();
			if (rows == 0) {
				System.out.println("EMployee Details Not Found!!!");
			} else {
				System.out.println("************************************************");
				System.out.println("|------------PAY SLIP FOR EMPLOYEE-------------|");
				System.out.println("************************************************");
				ResultSet rs = stmt2.executeQuery("select * from managepayroll where emp_id=" + EmpId + "");

				while (rs.next()) {
					System.out.printf("\nEmployeee ID           :" + rs.getInt(1));
					System.out.printf("\nEmployeee Department   :" + rs.getString(2));
					System.out.printf("\nEmployeee Basic Pay    :Rs.%.2f", rs.getFloat(3));
					System.out.printf("\nEmployeee HRA          :Rs.%.2f", rs.getFloat(4));
					System.out.printf("\nEmployeee DA           :Rs.%.2f", rs.getFloat(5));
					System.out.printf("\nEmployeee Bonus        :Rs.%.2f", rs.getFloat(6));
					System.out.printf("\nEmployeee PF           :Rs.%.2f", rs.getFloat(7));
					System.out.printf("\nEmployeee Net Payable  :Rs.%.2f", rs.getFloat(8));
					System.out.println("(Dispersable Amount)");
					System.out.println("\n************************************************");
					System.out.println("|------------******FSS-Corp*******-------------|");
					System.out.println("************************************************");
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private static void recordattendance() {
		// TODO Auto-generated method stub
		try {
			// Creating Connection using Driver Manager to establish connection to the
			// Database
			br = new BufferedReader(new InputStreamReader(System.in));

			Class.forName("com.mysql.cj.jdbc.Driver");
			float emp_wd, emp_wh, n_leaves = 0;
			int emp_id = 0;
			System.out.println("Enter Employee Id :");
			emp_id = Integer.parseInt(br.readLine());
			System.out.println("Enter no of Hours Employee Worked :");
			emp_wh = Float.parseFloat(br.readLine());
			System.out.println("Enter no of Days Employee Worked:");
			emp_wd = Float.parseFloat(br.readLine());
			System.out.println("Enter no.of Leaves taken :");
			n_leaves = Float.parseFloat(br.readLine());

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pms", "root", "40540633@Sai");

			PreparedStatement stmt = con.prepareStatement("insert into empatendance values(?,?,?,?)");
//'?'refers to parameter index in the database
			stmt.setInt(1, emp_id);
			stmt.setFloat(2, emp_wh);
			stmt.setFloat(3, emp_wd);
			stmt.setFloat(4, n_leaves);

			int i = stmt.executeUpdate();
			if (i == 0) {
				System.out.println("EMployee Details Not Found!!!");
			} else {

				System.out.println("|--------Employee Attendance Details Recorded Successfully--------|");
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
