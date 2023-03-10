package Mini_Project;

import java.util.Scanner;

public class Home {

	static Scanner input = new Scanner(System.in);

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_RED_BG = "\u001B[41m";
	public static final String ANSI_GREEN_BG = "\u001B[42m";
	public static final String ANSI_YELLOW_BG = "\u001B[43m";
	public static final String ANSI_BOLD = "\u001B[1m";

	public static void main(String[] args) {

		menu();
	}

	static void hr() {
		for (int i = 0; i < 50; i++) {
			System.out.print("-");
		}
		System.out.println();
	}

	static void login() {
		System.out.println(ANSI_GREEN_BG + ANSI_WHITE + "WELCOME TO STUDENT MANAGEMENT SYSTEM !" + ANSI_RESET);
		System.out.println(ANSI_BLUE + "Admin Login --> " + ANSI_RESET);
		System.out.print(ANSI_BOLD + "Username : " + ANSI_RESET);
		String username = input.next();
		System.out.print(ANSI_BOLD + "Password : " + ANSI_RESET);
		String password = input.next();

		if (username.equals("admin") && password.equals("12345")) {
			System.out.println(ANSI_GREEN + "Successfully Logged In !" + ANSI_RESET);
			hr();
			menu();
		} else {
			System.out.println(ANSI_RED + "Invalid Credentials ! Please Reload The Program !" + ANSI_RESET);
			hr();
		}
	}

	static void menu() {
		System.out.println(ANSI_BLUE + "What Do You  Want To Do ? " + ANSI_RESET);
		System.out.println("Please Choose One Option From Following : ");
		System.out.println("1. Student Operations");
		System.out.println("2. Course Operations");
		System.out.println("3. Logout");
		System.out.print(ANSI_CYAN + "Your Option : " + ANSI_RESET);
		int n = input.nextInt();
		hr();
		switch (n) {
		case 1:
			student_menu();
			break;
		case 2:
			course_menu();
			break;
		case 3:
			System.out.println(ANSI_GREEN + "Successfully Logged Out !" + ANSI_RESET);
			System.out.println(ANSI_BLUE + ANSI_BOLD + "Thank You For Using Student Management System !" + ANSI_RESET);
			break;
		default:
			System.out.println(ANSI_RED + "Invalid Option Chosen !" + ANSI_RESET);
			hr();
			menu();
		}
	}
	
	
	// Student Menu
	static void student_menu() {
		System.out.println(ANSI_BLUE + "Students Operations --> " + ANSI_RESET);
		System.out.println("Please Choose One Option From Following : ");
		System.out.println("1. Show All Students");
		System.out.println("2. Add New Student");
		System.out.println("3. Show Status");
		System.out.println("4. Delete Student");
		System.out.println("5. Main Menu");
		System.out.print(ANSI_CYAN + "Your Option : " + ANSI_RESET);
		int n = input.nextInt();
		hr();
		switch (n) {
		case 1:
			showStudents();
			hr();
			student_menu();
			break;
		case 2:
			addNewStudent();
			hr();
			student_menu();
			break;
		case 3:
			showStatus();
			hr();
			student_menu();
			break;
		case 4:
			deleteStudent();
			hr();
			student_menu();
			break;
		case 5:
			menu();
			break;
		default:
			System.out.println(ANSI_RED + "Invalid Option Chosen !" + ANSI_RESET);
			hr();
			student_menu();
		}
	}

	static void showStudents() {
		System.out.println(ANSI_YELLOW + ANSI_BOLD + "ALL STUDENTS" + ANSI_RESET);
		Student.showAllStudents();
	}

	static void addNewStudent() {
		System.out.println(ANSI_YELLOW + ANSI_BOLD + "ADD NEW STUDENT" + ANSI_RESET);
		System.out.println("Enter Following Details : ");
		System.out.print(ANSI_BOLD + "Name : " + ANSI_RESET);
		String name = input.next();

		int id = Student.addStudent(name);
		if (id != 0) {
			System.out.println(ANSI_GREEN + "Successfully Added New Student ! " + ANSI_RESET);
			System.out.println(ANSI_BLUE + ANSI_BOLD + "Student ID : " + id + ANSI_RESET);
		} else {
			System.out.println(ANSI_RED + "An Error Occured At Server Side !" + ANSI_RESET);
		}
		hr();
		student_menu();
	}

	static void showStatus() {
		System.out.println(ANSI_YELLOW + ANSI_BOLD + "STATUS OF STUDENT" + ANSI_RESET);
		System.out.println("Please Enter Student ID : ");
		try {
			int id = input.nextInt();
			Student.showStatus(id);
		} catch (Exception e) {
			System.out.println("Error : " + e);
			showStatus();
		}
	}

	static void deleteStudent() {
		System.out.println(ANSI_YELLOW + ANSI_BOLD + "DELETE STUDENT" + ANSI_RESET);
		System.out.println("Please Enter Student ID : ");
		try {
			int id = input.nextInt();
			Student.deleteStudent(id);
		} catch (Exception e) {
			System.out.println("Error : " + e);
			deleteStudent();
		}
	}
	
	// End of Student Menu
	
	
	// Course Menu
	static void course_menu() {
		System.out.println(ANSI_BLUE + "Course Operations --> " + ANSI_RESET);
		System.out.println("Please Choose One Option From Following : ");
		System.out.println("1. Enroll To Course");
		System.out.println("2. Main Menu");
		System.out.print(ANSI_CYAN + "Your Option : " + ANSI_RESET);
		int n = input.nextInt();
		hr();
		
		switch (n) {
		case 1:
			enrollCourse();
			hr();
			course_menu();
			break;
		case 2:
			menu();
			break;
		default:
			System.out.println(ANSI_RED + "Invalid Option Chosen !" + ANSI_RESET);
			hr();
			course_menu();
		}
	}
	
	static void enrollCourse() {
		int id = 0, c_id = 0, count;
		System.out.println(ANSI_YELLOW + ANSI_BOLD + "ENROLL TO COURSE" + ANSI_RESET);
		System.out.println("Please Enter Student ID : ");
		try {
			id = input.nextInt();
		} catch (Exception e) {
			System.out.println("Error : " + e);
			enrollCourse();
		}
		
		System.out.println("Please Enter Course ID From Following : ");
		count = Course.showCourses();
		try {
			System.out.print(ANSI_CYAN + "Your Option : " + ANSI_RESET);
			c_id = input.nextInt();
		} catch (Exception e) {
			System.out.println("Error : " + e);
			enrollCourse();
		}
		
		if(c_id >= 1 && c_id <=count)
		{
			Course.enrollCourse(id, c_id);
		}
		else {
			System.out.println(ANSI_RED + "Invalid Option Chosen !" + ANSI_RESET);
			hr();
			enrollCourse();
		}
		
		
	}
	
}
