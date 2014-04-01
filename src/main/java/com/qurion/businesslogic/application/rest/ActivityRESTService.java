/**
 * 
 */
package com.qurion.businesslogic.application.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.qurion.businesslogic.application.rest.data.ActivityRequestData;
import com.qurion.businesslogic.application.rest.data.ActivityResponse;
import com.qurion.businesslogic.application.rest.data.ResponseData;
import com.qurion.businesslogic.application.rest.data.UiComponentAttributeData;
import com.qurion.businesslogic.application.rest.data.UiComponentData;
import com.qurion.businesslogic.application.rest.data.UiComponentQueryConfigData;
import com.qurion.businesslogic.application.service.ActivityService;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.application.util.EntityUtil;
import com.qurion.businesslogic.application.util.ErrorCodes;
import com.qurion.businesslogic.application.util.StringUtil;
import com.qurion.businesslogic.businessobject.data.BusinessObjectData;
import com.qurion.businesslogic.businessobject.data.BusinessObjectDataImpl;
import com.qurion.businesslogic.businessobject.data.BusinessObjectFieldData;
import com.qurion.businesslogic.businessobject.data.SearchData;
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
public class ActivityRESTService extends AbstractRESTService 
{
	
	public static final String ACTIVITY_BO_NM = "Activity";
	public static final String ACTIVITY_URL_PARAM_NM = "activityURL";
	public static final String ACTIVITY_BO_PARAM_NM = "businessObjectName";
	public static final String ENTITY_QUERY_PARAM_NM = "activityQuery[entityQuery]";
	public static final String PROCESS_QUERY_PARAM_NM = "activityQuery[processQuery]";
	public static final String UI_QUERY_PARAMETERS_PARAM_NM = "uiDataQueryParameters";
	public static final String BO_DATA_PROCESSOR_PARAM = "businessObjectDataProcessor";

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
    public ResponseData getActivity(@Context UriInfo uriInfo) 
    {
		logger.debug("Processing activity GET "
				+ "request context {}", uriInfo.getQueryParameters());
		try {
			Map<String, Object> context = 
	    			queryParamertersToContext(uriInfo.getQueryParameters());
	    	this.validateContext(context);
			return getActivityImpl(context);
		} catch (ApplicationException e) {
			this.processRESTException(e, new ActivityResponse());
		}
    	return null;
    }

	/**
     * @param businessObjectData
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
	public ResponseData getActivity(ActivityRequestData activityRequestData)
	{
		logger.debug("Processing activity POST request context {}", activityRequestData);
    	try {
        	Map<String, Object> context = 
        			requestDataToContext(activityRequestData);
        	// Validate the context and 
        	// process business object data
        	this.validateContext(context);
        	processBoDataProcessorParams(context, 
        			activityRequestData.getBusinessObjectData());
    		return getActivityImpl(context);
		} catch (ApplicationException e) {
			this.processRESTException(e, new ActivityResponse());
		}
    	return null;
	}

	private void validateContext(Map<String, Object> context) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param activityResponse
	 * @param context
	 * @return 
	 * @throws ApplicationException 
	 */
	private ResponseData getActivityImpl(Map<String, Object> context) 
			throws ApplicationException 
	{
		// Load the activity
		Activity activity = loadActivity(context);
		if(activity == null) 
			throw new ApplicationException(
					ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, 
					ErrorCodes.ARS_ACTIVITY_NOT_FOUND_MSG);
		ActivityResponse activityResponse = new ActivityResponse();
		// Load the business object for the activity
		activityResponse.setActivityData(this.activityToBusinessObject(activity));
		// Load the ui component for the activity 
		activityResponse.setUiComponentData(
				getUiComponentData(activity.getUiComponent(), context));
		return activityResponse;
	}

