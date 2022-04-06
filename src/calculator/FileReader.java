package calculator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class FileReader {
	
	private String courseWeightFilePath = "class/class_weight.txt";
	private String courseGradeFilePath = "grades/class_grade.txt";
	
	public FileReader(String courseWeightFilePath, String courseGradeFilePath) {
		this.courseWeightFilePath = courseWeightFilePath;
		this.courseGradeFilePath = courseGradeFilePath;
	}
	
	
	public ArrayList<String> readFile(String filePath) {
		
		File file = new File("./src/" + filePath);
		
		ArrayList<String> fileContents = new ArrayList<String>();
		
		try  {
			Scanner fileIn = new Scanner(file);
			
			while(fileIn.hasNextLine()) {
				String line = fileIn.nextLine();
				fileContents.add(line);
			}
			
		} catch(FileNotFoundException exception) {
			exception.printStackTrace();
		}
		
		return fileContents;
	}
	
	public Hashtable<String, Integer> createGradingScale() {
		ArrayList<String> fileContents = readFile(courseWeightFilePath);
		
		Hashtable<String, Integer> gradingScale = new Hashtable<String,Integer>();
		for (String line : fileContents) {
			//splitting each line into its 2 separate words; first words (grade type) is they key, second word (grade score) is the value
			String[] words = line.split(" ",2);
			gradingScale.put(words[0], Integer.parseInt(words[1]));
		}
		return gradingScale;
	}
	
	public ArrayList<Assignment> createAssignments() {
		ArrayList<String> fileContents = readFile(courseGradeFilePath);
		
		ArrayList<Assignment> assignments = ArrayList<Assignment>();
				
		for (String line : fileContents) {
			//splitting each line into its 3 separate words; assignment, assignment type, weight
			String[] words = line.split(" ",3);
			
			Assignment assignment = new Assignment(words[2], words[1], words[0]);
			assignments.add(assignment);
		}
		return assignments;
	}
}
