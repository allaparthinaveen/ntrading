package com.utils;

import java.math.BigDecimal;

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
}
