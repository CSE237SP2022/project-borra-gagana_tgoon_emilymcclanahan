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
	
	public String[] printCourses() {
		File file = new File("./src/courses");
		String[] courses = file.list();
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
	
	public void welcome() {
		System.out.println("Hi, I'm BearBot, your friendly grade calculator! Please pick a class from the list below!");
		String[] courses = printCourses();
		String courseName = prompt();
		if (Arrays.stream(courses).anyMatch(courseName::equals)) {
			getDesiredDateFile(courseName);
		}
		else {
			System.out.println("Class not found, please try again");
			welcome();
		}
	}
	
	public void getDesiredDateFile (String courseName) {
		System.out.println("Which day would you like to see your grades for?");
		String[] dateFiles = printDateFiles(courseName);
		String dateFileName = prompt();
		if (Arrays.stream(dateFiles).anyMatch(dateFileName::equals)) {
			printGrade(courseName, dateFileName);
		}
		else {
			System.out.println("File not found, please try again");
			getDesiredDateFile(courseName);
		}
	}
	
	public void printGrade(String courseName, String dateFileName) {
		
		String courseWeightsFilePath = "courses/" + courseName + "/weights.txt";
		String courseGradeFilePath = "courses/" + courseName + "/grades/" + dateFileName;
		
		FileReader fr = new FileReader(courseWeightsFilePath,courseGradeFilePath);
		
		Hashtable<String, Double> courseGradingScale = fr.createGradingScale();
		ArrayList<Assignment> courseAssignments = fr.createAssignments();
		
		Course c = new Course(courseGradingScale,courseAssignments);
		
		Double courseGrade = c.getFinalGrade();
		String courseLetterGrade = c.getLetterGrade();
		
		System.out.println("Your grade for " + courseName + " is: " + courseGrade + ", " + courseLetterGrade);
		
	}
	
	
}
