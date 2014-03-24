/**
 * 
 */
package com.qurion.businesslogic.application.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qurion.businesslogic.application.model.Activity;
import com.qurion.businesslogic.application.model.UiComponent;
import com.qurion.businesslogic.application.model.UiComponentAttribute;
import com.qurion.businesslogic.application.model.UiComponentType;
import com.qurion.businesslogic.application.service.ActivityService;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.application.util.EntityUtil;
import com.qurion.businesslogic.application.util.ErrorCodes;
import com.qurion.businesslogic.application.util.IntegerUtil;
import com.qurion.businesslogic.application.util.StringUtil;
import com.qurion.businesslogic.businessobject.data.BusinessObjectData;
import com.qurion.businesslogic.businessobject.data.BusinessObjectDataImpl;
import com.qurion.businesslogic.businessobject.data.BusinessObjectFieldData;
import com.qurion.businesslogic.businessobject.data.SearchData;
import com.qurion.businesslogic.businessobject.data.SearchFieldData;
import com.qurion.businesslogic.businessobject.service.BusinessObjectCreationService;
import com.qurion.businesslogic.businessobject.service.BusinessObjectSearchService;
import com.qurion.businesslogic.businessobject.service.data.BusinessObjectDataProcessorProducer;
import com.qurion.businesslogic.businessobject.service.data.BusinessObjectDataProcessorService;
import com.qurion.businesslogic.businessobject.service.data.EditFieldUiDataServiceImpl;
import com.qurion.businesslogic.businessobject.service.data.UiQueryDataService;
import com.qurion.businesslogic.businessobject.service.data.UiQueryDataServiceProducer;
import com.qurion.businesslogic.businessobject.util.BusinessObjectUtil;
import com.qurion.businesslogic.ide.service.UiComponentBuilderService;

/**
 * @author Edward Banfa
 *
 */
@Path("/activity-service")
@Stateless
public class ActivityRESTService extends AbstractRESTService {

	public static final String ACTIVITY_BO_NM = "Activity";
	public static final String ACTIVITY_URL_PARAM_NM = "activityURL";
	public static final String ACTIVITY_BO_PARAM_NM = "businessObjectName";
	public static final String ENTITY_QUERY_PARAM_NM = "activityQuery[entityQuery]";
	public static final String PROCESS_QUERY_PARAM_NM = "activityQuery[processQuery]";
	public static final String UI_QUERY_PARAMETERS_PARAM_NM = "uiDataQueryParameters";
	public static final String BO_DATA_PROCESSOR_PARAM = "businessObjectDataProcessor";
	
	public static final String USER_NAME = "userName";
	public static final String REQUEST_IP_ADDRESS = "requestIPAddress";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String SEQ = "seq";
	public static final String OPERATOR = "operator";
	private static final Object FIELD_DATA_TYPE = "fieldDataType";
	public static final String FIELD_NAME = "fieldName";
	public static final String FIELD_VALUE = "fieldValue";
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject ActivityService activityService;
	@Inject UiComponentRESTService componentRESTService;
	@Inject BusinessObjectSearchService businessObjectSearchService;
	@Inject BusinessObjectCreationService businessObjectCreationService;
	@Inject UiQueryDataServiceProducer uiQueryDataServiceProducer;
	@Inject BusinessObjectDataProcessorProducer businessObjectDataProcessorProducer;

    /**
     * @param uriInfo
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData getActivity(@Context UriInfo uriInfo) {
		logger.debug("Processing activity GET request context {}", uriInfo.getQueryParameters());
    	ActivityResponse activityResponse = new ActivityResponse();
		getActivityImpl(activityResponse, 
				processQueryParamerters(uriInfo.getQueryParameters()));
		return activityResponse;
    
    }

	/**
     * @param businessObjectData
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
	public ResponseData getActivity(ActivityRequestData activityRequestData)
	{
		logger.debug("Processing activity POST request context {}", activityRequestData);
    	ActivityResponse activityResponse = new ActivityResponse();
    	Map<String, Object> context = processRequestContext(activityRequestData);
    	BusinessObjectData businessObjectData = activityRequestData.getBusinessObjectData();
    	if(businessObjectData.getDataValues().containsKey(BO_DATA_PROCESSOR_PARAM)) 
    	{
    		Object formDataProcessorParameter = 
    				businessObjectData.getDataValues().get(BO_DATA_PROCESSOR_PARAM);
    		if(formDataProcessorParameter != null) {
        		String formDataProcessorName = formDataProcessorParameter.toString();
        		try {
					BusinessObjectDataProcessorService dataProcessorService = 
							businessObjectDataProcessorProducer.getBusinessObjectDataProcessorService(formDataProcessorName);
					dataProcessorService.processData(businessObjectData, context);
				} catch (ApplicationException e) {
					this.processRESTException(e, activityResponse);
				}
    		}
    	}
    	getActivityImpl(activityResponse, context);
		return activityResponse;
	}

	/**
	 * @param activityResponse
	 * @param context
	 */
	private void getActivityImpl(ActivityResponse activityResponse,
			Map<String, Object> context) {
		try {
			// Load the activity
			Activity activity = loadActivity(context);
			if(activity == null) 
				throw new ApplicationException(
						ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, 
						ErrorCodes.ARS_ACTIVITY_NOT_FOUND_MSG);
			
			// Load the business object for the activity
			activityResponse.setActivityData(this.activityToBusinessObject(activity));
			// Load the ui component for the activity 
			activityResponse.setUiComponentData(
					getUiComponentData(activity.getUiComponent(), context));

		} catch (ApplicationException e) {
			this.processRESTException(e, activityResponse);
		}
	}


