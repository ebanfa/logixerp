/**
 * 
 */
package com.qurion.businesslogic.businessobject.service;

import java.util.List;

import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.businessobject.data.BusinessObjectData;
import com.qurion.businesslogic.businessobject.data.BusinessObjectFieldData;
import com.qurion.businesslogic.businessobject.data.SearchData;

/**
 * {@link BusinessObjectData} search interface.
 * 
 * @author Edward Banfa
 *
 */
public interface BusinessObjectSearchService {
	
	/**
	 * Finds all the {@link BusinessObjectData}s that 
	 * satisfy a the the information in the {@link SearchData}.
	 * 
	 * @param searchData the search data
	 * @param fieldsWanted the fields of the business object we 
	 * 					   are interested in
	 * @return the list of business objects that satisfy the search
	 *         condition
	 * @throws ApplicationException if an exception is encountered
	 */
	public List<BusinessObjectData> find(SearchData searchData, 
			List<BusinessObjectFieldData> fieldsWanted) throws ApplicationException;

	/**
	 * Finds a single {@link BusinessObjectData} by its id.
	 * 
	 * @param businessObjectName the name of the business object
	 * @param businessObjectId the id of the business object
	 * @param fieldsWanted the fields of the business object we 
	 * 					   are interested in
	 * @return the business object we wanted
	 * @throws ApplicationException if an exception is encountered
	 */
	public BusinessObjectData findById(String businessObjectName,
			Integer businessObjectId, List<BusinessObjectFieldData> fieldsWanted) throws ApplicationException;
	
	/**
	 * Finds a single {@link BusinessObjectData} by its code.
	 * 
	 * @param businessObjectNamethe name of the business object
	 * @param businessObjectCode the id of the business object
	 * @param fieldsWanted the fields of the business object we 
	 * 					   are interested in
	 * @return the business object we wanted
	 * @throws ApplicationException if an exception is encountered
	 */
	public BusinessObjectData findByCode(String businessObjectName,
			String businessObjectCode, List<BusinessObjectFieldData> fieldsWanted) throws ApplicationException;

	/**
	 * Finds a single {@link BusinessObjectData} that 
	 * satisfy a the the information in the {@link SearchData}.
	 * If more than one {@link BusinessObjectData} is found, the
	 * first instance in the result list will be returned
	 * 
	 * @param searchData the search data
	 * @param fieldsWanted the fields of the business object we 
	 * 					   are interested in
	 * @return the first business object that satisfies the
	 *         search condition 
	 * @throws ApplicationException if an exception was encountered
	 */
	public BusinessObjectData findSingle(SearchData searchData, List<BusinessObjectFieldData> fieldsWanted) throws ApplicationException;

	/**
	 * @param businessObjectName
	 * @param fieldsWanted
	 * @return
	 * @throws ApplicationException
	 */
	List<BusinessObjectData> findAll(String businessObjectName,	List<BusinessObjectFieldData> fieldsWanted)
			throws ApplicationException;
}
