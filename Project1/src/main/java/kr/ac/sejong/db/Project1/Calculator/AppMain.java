package kr.ac.sejong.db.Project1.Calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AppMain {
	public static String mainExpression;
	public static double mainMemory = 0;
	
	public static int returnPriority(Character val) {
		if(val.equals('/') || val.equals('*') || val.equals('%')) return 3;
		else if(val.equals('+') || val.equals('-')) return 4;
		else return -1;
	}
	
	public static List<Double> returnOperands(){
		List<Double> operands = new ArrayList<>();
		String[] operandList = mainExpression.split("\\-|\\*|\\/|\\%|\\+|\\(|\\)");
		for(String data : operandList) operands.add(Double.valueOf(data.trim()));
		return operands;
	}
	
	public static List<Character> returnOperators(){
		List<Character> operators = new ArrayList<>();
		for(int i = 0; i < mainExpression.length(); i++) {
			if((mainExpression.charAt(i) < '0' || mainExpression.charAt(i) > '9') && mainExpression.charAt(i) != ' ')
				operators.add(Character.valueOf(mainExpression.charAt(i)));
		}
		return operators;
	}
	
	public static double Calculate(List<Double> operands, List<Character> operators) {	//Fix it!!!
		Stack<Double> operands_s = new Stack<>();
		Stack<Character> operators_s = new Stack<>();
		
		operands_s.push(operands.remove(0));
		while(!operands.isEmpty()) {
			//When operators are not in stack
			if(operators_s.isEmpty()) {operators_s.push(operators.remove(0));}
			
			//When file ends!! Get Leftover operand and calculate
			else if(operators.isEmpty()) {	
				Character op = operators_s.pop(); double val = operands_s.pop();
				if(op.equals('+')) val += operands.remove(0);
				else if(op.equals('-')) val -= operands.remove(0);
				else if(op.equals('*')) val *= operands.remove(0);
				else if(op.equals('/')) val /= operands.remove(0);
				else if(op.equals('%')) val %= operands.remove(0);
				operands_s.push(val);
			}
			
			//When operators still exists in stack
			else {
				Character op = operators_s.pop();
				if(returnPriority(operators.get(0)) < returnPriority(op)) {
					operators_s.push(op);
					operands_s.push(operands.remove(0));
					operators_s.push(operators.remove(0));
				}
				else {
					double val = operands_s.pop();
					if(op.equals('+')) val += operands.remove(0);
					else if(op.equals('-')) val -= operands.remove(0);
					else if(op.equals('*')) val *= operands.remove(0);
					else if(op.equals('/')) val /= operands.remove(0);
					else if(op.equals('%')) val %= operands.remove(0);
					operands_s.push(val); operators_s.push(operators.remove(0));
				}
			}
		}
		
		while(!operators_s.isEmpty()) {
			Character op = operators_s.pop();
			double val2 = operands_s.pop(); double val1 = operands_s.pop();
			if(op.equals('+')) val1 += val2;
			else if(op.equals('-')) val1 -= val2;
			else if(op.equals('*')) val1 *= val2;
			else if(op.equals('/')) val1 /= val2;
			else if(op.equals('%')) val1 %= val2;
			operands_s.push(val1);
		}
		return operands_s.pop();
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		
		mainExpression = r.readLine();
		List<Double> operands = returnOperands();
		List<Character> operators = returnOperators();
		
		double result = Calculate(operands, operators);
		System.out.println(result);
		r.close();
	}

}
