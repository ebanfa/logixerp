/**
 * 
 */
package com.qurion.businesslogic.application.util;

import java.math.BigDecimal;

/**
 * @author Edward Banfa
 *
 */
public class IntegerUtil {
	
	public static Integer toInteger(Object value) throws ApplicationException
	{
		if(value == null)
			throw new ApplicationException(
					ErrorCodes.IU_INTEGER_CONVERSION_ERROR, 
					ErrorCodes.IU_INVALID_SOURCE_DATA_TY_ERROR_MSG);
		else if(value instanceof Integer)
			return (Integer) value;
		else if(value instanceof String)
			return Integer.valueOf((String) value);
		else if(value instanceof BigDecimal)
			return ((BigDecimal) value).intValue();
		else if(value instanceof Float)
			return ((Float) value).intValue();
		else if(value instanceof Double)
			return ((Double) value).intValue();
		else
			throw new ApplicationException(
					ErrorCodes.IU_INTEGER_CONVERSION_ERROR, 
					ErrorCodes.IU_INVALID_SOURCE_DATA_TY_ERROR_MSG);
	}
	
	/**
	 * @param firstValue
	 * @param secondValue
	 * @return
	 */
	public static Boolean isLessThan(Integer firstValue, Integer secondValue)
	{
		return (firstValue < secondValue) ?  true : false;
	}
	
	/**
	 * @param firstValue
	 * @param secondValue
	 * @return
	 */
	public static Boolean isEqualsTo(Integer firstValue, Integer secondValue)
	{
		return (firstValue == secondValue) ?  true : false;
	}
	
	/**
	 * @param firstValue
	 * @param secondValue
	 * @return
	 */
	public static Boolean isGreaterThan(Integer firstValue, Integer secondValue)
	{
		return (firstValue > secondValue) ?  true : false;
	}

}
