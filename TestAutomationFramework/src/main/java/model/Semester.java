package model;

import java.util.List;

public class Semester {

	private List<Course> semesterCourses;

	public List<Course> getSemesterCourses() {
		return semesterCourses;
	}

	public void setSemesterCourses(List<Course> semesterCourses) {
		this.semesterCourses = semesterCourses;
	}
}
