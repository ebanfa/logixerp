/**
 * 
 */
package com.qurion.businesslogic.businessobject.service.data;

import com.qurion.businesslogic.application.util.ApplicationException;

/**
 * @author Edward Banfa
 *
 */
public interface BusinessObjectDataProcessorProducer {
	

	public static final String BO_FORM_DATA_PROCESSOR_SERVICE = "BusinessObjectFormDataProcessorService";
	
	public BusinessObjectDataProcessorService getBusinessObjectDataProcessorService(
			String businessObjectDataProcessorServiceCode) throws ApplicationException;

}
