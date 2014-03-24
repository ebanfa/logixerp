/**
 * 
 */
package com.qurion.businesslogic.application.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author Edward Banfa 
 *
 */
public class DateUtil {

	private static final String DATE_FORMAT = "dd/MM/yyyy";
	public static final String DB_DATE_FORMAT = "yyyy-MM-dd";
	public static final String DEFAULT_DATE_FORMAT = DATE_FORMAT;
	/**
	 * Uses joda time to convert a string to a java util date
	 * @param dateString
	 * @return
	 */
	private static final String INVALID_DATE_STRING_PROVIDED = "DateUtil.INVALID_DATE_STRING_PROVIDED";
	
	public static Date convertStringToJavaDate(String dateString) throws ApplicationException
	{
		try {
			DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_FORMAT);
			DateTime dt = formatter.parseDateTime(dateString);
			return dt.toDate();
		} catch (Exception e) {
			throw new ApplicationException(INVALID_DATE_STRING_PROVIDED, e.getMessage());
		}
	}

	/**
	 * @return
	 */
	public static String convertJavaDateToString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.DEFAULT_DATE_FORMAT);
		return dateFormat.format(date);
	}
	/**
	 * @param firstValue
	 * @param secondValue
	 * @return
	 */
	public static Boolean isLessThan(Date firstValue, Date secondValue)
	{
		return (firstValue.compareTo(secondValue) < 0) ?  true : false;
	}
	
	/**
	 * @param firstValue
	 * @param secondValue
	 * @return
	 */
	public static Boolean isEqualsTo(Date firstValue, Date secondValue)
	{
		return (firstValue.compareTo(secondValue) == 0) ?  true : false;
	}
	
	/**
	 * @param firstValue
	 * @param secondValue
	 * @return
	 */
	public static Boolean isGreaterThan(Date firstValue, Date secondValue)
	{
		return (firstValue.compareTo(secondValue) > 0) ?  true : false;
	}
	
	/**
	 * @param dateFormat
	 * @param dateString
	 * @return
	 * @throws ApplicationException
	 */
	public static Date parseDateString(String dateFormat, String dateString) throws ApplicationException {
		try {
			// Convert the string to a date using the default date format
			if(!StringUtil.isValidString(dateFormat))
				dateFormat = DEFAULT_DATE_FORMAT;
			DateTime dateTime = DateTime.parse(dateString, DateTimeFormat.forPattern(dateFormat));
		    return dateTime.toDate();
		} catch (Exception e) {
			throw new ApplicationException(ErrorCodes.INVALID_DATE_STRING);
		}
	}
	
	/**
	 * @param dataFormatString
	 * @param dateString
	 * @return
	 */
	public static Date convertStringToJavaDate(String dataFormatString, String dateString)
	{
		DateTimeFormatter formatter = DateTimeFormat.forPattern(dataFormatString);
		DateTime dt = formatter.parseDateTime(dateString);
		return dt.toDate();
	}
	
	public static Long getCurrentTimeInMilliSeconds() {
		return new Date().getTime();
	}

	public static String getCurrentTime() {
		return getCurrentTimeInMilliSeconds().toString();
	}

	public static Date getCurrentDate() {
		return new Date();
	}

}
