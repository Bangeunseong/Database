package kr.ac.sejong.db.Project1.oop;

public class Circle {
	//Field
	private int radius; private Position p;
	
	//constructor
	public Circle() {p = new Position(); radius = 1;}
	public Circle(int x, int y, int radius) {p = new Position(x,y); this.radius = radius;}
	
	//Method
	public void setPosition(int x, int y) {p.setXY(x, y);}
	public Position getPosition() {return p;}
	public void setRadius(int radius) {this.radius = radius;}
	public int getRadius() {return radius;}
	public double getArea() {return Math.PI * radius * radius;}
}
