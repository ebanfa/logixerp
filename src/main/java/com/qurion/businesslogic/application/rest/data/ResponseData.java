/**
 * 
 */
package com.qurion.businesslogic.application.rest.data;



/**
 * @author Edward Banfa 
 *
 */
public class ResponseData {
		
	private Boolean errors;
	private String errorMessage;
	
	/**
	 * 
	 */
	public ResponseData() {
		this.errors = false;
		this.errorMessage = "";
	}
	/**
	 * @param errors
	 * @param errorMessage
	 */
	public ResponseData(Boolean errors, String errorMessage) {
		this.errors = errors;
		this.errorMessage = errorMessage;
	}
	/**
	 * @return the errors
	 */
	public Boolean getErrors() {
		return errors;
	}
	/**
	 * @param errors the errors to set
	 */
	public void setErrors(Boolean errors) {
		this.errors = errors;
	}
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
