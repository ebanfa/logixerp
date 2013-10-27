/**
 * 
 */
package com.qurion.businesslogic.application.util;

/**
 * @author Edward Banfa 
 *
 */
public class ApplicationException extends Exception {

	private String code;
	private String message;
	public static final String UNKNOWN_EXCEPTION = 
			"ApplicationException.UNKNOWN_EXCEPTION";
	private static final long serialVersionUID = 5716304693948177473L;
	

	/**
	 * @param code
	 * @param message 
	 */
	public ApplicationException(String code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * @param cause
	 */
	public ApplicationException(String code, Throwable cause) {
		super(cause);
		this.code = code;
	}

	/**
	 * @param code
	 * @param message 
	 */
	public ApplicationException(String code) {
		this.code = code;
		//this.message = Messages.getString(code);
	}
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}



}
