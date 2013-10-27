/**
 * 
 */
package com.qurion.businesslogic.businessobject.service;

import com.qurion.businesslogic.application.model.BaseEntity;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.businessobject.data.BusinessObjectData;

/**
 * An interface for business object creation services.
 * {@link BusinessObjectData} creation is basically persisting
 * the {@link BusinessObjectData} to some datastore.
 * 
 * @author Edward Banfa
 * 
 */
public interface BusinessObjectCreationService {

	/**
	 * Creates an instance of the {@link BusinessObjectData}
	 * in the underlying storage system.
	 * 
	 * @param businessObjectData the business object we are creating
	 * @return a copy of the persisted business object
	 * @throws ApplicationException if an exception occurred
	 */
	public BaseEntity create(BusinessObjectData businessObjectData) throws ApplicationException;

}
