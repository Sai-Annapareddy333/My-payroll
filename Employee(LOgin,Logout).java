Public class ELogin
{
  private static void employeeLogin() throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter User Name:");
		String username = br.readLine();
		System.out.println("Enter Password:");
		String password = br.readLine();
		if ("employee".equals(username) && "employee123".equals(password))

		{
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
				case 2:
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
		} else {
			System.out.println("Invalid Username 'or' Password !!");
			System.out.println("!!!!Please Enter a Valid Details!!! ");
		}

	}
}
