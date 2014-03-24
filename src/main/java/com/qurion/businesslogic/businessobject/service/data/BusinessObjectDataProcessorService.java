/**
 * 
 */
package com.qurion.businesslogic.businessobject.service.data;

import java.util.Map;

import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.businessobject.data.BusinessObjectData;

/**
 * Instances of this interface are classes the know how to
 * process the data of a business object. 
 * 
 * @author Edward Banfa
 *
 */
public interface BusinessObjectDataProcessorService {
	
	public BusinessObjectData processData(
			BusinessObjectData businessObjectData, Map<String, Object> context) throws ApplicationException;

}
