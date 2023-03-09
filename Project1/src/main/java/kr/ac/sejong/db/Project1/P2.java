package kr.ac.sejong.db.Project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class P2 {

	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("C:\\Users\\bange\\eclipse-workspace\\Grade.txt"));
		
		Map<String, List<Integer>> m = new HashMap<>();
		while(true) {
			String data = r.readLine();
			if(data == null) break;
			
			String[] arr = data.split("\\|");
			String name = arr[0];
			int score = Integer.parseInt(arr[arr.length - 1]);
			if(!m.containsKey(name)) {
				List<Integer> l = new ArrayList<>();
				l.add(score);
				m.put(name, l);
			}
			else {
				List<Integer> l = m.get(name);
				l.add(score);
			}
		}
		
		Map<String, Double> newMap = new HashMap<>();
		
		Set<Map.Entry<String, List<Integer>>> s = m.entrySet();
		for(Map.Entry<String, List<Integer>> entry : s) {
			double avg = entry.getValue().stream()
			.mapToInt(Integer::intValue)
			.average()
			.orElse(0.0);
			newMap.put(entry.getKey(), avg);
		}
		System.out.println(newMap);
		r.close();
	}

}
