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
		
		assertEquals(true,Arrays.stream(courses).anyMatch("CSE237"::equals));
	}
	
	@Test
	void testPrintDateFiles() {
		String [] dateFiles = bearbotTest.printDateFiles("CSE237");
		
		assertEquals(true,Arrays.stream(dateFiles).anyMatch("04_06_2020.txt"::equals));
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
		
		assertEquals(true,doesContainString);
	}
	
	@Test
	void testArrayContainsStringFalse() {
		
		String[] testArray = {"hi","bye"};
		boolean doesContainString = bearbotTest.arrayContainsString(testArray, "hello");
		
		assertEquals(false,doesContainString);
	}
	

}
