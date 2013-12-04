/**
 * 
 */
package com.qurion.businesslogic.application.rest;

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
import com.qurion.businesslogic.application.service.UiComponentEntityService;
import com.qurion.businesslogic.application.util.ApplicationException;

/**
 * @author Edward Banfa
 *
 */
@Path("/ui-service")
@Stateless
public class UiComponentRESTService extends AbstractRESTService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Inject UiComponentEntityService componentEntityService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseData getUiComponent(@QueryParam("componentName") String componentName) {
    	logger.debug("Loading component from request {}", componentName);
		UiComponentData uiComponentData = new UiComponentData();
    	try {
			UiComponent uiComponent = componentEntityService.findByName(componentName);
			if(uiComponent != null)
				uiComponentData = loadUiComponent(uiComponent);
		} catch (ApplicationException e) {
			this.processRESTException(e, uiComponentData);
		}
    	return uiComponentData;
    }

	/**
	 * @param uiComponentData
	 * @param uiComponent
	 */
	private UiComponentData loadUiComponent(UiComponent uiComponent) {

		UiComponentData uiComponentData = new UiComponentData();
		uiComponentData.setName(uiComponent.getName());
		uiComponent.setCode(uiComponent.getCode());
		uiComponentData.setType(uiComponent.getUiComponentType().getName());
		uiComponentData.setDescription(uiComponent.getDescription());
		for(UiComponentAttribute attribute: uiComponent.getUiComponentAttributes())
		{
			UiComponentAttributeData uiComponentAttributeData = 
					new UiComponentAttributeData();
			uiComponentAttributeData.setName(attribute.getAttrName());
			uiComponentAttributeData.setValue(attribute.getAttrValue());
			uiComponentData.getAttributes().put(
					attribute.getAttrName(), attribute.getAttrValue());
		}
		for(UiComponent component: uiComponent.getUiComponents()){
			uiComponentData.getComponents().add(loadUiComponent(component));
		}
			
		return uiComponentData;
	}

}
