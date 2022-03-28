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
	void testAverageArrayList() {
		ArrayList<Integer> testGrades = new ArrayList<Integer>();
		testGrades.add(70);
		testGrades.add(80);
		testGrades.add(90);
		float averageGrade = gradeCalculator.averageArrayList(testGrades);
		assertEquals(80, averageGrade, 0.01);
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
                add("Assignment_2 Hw 90");
                add("Assignment_3 Test 75");
                add("Assignment_4 Hw 95");
                add("Assignment_5 Par 77");
                add("Assignment_6 Hw 66");
                add("Assignment_7 Par 99");
                add("Assignment_8 Test 83");
            }
        };
        
        assertEquals(grades, fileContents);
	}
	
	@Test
	void testReadFileWeights() {
		String filePath = "class/class_weight.txt";
		ArrayList<String> fileContents = gradeCalculator.readFile(filePath);
		
		ArrayList<String> weights = new ArrayList<String>() {
            {
                add("Test 60");
                add("Hw 20");
                add("Par 20");
            }
        };
        
        assertEquals(weights, fileContents);
	}
	
	@Test
	void testCalculateFinalGrade() {
		Hashtable<String, ArrayList<Integer>> grades = new Hashtable<>();
		Hashtable<String, Integer> gradingScale = new Hashtable<>();
		
		ArrayList<Integer> hws = new ArrayList<Integer>();
		ArrayList<Integer> tests = new ArrayList<Integer>(); 
		
		hws.add(100);
		hws.add(90);
		tests.add(85);
		tests.add(90);
		
		grades.put("Hw",hws);
		grades.put("Test",tests);
		
		gradingScale.put("Hw",40);
		gradingScale.put("Test",60);
		
		float finalGrade = gradeCalculator.calculateFinalGrade(grades, gradingScale);
		assertEquals(90.5, finalGrade, 0.01);
	}
}
