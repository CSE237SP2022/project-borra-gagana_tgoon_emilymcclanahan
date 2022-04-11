package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Hashtable;

import org.junit.jupiter.api.Test;

import calculator.gradeCalculator;
import calculator.Assignment;
import calculator.FileReader;

class testFileReader {

	@Test
	void testCreateGradingScaleLength() {
		ArrayList<String> fileContents = new ArrayList<String>();

		FileReader fileReader = new FileReader("courses/CSE237/weights.txt", "courses/CSE237/grades/04_06_2020.txt");
		
		Hashtable<String, Double> gradingScale = fileReader.createGradingScale();
		
		assertEquals(2, gradingScale.size());
	}
	
	@Test
	void testCreateAssignmentsLength() {
		ArrayList<String> fileContents = new ArrayList<String>();

		FileReader fileReader = new FileReader("courses/CSE237/weights.txt", "courses/CSE237/grades/04_06_2020.txt");
		
		ArrayList<Assignment> assignments = fileReader.createAssignments();
		
		assertEquals(8, assignments.size());
	}
}
