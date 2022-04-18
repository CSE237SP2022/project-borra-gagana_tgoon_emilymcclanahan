package calculator;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

public class Bearbot {

	private Scanner scanner = new Scanner(System.in);
	
	public String prompt() {
		String userInput = scanner.nextLine();
		return userInput;
	}
	
	public String[] getCourses() {
		File file = new File("./src/courses");
		String[] courses = file.list();
		return courses;
	}
	
	public String[] printCourses() {
		String[] courses = getCourses();
		for (String course: courses) {
			System.out.println(course);
		}
		return courses;
	}
	
	public String[] printDateFiles(String courseName) {
		File file = new File("./src/courses/" + courseName + "/grades");
		String[] dateFiles = file.list();
		for (String dateFile: dateFiles) {
			System.out.println(dateFile);
		}
		return dateFiles;
	}
	
	public boolean arrayContainsString(String[] array, String testString) {
		return Arrays.stream(array).anyMatch(testString::equals);
	}
	
	public void welcome() {
		System.out.println("Hi, I'm BearBot, your friendly grade calculator! Please pick a course from the list below, or type 'new' to create a new course.");
		String[] courses = printCourses();
		String courseName = prompt();
		
		if (courseName.equals("new")) {
			createNewCourse();
		}
		else if (arrayContainsString(courses, courseName)) {
			getDesiredDateFile(courseName);
		}
		else {
			System.out.println("Class not found, please try again.");
			welcome();
		}
	}
	
	public void createNewCourse() {
		System.out.println("What would you like to name this course?");
		String newCourseName = prompt();
		
		String[] courses = getCourses();
		
		if (arrayContainsString(courses, newCourseName)) {
			System.out.println("Course with the name '" + newCourseName + "' already exists. Please try again.");
			createNewCourse();
		}
		else if (!isValidStringInput(newCourseName)) {
			createNewCourse();
		}
		else {
			System.out.println("Created '" + newCourseName + "' course.");
			addNewAssignmentType(newCourseName);
		}
	}
	
	public void addNewAssignmentType(String courseName) {
		System.out.println("Would you like to add a new assignment type for '" + courseName + "' course? Please type 'yes' or 'no'.");
		String wantsToAdd = prompt();
		
		if (wantsToAdd.equals("yes")) {
			String assignmentTypeName = promptAssignmentType();
			String assignmentWeight = promptWeight(assignmentTypeName);
			
			System.out.println("Assignment '" + assignmentTypeName + "' with a weight of " + assignmentWeight + " added to course '" + courseName + "'.");
			addNewAssignmentType(courseName);
		}
		else if (wantsToAdd.equals("no")) {
			System.out.println("Done!");
		}
		else {
			System.out.println("Response not understood. Would you like to add a new assignment type for course " + courseName + "? Please type 'yes' or 'no'.");
		}
	}
	
	public String promptAssignmentType() {
		System.out.println("What would you like to call this assignment type?");
		String assignmentTypeName = prompt();
		
		if (!isValidStringInput(assignmentTypeName)) {
			return promptAssignmentType();
		}
		
		return assignmentTypeName;
	}
	
	public String promptWeight(String assignmentType) {
		System.out.println("Enter the percentage weight for assignment type '" + assignmentType + "'.");
		String assignmentWeight = prompt();
		
		if (!isValidPercentInput(assignmentWeight)) {
			return promptWeight(assignmentType);
		}
		
		return assignmentWeight;
	}
	
	public Boolean isValidStringInput(String input) {
		if (input.contains(" ") ) {
			System.out.println("Please enter a value that does not countain spaces.");
			return false;
		}
		if (input.isBlank()) {
			System.out.println("Please enter a non-blank value.");
			return false;
		}
		return true;
	}
	
	public Boolean isValidPercentInput(String input) {
		String regex = "^[0-9]+$";
		if (!input.matches(regex)) {
			System.out.println("Please enter a percent value that only contains numbers.");
			return false;
		}
		return true;
	}
	
	
	public void getDesiredDateFile (String courseName) {
		System.out.println("Which day would you like to see your grades for?");
		String[] dateFiles = printDateFiles(courseName);
		String dateFileName = prompt();
		if (arrayContainsString(dateFiles,dateFileName)) {
			printGrade(courseName, dateFileName);
		}
		else {
			System.out.println("File not found, please try again.");
			getDesiredDateFile(courseName);
		}
	}
	
	public double printGrade(String courseName, String dateFileName) {
		
		String courseWeightsFilePath = "courses/" + courseName + "/weights.txt";
		String courseGradeFilePath = "courses/" + courseName + "/grades/" + dateFileName;
		
		FileReader fr = new FileReader(courseWeightsFilePath,courseGradeFilePath);
		
		Hashtable<String, Double> courseGradingScale = fr.createGradingScale();
		ArrayList<Assignment> courseAssignments = fr.createAssignments();
		
		Course c = new Course(courseGradingScale,courseAssignments);
		
		Double courseGrade = c.getFinalGrade();
		String courseLetterGrade = c.getLetterGrade();
		
		System.out.println("Your grade for " + courseName + " is: " + courseGrade + ", " + courseLetterGrade);
		
		return courseGrade;
	}

	
	
}
