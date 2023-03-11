package kr.ac.sejong.db.Project1.oop;

public class Position {
	//Field
	private int x, y;
	
	//Constructor
	public Position() {
		x = y = 0;
	}
	public Position(int x, int y) {
		this.x = x; this.y = y;
	}
	
	//Method
	public void setXY(int x, int y) {this.x = x; this.y = y;}
	public int getX() {return x;}
	public int getY() {return y;}
}
