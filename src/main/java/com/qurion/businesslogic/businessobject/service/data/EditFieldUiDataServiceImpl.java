/**
 * 
 */
package com.qurion.businesslogic.businessobject.service.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qurion.businesslogic.application.model.Activity;
import com.qurion.businesslogic.application.model.EntityData;
import com.qurion.businesslogic.application.rest.ActivityRESTService;
import com.qurion.businesslogic.application.service.ActivityService;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.application.util.BooleanUtil;
import com.qurion.businesslogic.application.util.EntityUtil;
import com.qurion.businesslogic.application.util.ErrorCodes;
import com.qurion.businesslogic.application.util.IntegerUtil;
import com.qurion.businesslogic.application.util.StringUtil;
import com.qurion.businesslogic.businessobject.annotation.BusinessObjectEditFieldUiDataService;
import com.qurion.businesslogic.businessobject.data.BusinessObjectData;
import com.qurion.businesslogic.businessobject.data.BusinessObjectDataImpl;
import com.qurion.businesslogic.businessobject.data.BusinessObjectFieldData;
import com.qurion.businesslogic.businessobject.data.SearchData;
import com.qurion.businesslogic.businessobject.data.SearchFieldData;
import com.qurion.businesslogic.businessobject.service.BusinessObjectSearchService;
import com.qurion.businesslogic.businessobject.util.BusinessObjectUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
@BusinessObjectEditFieldUiDataService
public class EditFieldUiDataServiceImpl implements UiQueryDataService {

	@Inject ActivityService activityService;
	@Inject private EntityManager entityManager;
	@Inject BusinessObjectSearchService businessObjectSearchService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	public static final String EDIT = "EDIT";
	public static final String VIEW = "VIEW";
	public static final String SEARCH = "SEARCH";
	public static final String CREATE = "CREATE";
	public static final String DELETE = "DELETE";
	public static final String QUERY = "select e from Activity e where id = ";
	public static final String ACTIVITY_ID_UI_QUERY_PARAM = "uiDataQuery[activityId]";
	public static final String BUSINESS_OBJECT_ID_UI_QUERY_PARAM = "uiDataQuery[businessObjectId]";
	public static final String BUSINESS_OBJECT_NAME_UI_QUERY_PARAM = "uiDataQuery[businessObjectName]";
	public static final String RELATIONSHIP_FIELD_OPTIONS = "businessObjectRelationshipFieldOptions";

	
	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.businessobject.service.UiQueryDataService#find(javax.ws.rs.core.MultivaluedMap)
	 */
	@Override
	public List<BusinessObjectData> find(Map<String, Object> context)
			throws ApplicationException 
	{
		logger.debug("Processing context {}", context);
		List<BusinessObjectData> dataList = new ArrayList<BusinessObjectData>();
		
		EntityData entityData = null;
		String businessObjectEditMode = getBusinessObjectEditEmode(context);
		
		if(context.containsKey(ACTIVITY_ID_UI_QUERY_PARAM))		
			entityData = getEntityDataByActivityId(
					context, entityData, businessObjectEditMode);

		else if(context.containsKey(BUSINESS_OBJECT_NAME_UI_QUERY_PARAM))		
			entityData = getEntityDataByBusinessObjectName(
					context, entityData, businessObjectEditMode);
		else
			throw new ApplicationException(
					ErrorCodes.EFUDS_FIND_BO_ERROR_CD,
					ErrorCodes.EFUDS_INVALID_ID_UI_QUERY_PARAM_ERROR_MSG);
			
		// Get business object with fields defined by the
		// edit mode
		BusinessObjectData editModeBusinessObject = 
				getBusinessObjectEditFields(businessObjectEditMode, entityData, context);
		
		dataList.add(editModeBusinessObject);
		
		return dataList;
	}

	/**
	 * @param context
	 * @param entityData
	 * @param businessObjectEditMode
	 * @return
	 * @throws ApplicationException
	 */
	@SuppressWarnings("unchecked")
	private EntityData getEntityDataByActivityId(Map<String, Object> context,
			EntityData entityData, String businessObjectEditMode)
			throws ApplicationException 
	{
		// 1. Get the entity id
		Integer activityId = IntegerUtil.toInteger(
				context.get(ACTIVITY_ID_UI_QUERY_PARAM));
		// 2. Compose query and get the activity mode
		String queryString = QUERY.concat(activityId.toString());
		
		// 3. Get the fields of the business object and convert to required format
		Query query = entityManager.createQuery(queryString);
		List<Activity> activityList = 
				(List<Activity>) query.getResultList();
		
		if(!activityList.isEmpty())
		{
			entityData = activityList.get(0).getEntityData();
		}
		return entityData;
	}