	/**
	 * Creates a instance of {@link BusinessObjectData} and copies the
	 * data from the activity into its dataValues map
	 * 
	 * @param activity
	 * @return
	 * @throws ApplicationException
	 */
	private BusinessObjectData activityToBusinessObject(Activity activity) throws ApplicationException 
	{
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(
				new Object[]{activity}, "loadActivityBusinessObject");
		BusinessObjectData activityBusinessObjectData = 
				new BusinessObjectDataImpl(EntityUtil.ACTIVITY_BO_NM);

		List<BusinessObjectFieldData> entityFields = 
				activityService.getBusinessObjectFields(EntityUtil.ACTIVITY_BO_NM);
		BusinessObjectUtil.copyDataToBusinessObject(
				activityBusinessObjectData, activity, entityFields);
		return activityBusinessObjectData;
	}

	/**
	 * @param queryParameters
	 * @return
	 * @throws ApplicationException
	 */
	private Activity loadActivity(Map<String, Object> context) throws ApplicationException 
	{
		return activityService.findByActivityURL(this.extractActivityURL(context));
	}

	/**
	 * Extract the activityURL from the query parameters.
	 * 
	 * @param queryParameters
	 * @return
	 * @throws ApplicationException
	 */
	private String extractActivityURL(Map<String, Object> context) throws ApplicationException
	{
		if(context.containsKey(ACTIVITY_URL_PARAM_NM)){
			Object activityURL = context.get(ACTIVITY_URL_PARAM_NM);
			if(activityURL != null) return activityURL.toString();
		}
		throw new ApplicationException(
				ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, 
				ErrorCodes.ACTIVITY_WITH_URL_NOT_FOUND_ERROR_MSG);
	}
	
    /**
	 * @param uiComponentData
	 * @param uiComponent
	 * @param multivaluedMap 
	 */
	public UiComponentData getUiComponentData(UiComponent uiComponent, 
			Map<String, Object> context) throws ApplicationException 
	{
		UiComponentData uiComponentData = new UiComponentData();
		uiComponent.setCode(uiComponent.getCode());
		uiComponentData.setName(uiComponent.getName());
		uiComponentData.setSequenceNo(uiComponent.getSequenceNo());
		uiComponentData.setDescription(uiComponent.getDescription());
		uiComponentData.setType(uiComponent.getUiComponentType().getName());
		// Process the attributes of the component
		for(UiComponentAttribute attribute: uiComponent.getUiComponentAttributes())
		{
			UiComponentAttributeData uiComponentAttributeData = 
					new UiComponentAttributeData();
			uiComponentAttributeData.setName(attribute.getAttrName());
			uiComponentAttributeData.setValue(attribute.getAttrValue());
			uiComponentData.getAttributes().put(
					attribute.getAttrName(), attribute.getAttrValue());
		}
		// Recursively load all the child components
		for(UiComponent component: uiComponent.getUiComponents())
		{
			UiComponentType componentType = component.getUiComponentType();
			if(componentType.getCode().equals(UiComponentBuilderService.COMP_TY_UI_DATA_QUERY)){
				this.loadUiComponentData(component, uiComponentData, context);
			}
			else{
				uiComponentData.getComponents().add(getUiComponentData(component, context));
			}
		}
		return uiComponentData;
	}

