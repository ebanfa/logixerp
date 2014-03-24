/**
 * 
 */
package com.qurion.businesslogic.application.rest;

/**
 * @author Edward Banfa
 *
 */
public class RequestData {
	
	private String method;
	private String userName;
	private String requestIPAddress;
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the requestIPAddress
	 */
	public String getRequestIPAddress() {
		return requestIPAddress;
	}
	/**
	 * @param requestIPAddress the requestIPAddress to set
	 */
	public void setRequestIPAddress(String requestIPAddress) {
		this.requestIPAddress = requestIPAddress;
	}
	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

}
