package kr.ac.sejong.db.Project1.Calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class BitLogic {
	public static boolean mainMemory_L = false;
	public static StringBuffer mainExp_L = new StringBuffer();
	
	//Return boolean value
	public static boolean isOperand(char val) {
		if(val >= '0' && val <= '9') return true;
		return false;
	}
	
	//Return operators priority
	public static int returnPriority(String val) throws Exception {
		if(val.equals("(")) return 1;
		else if(val.equals("+") || val.equals("-")) return 2;
		else if(val.equals("<<") || val.equals(">>")) return 3;
		else if(val.equals("&")) return 4;
		else if(val.equals("^")) return 5;
		else if(val.equals("|")) return 6;
		else if(val.equals("<") || val.equals("<=") || val.equals(">") || val.equals(">=") || val.equals("!=") || val.equals("==")) return 7;
		else if(val.equals("and")) return 9;
		else if(val.equals("or")) return 10;
		else throw new Exception("Wrong operator input!");
	}
	
	//Return result of calculation
	public static Integer doOperator(String op, Integer v1, Integer v2) throws Exception {
		//FIXME return type consistancy 
		if(op.equals("+")) return v1 + v2;
		else if(op.equals("-")) return v1 - v2;
		else if(op.equals("<<")) return v1 << v2;
		else if(op.equals(">>")) return v1 >> v2;
		else if(op.equals("&")) return v1 & v2;
		else if(op.equals("^")) return v1 ^ v2;
		else if(op.equals("|")) return v1 | v2;
		else if(op.equals("<")) return v1 < v2 ? 1 : 0;
		else if(op.equals("<=")) return v1 <= v2 ? 1 : 0;
		else if(op.equals(">")) return v1 > v2 ? 1 : 0;
		else if(op.equals(">=")) return v1 >= v2 ? 1 : 0;
		else if(op.equals("!=")) return v1 != v2 ? 1 : 0;
		else if(op.equals("and")) return (v1 != 0 ? true : false) && (v2 != 0 ? true : false) ? 1 : 0;
		else if(op.equals("or")) return (v1 != 0 ? true : false) || (v2 != 0 ? true : false) ? 1 : 0;
		else throw new Exception("Wrong operator input!");
	}
	
	//Convert mainExp_L
	public static List<String> convert_BitLogic() throws Exception, EmptyStackException {
		List<String> result = new ArrayList<>();
		Stack<String> s = new Stack<>();
		
		int ind = 0;
		while(ind < mainExp_L.length()) {
			StringBuffer tmp = new StringBuffer();
			if(isOperand(mainExp_L.charAt(ind))) {
				while(ind < mainExp_L.length() && isOperand(mainExp_L.charAt(ind))) {
					tmp.append(mainExp_L.charAt(ind++));
				}
				result.add(tmp.toString());
			}
			else if(mainExp_L.charAt(ind) == '(') {
				s.push(String.valueOf(mainExp_L.charAt(ind++)));
			}
			else if(mainExp_L.charAt(ind) == ')') {
				while(!s.peek().equals("(")) {
					result.add(s.pop());
				}
				s.pop(); ind++;
			}
			else {
				while(ind < mainExp_L.length() && !isOperand(mainExp_L.charAt(ind))
						&& (mainExp_L.charAt(ind) != ')' && mainExp_L.charAt(ind) != '(')) {
					tmp.append(mainExp_L.charAt(ind++));
				}
				if(tmp.toString().equals("+") || tmp.toString().equals("-")) result.add("0");
				while(!s.isEmpty() && (returnPriority(tmp.toString()) <= returnPriority(s.peek()))) {
					result.add(s.pop());
				}
				s.push(tmp.toString());
			}
		}
		while(!s.isEmpty()) {
			result.add(s.pop());
		}
		System.out.println(result);
		return result;
	}
	
	//Calculate converted formula
	public static boolean Calculate(List<String> reg) throws EmptyStackException, Exception {
		Stack<Integer> s = new Stack<>();
		
		while(!reg.isEmpty()) {
			if(isOperand(reg.get(0).charAt(0))) {s.push(Integer.parseInt(reg.remove(0)));}
			else {
				Integer v1 = s.pop();
				Integer v2 = s.pop();
				s.push(doOperator(reg.remove(0), v2, v1));
			}
		}
		return s.pop() != 0 ? true : false;
	}
}