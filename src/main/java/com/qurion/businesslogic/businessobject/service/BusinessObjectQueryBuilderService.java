/**
 *
 */
package com.qurion.businesslogic.businessobject.service;

import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.businessobject.data.BusinessObjectData;
import com.qurion.businesslogic.businessobject.data.SearchData;

/**
 * Builds a query string for finding {@link BusinessObjectData}s.
 * 
 * @author Edward Banfa
 *
 */
public interface BusinessObjectQueryBuilderService {
	
	public String buildQuery(SearchData data) throws ApplicationException;

}
