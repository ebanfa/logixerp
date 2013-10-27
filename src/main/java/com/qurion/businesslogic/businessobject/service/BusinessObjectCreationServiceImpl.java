/**
 * 
 */
package com.qurion.businesslogic.businessobject.service;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qurion.businesslogic.application.model.BaseEntity;
import com.qurion.businesslogic.application.model.EntityData;
import com.qurion.businesslogic.application.service.ActivityService;
import com.qurion.businesslogic.application.service.EntityDataEntityService;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.application.util.EntityUtil;
import com.qurion.businesslogic.application.util.ErrorCodes;
import com.qurion.businesslogic.application.util.ExceptionUtil;
import com.qurion.businesslogic.application.util.StringUtil;
import com.qurion.businesslogic.businessobject.data.BusinessObjectData;
import com.qurion.businesslogic.businessobject.data.BusinessObjectFieldData;
import com.qurion.businesslogic.businessobject.util.BusinessObjectUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class BusinessObjectCreationServiceImpl implements BusinessObjectCreationService {

	@Inject ActivityService activityService;
	@PersistenceUnit EntityManager entityManager;
	@Inject EntityDataEntityService applicationEntityService;
	@Inject BusinessObjectSearchService businessObjectSearchService;
	
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
			logger.debug("Persisting {}", entityInstance);
			entityManager.merge(entityInstance);
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.BOCS_BUSINESS_OBJECT_CREATION_ERROR_CD);
		}
		return entityInstance;
	}
	
	private BaseEntity businessObjectDataToEntityInstance(BusinessObjectData businessObjectData) throws ApplicationException 
	{
		// Convert the id's of relationship fields into 
		// business objects
		BaseEntity entityInstance = BusinessObjectUtil.businessObjectToEntityInstance(businessObjectData);
		entityInstance = resolveRelationships(entityInstance, businessObjectData);
		return entityInstance;
	}
	
	private BaseEntity resolveRelationships(
			BaseEntity entityInstance, BusinessObjectData businessObjectData) throws ApplicationException
	{
		Map<String, Object> dataValues = 
				businessObjectData.getDataValues();
		for(String fieldName : dataValues.keySet()) {
			BusinessObjectFieldData fieldData = 
					(BusinessObjectFieldData) dataValues.get(fieldName);
			if (fieldData != null)
				if (fieldData.getFieldValue() != null && 
								StringUtil.isValidString(fieldData.getRelatedBusinessObjectName())) 
				{
					Integer relatedEntityId = stringIdToInteger(fieldData);
					EntityData entity = 
							applicationEntityService.findByName(fieldData.getRelatedBusinessObjectName());
					if(entity == null)
						throw new ApplicationException(
								ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD,
								ErrorCodes.BOCS_APPLICATION_ENTITY_NOT_FOUND_ERROR_MSG);
					BaseEntity relatedEntity = 
							entityManager.find(EntityUtil.getEntityClass(entity), relatedEntityId);
					logger.debug("Processing relationship data for field {} with id {} and data type {} and entity {}", 
							fieldName, relatedEntityId,	fieldData.getRelatedBusinessObjectName(), relatedEntity);
					if(relatedEntity != null)
						EntityUtil.invokeMethodOnEntityInstance(entityInstance, fieldName, relatedEntity);
				}
		}
		return entityInstance;
	}

	/**
	 * @param businessObjectData
	 * @throws ApplicationException
	 */
	public BusinessObjectData relationshipIdsToRelatedBusinessObjects(BusinessObjectData businessObjectData) 
			throws ApplicationException
	{
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(
				new Object[] {businessObjectData}, "changeIdsToObjects");
		
		Map<String, Object> dataValues = 
				businessObjectData.getDataValues();
		// Loop through the fields of the business object
		// and for each relationship field, convert the string id 
		// value into an instance of the related entity
		for(String fieldName : dataValues.keySet()){
			BusinessObjectFieldData fieldData = 
					(BusinessObjectFieldData) dataValues.get(fieldName);
			// If this field is a related entity field
			if (fieldData != null)
				if (fieldData.getFieldValue() != null && 
								StringUtil.isValidString(fieldData.getRelatedBusinessObjectName())) 
				{
					// Convert the id value into an integer
					Integer relatedEntityId = stringIdToInteger(fieldData);
					// Get the fields of the related entity.
					List<BusinessObjectFieldData> fieldsWanted = activityService
							.getEntityEditFields(businessObjectData.getBusinessObjectName());
					// Find the related business object
					BusinessObjectData relatedBusinessObject = businessObjectSearchService
							.findById(businessObjectData.getBusinessObjectName(), relatedEntityId, fieldsWanted);
					// Replace the related Id with the related object
					dataValues.put(fieldName, relatedBusinessObject);
				}
		}
		return businessObjectData;
	}

	/**
	 * Convert the {@code String} representation of the value of a 
	 * relationship field id into an {@code Integer}. 
	 * 
	 * @param fieldData
	 * @return
	 * @throws ApplicationException
	 */
	private Integer stringIdToInteger(BusinessObjectFieldData fieldData) 
			throws ApplicationException {
		try {
			// Get the id of the related entity/ business object
			Object fieldValue = fieldData.getFieldValue();
			logger.debug("Converting related entity id {} from String to Intger", fieldValue);
				Integer relatedEntityId = 
						Integer.valueOf(fieldValue.toString());
				/*if(relatedEntityId == null) 
					throw new ApplicationException(ErrorCodes.BOCS_BUSINESS_OBJECT_CREATION_ERROR_CD, 
							ErrorCodes.BOCS_INVALID_RELATED_BUSINESS_OBJECT_ID_ERROR_MSG);*/
				return relatedEntityId;
		} catch (NumberFormatException e) {
			throw new ApplicationException(ErrorCodes.BOCS_BUSINESS_OBJECT_CREATION_ERROR_CD, 
					ErrorCodes.BOCS_INVALID_RELATED_BUSINESS_OBJECT_ID_ERROR_MSG);
		}
	}

}
