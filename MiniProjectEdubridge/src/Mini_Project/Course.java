package Mini_Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Course {

	void addCourse(String name, int fee)
	{
		Statement st = DatabaseConnectivity.getStatement();
		String query = "INSERT INTO courses VALUES (null, '" + name + "', " + fee + ")";
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Error : " + e);
		}
	}
	
	static void enrollCourse(int student_id, int course_id)
	{
		int fee;
		Statement st = DatabaseConnectivity.getStatement();
		String query = "INSERT INTO enrollments VALUES (null, " + student_id + ", " + course_id + ")";
		try {
			st.executeUpdate(query);
			ResultSet result = st.executeQuery("SELECT fee FROM courses WHERE id = " + course_id);
			result.first();
			fee = Integer.parseInt(result.getString(1));
			Student.payFee(student_id, (-1 * fee));
			System.out.println("Successfully Enrolled To The Course !");
			System.out.println("\u001B[32m" + "Student With ID " + student_id + " Has Been Enrolled To Course With ID "+ course_id +
					" Succesfully !" + "\u001B[0m");
		} catch (SQLException e) {
			System.out.println("Error : " + e);
		}
		
		
		
	}
	
	static int showCourses() {
		int count = 0;
		Statement st = DatabaseConnectivity.getStatement();
		String query = "SELECT * FROM courses";
		ResultSet result;
		try {
			result = st.executeQuery(query);
			System.out.println("\u001B[1m" + "Course ID \t Name \t\tFee" + "\u001B[0m");
			while (result.next()) {
				System.out.println(result.getString(1) + "\t\t" + result.getString(2) + "\t\t" + result.getString(3));
				count++;
			}
		} catch (SQLException e) {
			System.out.println("Error : " + e);
		}
		return count;
	}
}