	/**
	 * @param context
	 * @param entityData
	 * @param businessObjectEditMode
	 * @return
	 * @throws ApplicationException
	 */
	private EntityData getEntityDataByBusinessObjectName(
			Map<String, Object> context, EntityData entityData,
			String businessObjectEditMode) throws ApplicationException {
		
		Object businessObjectNameObj = 
				context.get(BUSINESS_OBJECT_NAME_UI_QUERY_PARAM);
		
		if(businessObjectNameObj == null)
			throw new ApplicationException(
					ErrorCodes.EFUDS_FIND_BO_ERROR_CD,
					ErrorCodes.EFUDS_INVALID_BUSINESS_OBJECT_NAME_UI_QUERY_PARAM_ERROR_MSG);
		
		String businessObjectName = businessObjectNameObj.toString();
		if(!StringUtil.isValidString(businessObjectName))
			throw new ApplicationException(
					ErrorCodes.EFUDS_FIND_BO_ERROR_CD,
					ErrorCodes.EFUDS_INVALID_BUSINESS_OBJECT_NAME_UI_QUERY_PARAM_ERROR_MSG);
		
		return activityService.getEntityData(businessObjectName);
	}


	/**
	 * @param businessObjectData
	 * @param businessObjectEditMode
	 * @param entityData
	 * @return 
	 * @throws ApplicationException
	 */
	private BusinessObjectData getBusinessObjectEditFields(
			String businessObjectEditMode, 
			EntityData entityData, Map<String, Object> context)	throws ApplicationException 
	{
		BusinessObjectData businessObjectData = new BusinessObjectDataImpl();
		if(entityData != null)
		{
			businessObjectData.setBusinessObjectName(entityData.getName());
			businessObjectData.setBusinessObjectClassName(entityData.getEntityClassNm());
			// Get the fields for the edit mode
			List<BusinessObjectFieldData> businessObjectFields = 
					getBusinessObjectEditFields(businessObjectEditMode, entityData);
			// Set the field data as a data value for the business object
			for(BusinessObjectFieldData field: businessObjectFields) 
			{
				// Ignore the universe field
				if(field.getFieldName().equals(
						BusinessObjectFormDataProcessorServiceImpl.UNIVERSE_FIELD_NM))
					continue;
				businessObjectData.setDataValue(field.getFieldName(), field);
			}

			// If an business object id was specified then we look
			// the instance of the business object and copy its 
			// data to the business object we intend to return
			if(context.containsKey(BUSINESS_OBJECT_ID_UI_QUERY_PARAM))		
				copyInstanceData(entityData, 
						context, businessObjectData, businessObjectFields);

			if(context.containsKey(ActivityRESTService.UI_QUERY_PARAMETERS_PARAM_NM))
				this.processUiDataQueryParameters(businessObjectData, context);
		}
		return businessObjectData;
	}

	private void processUiDataQueryParameters(BusinessObjectData businessObjectData, 
			Map<String, Object> context) throws ApplicationException 
	{
		SearchData uiDataQueryParams = (SearchData) 
				context.get(ActivityRESTService.UI_QUERY_PARAMETERS_PARAM_NM);
		Map<String, SearchFieldData> uiDataQueryParamsMap = uiDataQueryParams.getSearchFields();
		
		if(uiDataQueryParamsMap.containsKey(RELATIONSHIP_FIELD_OPTIONS)){
			SearchFieldData uiDataQueryParameter = 
					uiDataQueryParamsMap.get(RELATIONSHIP_FIELD_OPTIONS);
			if(uiDataQueryParameter.getFieldValue().equals(BooleanUtil.BOOLEAN_TRUE))
				this.loadOptionsForRelationshipField(businessObjectData, context);
		}
	}

	private void loadOptionsForRelationshipField(BusinessObjectData businessObjectData, 
			Map<String, Object> context) throws ApplicationException 
	{
		logger.debug("Loading field options for related field {}");
		Map<String, Object> dataValues = businessObjectData.getDataValues();
	
		for(String fieldName: dataValues.keySet())
		{
			BusinessObjectFieldData field = 
					(BusinessObjectFieldData) dataValues.get(fieldName);
			if(field.getFieldDataType().equals(
					EntityUtil.RELATIONSHIP_FIELD_CODE))
			{
				String relatedBusinessObjectName = 
						field.getRelatedBusinessObjectName();
				if(StringUtil.isValidString(relatedBusinessObjectName))
				{
					logger.debug("Loading field options for related field {}", relatedBusinessObjectName);
					List<BusinessObjectFieldData> fieldsWanted =
							activityService.getBusinessObjectListFields(relatedBusinessObjectName);
					List<BusinessObjectData> fieldOptions = 
							businessObjectSearchService.findAll(relatedBusinessObjectName, fieldsWanted);
					String businessObjectName = businessObjectData.getBusinessObjectName();
					// The unfortunate situation where we have to loop and remove
					// any possiblity of an instance appearing as an option for a one
					//of its fields
					if(businessObjectData.getId() != null &&
							businessObjectName.equals(relatedBusinessObjectName))
					{
						BusinessObjectData option = null;
						for(BusinessObjectData fieldOption: fieldOptions){
							if(businessObjectData.getId() == fieldOption.getId()){
								option = fieldOption;
							}
						}
						if(option != null)
							fieldOptions.remove(option);
					}
					field.setFieldOptions(
							BusinessObjectUtil.prepareListableItems(fieldOptions));
				}
			}
		}
	}

