/**
 * 
 */
package com.qurion.businesslogic.ide.service;

import java.util.List;

import com.qurion.businesslogic.application.model.Activity;
import com.qurion.businesslogic.application.model.EntityData;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.ide.config.BuilderConfiguration;

/**
 * Builder for activity specific back end entries.
 * For each entity it will create 3 activities for 
 * the entity. An activity group is also created to 
 * contain the created activities.
 * 
 * @author Edward Banfa
 *
 */
public interface ActivityBuilderService {
	
	/** 
	 * Build the entity activities for the provided
	 * entities.
	 * 
	 * @param builderConfig COnfiguration information.
	 * @param entities The entities we are building the activities
	 * 				   for.
	 * @return The list of activities that have been created.
	 * @throws ApplicationException If an exception was encountered.
	 */
	public List<Activity> createActvitiesFromEntities(
			BuilderConfiguration configuration, List<EntityData> entities) throws ApplicationException;
}
