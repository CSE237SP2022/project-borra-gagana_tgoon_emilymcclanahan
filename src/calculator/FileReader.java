package calculator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class FileReader {

	private String courseWeightFilePath;
	private String courseGradeFilePath;

	public FileReader(String courseWeightFilePath, String courseGradeFilePath) {
		this.courseWeightFilePath = courseWeightFilePath;
		this.courseGradeFilePath = courseGradeFilePath;
	}

	private ArrayList<String> readFile(String filePath) {

		File file = new File("./src/" + filePath);

		ArrayList<String> fileContents = new ArrayList<String>();

		try {
			Scanner fileIn = new Scanner(file);

			this.addFileContents(fileContents, fileIn);

			fileIn.close();

		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}

		return fileContents;
	}

	private void addFileContents(ArrayList<String> fileContents, Scanner fileIn) {
		while (fileIn.hasNextLine()) {
			String line = fileIn.nextLine();
			fileContents.add(line);
		}
	}

	public Hashtable<String, Double> createGradingScale() {

		ArrayList<String> fileContents = readFile(courseWeightFilePath);

		Hashtable<String, Double> gradingScale = new Hashtable<String, Double>();

		for (String line : fileContents) {
			this.addWeight(gradingScale, line);
		}
		return gradingScale;
	}

	private void addWeight(Hashtable<String, Double> gradingScale, String line) {
		// splitting each line into its 2 separate words; first words (grade type) is
		// they key, second word (grade score) is the value
		String[] words = line.split(" ", 2);
		gradingScale.put(words[0], Double.parseDouble(words[1]));
	}

	public ArrayList<Assignment> createAssignments() {
		
		ArrayList<String> fileContents = readFile(courseGradeFilePath);

		ArrayList<Assignment> assignments = new ArrayList<Assignment>();

		for (String line : fileContents) {
			this.addAssignment(assignments, line);
		}
		return assignments;
	}
	
	private void addAssignment(ArrayList<Assignment> assignments, String line) {
		
		// splitting each line into its 3 separate words; assignment, assignment type,
		// weight
		String[] words = line.split(" ", 3);

		Assignment assignment = new Assignment(Double.parseDouble(words[2]), words[1], words[0]);
		assignments.add(assignment);
	}
}
