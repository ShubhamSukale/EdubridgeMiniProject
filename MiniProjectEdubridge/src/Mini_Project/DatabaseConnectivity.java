package Mini_Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnectivity {
	
	public static Statement getStatement()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management_system?useSSL=false","root","Shubham#123");
			return connection.createStatement();
			
		}
		catch(Exception e)
		{
			System.out.println("Error : " + e);
			return null;
		}
	}
	
	

}
