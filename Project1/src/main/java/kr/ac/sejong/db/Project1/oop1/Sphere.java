package kr.ac.sejong.db.Project1.oop1;

public class Sphere extends Circle {
	//Field
	private VectorSpace v = new VectorSpace();
	
	//Constructor
	public Sphere(int x, int y, int z, int radius) {
		super(radius); v.setXYZ(x, y, z);
	}
	
	//Method
	public void setPosition(int x, int y, int z) {
		v.setXYZ(x, y, z);
	}
	public double getVolume() {
		return getRadius() * getRadius() * getRadius() * 4 / 3;
	}
	
	//Overriding Method
	@Override
	public String toString() {
		return "(" + v.getX() + "," + v.getY() + "," + v.getZ() + ")-" + getRadius();
	}
	
}