	/**
	 * @param activityResponse
	 * @param context
	 * @param businessObjectData
	 * @throws ApplicationException 
	 */
	private void processBoDataProcessorParams(Map<String, Object> context,
			BusinessObjectData businessObjectData) throws ApplicationException 
	{
		if(businessObjectData.getDataValues().containsKey(BO_DATA_PROCESSOR_PARAM)) 
    	{
    		Object formDataProcessorParameter = 
    				businessObjectData.getDataValues().get(BO_DATA_PROCESSOR_PARAM);
    		if(formDataProcessorParameter != null) {
        		String formDataProcessorName = formDataProcessorParameter.toString();
				BusinessObjectDataProcessorService dataProcessorService = 
						businessObjectDataProcessorProducer.getBusinessObjectDataProcessorService(formDataProcessorName);
				dataProcessorService.processData(businessObjectData, context);
    		}
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
		UiComponentData uiComponentData = getUiComponentData(uiComponent);
		getUiComponentDataAttributes(uiComponent, uiComponentData);
		// Recursively load all the child components
		getChildUiComponentData(uiComponent, context, uiComponentData);
		return uiComponentData;
	}

	/**
	 * @param uiComponent
	 * @return
	 */
	private UiComponentData getUiComponentData(UiComponent uiComponent) 
	{
		UiComponentData uiComponentData = new UiComponentData();
		uiComponent.setCode(uiComponent.getCode());
		uiComponentData.setName(uiComponent.getName());
		uiComponentData.setSequenceNo(uiComponent.getSequenceNo());
		uiComponentData.setDescription(uiComponent.getDescription());
		uiComponentData.setType(uiComponent.getUiComponentType().getName());
		return uiComponentData;
	}

	/**
	 * @param uiComponent
	 * @param uiComponentData
	 */
	private void getUiComponentDataAttributes(UiComponent uiComponent,
			UiComponentData uiComponentData) {
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
	}
	
	/**
	 * @param uiComponent
	 * @param context
	 * @param uiComponentData
	 * @throws ApplicationException
	 */
	private void getChildUiComponentData(UiComponent uiComponent,
			Map<String, Object> context, UiComponentData uiComponentData)
			throws ApplicationException {
		for(UiComponent component: uiComponent.getUiComponents())
		{
			UiComponentType componentType = component.getUiComponentType();
			if(componentType.getCode().equals(UiComponentBuilderService.COMP_TY_UI_DATA_QUERY)){
				this.getUiComponentQueryData(component, uiComponentData, context);
			}
			else{
				uiComponentData.getComponents().add(getUiComponentData(component, context));
			}
		}
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
	private void getUiComponentQueryData(UiComponent uiDataQuery, 
			UiComponentData uiComponentData, Map<String, Object> context) throws ApplicationException 
	{
		logger.debug("Processing Ui data query {}", uiDataQuery.getName());
		// Get the query configuration
		UiComponentQueryConfigData data = getUiComponentQueryConfigData(uiDataQuery);
		List<BusinessObjectData> uiDataQueryList = new ArrayList<BusinessObjectData>();
		// Process data query service definition
		if(data.getDataQueryType().equals(
				UiComponentQueryConfigData.DATA_QUERY_TY_SERVICE)) {
			uiDataQueryList = executeUiDataQueryService(
					uiDataQuery, context, uiDataQueryList, data.getBusinessObjectName());
		}
		// Process raw data query definition 
		else {
			uiDataQueryList = 
					executeUiDataQuery(uiDataQuery, context, data.getBusinessObjectName());
		}
		// Nast hack to set the Ui data query descriptor
		if(!uiDataQueryList.isEmpty())
			uiComponentData.setUiQueryDataDescriptor(uiDataQueryList.get(0).getBusinessObjectName());
		uiComponentData.setUiQueryData(uiDataQueryList);
	}

	/**
	 * @param uiDataQuery
	 * @param context
	 * @param businessObjectName
	 * @return
	 * @throws ApplicationException
	 */
	private List<BusinessObjectData> executeUiDataQuery(
			UiComponent uiDataQuery, Map<String, Object> context,
			String businessObjectName) throws ApplicationException 
	{
		List<BusinessObjectData> uiDataQueryList;
		SearchData searchData = 
				uiDataQueryToBOSearchData(businessObjectName, uiDataQuery, context);
		List<BusinessObjectFieldData> entityFields = 
				activityService.getBusinessObjectFields(searchData.getBusinessObjectName());
		uiDataQueryList = businessObjectSearchService.find(searchData, entityFields);
		return uiDataQueryList;
	}

	/**
	 * @param uiDataQuery
	 * @param context
	 * @param uiDataQueryList
	 * @param businessObjectName
	 * @return
	 * @throws ApplicationException
	 */
	private List<BusinessObjectData> executeUiDataQueryService(
			UiComponent uiDataQuery, Map<String, Object> context,
			List<BusinessObjectData> uiDataQueryList, String businessObjectName)
			throws ApplicationException 
	{
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
		return uiDataQueryList;
	}

	/**
	 * @param uiDataQuery
	 * @return
	 */
	private UiComponentQueryConfigData getUiComponentQueryConfigData(
			UiComponent uiDataQuery) 
	{
		UiComponentQueryConfigData data = 
				new UiComponentQueryConfigData();
		data.setDataQueryType(UiComponentQueryConfigData.DATA_QUERY_TY_QUERY);
		// Loop through attributes and extract
		// query type definition and business object name
		for(UiComponentAttribute attribute : 
			uiDataQuery.getUiComponentAttributes()) 
		{
			if(attribute.getAttrName().equals("type") &&
					attribute.getAttrValue().equals(
							UiComponentQueryConfigData.DATA_QUERY_TY_SERVICE))	
			{
				data.setDataQueryType(UiComponentQueryConfigData.DATA_QUERY_TY_SERVICE);
			}
			if(attribute.getAttrName().equals("businessObject"))
				data.setBusinessObjectName(attribute.getAttrValue());
		}
		return data;
	}


	/**
	 * @param businessObjectName2 
	 * @param uiDataQuery
	 */
	private SearchData uiDataQueryToBOSearchData(String businessObjectName, 
			UiComponent uiDataQuery, Map<String, Object> context) throws ApplicationException 
	{
		return ActivityRESTUtil.uiDataQueryToBOSearchData(
				businessObjectName, uiDataQuery, context);
	}

    
    /**
     * @param queryParameters
     * @return
     */
    private Map<String, Object> queryParamertersToContext(
    		MultivaluedMap<String, String> queryParameters) 
    {
    	Map<String, Object> context = 
    			new HashMap<String, Object>();
    	// Loop through and set strings into context map as objects
    	for(String key: queryParameters.keySet()) {
    		context.put(key, queryParameters.getFirst(key));
    	}
		return context;
	}

	/**
	 * @param activityRequestData
	 * @return
	 */
	private Map<String, Object> requestDataToContext(
			ActivityRequestData activityRequestData) 
	{
		Map<String, Object> context = new HashMap<String, Object>();
		BusinessObjectData businessObjectData = 
				activityRequestData.getBusinessObjectData();
    	context.put(ActivityRESTUtil.USER_NAME, activityRequestData.getUserName());
    	context.put(ActivityRESTUtil.REQUEST_IP_ADDRESS, activityRequestData.getRequestIPAddress());
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
}
