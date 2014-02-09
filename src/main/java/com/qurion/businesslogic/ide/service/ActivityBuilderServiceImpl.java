/**
 * 
 */
package com.qurion.businesslogic.ide.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qurion.businesslogic.application.model.Activity;
import com.qurion.businesslogic.application.model.ActivityGroup;
import com.qurion.businesslogic.application.model.ActivityGroupType;
import com.qurion.businesslogic.application.model.ActivityGrouping;
import com.qurion.businesslogic.application.model.ActivityType;
import com.qurion.businesslogic.application.model.EntityData;
import com.qurion.businesslogic.application.model.UiComponent;
import com.qurion.businesslogic.application.model.UiComponentType;
import com.qurion.businesslogic.application.service.AbstractServiceImpl;
import com.qurion.businesslogic.application.service.ActivityEntityService;
import com.qurion.businesslogic.application.service.ActivityGroupEntityService;
import com.qurion.businesslogic.application.service.ActivityGroupTypeEntityService;
import com.qurion.businesslogic.application.service.ActivityTypeEntityService;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.application.util.BooleanUtil;
import com.qurion.businesslogic.application.util.BuilderUtil;
import com.qurion.businesslogic.application.util.ErrorCodes;
import com.qurion.businesslogic.application.util.ExceptionUtil;
import com.qurion.businesslogic.application.util.StringUtil;
import com.qurion.businesslogic.ide.config.BuilderConfiguration;

/**
 * Implementation of {@code ActivityBuilderService}.
 * 
 * @author Edward Banfa
 *
 */
public class ActivityBuilderServiceImpl extends AbstractServiceImpl implements ActivityBuilderService {
	@Inject UiComponentBuilderService uiComponentBuilderService;
	@Inject ActivityEntityService activityEntityService;
	@Inject ActivityTypeEntityService activityTypeEntityService;
	@Inject ActivityGroupEntityService activityGroupEntityService;
	@Inject ActivityGroupTypeEntityService activityGroupTypeEntityService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.application.ide.ActivityBuilderService#createActvitiesFromEntities(com.qurion.businesslogic.application.util.DBBuilderConfig, java.util.List)
	 */
	@Override
	public List<Activity> createActvitiesFromEntities(
			BuilderConfiguration configuration, List<EntityData> entities) throws ApplicationException 
	{
		List<Activity> activities = new ArrayList<Activity>();
		for(EntityData entity : entities)
			activities.addAll(createActivitiesFromEntity(configuration, entity));
		return activities;
	}
	
	/**
	 * Create the list, edit and view activities for the given,
	 * entity. An activity group is also created for the activities.
	 * 
	 * @param configuration Configuration information for the builder.
	 * @param entity The entity we are building the activities for.
	 * @return
	 * @throws ApplicationException If an exception is encountered.
	 */
	public List<Activity> createActivitiesFromEntity(
			BuilderConfiguration configuration, EntityData entity) throws ApplicationException 
	{
		logger.debug("Creating activities for entity {}", entity.getCode());
		List<Activity> activities = new ArrayList<Activity>();
		try {
			ActivityGroup activityGroup = createEntityActivityGroup(configuration, entity);
			activities.add(createEntityListActivity(configuration, activityGroup, entity));
			activities.add(createEntityEditActivity(configuration, activityGroup, entity));
			activities.add(createEntityViewActivity(configuration, activityGroup, entity));
		} catch (ApplicationException e) {
			e.printStackTrace();
			ExceptionUtil.processException(e, ErrorCodes.ENTITY_ACTIVITY_CREATION_ERROR_CD);
		}
		return activities;
	}
	
