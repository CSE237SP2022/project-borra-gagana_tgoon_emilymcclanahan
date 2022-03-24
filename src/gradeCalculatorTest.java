

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Hashtable;

import org.junit.jupiter.api.Test;

class gradeCalculatorTest {

	@Test
	void testCreateGradingScaleLength() {
		ArrayList<String> fileContents = new ArrayList<String>();

		fileContents.add("Test 60");
		fileContents.add("Hw 40");		
		
		Hashtable<String, Integer> gradingScale = gradeCalculator.createGradingScale(fileContents);
		assertEquals(2, gradingScale.size());
	}

}
