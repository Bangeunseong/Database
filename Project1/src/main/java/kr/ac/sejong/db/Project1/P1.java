package kr.ac.sejong.db.Project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class P1 {

	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("C:\\Users\\bange\\eclipse-workspace\\Grade.txt"));
		
		while(true) {
			String str = r.readLine();
			if(str == null) break;
			
			String[] arr = str.split("\\|");
			for(String data : arr) {
				System.out.print(data + " ");
			}
			System.out.println();
		}
		r.close();
	}
}
