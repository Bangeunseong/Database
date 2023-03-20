package kr.ac.sejong.db.Project1.RelationalModel;

public class Department {
	private String department;
	private int semester, score;
	
	public Department(String department, int semester, int score) {
		this.department = department; this.semester = semester; this.score = score;
	}
	
	public String getDepartment() {return department;}
	public int getSemester() {return semester;}
	public int getScore() {return score;}
	
	@Override
	public String toString() {
		return "{" + department + ", " + semester + ", " + score + "}";
	}
}
