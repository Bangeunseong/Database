package kr.ac.sejong.db.Project1.oop2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public class App {

	public static void main(String[] args) {
		List<Integer> arrayList = new ArrayList<Integer>();
		arrayList.addAll(Arrays.asList(3,4,5));
		
		NavigableSet<Integer> s = new TreeSet<Integer>();
		s.addAll(Arrays.asList(5,7,8,9));
		s.stream().forEach((data)->System.out.print(data + " "));
		
		new Thread(()->{System.out.println("Hello Runnable");}).start();
	}
}
