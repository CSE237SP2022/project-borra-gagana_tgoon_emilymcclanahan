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
			ArrayList<Double> currentGrades = grades.get(assignment.getType());
			if (currentGrades == null) {
				currentGrades = new ArrayList<Double>();
			}
			currentGrades.add(assignment.getGrade());
			grades.put(assignment.getType(), currentGrades);
		}
		
		return grades;
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
}
