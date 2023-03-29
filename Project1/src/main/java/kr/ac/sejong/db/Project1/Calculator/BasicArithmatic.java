package kr.ac.sejong.db.Project1.Calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BasicArithmatic {
	public static StringBuffer mainExp;
	public static double mainMemory = 0;
	
	//Return boolean if it is operator
	public static boolean isOperand(char val) {
		if(val >= '0' && val <= '9') return true;
		else if(val == '.') return true;
		return false;
	}
	//Return operators priority
	public static int returnPriority(Character val) {
		if(val.equals('(')) return 2;
		else if(val.equals('+') || val.equals('-')) return 3;
		else if(val.equals('/') || val.equals('*') || val.equals('%')) return 4;
		else return -1;
	}
	//Return calculated value
	public static double doOperator(String op, Double v1, Double v2) {
		if(op.equals("+")) return v1 + v2;
		else if(op.equals("-")) return v1 - v2;
		else if(op.equals("*")) return v1 * v2;
		else if(op.equals("/")) return v1 / v2;
		else if(op.equals("%")) return v1 % v2;
		return -1;
	}
	//Convert formula
	public static List<String> convert() {
		List<String> result = new ArrayList<>();
		Stack<Character> s = new Stack<>();
		
		int ind = 0;
		while(ind < mainExp.length()) {
			StringBuffer tmp = new StringBuffer();
			if(isOperand(mainExp.charAt(ind))) {
				while(ind < mainExp.length() && isOperand(mainExp.charAt(ind))) {
					tmp.append(mainExp.charAt(ind++));
				}
				result.add(tmp.toString());
				//System.out.println("Check operand");
			}
			else if(mainExp.charAt(ind) == '(') {
				s.push(mainExp.charAt(ind++));
				//System.out.println("Check open symbol");
			}
			else if(mainExp.charAt(ind) == ')') {
				while(!s.peek().equals('(')) {
					result.add(String.valueOf(s.pop()));
				}
				s.pop(); ind++;
				//System.out.println("Check close symbol");
			}
			else {
				while(!s.isEmpty() && (returnPriority(mainExp.charAt(ind)) <= returnPriority(s.peek()))) {
					result.add(String.valueOf(s.pop()));
				}
				s.push(mainExp.charAt(ind++));
				//System.out.println("Check operators");
			}
			//System.out.println(ind);
		}
		while(!s.isEmpty()) {
			result.add(String.valueOf(s.pop()));
		}
		return result;
	}
	//Calculate formula
	public static double Calculate(List<String> reg) {
		Stack<Double> s = new Stack<>();
		
		while(!reg.isEmpty()) {
			if(isOperand(reg.get(0).charAt(0))) {s.push(Double.valueOf(reg.remove(0)));}
			else {
				Double v1 = s.pop();
				Double v2 = s.pop();
				s.push(doOperator(reg.remove(0), v2, v1));
			}
		}
		return s.pop();
	}
	//Main
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		
		mainExp.append(r.readLine());
		
		List<String> reg = convert();
		System.out.println("Result: " + Calculate(reg));
		r.close();
	}

}
