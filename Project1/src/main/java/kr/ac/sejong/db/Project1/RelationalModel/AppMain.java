package kr.ac.sejong.db.Project1.RelationalModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AppMain {
	public static Set<Student> set = new HashSet<>();
	public static Map<Student, List<Department>> map = new HashMap<>();
	
	public static Map.Entry<Student, List<Department>> Select(String name){
		String phonePattern = "(02|010)-\\d{3,4}-\\d{4}";
		if(!Pattern.matches(phonePattern, name)) return null;
		
		for(Map.Entry<Student, List<Department>> entry : map.entrySet()) {
			if(entry.getKey().getPhonenumber().equals(name)) return entry;
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(
			new FileReader("C:\\Users\\bange\\eclipse-workspace\\Information.txt")
		);
		
		String format = "\\w+\\s(02|010)-\\d{3,4}-\\d{4}\\s\\w+@\\w+\\.\\w+(\\.\\w+)?"
				+ "(\\s\\w+(-\\w+)?\\s\\d\\s\\d{1,2})?";
		
		while(true) {
			String data = r.readLine(); if(data == null) break;
			if(Pattern.matches(format, data)) {
				String[] datas = data.split(" ");
				Student std = new Student(datas[0], datas[1], datas[2]);
				Department dpt = new Department(datas[3], Integer.parseInt(datas[4]), Integer.parseInt(datas[5]));
				set.add(std);
				if(map.containsKey(std)) {
					map.get(std).add(dpt);
				}
				else {
					List<Department> newList = new ArrayList<>();
					newList.add(dpt);
					map.put(std, newList);
				}
			}
		}
		
		set.stream().forEach((data)->{
			System.out.println(data.toString());
			if(map.containsKey(data)) {
				double avg = map.get(data).stream()
						.mapToInt(Department::getScore)
						.average().orElse(0.0);
				map.get(data).stream().forEach((list)->System.out.println(list.toString()));
				System.out.println("Average: " + avg);
			}
			System.out.println();
		});
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String execution = input.readLine();
			System.out.print(">:");
			if(execution.equals("SELECT")) {
				String name = input.readLine();
				Map.Entry<Student, List<Department>> entry = Select(name);
				if(entry != null)
					System.out.println(entry.toString());
				else
					System.out.println("Not Found!!");
			}
			else if(execution.equals("QUIT")) break;
		}
		
		input.close();
		r.close();
	}

}
