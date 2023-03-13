package kr.ac.sejong.db.Project1.oop1;

public class Circle {
	//Field
	private int radius; private Position p = new Position();
	
	//constructor
	public Circle() {radius = 1;}
	public Circle(int radius) {this.radius = radius;}
	public Circle(int x, int y, int radius) {p.setXY(x, y); this.radius = radius;}
	
	//Method
	public void setPosition(int x, int y) {p.setXY(x, y);}
	public Position getPosition() {return p;}
	public void setRadius(int radius) {this.radius = radius;}
	public int getRadius() {return radius;}
	public double getArea() {return Math.PI * radius * radius;}
	
	//Overriding Method
	@Override
	public String toString() {
		return "(" + p.getX() + "," + p.getY() + ")-" + radius;
	}
}
