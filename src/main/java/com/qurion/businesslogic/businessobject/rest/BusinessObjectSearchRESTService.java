/**
 * 
 */
package com.qurion.businesslogic.businessobject.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qurion.businesslogic.application.rest.AbstractRESTService;
import com.qurion.businesslogic.application.service.ActivityService;
import com.qurion.businesslogic.application.service.EntityFieldEntityService;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.businessobject.data.BusinessObjectData;
import com.qurion.businesslogic.businessobject.data.BusinessObjectFieldData;
import com.qurion.businesslogic.businessobject.data.BusinessObjectResponse;
import com.qurion.businesslogic.businessobject.data.SearchData;
import com.qurion.businesslogic.businessobject.service.BusinessObjectSearchService;
import com.qurion.businesslogic.businessobject.util.BusinessObjectRESTUtil;

/**
 * @author Edward Banfa
 *
 */
@Path("/search")
@Stateless
public class BusinessObjectSearchRESTService extends AbstractRESTService {

	
	@Inject ActivityService activityService;
	@Inject BusinessObjectSearchService businessObjectSearchService;
	@Inject EntityFieldEntityService applicationEntityFieldService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    /**
     * @param uriInfo
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public BusinessObjectResponse findByCriteria(@Context UriInfo uriInfo) 
    {
    	BusinessObjectResponse businessObjectResponse = new BusinessObjectResponse();
		try {
			// Extract the items from the map
			SearchData searchData = 
					BusinessObjectRESTUtil.mapToBusinessObjectSearchInfo(uriInfo.getQueryParameters());
			// Get the activity list fields
			businessObjectResponse.setBusinessObjectName(searchData.getBusinessObjectName());
			List<BusinessObjectFieldData> entityListFields = 
					activityService.getBusinessObjectListFields(searchData.getBusinessObjectName());
			logger.debug("Loaded {} fields for entity {}", 
					entityListFields.size(), searchData.getBusinessObjectName());
			// Do the search
			List<BusinessObjectData> results = businessObjectSearchService.find(searchData, entityListFields);
			businessObjectResponse.setDataFields(entityListFields);
			businessObjectResponse.setDataList(results);
			businessObjectResponse.setErrors(false);
		} 
		catch (ApplicationException e) {
			this.processRESTException(e, businessObjectResponse);
		}
		return businessObjectResponse;
    }

    @GET
    @Path("/single/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public BusinessObjectResponse findById(@Context UriInfo uriInfo) 
    {
    	BusinessObjectResponse businessObjectResponse = new BusinessObjectResponse();
		try {
			// Extract the items from the map
			SearchData searchData = 
					BusinessObjectRESTUtil.mapToBusinessObjectSearchInfo(uriInfo.getQueryParameters());
			// Get the activity list fields
			businessObjectResponse.setBusinessObjectName(searchData.getBusinessObjectName());
			List<BusinessObjectFieldData> entityListFields = 
					activityService.getBusinessObjectListFields(searchData.getBusinessObjectName());
			logger.debug("Loaded {} fields for entity {}", 
					entityListFields.size(), searchData.getBusinessObjectName());
			// Do the search
			BusinessObjectData businessObjectData = businessObjectSearchService.findById(
					searchData.getBusinessObjectName(), searchData.getBusinessObjectId(), entityListFields);

			logger.debug("Loaded business object: {}", businessObjectData);
			businessObjectResponse.setDataFields(entityListFields);
			businessObjectResponse.setData(businessObjectData);
			businessObjectResponse.setErrors(false);
		} 
		catch (ApplicationException e) {
			this.processRESTException(e, businessObjectResponse);
		}
		return businessObjectResponse;
    }
    
    @GET
    @Path("/searchFields")
    @Produces(MediaType.APPLICATION_JSON)
    public BusinessObjectResponse getEntitySearchFields(@QueryParam("entityName") String entityName)
    {
    	BusinessObjectResponse businessObjectResponse = new BusinessObjectResponse();
    	try {
    		businessObjectResponse.setDataFields(activityService.getBusinessObjectSearchFields(entityName));
    		businessObjectResponse.setBusinessObjectName(entityName);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
    	return businessObjectResponse;
    }

}
