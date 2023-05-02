package kr.ac.sejong.db.Project1.Calculator;

import java.util.HashMap;
import java.util.Map;

public class Matrix {
	public static int rowA = 3, colA = 3, rowB = 3, colB = 3;
	public static Map<Integer, Double> mainMatrix = new HashMap<>();
	public static Map<Integer, Double> subMatrix = new HashMap<>();
	public static Map<Integer, Double> result = new HashMap<>();
	
	public static void matrix_Add() throws ArithmeticException{
		if(mainMatrix.isEmpty() || subMatrix.isEmpty()) {
			throw new ArithmeticException("One of matrices is empty!");
		}
		if(rowA != rowB || colA != colB) {
			throw new ArithmeticException("Two matrices need to be same dimension");
		}
		
		result.clear();
		for(int i = 0; i < rowA * colA; i++) {
			double tmp = 0;
			if(mainMatrix.containsKey(i)) {tmp += mainMatrix.get(i);}
			else {throw new ArithmeticException();}
			if(subMatrix.containsKey(i)) {tmp += subMatrix.get(i);}
			else {throw new ArithmeticException();}
			result.put(i, tmp);
		}
	}
	
	public static void matrix_Sub() throws ArithmeticException{
		if(mainMatrix.isEmpty() || subMatrix.isEmpty()) {
			throw new ArithmeticException("One of matrices is empty!");
		}
		if(rowA != rowB || colA != colB) {
			throw new ArithmeticException("Two matrices need to be same dimension");
		}
		
		result.clear();
		for(int i = 0; i < rowA * colA; i++) {
			double tmp = 0;
			if(mainMatrix.containsKey(i)) {tmp += mainMatrix.get(i);}
			else {throw new ArithmeticException();}
			if(subMatrix.containsKey(i)) {tmp -= subMatrix.get(i);}
			else {throw new ArithmeticException();}
			result.put(i, tmp);
		}
	}
	
	public static void matrix_Mul() throws ArithmeticException{
		if(mainMatrix.isEmpty() || subMatrix.isEmpty()) {
			throw new ArithmeticException("One of matrices is empty!");
		}
		if(colA != rowB) {
			throw new ArithmeticException("One matrix column must be equal to another matrix row");
		}
		
		result.clear();
		for(int i = 0; i < rowA * colB; i++) {
			double tmp = 0;
			for(int j = 0; j < colA; j++) {
				tmp += mainMatrix.get(i / colB * colA + j) * subMatrix.get(i % colB + j * colB);
			}
			result.put(i, tmp);
		}
	}
}
