/**
 * 
 */
package com.qurion.businesslogic.ide.service;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.qurion.businesslogic.application.model.Activity;
import com.qurion.businesslogic.application.service.AbstractServiceImpl;
import com.qurion.businesslogic.application.service.ActivityEntityService;
import com.qurion.businesslogic.application.service.ActivityTypeEntityService;
import com.qurion.businesslogic.application.service.EntityDataEntityService;
import com.qurion.businesslogic.application.service.ModuleEntityService;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.application.util.BooleanUtil;
import com.qurion.businesslogic.application.util.XMLUtil;
import com.qurion.businesslogic.ide.config.Activities;
import com.qurion.businesslogic.ide.config.BuilderConfiguration;
import com.qurion.businesslogic.ide.config.PathElement;
import com.qurion.businesslogic.ide.util.BuilderUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class UserActivityBuilderServiceImpl extends AbstractServiceImpl implements UserActivityBuilderService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Inject ModuleEntityService moduleEntityService;
	@Inject ActivityEntityService activityEntityService;
	@Inject EntityDataEntityService entityDataEntityService;
	@Inject ActivityTypeEntityService activityTypeEntityService;
	@Inject EntityActivityBuilderService entityActivityBuilderService;
	/**
	 * Process activity definitions.
	 * 
	 * @param builderConfiguration The configuration.
	 * @throws ApplicationException If an exception is encountered.
	 */
	public void processActivityDefintions(BuilderConfiguration configuration)
			throws ApplicationException 
	{
		logger.debug("Processing activity definitions");
		Activities activities = configuration.getActivityList();
		loadActivityConfigurations(configuration, activities.getActivities());
	}
	
	/**
	 * Loads an {@code Activity} definition.
	 * 
	 * @param configuration Builder configuration
	 * @param activityConfigurations Pointers to the definition files.
	 * @throws ApplicationException If an exception is encountered.
	 */
	private void loadActivityConfigurations(BuilderConfiguration configuration, 
			List<PathElement> activityConfigurations) throws ApplicationException 
	{
		for(PathElement element : activityConfigurations) 
		{
			logger.debug("Loading UiComponent configuration {}", element.getPath());
			processActivityConfiguration(configuration, XMLUtil.getW3CDocument(element.getPath()));
		}
	}
	
	/**
	 * Process an activity configuration
	 * 
	 * @param configuration
	 * @param activityConfig
	 * @throws ApplicationException
	 */
	private void processActivityConfiguration(BuilderConfiguration configuration, 
			Document activityConfig) throws ApplicationException 
	{
		logger.debug("Processing Activity document {}", activityConfig);
		Element rootActivityElement = activityConfig.getDocumentElement();
		logger.debug("Processing rootActivityElement {}", rootActivityElement.getNodeName());
		// Normalize the document
		rootActivityElement.normalize();
		/*NodeList activityList = rootActivityElement.getChildNodes();
		logger.debug("Processing NodeList {} of length {} ", 
				activityList.toString(), activityList.getLength());*/
		buildActivity(configuration, null, rootActivityElement);
		/*for(int i = 0; i < activityList.getLength(); i++) {
			if(activityList.item(i) instanceof Element) {
				logger.debug("Processing Node {}", activityList.item(i).getNodeName());
				buildActivity(configuration, null, activityList.item(i));
			}
		}*/
	}

	/**
	 * @param configuration
	 * @param object
	 * @param activityNode
	 * @throws ApplicationException 
	 */
	private void buildActivity(BuilderConfiguration configuration, 
			Object object, Node activityNode) throws ApplicationException 
	{
		Map<String, String> attributesMap = BuilderUtil.getNodeAttributes(activityNode);
		
		logger.debug("Building activity: {} with attributes {}", 
				activityNode.getNodeName(), attributesMap);

		if(configuration.getOverWrite().equals(BooleanUtil.BOOLEAN_TRUE))
		{
			Activity activity = activityEntityService.findByCode(
					attributesMap.get(UiComponentBuilderService.CODE_ATTRIBUTE));
			if(activity != null) removeObject(activity);
		}
		Activity activity = new Activity();
		// Set the other required attributes
		activity.setCode(attributesMap.get(UiComponentBuilderService.CODE_ATTRIBUTE));
		activity.setName(attributesMap.get(UiComponentBuilderService.NAME_ATTRIBUTE));
		activity.setDisplayNm(attributesMap.get(UserActivityBuilderService.DISPLAY_NM_ATTRIBUTE));
		activity.setDisplayImg(attributesMap.get(UserActivityBuilderService.DISPLAY_IMG_ATTRIBUTE));
		activity.setDescription(attributesMap.get(UiComponentBuilderService.DESCRIPTION_ATTRIBUTE));
		activity.setActivityUrl(attributesMap.get(UserActivityBuilderService.ACTIVITY_URL_ATTRIBUTE));
		activity.setSequenceNo(0);
		activity.setOperationCd("");
		initializeFields(activity);
		// Fetch the module
		activity.setModule(moduleEntityService.findByCode(
				attributesMap.get(UserActivityBuilderService.MODULE_CODE_ATTRIBUTE)));
		//Fetch the activity type
		activity.setActivityType(activityTypeEntityService.findByCode(
				attributesMap.get(UserActivityBuilderService.ACTIVITY_TY_CODE_ATTRIBUTE)));
		// Fetch the entity 
		activity.setEntityData(entityDataEntityService.findByCode(
				attributesMap.get(UserActivityBuilderService.ENTITY_CODE_ATTRIBUTE)));
		// Create a UI component for the activity
		activity.setUiComponent(
				entityActivityBuilderService.createComponentForActivity(configuration, activity));
		save(activity);
	}
}
