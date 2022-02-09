ackage com.fss.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DbRcattndnce {
public static void main (String[] args) throws Exception {
		
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
		
		
		Class.forName("com.mysql.cj.jdbc.Driver");   
        float emp_wd,emp_wh,n_leaves = 0;
        int emp_id = 0;
		System.out.println("Enter Employee Id :");
		emp_id=Integer.parseInt(br.readLine());
		System.out.println("Enter no of Hours Employee Worked :");
		emp_wh=Float.parseFloat(br.readLine());
		System.out.println("Enter no of Days Employee Worked:");
		emp_wd=Float.parseFloat(br.readLine());
		System.out.println("Enter no.of Leaves taken :");
		n_leaves=Float.parseFloat(br.readLine());
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pms","root","40540633@Sai");
		
		PreparedStatement stmt=con.prepareStatement("insert into empatendance values(?,?,?,?)");

	    stmt.setInt(1, emp_id);
	    stmt.setFloat(2,emp_wh);
	    stmt.setFloat(3,emp_wd);
	    stmt.setFloat(4, n_leaves);
	    
	    int i=stmt.executeUpdate();

	    System.out.println("|--------Employee Attendance Details Recorded Successfully--------|");
		   
	    
		}catch(Exception e) {System.out.println(e);
		}
			}

		}
