package kr.ac.sejong.db.Project1.oop;

public class Circle {
	//Field
	private int radius, x, y;
	
	//constructor
	public Circle() {x = y = 0; radius = 1;}
	public Circle(int x, int y, int radius) {this.x = x; this.y = y; this.radius = radius;}
	
	//Method
	public void setPosition(int x, int y) {this.x = x; this.y = y;}
	public void setRadius(int radius) {this.radius = radius;}
	public int getRadius() {return radius;}
	public double getArea() {return Math.PI * radius * radius;}
}