	/**
	 * Processes the ui query data definition for a {@code UiComponentData}.
	 * There are two types of ui query data:
	 * 1. Query: This is the definition of an JPA query that will be built and executed
	 * to generated the required data
	 * 2. Service: This is a handle to an instance of {@code UiQueryDataService}, whose
	 * {@code find} method will be invoked to generate the required data.
	 * 
	 * @param uiDataQuery
	 * @param uiComponentData
	 * @throws ApplicationException
	 */
	private void loadUiComponentData(UiComponent uiDataQuery, 
			UiComponentData uiComponentData, Map<String, Object> context) throws ApplicationException 
	{
		logger.debug("Processing Ui data query {}", uiDataQuery.getName());
		String businessObjectName = "";
		String uiDataQueryType = "query";
		List<BusinessObjectData> uiDataQueryList = new ArrayList<BusinessObjectData>();
		// Loop through attributes and extract
		// query type definition and business object name
		for(UiComponentAttribute attribute : 
			uiDataQuery.getUiComponentAttributes()) 
		{
			if(attribute.getAttrName().equals("type") &&
					attribute.getAttrValue().equals("service"))	{
				uiDataQueryType = "service";
			}
			if(attribute.getAttrName().equals("businessObject"))
				businessObjectName = attribute.getAttrValue();
		}
		// Process data query service definition
		if(uiDataQueryType.equals("service")) {
			// Add the query parameters to the context
			// The parameters would be used by the data service
			SearchData searchData = 
					uiDataQueryToBOSearchData(businessObjectName, uiDataQuery, context);
			context.put(UI_QUERY_PARAMETERS_PARAM_NM, searchData);
			// Only process data query service if we
			// a valid business object name
			if(StringUtil.isValidString(businessObjectName)) {
				UiQueryDataService uiQueryDataService = 
						uiQueryDataServiceProducer.getUiQueryDataService(businessObjectName);
				uiDataQueryList = uiQueryDataService.find(context);
			}
		}
		// Process raw data query definition 
		else {
			SearchData searchData = 
					uiDataQueryToBOSearchData(businessObjectName, uiDataQuery, context);
			List<BusinessObjectFieldData> entityFields = 
					activityService.getBusinessObjectFields(searchData.getBusinessObjectName());
			uiDataQueryList = businessObjectSearchService.find(searchData, entityFields);
		}
		// Nast hack to set the Ui data query descriptor
		if(!uiDataQueryList.isEmpty())
			uiComponentData.setUiQueryDataDescriptor(uiDataQueryList.get(0).getBusinessObjectName());
		uiComponentData.setUiQueryData(uiDataQueryList);
	}


	/**
	 * @param businessObjectName2 
	 * @param uiDataQuery
	 */
	private SearchData uiDataQueryToBOSearchData(String businessObjectName, 
			UiComponent uiDataQuery, Map<String, Object> context) throws ApplicationException 
	{
		SearchData searchData = new SearchData();
		if(StringUtil.isValidString(businessObjectName))
			searchData.setBusinessObjectName(businessObjectName);

		// Process the child components (query parameters)
		for(UiComponent queryParameter : uiDataQuery.getUiComponents()) 
		{
			SearchFieldData fieldData = new SearchFieldData();
			for(UiComponentAttribute attribute: 
				queryParameter.getUiComponentAttributes())
			{
				String attributeName = attribute.getAttrName();
				if(attributeName.equals(OPERATOR) &&  
						StringUtil.isValidString(attribute.getAttrValue())){
					fieldData.setFieldSearchOperator(attribute.getAttrValue());
				}
				else if(attributeName.equals(FIELD_NAME) &&  
						StringUtil.isValidString(attribute.getAttrValue())){
					fieldData.setFieldName(attribute.getAttrValue());
				}
				else if(attributeName.equals(FIELD_DATA_TYPE) &&  
						StringUtil.isValidString(attribute.getAttrValue())){
					fieldData.setFieldDataTypeName(attribute.getAttrValue());
				}
				else if(attributeName.equals(FIELD_VALUE) && 
						StringUtil.isValidString(attribute.getAttrValue()))
				{
					String attributeValue = attribute.getAttrValue();
					// If the attribute value contains a colon, then it
					// is a reference to a context variable, 
					if(attributeValue.contains(StringUtil.COLON)){
						// Remove the colon
						attributeValue = attributeValue.replace(
								StringUtil.COLON, StringUtil.EMPTY_STRING);
						// TODO: This has to be improved
						fieldData.setFieldValue(
								context.get(attributeValue).toString());
					}
					else {
						fieldData.setFieldValue(attributeValue);
					}
				}
				else if(attributeName.equals(SEQ) |
						attributeName.equals(NAME)|
						attributeName.equals(DESCRIPTION)){
				}
				else
					throw new ApplicationException(
							ErrorCodes.ARS_ACTIVITY_NOT_FOUND_CD, 
							ErrorCodes.ARS_INVALID_QUERY_PARAM_MSG);
			}
			// Only add the field data if we have a valid field name
			if(StringUtil.isValidString(fieldData.getFieldName()))
				searchData.addField(fieldData.getFieldName(), fieldData);
		}
		return searchData;
	}

    
    private Map<String, Object> processQueryParamerters(
    		MultivaluedMap<String, String> queryParameters) {
    	Map<String, Object> context = new HashMap<String, Object>();
    	for(String key: queryParameters.keySet()){
    		context.put(key, queryParameters.getFirst(key));
    	}
		return context;
	}

