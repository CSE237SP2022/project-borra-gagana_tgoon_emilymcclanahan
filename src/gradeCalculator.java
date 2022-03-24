import java.io.File;
import java.io.FileNotFoundException;
import java.lang.String;
import java.util.Scanner;
import java.util.ArrayList;

public class gradeCalculator {

	public static void main(String[] args) {
		System.console().writer().println("start");
		//hardcoded for now, later will read the user's input
		String classWeightFileName = "class/class_weight";
		String classGradeFileName = "grades/class_grade";
		
		ArrayList<String> weights = readFile(classWeightFileName);
		ArrayList<String> grades = readFile(classGradeFileName);
		
		for (String s : weights) {
	      System.console().writer().println(s);
	    }
	}
	
	public static ArrayList<String> readFile(String fileName) {
				
		File file = new File("src/" + fileName);
		
		ArrayList<String> fileContents = new ArrayList<String>();
		
		try  {
			Scanner fileIn = new Scanner(file);
			
			while(fileIn.hasNextLine()) {
				String line = fileIn.nextLine();
				fileContents.add(line);
			}
			
		} catch(FileNotFoundException exception) {
			System.console().writer().println("exception");
			exception.printStackTrace();
		}
		
		return fileContents;
	}
}
