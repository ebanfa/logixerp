/**
 * 
 */
package com.qurion.businesslogic.application.rest;

import com.qurion.businesslogic.application.util.ExceptionUtil;

/**
 * @author Edward Banfa
 *
 */
public class AbstractRESTService {
	
	protected void processRESTException(Exception e, ResponseData response)
	{
		response.setErrors(true);
		response.setErrorMessage(e.getMessage());
		ExceptionUtil.logException(e);
	}

}