	/**
	 * @param activityRequestData
	 * @return
	 */
	private Map<String, Object> processRequestContext(
			ActivityRequestData activityRequestData) {
		Map<String, Object> context = new HashMap<String, Object>();
		BusinessObjectData businessObjectData = 
				activityRequestData.getBusinessObjectData();
		
		//businessObjectData.setBusinessObjectName(activityRequestData.);
    	context.put(USER_NAME, activityRequestData.getUserName());
    	context.put(REQUEST_IP_ADDRESS, activityRequestData.getRequestIPAddress());
    	context.put(ACTIVITY_URL_PARAM_NM, activityRequestData.getActivityURL());
    	context.put(ACTIVITY_BO_PARAM_NM, businessObjectData);
    	context.put(
    			EditFieldUiDataServiceImpl.BUSINESS_OBJECT_NAME_UI_QUERY_PARAM,
    			businessObjectData.getBusinessObjectName());
    	if(businessObjectData.getId() != null)
        	context.put(
        			EditFieldUiDataServiceImpl.BUSINESS_OBJECT_ID_UI_QUERY_PARAM,
        			businessObjectData.getId());
		return context;
	}
	/**
     * Converts all the data fields from a map of field attributes
     * into a map of {@code BusinessObjectFieldData}.
     * 
     * @param businessObjectData the business object data we are converting its
     * data values
     * @return thebusiness object data with its data values converted
     * @throws ApplicationException If an exception occured
     *//*
    private BusinessObjectData dataValuesToFieldData(BusinessObjectData businessObjectData) throws ApplicationException {
    	Map<String, Object> dataValues = businessObjectData.getDataValues();
    	Map<String, Object> fieldDataValues = new HashMap<String, Object>();
    	for(String fieldName : dataValues.keySet()){
    		BusinessObjectFieldData fieldData = new BusinessObjectFieldDataImpl();
    		Map<String, Object> fieldInfo = (Map<String, Object>) dataValues.get(fieldName);
    		for(String fieldAttribute : fieldInfo.keySet())
    		{
				if(fieldInfo.get(fieldAttribute) != null) 
				{
	    			if(fieldAttribute.equals(FIELD_NAME))
	    				fieldData.setFieldName(
	    						fieldInfo.get(fieldAttribute).toString());
	    			if(fieldAttribute.equals("required"))
	    				fieldData.setRequired(
	    						Boolean.parseBoolean(fieldInfo.get(fieldAttribute).toString()));
	    			if(fieldAttribute.equals(FIELD_VALUE))
	    				fieldData.setFieldValue(fieldInfo.get(fieldAttribute));
	    			if(fieldAttribute.equals("fieldText"))
	    				fieldData.setFieldText(fieldInfo.get(fieldAttribute));
	    			if(fieldAttribute.equals("fieldDataType"))
	    				fieldData.setFieldDataType(
	    						fieldInfo.get(fieldAttribute).toString());
	    			if(fieldAttribute.equals("fieldSequence")){
							try {
								fieldData.setFieldSequence(
										Integer.valueOf(fieldInfo.get(fieldAttribute).toString()));
							} catch (NumberFormatException e) {
								e.printStackTrace();
							}
	    			}
	    			if(fieldAttribute.equals("fieldDescription"))
	    				fieldData.setFieldDescription(
	    						fieldInfo.get(fieldAttribute).toString());
	    			if(fieldAttribute.equals("relatedBusinessObjectName"))
	    				fieldData.setRelatedBusinessObjectName(
	    						fieldInfo.get(fieldAttribute).toString());
				}
    		}
    		fieldDataValues.put(fieldName, fieldData);
    	}
    	businessObjectData.setDataValues(fieldDataValues);
    	return businessObjectData;
    }*/
}