	/**
	 * Create an activity group of type "ENTITY_ACTIVITY"
	 * only if it does not exist. Deleting an existing 
	 * group could disrupt references from child activities.
	 * 
	 * @param configuration Configuration information for the builder.
	 * @param entity The entity we are building the group for.
	 * @return The created group.
	 * @throws ApplicationException If an exception is encountered.
	 */
	public ActivityGroup createEntityActivityGroup(BuilderConfiguration configuration, 
			EntityData entity) throws ApplicationException 
	{
		logger.debug("Creating activity group for entity {}", entity.getCode());
		String groupCode = entity.getCode();
		ActivityGroup activityGroup = activityGroupEntityService.findByCode(groupCode);
		// Do not overwrite groups due to references from 
		// child activities
		if(activityGroup == null)
		{
			activityGroup = 
					BuilderUtil.initActivityGroup(entity.getCode(), entity.getName(), 
							entity.getDescription(), entity.getDisplayNm(), entity.getName(), "", StringUtil.NO_FG, 0);
			ActivityGroupType entityGroupType = 
					activityGroupTypeEntityService.findByCode(ActivityGroupTypeEntityService.ENTITY_ACTIVITY_GRP_TY_CD);
			activityGroup.setActivityGroupType(entityGroupType);
			activityGroup.setModule(entity.getModule());
			getEntityManager().persist(activityGroup);
		}
		return activityGroup;
	}

	/**
	 * Create the list activity for an entity.
	 * 
	 * @param configuration Configuration information for the builder.
	 * @param activityGroup The parent group for the activity.
	 * @param entity The entity we are building the activity for.
	 * @return The created activity.
	 * @throws ApplicationException If an exception is encountered.
	 */
	public Activity createEntityListActivity(BuilderConfiguration configuration, 
			ActivityGroup activityGroup, EntityData entity)	throws ApplicationException 
	{
		logger.debug("Creating list activity for entity {}", entity.getCode());
		String activityCode = ActivityTypeEntityService.LIST_ACTIVITY_CD_PREFIX.concat(entity.getCode());
		logger.debug("Finding activity before create with code {}", activityCode);
		Activity listActivity = activityEntityService.findByCode(activityCode);
		
		if(listActivity == null | 
				configuration.getOverWrite().equals(BooleanUtil.BOOLEAN_TRUE))
		{
			ActivityType listActivityType = 
					activityTypeEntityService.findByCode(ActivityTypeEntityService.LIST_ACTIVITY_TY_CD);
			if(listActivity != null){
				for(ActivityGrouping activityGrouping: listActivity.getActivityGroupings()){
					getEntityManager().remove(activityGrouping);
				}
				removeObject(listActivity);
			}
			listActivity = createEntityActivity(configuration, entity, listActivityType, activityGroup, activityCode);
		}
		return listActivity;
	}
	
	/**
	 * Create the edit activity for an entity.
	 * 
	 * @param configuration Configuration information for the builder.
	 * @param activityGroup The parent group for the activity.
	 * @param entity The entity we are building the activity for.
	 * @return The created activity.
	 * @throws ApplicationException If an exception is encountered.
	 */
	public Activity createEntityEditActivity(BuilderConfiguration configuration, 
			ActivityGroup activityGroup, EntityData entity)	throws ApplicationException 
	{
		logger.debug("Creating edit activity for entity {}", entity.getCode());
		String activityCode = ActivityTypeEntityService.EDIT_ACTIVITY_CD_PREFIX.concat(entity.getCode());
		logger.debug("Finding activity before create with code {}", activityCode);
		Activity editActivity = activityEntityService.findByCode(activityCode);
		
		if(editActivity == null | 
				configuration.getOverWrite().equals(BooleanUtil.BOOLEAN_TRUE))
		{
			ActivityType editActivityType = 
					activityTypeEntityService.findByCode(ActivityTypeEntityService.EDIT_ACTIVITY_TY_CD);
			if(editActivity != null){
				for(ActivityGrouping activityGrouping: editActivity.getActivityGroupings()){
					getEntityManager().remove(activityGrouping);
				}
				removeObject(editActivity);
			}
			editActivity = createEntityActivity(configuration, entity, editActivityType, activityGroup, activityCode);
		}
		return editActivity;
	}
	
