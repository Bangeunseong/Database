package kr.ac.sejong.db.Project1.oop1;

public class App {

	public static void main(String[] args) {
		Circle c1 = new Circle();
		System.out.println(c1.getArea());
		
		Circle c2 = new Circle(4,5,6);
		System.out.println(c2.getArea());
		
		Sphere s1 = new Sphere(0, 0, 0, 3);
		s1.setPosition(7, 8, 9);
		System.out.println(s1.getVolume());
		
		Circle c3 = new Circle();
		if(c3 instanceof Sphere) {
			Sphere s2 = (Sphere)c3;
			System.out.println(s2.getVolume());
		}
		
		GenericPrinter<String> str = new GenericPrinter<String>("HelloWorld");
		str.print();
		GenericPrinter<Circle> strCircle = new GenericPrinter<Circle>(c2);
		strCircle.print();
		GenericPrinter<Sphere> strSphere = new GenericPrinter<Sphere>(s1);
		strSphere.print();
	}

}
