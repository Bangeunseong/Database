package kr.ac.sejong.db.Project1.Calculator;

public class BitLogic {
	public static boolean mainMemory_L = false;
	public static StringBuffer mainExp_L = new StringBuffer();
	
	//Return boolean value
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
		else if(val.equals('^')) return 5;
		else return -1;
	}
}