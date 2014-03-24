/**
 * 
 */
package com.qurion.businesslogic.businessobject.service.data;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.businessobject.annotation.BusinessObjectFormDataProcessorService;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class BusinessObjectDataProcessorProducerImpl implements
		BusinessObjectDataProcessorProducer {
	
	@Inject @BusinessObjectFormDataProcessorService BusinessObjectDataProcessorService businessObjectFormDataProcessor;
	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.businessobject.service.data.BusinessObjectDataProcessorProducer#getBusinessObjectDataProcessorService(java.lang.String)
	 */
	@Override
	public BusinessObjectDataProcessorService getBusinessObjectDataProcessorService(
			String businessObjectDataProcessorServiceCode) throws ApplicationException 
	{
		if(businessObjectDataProcessorServiceCode.equals(BO_FORM_DATA_PROCESSOR_SERVICE))
			return businessObjectFormDataProcessor;
		return null;
	}

}
