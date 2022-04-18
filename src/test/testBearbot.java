package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculator.Assignment;
import calculator.Bearbot;
import calculator.Course;

class testBearbot {

	private Bearbot bearbotTest;
	
	@BeforeEach
	void setup() {
		bearbotTest = new Bearbot();
	}

	@Test
	void testPrintCoureses() {
		String [] courses = bearbotTest.printCourses();
		
		assertTrue(Arrays.stream(courses).anyMatch("CSE237"::equals));
	}
	
	@Test
	void testPrintDateFiles() {
		String [] dateFiles = bearbotTest.printDateFiles("CSE237");
		
		assertTrue(Arrays.stream(dateFiles).anyMatch("04_06_2020.txt"::equals));
	}
	
	@Test
	void testPrintGrade() {
		Double grade = bearbotTest.printGrade("CSE237", "04_06_2020.txt");
		
		assertEquals(83.32, grade, 0.01);
	}
	
	@Test
	void testArrayContainsStringTrue() {
		
		String[] testArray = {"hi","bye"};
		boolean doesContainString = bearbotTest.arrayContainsString(testArray, "hi");
		
		assertTrue(doesContainString);
	}
	
	@Test
	void testArrayContainsStringFalse() {
		
		String[] testArray = {"hi","bye"};
		boolean doesContainString = bearbotTest.arrayContainsString(testArray, "hello");
		
		assertFalse(doesContainString);
	}
	
	@Test
	void testisValidStringInputTrue() {
		String testInput = "hello";
		boolean isValidStringInput = bearbotTest.isValidStringInput(testInput);
		
		assertTrue(isValidStringInput);
	}
	
	@Test
	void testisValidStringInputBlank() {
		String testInput = "";
		boolean isValidStringInput = bearbotTest.isValidStringInput(testInput);
		
		assertFalse(isValidStringInput);
	}
	
	@Test
	void testisValidStringInputContainsSpaces() {
		String testInput = "hello world";
		boolean isValidStringInput = bearbotTest.isValidStringInput(testInput);
		
		assertFalse(isValidStringInput);
	}
	
	@Test
	void testisValidPercentInputTrue() {
		String testInput = "90";
		boolean isValidPercentInput = bearbotTest.isValidPercentInput(testInput);
		
		assertTrue(isValidPercentInput);
	}
	
	@Test
	void testisValidPercentInputZero() {
		String testInput = "0";
		boolean isValidPercentInput = bearbotTest.isValidPercentInput(testInput);
		
		assertTrue(isValidPercentInput);
	}
	
	@Test
	void testisValidPercentInputNonNumeric() {
		String testInput = "hello";
		boolean isValidPercentInput = bearbotTest.isValidPercentInput(testInput);
		
		assertFalse(isValidPercentInput);
	}
	
	@Test
	void testisValidPercentInputContainsSpaces() {
		String testInput = "1 2";
		boolean isValidPercentInput = bearbotTest.isValidPercentInput(testInput);
		
		assertFalse(isValidPercentInput);
	}
	
	@Test
	void testisValidPercentInputBlank() {
		String testInput = "";
		boolean isValidPercentInput = bearbotTest.isValidPercentInput(testInput);
		
		assertFalse(isValidPercentInput);
	}
}
