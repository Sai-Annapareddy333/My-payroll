Publlic class ViewPayslip{
	private static void viewpayslip() {
		// TODO Auto-generated method stub
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			Class.forName("com.mysql.cj.jdbc.Driver");

			int EmpId = 0;
			String Emp_Dept;
			float Basic_pay, Hra, Da, Bonus, Pf;

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pms", "root", "40540633@Sai");
			System.out.println("Connection Established");
			System.out.println("Enter your Employee ID to View Payslip:");
			EmpId = Integer.parseInt(br.readLine());

			PreparedStatement stmt1 = con.prepareStatement(
					"update managepayroll set Gross=(basic_pay + hra + da + bonus) -pf where emp_id=" + EmpId + "");
			PreparedStatement stmt2 = con.prepareStatement("select * from managepayroll where emp_id=" + EmpId + "");
			stmt1.executeLargeUpdate();
			System.out.println("*************************************************");
			System.out.println("|------------PAY SLIP FOR EMPLOYEE--------------|");
			System.out.println("************************************************");
			ResultSet rs = stmt2.executeQuery("select * from managepayroll where emp_id=" + EmpId + "");

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
}
