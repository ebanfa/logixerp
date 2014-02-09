/**
 * 
 */
package com.qurion.businesslogic.ide.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.qurion.businesslogic.application.model.Activity;
import com.qurion.businesslogic.application.model.ActivityType;
import com.qurion.businesslogic.application.model.Module;
import com.qurion.businesslogic.application.model.UiComponent;
import com.qurion.businesslogic.application.model.UiComponentAttribute;
import com.qurion.businesslogic.application.model.UiComponentType;
import com.qurion.businesslogic.application.service.AbstractServiceImpl;
import com.qurion.businesslogic.application.service.ActivityEntityService;
import com.qurion.businesslogic.application.service.ModuleEntityService;
import com.qurion.businesslogic.application.service.UiComponentAttributeTypeEntityService;
import com.qurion.businesslogic.application.service.UiComponentEntityService;
import com.qurion.businesslogic.application.service.UiComponentTypeEntityService;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.application.util.BooleanUtil;
import com.qurion.businesslogic.application.util.DateUtil;
import com.qurion.businesslogic.application.util.EntityUtil;
import com.qurion.businesslogic.application.util.ErrorCodes;
import com.qurion.businesslogic.application.util.ExceptionUtil;
import com.qurion.businesslogic.ide.config.BuilderConfiguration;
import com.qurion.businesslogic.ide.util.BuilderUtil;

/**
 * Utility class to build components.
 * 
 * @author Edward Banfa
 *
 */
@Stateless
public class UiComponentBuilderServiceImpl extends AbstractServiceImpl implements UiComponentBuilderService {

	private static final String DEFAULT = "DEFAULT";
	@Inject ModuleEntityService moduleEntityService;
	@Inject ActivityEntityService activityEntityService;
	@Inject UiComponentEntityService componentEntityService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Inject UiComponentTypeEntityService componentTypeEntityService;
	@Inject UiComponentAttributeTypeEntityService attributeTypeEntityService;

	
	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.ide.service.UiComponentBuilderService#buildComponent(com.qurion.businesslogic.ide.config.BuilderConfiguration, java.lang.String, com.qurion.businesslogic.application.model.UiComponent, org.w3c.dom.Node)
	 */
	@Override
	public void buildComponent(BuilderConfiguration configuration,
			UiComponent parentComponent, Node componentNode) throws ApplicationException 
	{
		if(configuration.getOverWrite().equals(BooleanUtil.BOOLEAN_TRUE))
			buildComponent(configuration, componentNode.getNodeName(), 
					parentComponent, componentNode, BuilderUtil.getNodeAttributes(componentNode));
	}

