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
	
	private Course c;
	
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
		
		c = new Course(gradingScale,assignments);
	}
	
	
	@Test
    void testAverageAssignmentWithinType() {
		ArrayList<Double> grades = new ArrayList<Double>();
		grades.add(80.0);
		grades.add(90.0);
		grades.add(85.0);
		double averageGrade = c.averageAssignmentWithinType(grades);
    	assertEquals(85.0, averageGrade, 0.01);
	}
	
	@Test
	void testSeparateAssignmentsByTypeNumberOfAssignments() {
		
		Hashtable<String, ArrayList<Double>> grades = c.separateAssignmentsByType(c.getAssignments());
		Enumeration<String> gradeTypes = grades.keys();
		
		while (gradeTypes.hasMoreElements()) {
			String gradeType = gradeTypes.nextElement();
			ArrayList<Double> gradesOfCertainType = grades.get(gradeType);
			assertEquals(2,gradesOfCertainType.size());
        }
	}
	
	@Test
	void testSeparateAssignmentsByTypeKeysEqual() {
		Hashtable<String, ArrayList<Double>> grades = c.separateAssignmentsByType(c.getAssignments());
		
		assertTrue(grades.keySet().equals(c.getGradingScale().keySet()));
	}
	
	@Test
	void testGetFinalGrade() {
		assertEquals(92.2,c.getFinalGrade(),0.01);
	}
}
