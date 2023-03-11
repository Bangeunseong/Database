package kr.ac.sejong.db.Project1.oop;

public class App {

	public static void main(String[] args) {
		Circle c1 = new Circle();
		System.out.println(c1.getArea());
		
		Circle c2 = new Circle(4,5,6);
		System.out.println(c2.getArea());
	}

}
