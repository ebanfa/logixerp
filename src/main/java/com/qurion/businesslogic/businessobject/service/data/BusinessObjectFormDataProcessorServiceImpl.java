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
import com.qurion.businesslogic.application.rest.ActivityRESTService;
import com.qurion.businesslogic.application.service.ActivityService;
import com.qurion.businesslogic.application.service.EntityDataEntityService;
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
			throws ApplicationException {
		logger.debug("Processing business object data {}", businessObjectData);
		
		if(StringUtil.isValidString(businessObjectData.getBusinessObjectName())) 
		{
			BusinessObjectData clone = 
					new BusinessObjectDataImpl(businessObjectData.getBusinessObjectName());
			EntityData entityData = entityService.findByName(
					businessObjectData.getBusinessObjectName());
			
			if(entityData == null) 
				throw new ApplicationException(
						ErrorCodes.BOFDPS_PROCESS_DATA_ERROR_CD,
						ErrorCodes.BOFDPS_ENTITY_NOT_FOUND_ERROR_MSG);

			logger.debug("Processing business object dataxxx1 {} ", entityData.getName());
			businessObjectData.setBusinessObjectClassName(
					entityData.getEntityClassNm());
			clone.setBusinessObjectClassName(entityData.getEntityClassNm());
			// Fields of the business object
			List<BusinessObjectFieldData> fields = 
					activityService.getBusinessObjectFields(entityData.getName());

			logger.debug("Processing business object dataxxx2");
			for(String fieldName: 
				businessObjectData.getDataValues().keySet())
			{
				// Build a field data for this field
				BusinessObjectFieldData fieldData = 
						new BusinessObjectFieldDataImpl(fieldName, 
								businessObjectData.getDataValues().get(fieldName), null);
				
				// We populate some need meta info about the field
				for(BusinessObjectFieldData businessObjectFieldData: fields){
					if(fieldName.equals(businessObjectFieldData.getFieldName())) {
						
						fieldData.setFieldDataType(
								businessObjectFieldData.getFieldDataType());
						fieldData.setRelatedBusinessObjectName(
								businessObjectFieldData.getRelatedBusinessObjectName());
					}
				}
				clone.setDataValue(fieldName, fieldData);
			}
			logger.debug("Processing business object dataxxx3");
			clone.setDataValue(BusinessObjectUtil.CREATED_BY_USR_DATA_VALUE, 
					new BusinessObjectFieldDataImpl(BusinessObjectUtil.CREATED_BY_USR_DATA_VALUE, 
							context.get(ActivityRESTService.USER_NAME), null));
			
			businessObjectData.getDataValues().remove(ActivityRESTService.BO_DATA_PROCESSOR_PARAM);
			businessObjectData.getDataValues().remove(BO_EDIT_MODE_PARAMETER);
			businessObjectCreationService.create(clone);

		}
		// TODO Auto-generated method stub
		return businessObjectData;
	}
	

}
