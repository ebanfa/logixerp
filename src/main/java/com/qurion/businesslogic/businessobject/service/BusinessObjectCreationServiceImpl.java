/**
 * 
 */
package com.qurion.businesslogic.businessobject.service;

import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qurion.businesslogic.application.model.BaseEntity;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.application.util.ErrorCodes;
import com.qurion.businesslogic.application.util.ExceptionUtil;
import com.qurion.businesslogic.businessobject.data.BusinessObjectData;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class BusinessObjectCreationServiceImpl extends AbstractBusinessObjectServiceImpl implements BusinessObjectCreationService {

	
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.rule.service.process.BusinessObjectCreationService#create(com.qurion.businesslogic.rule.engine.BusinessObjectData)
	 */
	public BaseEntity create(BusinessObjectData businessObjectData)	throws ApplicationException {
		BaseEntity entityInstance = null;
		try {
			// Convert the business object into the appropriate instance of
			// BaseEntity
			entityInstance = businessObjectDataToEntityInstance(businessObjectData);
			save(entityInstance);
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.BOCS_BUSINESS_OBJECT_CREATION_ERROR_CD);
		}
		return entityInstance;
	}

}