	/**
	 * @param configuration
	 * @param componentType
	 * @param parentComponent
	 * @param componentNode
	 * @param attributesMap
	 * @throws ApplicationException
	 */
	private void buildComponent(BuilderConfiguration configuration,	String componentType, 
			UiComponent parentComponent, Node componentNode, Map<String, String> attributesMap)
			throws ApplicationException 
	{
		UiComponent component = null;
		logger.debug("Building component: {} with attributes {}", componentNode.getNodeName(), attributesMap);
		// Check if the current node is an event definition
		if(componentType.equals(COMP_TY_EVENT_HANDLER)) 
			createEventForUiComponent(configuration, 
					componentNode, attributesMap, parentComponent);
		// else the current node is a component definition
		else {
			// Build the component
			component = createComponent(configuration, 
					getComponentType(componentType), parentComponent, attributesMap);
			// Hook to create an activity if the component we
			// are creating is an activity
			if(componentType.equals(ACTIVITY)) 
				createActivityForUiComponent(component, attributesMap);
			// Process the children of the current node
			// Note: Event nodes don't have child nodes
			NodeList childNodes = componentNode.getChildNodes();
			for(int i = 0; i < childNodes.getLength(); i++)
			{
				if(childNodes.item(i) instanceof Element)
					buildComponent(configuration, component, childNodes.item(i));
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.ide.service.ComponentBuilder#buildComponent(com.qurion.businesslogic.application.model.UiComponentType, com.qurion.businesslogic.application.model.UiComponent, com.qurion.businesslogic.application.model.EntityData, com.qurion.businesslogic.application.model.EntityField, java.lang.String, java.lang.String, java.lang.String, java.util.Map)
	 */
	@Override
	public UiComponent createComponent(BuilderConfiguration configuration, UiComponentType componentType, 
			UiComponent parentComponent, Map<String, String> attributesMap) throws ApplicationException 
	{
		String componentName = attributesMap.get(NAME_ATTRIBUTE);
		System.out.println(">>>>>>>>>>Creating component with attributesMap::" + attributesMap);
		UiComponent component = componentEntityService.findByName(componentName);
		try {
			if(component != null) 
				this.deleteComponent(component);
			component = new UiComponent();
			component.setUiComponentType(componentType);
			component.setCode(attributesMap.get(NAME_ATTRIBUTE));
			component.setName(attributesMap.get(NAME_ATTRIBUTE));
			component.setDescription(attributesMap.get(DESCRIPTION_ATTRIBUTE));
			if(attributesMap.get(SEQUENCE_ATTRIBUTE) != null )
				component.setSequenceNo(Integer.parseInt(attributesMap.get(SEQUENCE_ATTRIBUTE)));
			initializeFields(component);			
			if(parentComponent != null)
				component.setUiComponent(parentComponent);
			save(component);
			// Create the attributes for the component
			createUiComponentAttributes(component, attributesMap);
			return component;
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.processException(e, ErrorCodes.BPS_ENTITY_CREATION_ERROR_CD);
		}
		return component;
	}
	
	private List<UiComponentAttribute> createUiComponentAttributes(
			UiComponent uiComponent, Map<String, String> attributesMap)	throws ApplicationException 
	{
		List<UiComponentAttribute> attributes = 
				new ArrayList<UiComponentAttribute>();
		try {
			
			for(String attributeName: attributesMap.keySet()) 
			{
				String attributeValue = attributesMap.get(attributeName);
				UiComponentAttribute attribute = new UiComponentAttribute();
				attribute.setUiComponentAttributeType(
						attributeTypeEntityService.findByCode(DEFAULT));
				attribute.setAttrName(attributeName);
				attribute.setAttrValue(attributeValue);
				attribute.setCode(uiComponent.getCode().concat(DateUtil.getCurrentTime()));
				attribute.setUiComponent(uiComponent);
				initializeFields(attribute);
				attributes.add((UiComponentAttribute) save(attribute));
			}
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.processException(e, ErrorCodes.BPS_ENTITY_CREATION_ERROR_CD);
		}
		return attributes;
	}
	

	public void createEventForUiComponent(BuilderConfiguration configuration, Node componentNode, 
			Map<String, String> attributesMap, UiComponent parentUiComponent) throws ApplicationException 
	{
		logger.debug("Building event handler component: {} with attributes {}", componentNode.getNodeName(), attributesMap);
		this.createComponent(configuration, 
				getComponentType(COMP_TY_EVENT_HANDLER), parentUiComponent, attributesMap);
		
	}

	/**
	 * Creates an {@code Activity} for an activity {@code UiComponent}.
	 * 
	 * @param component
	 * @param attributesMap
	 * @return
	 * @throws ApplicationException
	 */
	private Activity createActivityForUiComponent(UiComponent component, 
			Map<String, String> attributesMap) throws ApplicationException 
	{
		Activity activity = null;
		try {
			activity = new Activity();
			// Get the activity type
			ActivityType activityType = (ActivityType) 
					EntityUtil.returnOrThrowIfNull(
					activityEntityService.findByCode(attributesMap.get(ACTIVITY_TY_ATTRIBUTE)), 
					ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, 
					"createActivityForUiComponent:ActivityType");
			activity.setActivityType(activityType);
			// Get the module
			Module module = (Module) 
					EntityUtil.returnOrThrowIfNull(
					moduleEntityService.findByCode(attributesMap.get(MODULE_ATTRIBUTE)), 
					ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, 
					"createActivityForUiComponent:Module");
			activity.setModule(module);
			// Set other properties
			activity.setCode(attributesMap.get(CODE_ATTRIBUTE));
			activity.setName(attributesMap.get(NAME_ATTRIBUTE));
			activity.setActivityUrl(attributesMap.get(URL_ATTRIBUTE));
			activity.setDisplayNm(attributesMap.get(DISPLAY_NAME_ATTRIBUTE));
			activity.setDescription(attributesMap.get(DESCRIPTION_ATTRIBUTE));
			activity.setDisplayImg(attributesMap.get(DISPLAY_IMAGE_ATTRIBUTE));
			initializeFields(activity);
			return (Activity) save(activity);
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.BPS_ENTITY_CREATION_ERROR_CD);
		}
		return activity;
	}
	
	private void deleteComponent(UiComponent component) throws ApplicationException
	{
		for(UiComponentAttribute attribute: component.getUiComponentAttributes())	
			delete(attribute);

		for(UiComponent childComponent: component.getUiComponents())	
			deleteComponent(childComponent);
		
		delete(component);
	}

	/**
	 * Utility method to retrieve a component type.
	 * 
	 * @param name The name of the component type.
	 * @return The component type.
	 * @throws ApplicationException If an exception is encountered
	 */
	public UiComponentType getComponentType(String name) 
			throws ApplicationException 
	{
		return (UiComponentType) EntityUtil.returnOrThrowIfNull(
				componentTypeEntityService.findByName(name), 
				ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "getComponentType");
	}

}
