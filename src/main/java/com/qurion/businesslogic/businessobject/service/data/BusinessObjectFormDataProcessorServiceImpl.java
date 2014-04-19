/**
 * 
 */
package com.qurion.businesslogic.businessobject.service.data;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qurion.businesslogic.application.model.EntityData;
import com.qurion.businesslogic.application.model.Universe;
import com.qurion.businesslogic.application.rest.ActivityRESTService;
import com.qurion.businesslogic.application.rest.ActivityRESTUtil;
import com.qurion.businesslogic.application.service.ActivityService;
import com.qurion.businesslogic.application.service.EntityDataEntityService;
import com.qurion.businesslogic.application.service.UniverseEntityService;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.application.util.ErrorCodes;
import com.qurion.businesslogic.application.util.StringUtil;
import com.qurion.businesslogic.businessobject.annotation.BusinessObjectFormDataProcessorService;
import com.qurion.businesslogic.businessobject.data.BusinessObjectData;
import com.qurion.businesslogic.businessobject.data.BusinessObjectDataImpl;
import com.qurion.businesslogic.businessobject.data.BusinessObjectFieldData;
import com.qurion.businesslogic.businessobject.data.BusinessObjectFieldDataImpl;
import com.qurion.businesslogic.businessobject.service.BusinessObjectCreationService;
import com.qurion.businesslogic.businessobject.util.BusinessObjectUtil;

/**
 * Handles business object form data.
 * @author Edward Banfa
 *
 */
@Stateless
@BusinessObjectFormDataProcessorService
public class BusinessObjectFormDataProcessorServiceImpl implements
		BusinessObjectDataProcessorService {
	public static final String UNIVERSE_FIELD_NM = "universe";
	@Inject UniverseEntityService universeEntityService;
	@Inject ActivityService activityService;
	@Inject EntityDataEntityService entityService;
	@Inject BusinessObjectCreationService businessObjectCreationService;
	private static final String BO_EDIT_MODE_PARAMETER = "businessObjectEditMode";
	
	Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.businessobject.service.data.BusinessObjectDataProcessorService#processData(com.qurion.businesslogic.businessobject.data.BusinessObjectData, java.util.Map)
	 */
	@Override
	public BusinessObjectData processData(
			BusinessObjectData businessObjectData, Map<String, Object> context)
			throws ApplicationException 
	{
		logger.debug("Processing form data {}", businessObjectData);
		if(StringUtil.isValidString(businessObjectData.getBusinessObjectName())) 
		{
			BusinessObjectData clone = this.cloneBusinessObject(businessObjectData);
			cloneBusinessObjectFields(businessObjectData, clone, context);
			
			businessObjectData.getDataValues().remove(ActivityRESTService.BO_DATA_PROCESSOR_PARAM);
			businessObjectData.getDataValues().remove(BO_EDIT_MODE_PARAMETER);
			businessObjectCreationService.create(clone);
		}
		return businessObjectData;
	}

	/**
	 * @param businessObjectData
	 * @return
	 * @throws ApplicationException
	 */
	private BusinessObjectData cloneBusinessObject(
			BusinessObjectData businessObjectData) throws ApplicationException 
	{
		BusinessObjectData clone = 
				new BusinessObjectDataImpl(businessObjectData.getBusinessObjectName());
		// Get the entity referenced by the BO
		EntityData entityData = entityService.findByName(
				businessObjectData.getBusinessObjectName());
		if(entityData == null) 
			throw new ApplicationException(
					ErrorCodes.BOFDPS_PROCESS_DATA_ERROR_CD,
					ErrorCodes.BOFDPS_ENTITY_NOT_FOUND_ERROR_MSG);
		
		businessObjectData.setBusinessObjectClassName(entityData.getEntityClassNm());
		clone.setBusinessObjectClassName(entityData.getEntityClassNm());
		return clone;
	}
	
	/**
	 * Copies the fields of a business object 
	 * into another business object. The source BO is taken
	 * from a form hence it has primitive fields (incomplete).
	 * 
	 * @param businessObjectData
	 * @param context
	 * @param clone
	 * @param fields
	 * @throws ApplicationException 
	 */
	private void cloneBusinessObjectFields(BusinessObjectData businessObjectData, 
			BusinessObjectData clone, Map<String, Object> context) throws ApplicationException 
	{
		// Fields of the business object
		List<BusinessObjectFieldData> fields = 
				activityService.getBusinessObjectFields(
						businessObjectData.getBusinessObjectName());

		for(String fieldName: 
			businessObjectData.getDataValues().keySet())
		{
			Object fieldValue = 
					businessObjectData.getDataValues().get(fieldName);
			// Process if the current field is the universe field
			if(fieldName.equals(UNIVERSE_FIELD_NM)) 
				fieldValue = getValueForUniverseField(fieldValue);
			// Build a field data for this field
			BusinessObjectFieldData fieldData = 
					new BusinessObjectFieldDataImpl(fieldName, fieldValue, null);
			// We populate some need meta info about the field
			populateFieldMetaInfo(fields, fieldName, fieldData);
			clone.setDataValue(fieldName, fieldData);
		}
		clone.setDataValue(BusinessObjectUtil.CREATED_BY_USR_DATA_VALUE, 
				new BusinessObjectFieldDataImpl(BusinessObjectUtil.CREATED_BY_USR_DATA_VALUE, 
						context.get(ActivityRESTUtil.USER_NAME), null));
	}

	/**
	 * Adds additional information to a 
	 * business object field data 
	 * @param fields
	 * @param fieldName
	 * @param fieldData
	 */
	private void populateFieldMetaInfo(List<BusinessObjectFieldData> fields,
			String fieldName, BusinessObjectFieldData fieldData) 
	{
		for(BusinessObjectFieldData businessObjectFieldData: fields) 
		{
			if(fieldName.equals(businessObjectFieldData.getFieldName())) 
			{
				fieldData.setFieldDataType(
						businessObjectFieldData.getFieldDataType());
				fieldData.setRelatedBusinessObjectName(
						businessObjectFieldData.getRelatedBusinessObjectName());
			}
		}
	}

	/**
	 * @param fieldValue
	 * @return
	 * @throws ApplicationException
	 */
	private Object getValueForUniverseField(Object fieldValue)
			throws ApplicationException {
		if(fieldValue == null)
			throw new ApplicationException(
					ErrorCodes.BOFDPS_PROCESS_DATA_ERROR_CD,
					ErrorCodes.BOFDPS_ENTITY_NOT_FOUND_ERROR_MSG);	
		// Find the universe
		Universe universe = 
				universeEntityService.findByCode(fieldValue.toString());
		// Null result not allowed
		if(universe == null)
			throw new ApplicationException(
					ErrorCodes.BOFDPS_PROCESS_DATA_ERROR_CD,
					ErrorCodes.BOFDPS_ENTITY_NOT_FOUND_ERROR_MSG);
		fieldValue = universe.getId().toString();
		return fieldValue;
	}
	

}
