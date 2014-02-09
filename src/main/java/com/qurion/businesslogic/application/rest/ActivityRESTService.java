/**
 * 
 */
package com.qurion.businesslogic.application.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qurion.businesslogic.application.model.Activity;
import com.qurion.businesslogic.application.model.EntityData;
import com.qurion.businesslogic.application.service.ActivityService;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.application.util.EntityUtil;
import com.qurion.businesslogic.application.util.ErrorCodes;
import com.qurion.businesslogic.application.util.MultivaluedHashMap;
import com.qurion.businesslogic.application.util.StringUtil;
import com.qurion.businesslogic.businessobject.data.BusinessObjectData;
import com.qurion.businesslogic.businessobject.data.BusinessObjectDataImpl;
import com.qurion.businesslogic.businessobject.data.BusinessObjectFieldData;
import com.qurion.businesslogic.businessobject.data.SearchData;
import com.qurion.businesslogic.businessobject.service.BusinessObjectSearchService;
import com.qurion.businesslogic.businessobject.util.BusinessObjectRESTUtil;
import com.qurion.businesslogic.businessobject.util.BusinessObjectUtil;

/**
 * @author Edward Banfa
 *
 */
@Path("/activity-service")
@Stateless
public class ActivityRESTService extends AbstractRESTService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Inject ActivityService activityService;
	@Inject UiComponentRESTService componentRESTService;
	@Inject BusinessObjectSearchService businessObjectSearchService;
	public static final String ACTIVITY_BO_NM = "Activity";
	public static final String ACTIVITY_URL_PARAM_NM = "activityQuery[activityURL]";
	public static final String ENTITY_QUERY_PARAM_NM = "activityQuery[entityQuery]";
	public static final String PROCESS_QUERY_PARAM_NM = "activityQuery[processQuery]";

    /**
     * @param uriInfo
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData getUiComponent(@Context UriInfo uriInfo) {
    	ActivityResponse activityResponse = new ActivityResponse();
		try {
			// Load the activity
			Activity activity = loadActivity(uriInfo.getQueryParameters());
			if(activity == null) 
				throw new ApplicationException(
						ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, 
						ErrorCodes.ARS_ACTIVITY_NOT_FOUND_MSG);
			// Load the business object for the activity
			activityResponse.setActivityData(this.loadActivityBusinessObject(activity));
			// Load the ui component for the activity 
			activityResponse.setUiComponentData(
					componentRESTService.loadUiComponent(activity.getUiComponent()));
			// Process the entity query
			EntityData entityData = activity.getEntityData();
			if(entityData != null) loadEntityBusinessObject(
					uriInfo.getQueryParameters(), activityResponse, activity, entityData);
			// Process the business process query
			//loadProcessBusinessObject(uriInfo.getQueryParameters(), activityResponse, activity);
			
	    	System.out.println("Hey>>>>>>>>>>>>"+ activityResponse.toString());

		} catch (ApplicationException e) {
			this.processRESTException(e, activityResponse);
		}
		return activityResponse;
    
    }

	private void loadProcessBusinessObject(
			MultivaluedMap<String, String> queryParameters,
			ActivityResponse businessObjectResponse, Activity activity) throws ApplicationException 
	{
		// Load business process search data
		SearchData processSearchData = 
				BusinessObjectRESTUtil.mapToBusinessObjectSearchInfo(
						this.extractBusinessObjectQuery(
								EntityUtil.PROCESS_BO_NM, PROCESS_QUERY_PARAM_NM, queryParameters));
		if(!processSearchData.isEmpty());
	}

	/**
	 * Creates a instance of {@link BusinessObjectData} and copies the
	 * data from the activity into its dataValues map
	 * 
	 * @param activity
	 * @return
	 * @throws ApplicationException
	 */
	private BusinessObjectData loadActivityBusinessObject(Activity activity) throws ApplicationException 
	{
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(
				new Object[]{activity}, "loadActivityBusinessObject");
		logger.debug("Loading activity business object for activity {}", activity);
		BusinessObjectData activityBusinessObjectData = 
				new BusinessObjectDataImpl(EntityUtil.ACTIVITY_BO_NM);
		BusinessObjectUtil.copyDataToBusinessObject(
				activityBusinessObjectData, activity);
		return activityBusinessObjectData;
	}

	/**
	 * @param queryParameters
	 * @return
	 * @throws ApplicationException
	 */
	private Activity loadActivity(MultivaluedMap<String, String> queryParameters) throws ApplicationException 
	{
		return activityService.findByActivityURL(this.extractActivityURL(queryParameters));
	}

	/**
	 * @param uriInfo
	 * @param businessObjectResponse
	 * @param activity
	 * @param entityData
	 * @throws ApplicationException
	 */
	private void loadEntityBusinessObject(
			MultivaluedMap<String, String> queryParameters,
			ActivityResponse businessObjectResponse, Activity activity,	EntityData entityData) throws ApplicationException 
			{
		logger.debug("Loading entity bsuiness object {}, {}", entityData.getName(),queryParameters);
		// Load entity search data
		SearchData entitySearchData =
				BusinessObjectRESTUtil.mapToBusinessObjectSearchInfo(
						this.extractBusinessObjectQuery(entityData.getName(), ENTITY_QUERY_PARAM_NM, queryParameters));
		// Only execute the search if we have valid search fields
		if(true)
		{
			List<BusinessObjectFieldData> entityFields = null;
			// Load the list of fields we want returned in the search results
			if(activityService.isListActivity(activity))
				entityFields = 
					activityService.getEntityListFields(entitySearchData.getBusinessObjectName());
			if(activityService.isEditActivity(activity))
				entityFields = 
					activityService.getEntityEditFields(entitySearchData.getBusinessObjectName());
			if(activityService.isViewActivity(activity))
				entityFields = 
					activityService.getEntityViewFields(entitySearchData.getBusinessObjectName());
			else
				entityFields = 
					activityService.getEntityListFields(entitySearchData.getBusinessObjectName());
			
			logger.debug("Loaded {} fields for entity {}", 
					entityFields.size(), entitySearchData.getBusinessObjectName());
			// Do the search
			List<BusinessObjectData> results = businessObjectSearchService.find(entitySearchData, entityFields);
			logger.debug("Loaded {} results for entity search ", results.size());
			if(activityService.isEditActivity(activity) | activityService.isViewActivity(activity)){
				if(!results.isEmpty())
					businessObjectResponse.setData(results.get(0));
			}
			else
				businessObjectResponse.setDataList(results);
			businessObjectResponse.setDataFields(entityFields);
			
		}
	}

	/**
	 * Extract the activityURL from the query parameters.
	 * 
	 * @param queryParameters
	 * @return
	 * @throws ApplicationException
	 */
	private String extractActivityURL(MultivaluedMap<String, String> queryParameters) throws ApplicationException
	{
    	logger.debug("Extracting activityURL from request parameters: {}", queryParameters);
		if(queryParameters.containsKey(ACTIVITY_URL_PARAM_NM)){
			String activityURL = queryParameters.getFirst(ACTIVITY_URL_PARAM_NM);
	    	logger.debug("Extracted activityURL {}", activityURL);
			return activityURL;
		}
		throw new ApplicationException(
				ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, 
				ErrorCodes.ACTIVITY_WITH_URL_NOT_FOUND_ERROR_MSG);
	}
	
	/**
	 * @param queryParameters
	 * @param entityQueryParameters
	 * @param parameterKey
	 */
	private MultivaluedHashMap<String, String> extractBusinessObjectQuery(String businessObjectName,
			String queryKeyPrefix, MultivaluedMap<String, String> queryParameters) 
	{
		// Create a new empty map to hold parameter names mapped to parameter values
		MultivaluedHashMap<String, String> entityQueryParameters = MultivaluedHashMap.empty();
		entityQueryParameters.clear();
    	for(String parameterKey : queryParameters.keySet())
    	{
			if(parameterKey.startsWith(queryKeyPrefix))
			{
				String paramNm = 
						parameterKey.replace(queryKeyPrefix, StringUtil.EMPTY_STRING)
						.replace(StringUtil.OPEN_SQUARE_BRACKET_SYMBOL, StringUtil.EMPTY_STRING)
						.replace(StringUtil.CLOSE_SQUARE_BRACKET_SYMBOL, StringUtil.EMPTY_STRING);
				// Add the value and extracted parameter name to hash map
				entityQueryParameters.add(paramNm, queryParameters.getFirst(parameterKey));
			}
    	}
    	logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>{}", entityQueryParameters);
    	// Hook to insert the business object name into the query map
		if(!entityQueryParameters.containsKey(BusinessObjectRESTUtil.ENTITY_NAME))
			entityQueryParameters.putSingle(BusinessObjectRESTUtil.ENTITY_NAME, businessObjectName);
    	return entityQueryParameters;
	}
}
