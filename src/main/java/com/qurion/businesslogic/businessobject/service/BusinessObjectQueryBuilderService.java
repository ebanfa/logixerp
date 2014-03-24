/**
 *
 */
package com.qurion.businesslogic.businessobject.service;

import javax.persistence.TypedQuery;

import com.qurion.businesslogic.application.model.BaseEntity;
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
	
	/**
	 * Uses EJBQL to buid a query string
	 * @param data
	 * @return
	 * @throws ApplicationException
	 */
	public String buildQuery(SearchData data) throws ApplicationException;
	
	/**
	 * Uses JPA CriteriaBuilder to build a type query
	 * @param searchData
	 * @return
	 * @throws ApplicationException
	 */
	public TypedQuery<BaseEntity> getQuery(SearchData searchData) throws ApplicationException;

}
