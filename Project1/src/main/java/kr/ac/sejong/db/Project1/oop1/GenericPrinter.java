package kr.ac.sejong.db.Project1.oop1;

public class GenericPrinter<T> implements Printer {
	private T data;
	
	public GenericPrinter(T data) {this.data = data;}
	
	public void print() {
		System.out.println(data);
	}
}
