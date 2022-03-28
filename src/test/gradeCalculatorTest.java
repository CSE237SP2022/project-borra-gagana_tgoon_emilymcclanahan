package test;


import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

import org.junit.jupiter.api.Test;

import calculator.gradeCalculator;

class gradeCalculatorTest {

	@Test
	void testCreateGradingScaleLength() {
		ArrayList<String> fileContents = new ArrayList<String>();

		fileContents.add("Test 60");
		fileContents.add("Hw 40");		
		
		Hashtable<String, Integer> gradingScale = gradeCalculator.createGradingScale(fileContents);
		assertEquals(2, gradingScale.size());
	}

	@Test
	void testCreateGradesLength() {
		//creating grading scale
		ArrayList<String> gradingScaleFileContents = new ArrayList<String>();

		gradingScaleFileContents.add("Test 60");
		gradingScaleFileContents.add("Hw 40");		
		
		Hashtable<String, Integer> gradingScale = gradeCalculator.createGradingScale(gradingScaleFileContents);
		
		ArrayList<String> gradesFileContents = new ArrayList<String>();

		gradesFileContents.add("Assignment_1 Test 60");
		gradesFileContents.add("Assignment_2 Hw 40");		
		
		Hashtable<String, ArrayList<Integer>> grades = gradeCalculator.createGrades(gradesFileContents, gradingScale);
		assertEquals(2, grades.size());
	}
	
	@Test
	void testReadFileGrades() {
		String filePath = "grades/class_grade.txt";
		ArrayList<String> fileContents = gradeCalculator.readFile(filePath);
		
		ArrayList<String> grades = new ArrayList<String>() {
            {
                add("Assignment_1 Test 80");
                add("Assignment_1 Test 80");
                add("Assignment_2 Hw 90");
                add("Assignment_3 Test 75");
                add("Assignment_4 Hw 95");
                add("Assignment_5 Par 77");
                add("Assignment_6 Hw 66");
                add("Assignment_7 Par 99");
                add("Assignment_8 Test 83");
            }
        };
        
        assertEquals(fileContents, grades);
	}
	
}
