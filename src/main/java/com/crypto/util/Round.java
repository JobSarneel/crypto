package com.crypto.util;

public class Round {
	
	public static double twoDecimals(double number) {
		
		double result = Math.round(number * 100.0) / 100.0;
		return result;
	}

	public static double fourDecimals(double number) {
		double result = Math.round(number * 10000.0) / 10000.0;
		return result;
	}

}
