package calculator;

public class Assignment {

	private double grade;
	private String type;
	private String name;
	
	public Assignment(double grade, String type, String name) {
		this.grade = grade;
		this.type = type;
		this.name = name;
	}
	
	public double getGrade() {
		return this.grade;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getName() {
		return this.name;
	}
}