	/**
	 * @param businessObjectEditMode
	 * @param entityData
	 * @return
	 * @throws ApplicationException
	 */
	private List<BusinessObjectFieldData> getBusinessObjectEditFields(
			String businessObjectEditMode, EntityData entityData)
			throws ApplicationException {
		List<BusinessObjectFieldData> businessObjectFields = null;
		
		if(businessObjectEditMode.equals(VIEW)){
			businessObjectFields = 
				activityService.getBusinessObjectViewFields(entityData.getName());
		}
		else if(businessObjectEditMode.equals(EDIT)){
			businessObjectFields = 
					activityService.getBusinessObjectEditFields(entityData.getName());
		}
		else if(businessObjectEditMode.equals(CREATE)){
			businessObjectFields = 
					activityService.getBusinessObjectCreateFields(entityData.getName());
		}
		else if(businessObjectEditMode.equals(DELETE)){
			businessObjectFields = 
				activityService.getBusinessObjectDeleteFields(entityData.getName());
		}
		else if(businessObjectEditMode.equals(SEARCH)){
			businessObjectFields = 
				activityService.getBusinessObjectSearchFields(entityData.getName());
		}
		else{
			businessObjectFields = 
					activityService.getBusinessObjectEditFields(entityData.getName());
		}
		return businessObjectFields;
	}

	/**
	 * @param entityData
	 * @param context
	 * @param businessObjectData
	 * @param businessObjectFields
	 * @throws ApplicationException
	 */
	private void copyInstanceData(EntityData entityData,
			Map<String, Object> context, BusinessObjectData businessObjectData,
			List<BusinessObjectFieldData> businessObjectFields)
			throws ApplicationException {
		// 1. Get the entity id
		Integer businessObjectId = 
				IntegerUtil.toInteger(context.get(BUSINESS_OBJECT_ID_UI_QUERY_PARAM));
		BusinessObjectData businessObjectDataInstance = 				 
					businessObjectSearchService.findById(
							entityData.getName(), businessObjectId, businessObjectFields);

		businessObjectData.setId(businessObjectDataInstance.getId());
		Map<String, Object> businessObjectDataValues = businessObjectData.getDataValues();
		Map<String, Object> instanceDataValues = businessObjectDataInstance.getDataValues();
		
		for(String fieldName: instanceDataValues.keySet()) 
		{
			BusinessObjectFieldData instanceFieldData = 
					(BusinessObjectFieldData)instanceDataValues.get(fieldName);
			if(businessObjectDataValues.containsKey(fieldName))
			{
				BusinessObjectFieldData fieldData = 
						(BusinessObjectFieldData)businessObjectDataValues.get(fieldName);
				fieldData.setFieldValue(instanceFieldData.getFieldValue());
			}
		}
	}


	/**
	 * @param context
	 * @return
	 * @throws ApplicationException
	 */
	private String getBusinessObjectEditEmode(Map<String, Object> context)
			throws ApplicationException 
	{
		SearchData queryParameters = null;
		if(context.containsKey(ActivityRESTService.UI_QUERY_PARAMETERS_PARAM_NM))
			queryParameters = (SearchData) 
				context.get(ActivityRESTService.UI_QUERY_PARAMETERS_PARAM_NM);

		Object activityModeField = 
				queryParameters.getSearchFields().get("businessObjectEditMode");
		if(activityModeField == null)
			throw new ApplicationException(
					ErrorCodes.EFUDS_FIND_BO_ERROR_CD,
					ErrorCodes.EFUDS_INVALID_ACTIVITY_MODE_ERROR_MSG);
		
		SearchFieldData fieldData = (SearchFieldData) activityModeField;
		if(!StringUtil.isValidString(fieldData.getFieldValue()))
			throw new ApplicationException(
					ErrorCodes.EFUDS_FIND_BO_ERROR_CD,
					ErrorCodes.EFUDS_INVALID_ACTIVITY_MODE_ERROR_MSG);
		String businessObjectEditMode = fieldData.getFieldValue();
		return businessObjectEditMode;
	}

}
