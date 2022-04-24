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
	void testGetCoureses() {
		String [] courses = bearbotTest.getCourses();
		
		assertTrue(Arrays.stream(courses).anyMatch("CSE237"::equals));
	}
	
	@Test
	void testPrintDateFiles() {
		String [] dateFiles = bearbotTest.printDateFiles("CSE237");
		
		assertTrue(Arrays.stream(dateFiles).anyMatch("04_06_2020.txt"::equals));
	}
	
	@Test
	void testGetDateFiles() {
		String [] dateFiles = bearbotTest.getDateFiles("CSE237");
		
		assertTrue(Arrays.stream(dateFiles).anyMatch("04_06_2020.txt"::equals));
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
	void testIsValidStringInputTrue() {
		String testInput = "hello";
		boolean isValidStringInput = bearbotTest.isValidStringInput(testInput);
		
		assertTrue(isValidStringInput);
	}
	
	@Test
	void testIsValidStringInputBlank() {
		String testInput = "";
		boolean isValidStringInput = bearbotTest.isValidStringInput(testInput);
		
		assertFalse(isValidStringInput);
	}
	
	@Test
	void testIsValidStringInputContainsSpaces() {
		String testInput = "hello world";
		boolean isValidStringInput = bearbotTest.isValidStringInput(testInput);
		
		assertFalse(isValidStringInput);
	}
	
	@Test
	void testIsValidPercentInputTrue() {
		String testInput = "90";
		boolean isValidPercentInput = bearbotTest.isValidPercentInput(testInput);
		
		assertTrue(isValidPercentInput);
	}
	
	@Test
	void testIsValidPercentInputZero() {
		String testInput = "0";
		boolean isValidPercentInput = bearbotTest.isValidPercentInput(testInput);
		
		assertTrue(isValidPercentInput);
	}
	
	@Test
	void testIsValidPercentInputNonNumeric() {
		String testInput = "hello";
		boolean isValidPercentInput = bearbotTest.isValidPercentInput(testInput);
		
		assertFalse(isValidPercentInput);
	}
	
	@Test
	void testIsValidPercentInputContainsSpaces() {
		String testInput = "1 2";
		boolean isValidPercentInput = bearbotTest.isValidPercentInput(testInput);
		
		assertFalse(isValidPercentInput);
	}
	
	@Test
	void testIsValidPercentInputBlank() {
		String testInput = "";
		boolean isValidPercentInput = bearbotTest.isValidPercentInput(testInput);
		
		assertFalse(isValidPercentInput);
	}
	
	@Test
	void testIsValidDateInputTrue() {
		String testInput = "04_07_2020";
		boolean isValidDateInput = bearbotTest.isValidDateInput(testInput);
		
		assertTrue(isValidDateInput);
	}
	
	@Test
	void testIsValidDateInputBadDate() {
		String testInput = "13_07_2020";
		boolean isValidDateInput = bearbotTest.isValidDateInput(testInput);
		
		assertFalse(isValidDateInput);
	}
	
	@Test
	void testIsValidDateInputBadFormat() {
		String testInput = "12/07/2020";
		boolean isValidDateInput = bearbotTest.isValidDateInput(testInput);
		
		assertFalse(isValidDateInput);
	}
	
	@Test
	void testIsValidDateInputWithSpaces() {
		String testInput = "13_07_2020 ";
		boolean isValidDateInput = bearbotTest.isValidDateInput(testInput);
		
		assertFalse(isValidDateInput);
	}
	
	@Test
	void testIsValidDateInputBlank() {
		String testInput = "";
		boolean isValidDateInput = bearbotTest.isValidDateInput(testInput);
		
		assertFalse(isValidDateInput);
	}
	
	@Test
	void testIsValidDateInputNonNumeric() {
		String testInput = "december_07_2020";
		boolean isValidDateInput = bearbotTest.isValidDateInput(testInput);
		
		assertFalse(isValidDateInput);
	}
}
