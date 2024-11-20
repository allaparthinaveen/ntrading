package com.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculationUtils {
	/**
	 * Calculate the percentage with of and given number
	 * @param of
	 * @param number
	 * @return (of / 100) * number
	 */
	public static BigDecimal percentageOf(final BigDecimal of, final BigDecimal number){
		return (of.divide(new BigDecimal(100))).multiply(number);
	}
	
	/**
	 * change = difference between two prices/values
	 * @param one
	 * @param two
	 * @return (change/original)X100
	 */
	public static BigDecimal percentageOfChange(final BigDecimal one, final BigDecimal two){
		BigDecimal change = one.compareTo(two) > 0 ? one.subtract(two) : two.subtract(one);
		BigDecimal original = one.compareTo(two) > 0 ? two : one;
		
		return change.divide(original, RoundingMode.CEILING).multiply(new BigDecimal(100));
	}
}
