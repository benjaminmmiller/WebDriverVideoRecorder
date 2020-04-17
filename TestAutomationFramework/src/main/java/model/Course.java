package model;

public class Course {
	
	private String courseName;
	
	public Course(String newCourseName) {
		courseName = newCourseName;
	}
	
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
}
