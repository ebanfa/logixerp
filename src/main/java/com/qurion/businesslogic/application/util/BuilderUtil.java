/**
 * 
 */
package com.qurion.businesslogic.application.util;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qurion.businesslogic.application.model.Activity;
import com.qurion.businesslogic.application.model.ActivityGroup;
import com.qurion.businesslogic.application.model.ActivityGrouping;
import com.qurion.businesslogic.application.model.UiComponent;
import com.qurion.businesslogic.application.service.AbstractEntityService;

/**
 * @author Edward Banfa
 */
public class BuilderUtil {
	
	public static ActivityGroup initActivityGroup(String code, String name, String description, 
			String displayName, String groupURL, String displayImage, Character displayFG, Integer sequenceNo)
	{
		ActivityGroup activityGroup = new ActivityGroup();
		activityGroup.setCode(code);
		activityGroup.setName(name);
		activityGroup.setDescription(description);
		activityGroup.setDisplayFg(displayFG);
		activityGroup.setDisplayNm(displayName);
		activityGroup.setGroupUrl(groupURL);
		activityGroup.setSequenceNo(sequenceNo);
		activityGroup.setDisplayImg(displayImage);
		activityGroup.setRecSt(AbstractEntityService.ENTITY_STATUS_ACTIVE);
		activityGroup.setCreatedDt(new Date(DateUtil.getCurrentTimeInMilliSeconds()));
		activityGroup.setEffectiveDt(new Date(DateUtil.getCurrentTimeInMilliSeconds()));
		activityGroup.setCreatedByUsr(AbstractEntityService.SYSTEM_USR_NAME);
		return activityGroup;
	}

	public static Activity initActivity(String code, String name, String description, 
			String displayNm, String activityURL, String displayImage, Character noFg, Integer sequenceNo) 
	{
		Activity activity = new Activity();
		activity.setCode(code);
		activity.setName(name);
		activity.setDescription(description);
		activity.setActivityUrl(activityURL);
		activity.setDisplayImg(displayImage);
		activity.setDisplayNm(displayNm);
		activity.setOperationCd("");
		activity.setRecSt(AbstractEntityService.ENTITY_STATUS_ACTIVE);
		activity.setSequenceNo(sequenceNo);
		activity.setCreatedDt(new Date(DateUtil.getCurrentTimeInMilliSeconds()));
		activity.setEffectiveDt(new Date(DateUtil.getCurrentTimeInMilliSeconds()));
		activity.setCreatedByUsr(AbstractEntityService.SYSTEM_USR_NAME);
		return activity;
	}

	public static ActivityGrouping initActivityGrouping(ActivityGroup activityGroup, Activity activity)
	{
		ActivityGrouping activityGrouping = new ActivityGrouping();
		activityGrouping.setCode(activity.getCode());
		activityGrouping.setRecSt(AbstractEntityService.ENTITY_STATUS_ACTIVE);
		activityGrouping.setCreatedDt(new Date(DateUtil.getCurrentTimeInMilliSeconds()));
		activityGrouping.setEffectiveDt(new Date(DateUtil.getCurrentTimeInMilliSeconds()));
		activityGrouping.setCreatedByUsr(AbstractEntityService.SYSTEM_USR_NAME);
		activityGrouping.setActivity(activity);
		activityGrouping.setActivityGroup(activityGroup);
		activityGrouping.setName(activityGroup.getName().concat(StringUtil.UNDERSCORE).concat(activity.getName()));
		return activityGrouping;
	}
	
	public static UiComponent initUiComponent(String code, String name, String description, String targetNm) 
	{
		UiComponent uiComponent = new UiComponent();
		uiComponent.setCode(code);
		uiComponent.setName(name);
		uiComponent.setCreatedDt(new Date(DateUtil.getCurrentTimeInMilliSeconds()));
		uiComponent.setEffectiveDt(new Date(DateUtil.getCurrentTimeInMilliSeconds()));
		uiComponent.setCreatedByUsr(AbstractEntityService.SYSTEM_USR_NAME);
		return uiComponent;
	}

}
