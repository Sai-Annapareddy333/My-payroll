private static void viewprofile() {
		// TODO Auto-generated method stub

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			Class.forName("com.mysql.cj.jdbc.Driver");
			String ename, edesig, eaddress, eemail;
			int empId = 0, phone = 0;
			float salary = 0;

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pms", "root", "40540633@Sai");
			System.out.println("Connection Established");
			System.out.println("Enter your Employee ID:");
			empId = Integer.parseInt(br.readLine());
			PreparedStatement stmt = con.prepareStatement("select * from employee where emp_id = " + empId + "");

			ResultSet rs = stmt.executeQuery();

			System.out.println("|---------------Employee Profile-----------------|");
			System.out
					.println("Emp Id  Emo_Name  Emp_Desig       Emp_Salary    Emp_Address   Emp_Email       Emp_Phone");
			while (rs.next()) {
				System.out.printf(rs.getInt(1) + "        " + rs.getString(2) + "       " + rs.getString(3) + "      "
						+ rs.getFloat(4) + "      " + rs.getString(5) + "     " + rs.getString(6) + "     "
						+ rs.getInt(7));
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}
