/**
 * 
 */
package com.qurion.businesslogic.application.rest;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qurion.businesslogic.application.model.UiComponent;
import com.qurion.businesslogic.application.model.UiComponentAttribute;
import com.qurion.businesslogic.application.model.UiComponentType;
import com.qurion.businesslogic.application.rest.data.ResponseData;
import com.qurion.businesslogic.application.rest.data.UiComponentAttributeData;
import com.qurion.businesslogic.application.rest.data.UiComponentData;
import com.qurion.businesslogic.application.rest.data.UiResponse;
import com.qurion.businesslogic.application.service.ActivityService;
import com.qurion.businesslogic.application.service.UiComponentEntityService;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.businessobject.data.BusinessObjectFieldData;
import com.qurion.businesslogic.businessobject.data.SearchData;
import com.qurion.businesslogic.businessobject.service.BusinessObjectSearchService;
import com.qurion.businesslogic.ide.service.UiComponentBuilderService;

/**
 * @author Edward Banfa
 *
 */
@Path("/ui-service")
@Stateless
public class UiComponentRESTService extends AbstractRESTService {

	@Inject ActivityService activityService;
	@Inject UiComponentEntityService componentEntityService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Inject BusinessObjectSearchService businessObjectSearchService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData getUiComponent(@QueryParam("componentName") String componentName) {
    	logger.debug("Loading component from request {}", componentName);
		UiResponse uiResponse = new UiResponse();
    	/*try {
			UiComponent uiComponent = componentEntityService.findByName(componentName);
			if(uiComponent != null)
				uiResponse.setUiComponentData(loadUiComponent(uiComponent));
		} catch (ApplicationException e) {
			this.processRESTException(e, uiResponse);
		}*/
    	return uiResponse;
    }

	/**
	 * @param uiComponentData
	 * @param uiComponent
	 * @param multivaluedMap 
	 */
	public UiComponentData loadUiComponent(
			UiComponent uiComponent, Map<String, String> context) throws ApplicationException {

		UiComponentData uiComponentData = new UiComponentData();
		uiComponentData.setName(uiComponent.getName());
		uiComponent.setCode(uiComponent.getCode());
		uiComponentData.setType(uiComponent.getUiComponentType().getName());
		uiComponentData.setDescription(uiComponent.getDescription());
		uiComponentData.setSequenceNo(uiComponent.getSequenceNo());
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
		for(UiComponent component: uiComponent.getUiComponents()){
			UiComponentType componentType = component.getUiComponentType();
			logger.debug("Processing ccomponent of type: {} ", componentType.getCode());
			
			if(componentType.getCode().equals(
					UiComponentBuilderService.COMP_TY_UI_DATA_QUERY) |
					componentType.getCode().equals(
							UiComponentBuilderService.COMP_TY_UI_DATA_QUERY_PARAM))
				// Process the ui query data
				this.loadUiComponentData(component, uiComponentData);
			else
				uiComponentData.getComponents().add(loadUiComponent(component, context));
		}
		return uiComponentData;
	}

	/**
	 * Builds and executes a {@link SearchData} based on the 
	 * provided {@code uiDataQuery}. The results return from the
	 * search are load into the {@code uiQueryData} property
	 * of the {@link UiComponentData}.
	 * 
	 * @param uiDataQuery
	 * @param uiComponentData
	 * @throws ApplicationException
	 */
	private void loadUiComponentData(UiComponent uiDataQuery, 
			UiComponentData uiComponentData) throws ApplicationException 
	{
		SearchData searchData = uiDataQueryToBOSearchData(uiDataQuery);
		List<BusinessObjectFieldData> entityFields = 
				activityService.getBusinessObjectViewFields(searchData.getBusinessObjectName());
		uiComponentData.setUiQueryData(businessObjectSearchService.find(searchData, entityFields));
	}

	/**
	 * @param uiDataQuery
	 */
	private SearchData uiDataQueryToBOSearchData(UiComponent uiDataQuery) {
		String businessObjectName = null;
		SearchData searchData = new SearchData();
		for(UiComponentAttribute attribute: uiDataQuery.getUiComponentAttributes())
			//logger.debug("Processing uiDataQuery attribute: {} ", attribute.getAttrName());
			if(attribute.getAttrName().equals("business-object"))
				businessObjectName = attribute.getAttrValue();
		if(businessObjectName != null)
			searchData.setBusinessObjectName(businessObjectName);
		return searchData;
	}

}
