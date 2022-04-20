package calculator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
		for (String course : courses) {
			System.out.println(course);
		}
		return courses;
	}

	public String[] getDateFiles(String courseName) {
		File file = new File("./src/courses/" + courseName + "/grades");
		String[] dateFiles = file.list();
		return dateFiles;
	}

	public String[] printDateFiles(String courseName) {
		String[] dateFiles = getDateFiles(courseName);
		for (String dateFile : dateFiles) {
			System.out.println(dateFile);
		}
		return dateFiles;
	}

	public boolean arrayContainsString(String[] array, String testString) {
		return Arrays.stream(array).anyMatch(testString::equals);
	}

	public void welcome() {
		System.out.println(
				"Hi, I'm BearBot, your friendly grade calculator! Please pick a course from the list below, or type 'new' to create a new course.");
		String[] courses = printCourses();
		String courseName = prompt();

		if (courseName.equals("new")) {
			createNewCourse();
		} else if (arrayContainsString(courses, courseName)) {
			getDesiredDateFile(courseName);
		} else {
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
		} else if (!isValidStringInput(newCourseName)) {
			createNewCourse();
		} else {
			this.makeNewCourseDirectory(newCourseName);
			System.out.println("Please create at least one assignment type for course '" + newCourseName + "'.");

			String assignmentTypeAndWeight = createNewAssignmentType(newCourseName);
			this.appendLineToFile("./src/courses/" + newCourseName + "/weights.txt", assignmentTypeAndWeight);

			System.out.println("Successfully created '" + newCourseName + "' course.");

			promptAddNewAssignmentType(newCourseName);
		}
	}

	private void makeNewCourseDirectory(String newCourseName) {
		new File("./src/courses/" + newCourseName).mkdirs();
		new File("./src/courses/" + newCourseName + "/grades").mkdirs();
		File newWeights = new File("./src/courses/" + newCourseName + "/weights.txt");
		try {
			newWeights.createNewFile();
		} catch (IOException fileExists) {
			fileExists.printStackTrace();
		}
	}

	// source:
	// https://stackoverflow.com/questions/1625234/how-to-append-text-to-an-existing-file-in-java
	private void appendLineToFile(String path, String line) {
		try (FileWriter fileWriter = new FileWriter(path, true);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
			printWriter.println(line);
		} catch (IOException failure) {
			failure.printStackTrace();
		}
	}

	public String createNewAssignmentType(String courseName) {
		String assignmentTypeName = promptAssignmentType();
		String assignmentWeight = promptWeight(assignmentTypeName);

		System.out.println("Assignment '" + assignmentTypeName + "' with a weight of " + assignmentWeight
				+ " added to course '" + courseName + "'.");

		return assignmentTypeName + " " + assignmentWeight;
	}

	public void promptAddNewAssignmentType(String courseName) {
		System.out.println("Would you like to add a new assignment type for '" + courseName
				+ "' course? Please type 'yes' or 'no'.");
		String wantsToAdd = prompt();

		if (wantsToAdd.equals("yes")) {
			String newAssignment = createNewAssignmentType(courseName);
			this.appendLineToFile("./src/courses/" + courseName + "/weights.txt", newAssignment);
			promptAddNewAssignmentType(courseName);
		} else if (wantsToAdd.equals("no")) {
			System.out.println("Done!");
		} else {
			System.out.println("Response not accepted. Please try again.");
			promptAddNewAssignmentType(courseName);
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
		if (input.contains(" ")) {
<<<<<<< HEAD
			System.out.println("Please enter a value that does not countain spaces.");
=======
			System.out.println("Please enter a value that does not contain spaces.");
>>>>>>> main
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

<<<<<<< HEAD
	public void getDesiredDateFile(String courseName) {
		System.out.println("Which day would you like to see your grades for?");
=======
	public Boolean isValidDateInput(String input) {
		String regex = "/^(0[1-9]|1[0-2])_(0[1-9]|[12][0-9]|3[01])_\\d{4}/";
		if (!input.matches(regex)) {
			System.out.println("Please enter a valid date.");
			return false;
		}
		return true;
	}

	public void getDesiredDateFile(String courseName) {
		System.out.println(
				"Would you like to create a new date file with updated grades or use an existing date file? If entering "
						+ "new date file, please enter 'new' ");
>>>>>>> main
		String[] dateFiles = printDateFiles(courseName);
		String dateFileName = prompt();
		if (dateFileName.equals("new")) {
			createNewDateFileGrades(courseName);
		} else if (arrayContainsString(dateFiles, dateFileName)) {
			printGrade(courseName, dateFileName);
		} else {
			System.out.println("File not found, please try again.");
			getDesiredDateFile(courseName);
		}
	}

	public void createNewDateFileGrades(String courseName) {
		System.out.println("Enter the date of your updated grades in the format: 'mm_dd_yyyy'.");
		String newDateFile = prompt();

		String[] dateFiles = getDateFiles(courseName);

		if (arrayContainsString(dateFiles, newDateFile + ".txt")) {
			System.out.println("Date file with the name '" + newDateFile + "' already exists. Please try again.");
			createNewDateFileGrades(courseName);
		} /*
			 * else if (!isValidDateInput(newDateFile)) {
			 * createNewDateFileGrades(courseName); }
			 */ else {
			// this.makeNewCourseDirectory(newDateFile);
			System.out.println("Please add at least one assignment's grade for date file '" + newDateFile + "'.");

			String assignmentNameTypeGrade = createNewAssignmentGrade(newDateFile);
			// this.appendLineToFile("./src/courses/" + newCourseName + "/weights.txt",
			// assignmentTypeAndWeight);

			System.out.println("Successfully created '" + newDateFile + "' date file.");

			promptAddNewAssignmentGrade(newDateFile);
		}
	}
	
	public String createNewAssignmentGrade(String dateFile) {
		String assignmentName = promptAssignmentName();
		String assignmentType = promptAssignmentType(); //fix this later
		String assignmentGrade = promptAssignmentGrade(assignmentName); 
		System.out.println("Assignment '" + assignmentName + "' with a type of '" + assignmentType +
				"' and a grade of " + assignmentGrade + " added to the date file '" + dateFile + "'.");
		
		return assignmentName + " " + assignmentType + " " + assignmentGrade;
	}

	public String promptAssignmentName() {
		System.out.println("What would you like to name this assignment?");
		String assignmentName = prompt();
		
		if (!isValidStringInput(assignmentName)) {
			return promptAssignmentName();
		}
				
		return assignmentName;
	}
	
	public String promptAssignmentGrade(String assignmentName) {
		System.out.println("What grade would you like to assign to assignment '" + assignmentName + "'?");
		String assignmentGrade = prompt();
		
		if (!isValidPercentInput(assignmentGrade)) {
			return promptAssignmentGrade(assignmentName);
		}
				
		return assignmentGrade;
	}
	public void promptAddNewAssignmentGrade(String dateFile) {
		System.out.println("Would you like to add a new assignment grade for '" + dateFile
				+ "'? Please type 'yes' or 'no'.");
		String wantsToAdd = prompt();

		if (wantsToAdd.equals("yes")) {
			String newAssignment = createNewAssignmentGrade(dateFile);
			//this.appendLineToFile("./src/courses/" + courseName + "/weights.txt", newAssignment);
			promptAddNewAssignmentGrade(dateFile);
		} else if (wantsToAdd.equals("no")) {
			System.out.println("Done!");
		} else {
			System.out.println("Response not accepted. Please try again.");
			promptAddNewAssignmentGrade(dateFile);
		}
	}

	public double printGrade(String courseName, String dateFileName) {

		String courseWeightsFilePath = "courses/" + courseName + "/weights.txt";
		String courseGradeFilePath = "courses/" + courseName + "/grades/" + dateFileName;

		FileReader fileReader = new FileReader(courseWeightsFilePath, courseGradeFilePath);

		Hashtable<String, Double> courseGradingScale = fileReader.createGradingScale();
		ArrayList<Assignment> courseAssignments = fileReader.createAssignments();

		Course course = new Course(courseGradingScale, courseAssignments);

		Double courseGrade = course.getFinalGrade();
		String courseLetterGrade = course.getLetterGrade();

		System.out.println("Your grade for " + courseName + " is: " + courseGrade + ", " + courseLetterGrade);

		return courseGrade;
	}

}
