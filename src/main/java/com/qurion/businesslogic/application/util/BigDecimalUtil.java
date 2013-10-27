/**
 * 
 */
package com.qurion.businesslogic.application.util;

import java.math.BigDecimal;

/**
 * @author Edward Banfa
 *
 */
public class BigDecimalUtil {
	
	/**
	 * @param firstValue
	 * @param secondValue
	 * @return
	 */
	public static Boolean isLessThan(BigDecimal firstValue, BigDecimal secondValue)
	{
		return (firstValue.compareTo(secondValue) < 0) ?  true : false;
	}
	
	/**
	 * @param firstValue
	 * @param secondValue
	 * @return
	 */
	public static Boolean isEqualsTo(BigDecimal firstValue, BigDecimal secondValue)
	{
		return (firstValue.compareTo(secondValue) == 0) ?  true : false;
	}
	
	/**
	 * @param firstValue
	 * @param secondValue
	 * @return
	 */
	public static Boolean isGreaterThan(BigDecimal firstValue, BigDecimal secondValue)
	{
		return (firstValue.compareTo(secondValue) > 0) ?  true : false;
	}

}
