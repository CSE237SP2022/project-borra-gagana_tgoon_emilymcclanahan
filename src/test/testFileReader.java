package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Hashtable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculator.gradeCalculator;
import calculator.Assignment;
import calculator.Bearbot;
import calculator.Course;
import calculator.FileReader;

class testFileReader {
	
	private FileReader fileReaderTest;
	
	@BeforeEach
	void setup() {
		fileReaderTest = new FileReader("courses/CSE237/weights.txt", "courses/CSE237/grades/04_06_2020.txt");
	}
	
	@Test
	void testCreateGradingScaleLength() {		
		Hashtable<String, Double> gradingScale = fileReaderTest.createGradingScale();
		
		assertEquals(2, gradingScale.size());
	}
	
	@Test
	void testCreateAssignmentsLength() {		
		ArrayList<Assignment> assignments = fileReaderTest.createAssignments();
		
		assertEquals(8, assignments.size());
	}
	
	@Test
	void testCreateGradingScale() {		
		Hashtable<String, Double> gradingScaleActual = fileReaderTest.createGradingScale();
		
		Hashtable<String, Double> gradingScaleExpected = new Hashtable<String,Double>();
		
		gradingScaleExpected.put("Assignment", 40.0);
		gradingScaleExpected.put("Project", 60.0);
		
		assertEquals(gradingScaleExpected, gradingScaleActual);
	}
	
	@Test
	void testCreateAssignments() {		
		ArrayList<Assignment> assignmentsActual = fileReaderTest.createAssignments();
		
		ArrayList<Assignment> assignmentsExpected = new ArrayList<Assignment>() {{
			add(new Assignment(80.0, "Assignment", "Assignment_1"));
			add(new Assignment(90.0, "Assignment", "Assignment_2"));
			add(new Assignment(75.0, "Project", "Assignment_3"));
			add(new Assignment(95.0, "Assignment", "Assignment_4"));
			add(new Assignment(77.0, "Project", "Assignment_5"));
			add(new Assignment(66.0, "Assignment", "Assignment_6"));
			add(new Assignment(99.0, "Project", "Assignment_7"));
			add(new Assignment(83.0, "Assignment", "Assignment_8"));
		}};
		
		boolean sameArrayLists = true;
		for (int i=0; i< assignmentsExpected.size(); i++) {
			double gradeExpected = assignmentsExpected.get(i).getGrade();
			String nameExpected = assignmentsExpected.get(i).getName();
			String typeExpected = assignmentsExpected.get(i).getType();
			
			double gradeActual = assignmentsActual.get(i).getGrade();
			String nameActual = assignmentsActual.get(i).getName();
			String typeActual = assignmentsActual.get(i).getType();
			
			if (!(gradeExpected==gradeActual && nameExpected.equals(nameActual) && typeExpected.equals(typeActual))) {
				sameArrayLists = false;
			}
		}
		
		assertTrue(sameArrayLists);
	}
}
