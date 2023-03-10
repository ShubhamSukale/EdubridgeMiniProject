package Mini_Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Student {

	static int addStudent(String name) {
		Statement st = DatabaseConnectivity.getStatement();
		String query = "INSERT INTO students VALUES(null, '" + name + "', " + 0 + ")";
		try {
			st.executeUpdate(query);
			ResultSet result = st.executeQuery("SELECT id FROM students WHERE name = " + "'" + name + "'");
			result.first();
			return Integer.parseInt(result.getString(1));
		} catch (SQLException e) {
			System.out.println("Error : " + e);
			return 0;
		}
	}

	static int getBalance(int id) {
		Statement st = DatabaseConnectivity.getStatement();
		String query = "SELECT balance FROM students WHERE id = " + id;
		try {
			ResultSet result = st.executeQuery(query);
			result.first();
			int balance = Integer.parseInt(result.getString(1));
			return balance;
		} catch (SQLException e) {
			System.out.println("Error : " + e);
			return -1;
		}
	}

	static void payFee(int id, int amount) {
		Statement st = DatabaseConnectivity.getStatement();
		int balance = getBalance(id);
		balance = balance - amount;
		// System.out.println("Balance " + balance);
		String query = "UPDATE students SET balance = " + balance + " WHERE id = " + id;

		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Error : " + e);
		}
	}

	static void showStatus(int id) {
		String name = null;
		int balance = 0;
		ArrayList<Integer> courses_ids = new ArrayList<>();
		ArrayList<String> courses = new ArrayList<>();

		Statement st = DatabaseConnectivity.getStatement();
		String query = "SELECT * FROM students WHERE id = " + id;
		try {
			ResultSet result = st.executeQuery(query);
			result.first();
			name = result.getString(2);
			balance = Integer.parseInt(result.getString(3));
		} catch (SQLException e) {
			System.out.println("Error : " + e);
		}

		query = "SELECT course_id FROM enrollments WHERE student_id = " + id;
		try {
			ResultSet result = st.executeQuery(query);
			while (result.next()) {
				courses_ids.add(Integer.parseInt(result.getString(1)));
			}
		} catch (SQLException e) {
			System.out.println("Error : " + e);
		}

		for (Integer c_id : courses_ids) {
			query = "SELECT name FROM courses WHERE id = " + c_id;
			try {
				ResultSet result = st.executeQuery(query);
				result.first();
				courses.add(result.getString(1));
			} catch (SQLException e) {
				System.out.println("Error : " + e);
			}
		}

		displayStatus(id, name, balance, courses);
	}

	static void displayStatus(int id, String name, int balance, ArrayList<String> courses) {
		System.out.println("Student ID : \t " + "\u001B[1m" + id + "\u001B[0m");
		System.out.println("Name : \t\t " + "\u001B[1m" + name + "\u001B[0m");
		System.out.println("Balance : \t " + "\u001B[1m" + balance + "\u001B[0m");
		System.out.println();
		System.out.println("Courses Enrolled : " + "\u001B[1m");
		for (String c : courses)
			System.out.println(c);
		System.out.println("\u001B[0m");
	}

	static void showAllStudents() {
		Statement st = DatabaseConnectivity.getStatement();
		String query = "SELECT * FROM students";
		ResultSet result;
		try {
			result = st.executeQuery(query);
			System.out.println("\u001B[1m" + "Student ID \t Name \t\tBalance" + "\u001B[0m");
			while (result.next()) {
				System.out.println(result.getString(1) + "\t\t" + result.getString(2) + "\t\t" + result.getString(3));
			}
		} catch (SQLException e) {
			System.out.println("Error : " + e);
		}
	}

	static void deleteStudent(int id) {
		Statement st = DatabaseConnectivity.getStatement();
		String query = "DELETE FROM students WHERE id = " + id;
		try {
			st.executeUpdate(query);
			System.out.println("\u001B[32m" + "Student With ID " + id + " Deleted Succesfully !" + "\u001B[0m");
		} catch (SQLException e) {
			System.out.println("Error : " + e);
		}
	}

}