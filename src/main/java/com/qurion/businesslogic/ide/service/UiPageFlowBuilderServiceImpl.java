/**
 * 
 */
package com.qurion.businesslogic.ide.service;

import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.qurion.businesslogic.application.model.Activity;
import com.qurion.businesslogic.application.model.ActivityGroup;
import com.qurion.businesslogic.application.model.ActivityGroupType;
import com.qurion.businesslogic.application.model.ActivityGrouping;
import com.qurion.businesslogic.application.model.Module;
import com.qurion.businesslogic.application.service.AbstractServiceImpl;
import com.qurion.businesslogic.application.service.ActivityEntityService;
import com.qurion.businesslogic.application.service.ActivityGroupTypeEntityService;
import com.qurion.businesslogic.application.service.ModuleEntityService;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.application.util.DateUtil;
import com.qurion.businesslogic.application.util.EntityUtil;
import com.qurion.businesslogic.application.util.ErrorCodes;
import com.qurion.businesslogic.application.util.ExceptionUtil;
import com.qurion.businesslogic.ide.util.BuilderUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class UiPageFlowBuilderServiceImpl extends AbstractServiceImpl 
	implements UiPageFlowBuilderService {

	@Inject ModuleEntityService moduleEntityService;
	@Inject ActivityEntityService activityEntityService;
	@Inject ActivityGroupTypeEntityService activityGroupTypeEntityService;

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.ide.service.UiPageFlowBuilderService#buildPageFlow(org.w3c.dom.Element)
	 */
	@Override
	public void buildPageFlow(Element rootUiComponentElement) throws ApplicationException 
	{
		// Create the activity group for the page flow
		ActivityGroup activityGroup = 
				createPageFlowGroup(rootUiComponentElement);
		// For each activity specified in the page flow definition,
		// we create a ActivityGrouping object that maps the activity
		// to the group
		NodeList componentsList = 
				rootUiComponentElement.getChildNodes();
		for(int i = 0; i < componentsList.getLength(); i++)
			createPageFlowGrouping(rootUiComponentElement, activityGroup);
	}

	/**
	 * @param rootPageFlowElement
	 * @param activityGroup
	 * @throws ApplicationException
	 */
	private void createPageFlowGrouping(Element rootPageFlowElement, ActivityGroup activityGroup) 
			throws ApplicationException 
	{
		try 
		{
			// Get the attributes of the page flow
			Map<String, String> activityAttributes = 
					BuilderUtil.getNodeAttributes(rootPageFlowElement);
			
			ActivityGrouping activityGrouping = new ActivityGrouping();
			activityGrouping.setActivityGroup(activityGroup);
			// Get the activity
			activityGrouping.setActivity(getActivity(
					activityAttributes.get(UiComponentBuilderService.NAME_ATTRIBUTE)));
			// Set other properties
			activityGrouping.setCode(DateUtil.getCurrentTime());
			activityGrouping.setName(DateUtil.getCurrentTime().concat(
					activityAttributes.get(UiComponentBuilderService.NAME_ATTRIBUTE)));
			activityGrouping.setCreatedDt(DateUtil.getCurrentDate());
			activityGrouping.setEffectiveDt(DateUtil.getCurrentDate());
			getEntityManager().persist(activityGrouping);
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.BPS_ENTITY_CREATION_ERROR_CD);
		}
	}

	/**
	 * @param rootUiComponentElement
	 * @throws ApplicationException
	 */
	private ActivityGroup createPageFlowGroup(Element rootUiComponentElement)
			throws ApplicationException 
	{
		ActivityGroup pageFlow = null;
		try {
			// Get the attributes of the page flow
			Map<String, String> pageFlowAttributes = 
					BuilderUtil.getNodeAttributes(rootUiComponentElement);
			// Create a group to represent the page flow
			pageFlow = new ActivityGroup();
			pageFlow.setActivityGroupType(getActivityPageFlowGroupType());
			// Get the module
			pageFlow.setModule(getModule(
					pageFlowAttributes.get(UiComponentBuilderService.MODULE_ATTRIBUTE)));
			// Set other properties
			pageFlow.setCode(pageFlowAttributes.get(
					UiComponentBuilderService.CODE_ATTRIBUTE));
			pageFlow.setName(pageFlowAttributes.get(
					UiComponentBuilderService.NAME_ATTRIBUTE));
			pageFlow.setGroupUrl(pageFlowAttributes.get(UiComponentBuilderService.URL_ATTRIBUTE));
			pageFlow.setDisplayNm(pageFlowAttributes.get(UiComponentBuilderService.DISPLAY_NAME_ATTRIBUTE));
			pageFlow.setDescription(pageFlowAttributes.get(UiComponentBuilderService.DESCRIPTION_ATTRIBUTE));
			pageFlow.setDisplayImg(pageFlowAttributes.get(UiComponentBuilderService.DISPLAY_IMAGE_ATTRIBUTE));
			pageFlow.setCreatedDt(DateUtil.getCurrentDate());
			pageFlow.setEffectiveDt(DateUtil.getCurrentDate());
			getEntityManager().persist(pageFlow);
			return pageFlow;
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.BPS_ENTITY_CREATION_ERROR_CD);
		}
		return null;
	}

	/**
	 * Returns the module with specified code.
	 * 
	 * @param code The code of the module we are fetching.
	 * @return The module.
	 * @throws ApplicationException If an exception is encountered.
	 */
	private Module getModule(String code) throws ApplicationException 
	{
		return (Module) 
				EntityUtil.returnOrThrowIfNull(
				moduleEntityService.findByCode(code), 
				ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, 
				"createActivityForUiComponent:Module");
	}
	
	/**
	 * Returns the {@code Activity} with specified code.
	 * 
	 * @param code The code of the module we are fetching.
	 * @return The module.
	 * @throws ApplicationException If an exception is encountered.
	 */
	private Activity getActivity(String code) throws ApplicationException 
	{
		return (Activity) 
				EntityUtil.returnOrThrowIfNull(
				activityEntityService.findByCode(code), 
				ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "getActivity");
	}

	/**
	 * Returns the {@code ActivityGroupType} for
	 * page flows.
	 * 
	 * @return The activity group type.
	 * @throws ApplicationException If an exception is encountered.
	 */
	private ActivityGroupType getActivityPageFlowGroupType() throws ApplicationException 
	{
		return (ActivityGroupType) 
				EntityUtil.returnOrThrowIfNull(
				activityGroupTypeEntityService.findByCode(PAGE_FLOW), 
				ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, 
				"createActivityForUiComponent:ActivityGroupType");
	}
}
