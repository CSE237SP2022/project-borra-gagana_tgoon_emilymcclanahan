package test;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Hashtable;

import org.junit.jupiter.api.Test;

import calculator.gradeCalculator;

class gradeCalculatorTest {

//	@Test
//	void testCreateGradingScaleLength() {
//		ArrayList<String> fileContents = new ArrayList<String>();
//
//		fileContents.add("Test 60");
//		fileContents.add("Hw 40");		
//		
//		Hashtable<String, Integer> gradingScale = gradeCalculator.createGradingScale(fileContents);
//		assertEquals(2, gradingScale.size());
//	}
	
//	@Test
//	void testAverageArrayList() {
//		ArrayList<Integer> testGrades = new ArrayList<Integer>();
//		testGrades.add(70);
//		testGrades.add(80);
//		testGrades.add(90);
//		float averageGrade = gradeCalculator.averageArrayList(testGrades);
//		assertEquals(80, averageGrade, 0.01);
//	}

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
