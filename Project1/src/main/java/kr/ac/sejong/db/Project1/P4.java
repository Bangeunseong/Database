package kr.ac.sejong.db.Project1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class P4 {
	
	public static int sum(int[] intArr) {
		int sum = 0;
		for(int data : intArr) sum += data;
		return sum;
	}
	
	public static int returnInput() throws ScoreExceedException {
		Scanner scan = new Scanner(System.in);
		int data = scan.nextInt();
		scan.close();
		if(data < 0) throw new ScoreExceedException("Do not input data less than 0!");
		return data;
	}

	public static void main(String[] args) throws IOException {
		//About InputStream
		System.out.println("Hello world!");
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(r.readLine());
		System.out.println(stringBuffer);
		
		Scanner scanner = new Scanner(System.in);
		String inputString = scanner.nextLine();
		System.out.println(inputString);
		
		
		//About Variable DataType
		int stdNumber = 19011640;
		String MBTI = "INTP";
		String nameString = "Bangeunseong";
		int semester = 5;
		System.out.println(nameString + " " + stdNumber + " " + MBTI + " " + semester);
		
		int x = 3, y = 4;
		System.out.println((x+y) + " " + (x-y) + " " + (x*y) + " " + (x/y) + " " + (x%y));
		boolean z = x > y;
		System.out.println(z);
		
		System.out.print("Tell me your point: ");
		int score = scanner.nextInt();
		if(score >= 90) System.out.println("A");
		else if(score >= 80) System.out.println("B");
		else if(score >= 70) System.out.println("C");
		else System.out.println("D");
		
		
		
		//About Loops
		int sum = 0;
		for(int i = 1; i < 10; i++) {
			if(i % 2 != 0) continue;
			sum += i;
		}
		System.out.println(sum);
		
		sum = 0;
		int index = 1;
		while(index < 10) {
			if(index % 2 == 0) sum += index;
			index++;
		}
		System.out.println(sum);
		
		Outter:for(int i = 0; i < 10 ; i++) {
			for(int j = 0; j < 10; j++) {
				if(i % 3 == 0) break Outter;
				System.out.println(i + " " + j);
			}
		}
		
		//About array
		int[] intArr = new int[9];
		for(int i = 0; i < intArr.length; i++) intArr[i] = i + 1;
		
		sum = 0;
		for(int data : intArr) {sum += data;}
		System.out.println(sum);
		
		//About Method
		System.out.println(sum(new int[] {1,3,5}));
		System.out.println(sum(new int[] {2,3,4,5}));
		
		//Exception
		Scanner scan = new Scanner(System.in);
		int base = 10, input = scan.nextInt();
		try {
			System.out.println(base/input);
		}
		catch(ArithmeticException e) {
			e.printStackTrace();
			System.out.println("Invalid Number Input!");
		}
		
		System.out.print("Input: "); int data;
		try {
			data = returnInput();System.out.println(data);
		}
		catch(ScoreExceedException e) {
			e.printStackTrace();
		}
		
		scan.close();
		r.close();
		scanner.close();
	}
}
