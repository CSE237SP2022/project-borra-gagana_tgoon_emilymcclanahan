package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculator.Course;
import calculator.Assignment;


public class testCourse {
	
	private Course courseTest;
	
	@BeforeEach
	void setup() {
		Hashtable<String, Double> gradingScale = new Hashtable<String, Double>();
		ArrayList<Assignment> assignments = new ArrayList<Assignment>();
		
		Assignment assignment1 = new Assignment(90.0, "Hw", "Hw1");
		Assignment assignment2 = new Assignment(95.0, "Hw", "Hw2");
		Assignment assignment3 = new Assignment(85.0, "Test", "Test1");
		Assignment assignment4 = new Assignment(99.0, "Test", "Test2");
	
		assignments.add(assignment1);
		assignments.add(assignment2);
		assignments.add(assignment3);
		assignments.add(assignment4);
	
		
		gradingScale.put("Hw",40.0);
		gradingScale.put("Test",60.0);
		
		courseTest = new Course(gradingScale,assignments);
	}
	
	
	@Test
    void testAverageAssignmentWithinType() {
		ArrayList<Double> grades = new ArrayList<Double>();
		grades.add(80.0);
		grades.add(90.0);
		grades.add(85.0);
		double averageGrade = courseTest.averageAssignmentWithinType(grades);
    	assertEquals(85.0, averageGrade, 0.01);
	}
	
	@Test
    void testAverageAssignmentWithinTypeSingleAssignment() {
		ArrayList<Double> grades = new ArrayList<Double>();
		grades.add(80.0);
		double averageGrade = courseTest.averageAssignmentWithinType(grades);
    	assertEquals(80.0, averageGrade, 0.01);
	}
	
	@Test
    void testAverageAssignmentWithinTypeNoAssignments() {
		ArrayList<Double> grades = new ArrayList<Double>();
		double averageGrade = courseTest.averageAssignmentWithinType(grades);
    	assertEquals(100.0, averageGrade, 0.01);
	}
	
	@Test
	void testSeparateAssignmentsByTypeNumberOfAssignments() {
		
		Hashtable<String, ArrayList<Double>> grades = courseTest.separateAssignmentsByType(courseTest.getAssignments());
		Enumeration<String> gradeTypes = grades.keys();
		
		while (gradeTypes.hasMoreElements()) {
			String gradeType = gradeTypes.nextElement();
			ArrayList<Double> gradesOfCertainType = grades.get(gradeType);
			assertEquals(2,gradesOfCertainType.size());
        }
	}
	
	@Test
	void testSeparateAssignmentsByTypeKeysEqual() {
		Hashtable<String, ArrayList<Double>> grades = courseTest.separateAssignmentsByType(courseTest.getAssignments());
		
		assertTrue(grades.keySet().equals(courseTest.getGradingScale().keySet()));
	}
	
	@Test
	void testGetFinalGrade() {
		assertEquals(92.2,courseTest.getFinalGrade(),0.01);
	}
	
	@Test
	void testGetFinalGradeNoAssignments() {
		Hashtable<String, Double> gradingScale = new Hashtable<String, Double>();
		gradingScale.put("Hw",40.0);
		gradingScale.put("Test",60.0);
		
		ArrayList<Assignment> assignments = new ArrayList<Assignment>();
		courseTest = new Course(gradingScale,assignments);
		
		assertEquals(100.0,courseTest.getFinalGrade(),0.01);
	}
	
	@Test
	void testGetLetterGrade() {
		assertEquals("B",courseTest.getLetterGrade());
	}
	
	@Test
	void testGetLetterGradeNoAssignments() {
		Hashtable<String, Double> gradingScale = new Hashtable<String, Double>();
		gradingScale.put("Hw",40.0);
		gradingScale.put("Test",60.0);
		
		ArrayList<Assignment> assignments = new ArrayList<Assignment>();
		courseTest = new Course(gradingScale,assignments);
		assertEquals("A",courseTest.getLetterGrade());
	}
}
