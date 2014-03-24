/**
 * 
 */
package com.qurion.businesslogic.businessobject.service.data;

import java.util.List;
import java.util.Map;

import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.businessobject.data.BusinessObjectData;

/**
 * @author Edward Banfa
 *
 */
public interface UiQueryDataService {
	
	public List<BusinessObjectData> find(Map<String, Object> context) 
			throws ApplicationException;

}
