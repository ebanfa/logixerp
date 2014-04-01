/**
 * 
 */
package com.qurion.businesslogic.businessobject.service;

import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qurion.businesslogic.application.model.BaseEntity;
import com.qurion.businesslogic.application.model.EntityData;
import com.qurion.businesslogic.application.service.ActivityService;
import com.qurion.businesslogic.application.service.EntityDataEntityService;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.application.util.EntityUtil;
import com.qurion.businesslogic.application.util.ErrorCodes;
import com.qurion.businesslogic.application.util.StringUtil;
import com.qurion.businesslogic.businessobject.data.BusinessObjectData;
import com.qurion.businesslogic.businessobject.data.BusinessObjectFieldData;
import com.qurion.businesslogic.businessobject.util.BusinessObjectUtil;

/**
 * @author Edward Banfa
 *
 */
public abstract class AbstractBusinessObjectServiceImpl {
	
	@Inject ActivityService activityService;
	@Inject protected EntityManager entityManager;
	@Inject EntityDataEntityService applicationEntityService;
	@Inject BusinessObjectSearchService businessObjectSearchService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	
	/**
	 * @param businessObjectData
	 * @return
	 * @throws ApplicationException
	 */
	protected BaseEntity businessObjectDataToEntityInstance(BusinessObjectData businessObjectData) throws ApplicationException 
	{
		// Convert the id's of relationship fields into 
		// business objects
		logger.debug("Converting business object to entity instance");
		BaseEntity entityInstance = BusinessObjectUtil.businessObjectToEntityInstance(businessObjectData);
		entityInstance = copyBusinessObjectRelationshipsToEntityInstance(entityInstance, businessObjectData);
		return entityInstance;
	}
	
	protected BaseEntity copyBusinessObjectRelationshipsToEntityInstance(
			BaseEntity entityInstance, BusinessObjectData businessObjectData) throws ApplicationException
	{
		Map<String, Object> dataValues = 
				businessObjectData.getDataValues();
		for(String fieldName : dataValues.keySet()) 
		{
			BusinessObjectFieldData fieldData = 
					(BusinessObjectFieldData) dataValues.get(fieldName);
			// Verify the field data
			if (fieldData == null)
				continue;
			if (fieldData.getFieldValue() == null)
				continue;
			if (StringUtil.isValidString(fieldData.getFieldValue().toString()) &&
					StringUtil.isValidString(fieldData.getRelatedBusinessObjectName())) 
				invokeEntityRelationshipFieldMethod(entityInstance,	fieldName, fieldData);
		}
		return entityInstance;
	}
	
	/**
	 * @param entityInstance
	 * @param fieldName
	 * @param fieldData
	 * @throws ApplicationException
	 */
	protected void invokeEntityRelationshipFieldMethod(BaseEntity entityInstance,
			String fieldName, BusinessObjectFieldData fieldData)
			throws ApplicationException 
	{

		Integer relatedEntityId = stringIdToInteger(fieldData);
		EntityData entity = 
				applicationEntityService.findByName(fieldData.getRelatedBusinessObjectName());
		
		if(entity == null)
			throw new ApplicationException(
					ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD,
					ErrorCodes.BOCS_APPLICATION_ENTITY_NOT_FOUND_ERROR_MSG);
		// Do some validation of the field value
		if(fieldData.getFieldValue() == null)
			return;
		if(!StringUtil.isValidString(fieldData.getFieldValue().toString()))
			return;
		
		BaseEntity relatedEntity = 
				entityManager.find(EntityUtil.getEntityClass(entity), relatedEntityId);
		if(relatedEntity != null)
			EntityUtil.invokeMethodOnEntityInstance(entityInstance, fieldData, relatedEntity);
		
	}

	
	/**
	 * Convert the {@code String} representation of the value of a 
	 * relationship field id into an {@code Integer}. 
	 * 
	 * @param fieldData
	 * @return
	 * @throws ApplicationException
	 */
	protected Integer stringIdToInteger(BusinessObjectFieldData fieldData) 
			throws ApplicationException {
		try {
			// Get the id of the related entity/ business object
			Object fieldValue = fieldData.getFieldValue();
				Integer relatedEntityId = 
						Integer.valueOf(fieldValue.toString());
				return relatedEntityId;
		} catch (NumberFormatException e) {
			throw new ApplicationException(ErrorCodes.BOCS_BUSINESS_OBJECT_CREATION_ERROR_CD, 
					ErrorCodes.BOCS_INVALID_RELATED_BUSINESS_OBJECT_ID_ERROR_MSG);
		}
	}
	/**
	 * @param entityInstance
	 */
	protected BaseEntity save(BaseEntity entityInstance) {
		logger.debug("Persisting {}", entityInstance);
		entityManager.merge(entityInstance);
		return entityInstance;
	}
	
	

}