	/**
	 * Create the view activity for an entity.
	 * 
	 * @param configuration Configuration information for the builder.
	 * @param activityGroup The parent group for the activity.
	 * @param entity The entity we are building the activity for.
	 * @return The created activity.
	 * @throws ApplicationException If an exception is encountered.
	 */
	private Activity createEntityViewActivity(BuilderConfiguration configuration, 
			ActivityGroup activityGroup, EntityData entity)	throws ApplicationException 
	{
		logger.debug("Creating view activity for entity {}", entity.getCode());
		String activityCode = ActivityTypeEntityService.VIEW_ACTIVITY_CD_PREFIX.concat(entity.getCode());
		Activity viewActivity = activityEntityService.findByCode(activityCode);
		
		if(viewActivity == null | 
				configuration.getOverWrite().equals(BooleanUtil.BOOLEAN_TRUE))
		{
			ActivityType viewActivityType = 
					activityTypeEntityService.findByCode(ActivityTypeEntityService.VIEW_ACTIVITY_TY_CD);
			if(viewActivity != null){
				for(ActivityGrouping activityGrouping: viewActivity.getActivityGroupings()){
					getEntityManager().remove(activityGrouping);
				}
				removeObject(viewActivity);
			}
			viewActivity = createEntityActivity(configuration, entity, viewActivityType, activityGroup, activityCode);
		}
		return viewActivity;
	}

	/**
	 * Create an entity activity with the specified entity, code, activity
	 * type, and group. 
	 * 
	 * @param entity The entity we are building the activity for.
	 * @param activityType The type of activity.
	 * @param activityGroup The parent group for the activity.
	 * @param activityCode The code of the activity.
	 * @return The created activity.
	 * @throws ApplicationException If an exception is encountered.
	 */
	private Activity createEntityActivity(BuilderConfiguration configuration,
			EntityData entity, ActivityType activityType, ActivityGroup activityGroup,
			String activityCode) throws ApplicationException
	{
		Activity activity = BuilderUtil.initActivity(activityCode, entity.getName(), 
				entity.getDescription(), entity.getDisplayNm(), activityCode.toLowerCase(), "", StringUtil.NO_FG, 0);
		try {
			activity.setUiComponent(
					this.createComponentForActivity(configuration, activity));
			activity.setEntityData(entity);
			activity.setActivityType(activityType);
			activity.setModule(entity.getModule());
			getEntityManager().persist(activity);
			// Create the activity grouping
			createActivityGrouping(activityGroup, activity);
		} catch (ApplicationException e) {
			e.printStackTrace();
			ExceptionUtil.processException(e, ErrorCodes.ENTITY_ACTIVITY_CREATION_ERROR_CD);
		}
		return activity;
	}
	
	/**
	 * @param configuration
	 * @param activity
	 * @return
	 * @throws ApplicationException
	 */
	private UiComponent createComponentForActivity(
			BuilderConfiguration configuration, Activity activity) throws ApplicationException
	{
		HashMap<String, String> attributesMap = new HashMap<String, String>();
		try {
			UiComponentType uiComponentType = 
					uiComponentBuilderService.getComponentType(UiComponentBuilderService.ACTIVITY_COMPONENT_TYPE);
			attributesMap.put("code", activity.getCode());
			attributesMap.put("name", activity.getCode());
			attributesMap.put("displayNm", activity.getDisplayNm());
			attributesMap.put("description", activity.getDescription());
			attributesMap.put("activityURL", activity.getActivityUrl());
			//attributesMap.put("displayImg", activity.getDisplayImg());
			return uiComponentBuilderService.createComponent(configuration, uiComponentType, null, attributesMap);
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.processException(e, ErrorCodes.ENTITY_ACTIVITY_CREATION_ERROR_CD);
			
		}
		return null;
	}

	/**
	 * Create the mapping between activity and group.
	 * 
	 * @param activityGroup The group.
	 * @param activity The activity.
	 */
	private void createActivityGrouping(ActivityGroup activityGroup,
			Activity activity) throws ApplicationException {
		// Create the activity grouping if it 
		ActivityGrouping activityGrouping = 
				BuilderUtil.initActivityGrouping(activityGroup, activity);
		getEntityManager().persist(activityGrouping);
	}

	/**
	 * Utility method to remove an object from
	 * storage. TODO: Refactor this into a persistence
	 * utility class.
	 * 
	 * @param object The object we are remove from storage.
	 */
	private void removeObject(Object object) {
		getEntityManager().flush();
		getEntityManager().remove(object);
		getEntityManager().flush();
	}

	
}
