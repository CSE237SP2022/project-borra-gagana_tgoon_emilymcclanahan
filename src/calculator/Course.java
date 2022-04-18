package calculator;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class Course {

	private Hashtable<String, Double> gradingScale;
	private ArrayList<Assignment> assignments;

	public Course(Hashtable<String, Double> gradingScale, ArrayList<Assignment> assignments) {
		this.gradingScale = gradingScale;
		this.assignments = assignments;
	}

	public void setAssignments(ArrayList<Assignment> assignments) {
		this.assignments = assignments;
	}

	public Hashtable<String, Double> getGradingScale() {
		return this.gradingScale;
	}

	public ArrayList<Assignment> getAssignments() {
		return this.assignments;
	}

	public Double averageAssignmentWithinType(ArrayList<Double> grades) {
		Double sum = 0.0;
		int size = grades.size();
		for (Double grade : grades) {
			sum += grade;
		}
		return sum/size;
	}

	public Hashtable<String, ArrayList<Double>> separateAssignmentsByType(ArrayList<Assignment> assignments){

		Hashtable<String, ArrayList<Double>> grades = new Hashtable<String, ArrayList<Double>>();

		for (Assignment assignment: this.assignments) {
			this.addAssignment(grades, assignment);
		}

		return grades;
	}

	private void addAssignment(Hashtable<String, ArrayList<Double>> grades, Assignment assignment) {
		ArrayList<Double> currentGrades = grades.get(assignment.getType());
		if (currentGrades == null) {
			currentGrades = new ArrayList<Double>();
		}
		currentGrades.add(assignment.getGrade());
		grades.put(assignment.getType(), currentGrades);
	}

	public Double getFinalGrade() {

		Double finalGrade = 0.0;

		Hashtable<String, ArrayList<Double>> grades = this.separateAssignmentsByType(this.assignments);

		Enumeration<String> gradeTypes = grades.keys();

		while (gradeTypes.hasMoreElements()) {
			String gradeType = gradeTypes.nextElement();
			finalGrade += this.gradingScale.get(gradeType)*this.averageAssignmentWithinType(grades.get(gradeType));
		}

		return finalGrade / 100;
	}

	public String getLetterGrade() {
		if(getFinalGrade() >= 93) {
			return "A";
		}

		else if(getFinalGrade() >= 85) {
			return "B";
		}

		else if(getFinalGrade() >= 75) {
			return "C";
		}

		else if(getFinalGrade() >= 65) {
			return "D";
		}

		else {
			return "F";
		}

	}

}
