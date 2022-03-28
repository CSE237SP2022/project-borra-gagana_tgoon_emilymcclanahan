package calculator;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.String;
//import java.util.Scanner;
//import java.util.ArrayList;
import java.util.*;

public class gradeCalculator {

	public static void main(String[] args) {

		String classWeightFilePath = "class/class_weight.txt";
		String classGradeFilePath = "grades/class_grade.txt";
		
		ArrayList<String> weightsList = readFile(classWeightFilePath);
		ArrayList<String> gradesList = readFile(classGradeFilePath);

		Hashtable<String, Integer> gradingScale = createGradingScale(weightsList);
		Hashtable<String, ArrayList<Integer>> grades = createGrades(gradesList,gradingScale);
		
		float finalGrade = calculateFinalGrade(grades,gradingScale);
		System.out.println(finalGrade);
	}
	

 
	public static ArrayList<String> readFile(String filePath) {
				
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
	
	public static Hashtable<String, Integer> createGradingScale(ArrayList<String> fileContents) {
		Hashtable<String, Integer> gradingScale = new Hashtable<String,Integer>();
		for (String line : fileContents) {
			String[] words = line.split(" ",2);
			gradingScale.put(words[0], Integer.parseInt(words[1]));
		}
		return gradingScale;
	}
	
	public static Hashtable<String, ArrayList<Integer>> createGrades(ArrayList<String> fileContents, Hashtable<String, Integer> gradingScale) {
		Hashtable<String, ArrayList<Integer>> grades = new Hashtable<String, ArrayList<Integer>>();
		Enumeration<String> keys = gradingScale.keys();
		while (keys.hasMoreElements()) {
            grades.put(keys.nextElement(),new ArrayList<Integer>());
        }
		for (String line : fileContents) {
			String[] words = line.split(" ",3);
			ArrayList<Integer> currentGrades = grades.get(words[1]);
			currentGrades.add(Integer.parseInt(words[2]));
			grades.put(words[1], currentGrades);
		}
		return grades;
	}
	
	public static float calculateFinalGrade(Hashtable<String, ArrayList<Integer>> grades, Hashtable<String, Integer> gradingScale) {
		
		float finalGrade = 0;
		
		Enumeration<String> gradeTypes = grades.keys();
		while (gradeTypes.hasMoreElements()) {
			String gradeType = gradeTypes.nextElement();
			finalGrade += gradingScale.get(gradeType)*averageArrayList(grades.get(gradeType));
        }
	
		return finalGrade / 100;
	}
	
	public static float averageArrayList(ArrayList<Integer> list) {
	    float sum = 0;
	    float size = list.size();
	    for (int i: list) {
	        sum += i;
	    }
	    return sum/size;
	}
}
