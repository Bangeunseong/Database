package kr.ac.sejong.db.Project1.Calculator;

import java.util.HashMap;
import java.util.Map;

public class Matrix {
	public static int rowA = 3, colA = 3, rowB = 3, colB = 3;
	public static Map<Integer, Double> mainMatrix = new HashMap<>();
	public static Map<Integer, Double> subMatrix = new HashMap<>();
	
	public Map<Integer, Double> matrix_Add() throws ArithmeticException{
		if(mainMatrix.isEmpty() || subMatrix.isEmpty()) {
			throw new ArithmeticException("One of matrices is empty!");
		}
		if(rowA != rowB || colA != colB) {
			throw new ArithmeticException("Two matrices need to be same dimension");
		}
		
		Map<Integer, Double> result = new HashMap<>();
		for(int i = 0; i < rowA * colA; i++) {
			double tmp = 0;
			if(mainMatrix.containsKey(i)) {tmp += mainMatrix.get(i);}
			else {throw new ArithmeticException();}
			if(subMatrix.containsKey(i)) {tmp += subMatrix.get(i);}
			else {throw new ArithmeticException();}
			result.put(i, tmp);
		}
		return result;
	}
	
	public Map<Integer, Double> matrix_Sub() throws ArithmeticException{
		if(mainMatrix.isEmpty() || subMatrix.isEmpty()) {
			throw new ArithmeticException("One of matrices is empty!");
		}
		if(rowA != rowB || colA != colB) {
			throw new ArithmeticException("Two matrices need to be same dimension");
		}
		
		Map<Integer, Double> result = new HashMap<>();
		for(int i = 0; i < rowA * colA; i++) {
			double tmp = 0;
			if(mainMatrix.containsKey(i)) {tmp += mainMatrix.get(i);}
			else {throw new ArithmeticException();}
			if(subMatrix.containsKey(i)) {tmp -= subMatrix.get(i);}
			else {throw new ArithmeticException();}
			result.put(i, tmp);
		}
		return result;
	}
	
	public Map<Integer, Double> matrix_Mul() throws ArithmeticException{
		if(mainMatrix.isEmpty() || subMatrix.isEmpty()) {
			throw new ArithmeticException("One of matrices is empty!");
		}
		if(colA != rowB) {
			throw new ArithmeticException("One matrix column must be equal to another matrix row");
		}
		
		Map<Integer, Double> result = new HashMap<>();
		for(int i = 0; i < rowA * colA; i++) {
			double tmp = 0;
			if(mainMatrix.containsKey(i)) {tmp += mainMatrix.get(i);}
			else {throw new ArithmeticException();}
			if(subMatrix.containsKey(i)) {tmp += subMatrix.get(i);}
			else {throw new ArithmeticException();}
			result.put(i, tmp);
		}
		return result;
	}
}
