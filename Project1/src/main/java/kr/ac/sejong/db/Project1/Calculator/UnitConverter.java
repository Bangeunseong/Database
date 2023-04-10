package kr.ac.sejong.db.Project1.Calculator;

import java.util.Arrays;
import java.util.List;

public class UnitConverter {
	public static double mainMemory_Unit = 0;
	
	//scale_factor of length, area, weight, volume, pressure, velocity, datacapacity
	public static List<Double> scaleOfLength = Arrays.asList(
			1.0,0.1,0.001,1e-6,0.0393701,0.00328084,
			0.00109361,6.2137e-7,0.0033,0.00055,
			9.1667e-6,2.5463e-6,5.3996e-7
		);
	public static List<Double> scaleOfArea = Arrays.asList(
			1.0, 0.01, 0.001, 1e-6, 10.76391, 1.19599,
			0.000247, 10.89, 0.3025, 0.001008, 0.000101
		);
	public static List<Double> scaleOfWeight = Arrays.asList(
			1.0, 0.001, 1e-6, 10e-10, 1e-12, 0.015432,
			0.000035, 2.2046e-6, 0.000267, 0.000027,
			1.6667e-6, 2.6667e-7
		);
	public static List<Double> scaleOfVolume = Arrays.asList(
			1.0, 1.0, 0.01, 0.001, 1.0, 1e-6, 0.061024,
			0.000035, 1.308e-6, 0.000264, 6.2933e-6, 0.033814,
			0.005544, 0.000554, 0.000055
		);
	public static List<Double> scaleOfPressure = Arrays.asList(
			1.0, 101325.0, 1013.25, 101.325, 0.101325, 1013250.0,
			1013.25, 1.01325, 1.033227, 14.696, 760.0, 29.92126,
			10332.275, 406.782188
		);
	public static List<Double> scaleOfVelocity = Arrays.asList(
			1.0, 3600.0, 0.001, 3.6, 39.370079, 141732.283,
			3.28084, 11811.0236, 0.000621, 2.236936, 1.943844, 0.002941
		);
	public static List<Double> scaleOfDataCapacity = Arrays.asList(
			1.0, 0.125, 0.000122, 1.1921e-7, 1.1642e-10,1.1369e-13,
			1.1102e-16,1.0842e-19
		);
	
	//Converting temperature unit
	public static double convertCToF(double mem) {
		return mem * 1.8 + 32;
	}
	public static double convertFToC(double mem) {
		return (mem - 32) / 1.8;
	}
	public static double convertCToK(double mem) {
		return mem + 273.15;
	}
	public static double convertKToC(double mem) {
		return mem - 273.15;
	}
	public static double convertCToR(double mem) {
		return (mem + 273.15)*9/5;
	}
	public static double convertRToC(double mem) {
		return mem*5/9 - 273.15;
	}
	
	//Converting temp.
	public static double convertOfTemp(int index1, int index2) {
		if(index1 == 0) {
			if(index2 == 0) return mainMemory_Unit;
			else if(index2 == 1) return convertCToF(mainMemory_Unit);
			else if(index2 == 2) return convertCToK(mainMemory_Unit);
			else return convertCToR(mainMemory_Unit);
		}
		else if(index1 == 1) {
			if(index2 == 0) return convertFToC(mainMemory_Unit);
			else if(index2 == 1) return mainMemory_Unit;
			else if(index2 == 2) return convertCToK(convertFToC(mainMemory_Unit));
			else return convertCToR(convertFToC(mainMemory_Unit));
		}
		else if(index1 == 2) {
			if(index2 == 0) return convertKToC(mainMemory_Unit);
			else if(index2 == 1) return convertCToF(convertKToC(mainMemory_Unit));
			else if(index2 == 2) return mainMemory_Unit;
			else return convertCToR(convertKToC(mainMemory_Unit));
		}
		else {
			if(index2 == 0) return convertRToC(mainMemory_Unit);
			else if(index2 == 1) return convertCToF(convertRToC(mainMemory_Unit));
			else if(index2 == 2) return convertCToK(convertRToC(mainMemory_Unit));
			else return mainMemory_Unit;
		}
	}
	
	//Converting unit by using scale
	public static double convertOfUnit(List<Double> selectedScale, int index1, int index2) throws IndexOutOfBoundsException {
		return selectedScale.get(index2)/ selectedScale.get(index1) * mainMemory_Unit;
	}
}
