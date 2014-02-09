/**
 * 
 */
package com.qurion.businesslogic.application.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qurion.businesslogic.application.model.Activity;
import com.qurion.businesslogic.application.model.EntityData;
import com.qurion.businesslogic.application.model.EntityField;
import com.qurion.businesslogic.application.model.Module;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.application.util.EntityUtil;
import com.qurion.businesslogic.application.util.ErrorCodes;
import com.qurion.businesslogic.application.util.ExceptionUtil;
import com.qurion.businesslogic.application.util.StringUtil;
import com.qurion.businesslogic.businessobject.data.BusinessObjectData;
import com.qurion.businesslogic.businessobject.data.BusinessObjectDataImpl;
import com.qurion.businesslogic.businessobject.data.BusinessObjectFieldData;
import com.qurion.businesslogic.businessobject.data.BusinessObjectFieldDataImpl;
import com.qurion.businesslogic.businessobject.util.BusinessObjectUtil;

/**
 * @author Edward Banfa
 * 
 * TODO: All the methods in actually just do the same
 * thing. Need to implement more specific methods for the
 * each of the use case scenarios (list, view and edit)
 */
@Stateless
public class ActivityServiceImpl implements ActivityService {
	
	@Inject EntityDataEntityService entityService;
	@Inject ActivityEntityService activityEntityService;
	@Inject ModuleEntityService applicationModuleService;
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.process.ActivityService#getEntityListFields(java.lang.String)
	 */
	@Override
	public List<BusinessObjectFieldData> getEntityListFields(String entityName)
			throws ApplicationException 
	{
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(
				new Object[]{entityName}, "ActivityServiceImpl.getEntityListFields");
		
		List<BusinessObjectFieldData> fieldDataList = new ArrayList<BusinessObjectFieldData>();
		EntityData entity = entityService.findByName(entityName);
		EntityUtil.returnOrThrowIfNull(entity, 
				ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, 
				ErrorCodes.AS_ENTITY_NOT_FOUND_ERROR_MSG);
		// Get all the fields of the entity
		for(EntityField entityField : entity.getEntityFieldsForEntityId()){
			BusinessObjectFieldDataImpl fieldData = new  BusinessObjectFieldDataImpl();
			fieldData.setFieldName(entityField.getName());
			fieldData.setFieldDescription(entityField.getDescription());
			fieldData.setFieldDataType(entityField.getEntityFieldType().getCode());
			fieldData.setFieldSequence(entityField.getSequenceNo());
			if(EntityUtil.isRelationshipField(entityField) && entityField.getEntityDataByRelatedEntityId() != null)
				fieldData.setRelatedBusinessObjectName(entityField.getEntityDataByRelatedEntityId().getName());
			// Check required flag
			if(StringUtil.flagToBoolean(entityField.getRequiredFg()))
				fieldData.setRequired(true);
			else
				fieldData.setRequired(false);
				
			fieldDataList.add(fieldData);
		}
		logger.debug("Loaded {} fields for entity {}", fieldDataList.size(), entityName);
		return fieldDataList;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.process.ActivityService#getEntityEditFields(java.lang.String)
	 */
	@Override
	public List<BusinessObjectFieldData> getEntityEditFields(String entityName)
			throws ApplicationException 
	{
		return this.getEntityListFields(entityName);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.process.ActivityService#getEntityViewFields(java.lang.String)
	 */
	@Override
	public List<BusinessObjectFieldData> getEntityViewFields(String entityName)
			throws ApplicationException 
	{
		return this.getEntityListFields(entityName);
	}
	
	public List<BusinessObjectFieldData> getEntitySearchFields(String entityName)
			throws ApplicationException 
	{
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(
				new Object[]{entityName}, "ActivityServiceImpl.getEntityListFields");
		
		List<BusinessObjectFieldData> fieldDataList = new ArrayList<BusinessObjectFieldData>();
		EntityData entity = entityService.findByName(entityName);
		EntityUtil.returnOrThrowIfNull(entity, 
				ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, 
				ErrorCodes.AS_ENTITY_NOT_FOUND_ERROR_MSG);
		// Get all the fields of the entity
		for(EntityField entityField : entity.getEntityFieldsForEntityId()) 
		{
			if(StringUtil.flagToBoolean(entityField.getSearchFieldFg())) 
			{
				BusinessObjectFieldDataImpl fieldData = new  BusinessObjectFieldDataImpl();
				fieldData.setFieldName(entityField.getName());
				fieldData.setFieldDescription(entityField.getDescription());
				fieldData.setFieldDataType(entityField.getEntityFieldType().getCode());
				fieldData.setFieldSequence(entityField.getSequenceNo());
				// Check required flag
				if(StringUtil.flagToBoolean(entityField.getRequiredFg()))
					fieldData.setRequired(true);
				else
					fieldData.setRequired(false);
					
				fieldDataList.add(fieldData);
			}
		}
		logger.debug("Loaded {} fields for entity {}", fieldDataList.size(), entityName);
		return fieldDataList;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.process.ActivityService#findByActivityURL(java.lang.String)
	 */
	@Override
	public Activity findByActivityURL(String activityURL)
			throws ApplicationException {
		return activityEntityService.findByActivityURL(activityURL);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.process.ActivityService#getUserModules(java.lang.String)
	 */
	@Override
	public List<BusinessObjectData> getUserModules(String userName)
			throws ApplicationException 
	{
		List<BusinessObjectData> moduleBusinessObjects = new ArrayList<BusinessObjectData> ();
		try {
			List<Module> modules = applicationModuleService.findAll(null);
			for(Module module: modules){

				List<BusinessObjectFieldData> entityListFields = getEntityListFields("ApplicationModule");
				BusinessObjectData businessObjectData = new BusinessObjectDataImpl();
				BusinessObjectUtil.copyDataToBusinessObject(businessObjectData, module, entityListFields);
				moduleBusinessObjects.add(businessObjectData);
			}
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.AS_USER_MODULE_LOOKUP_ERROR_CD, e.getMessage());
		}
		return moduleBusinessObjects;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.application.service.process.ActivityService#getUserActivities(java.lang.String)
	 */
	@Override
	public List<BusinessObjectData> getUserActivities(String userName)
			throws ApplicationException {
		List<BusinessObjectData> activitiesBusinessObject = new ArrayList<BusinessObjectData> ();
		
		return activitiesBusinessObject;
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.application.service.ActivityService#isListActivity(com.qurion.businesslogic.application.model.Activity)
	 */
	@Override
	public boolean isListActivity(Activity activity) {
		if(activity.getActivityType().getCode().equals(ActivityEntityService.LIST_ACTIVITY))
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.application.service.ActivityService#isViewActivity(com.qurion.businesslogic.application.model.Activity)
	 */
	@Override
	public boolean isViewActivity(Activity activity) {
		if(activity.getActivityType().getCode().equals(ActivityEntityService.VIEW_ACTIVITY))
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.application.service.ActivityService#isEditActivity(com.qurion.businesslogic.application.model.Activity)
	 */
	@Override
	public boolean isEditActivity(Activity activity) {
		if(activity.getActivityType().getCode().equals(ActivityEntityService.EDIT_ACTIVITY))
			return true;
		return false;
	}

}
