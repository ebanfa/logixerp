/**
 * 
 */
package com.qurion.businesslogic.ide.service;

import java.util.List;

import com.qurion.businesslogic.application.model.Activity;
import com.qurion.businesslogic.application.model.EntityData;
import com.qurion.businesslogic.application.model.EntityField;
import com.qurion.businesslogic.application.model.Module;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.ide.config.IDEConfiguration;

/**
 * The IDE Build Service is the primary component that
 * is responsible for the generation of the backend 
 * data entries required to successful run the LogixERP
 * application after deployment.
 * 
 * @author Edward Banfa
 *
 */
public interface IDEBuilderService {

	/**
	 * Generate all the backend entries required to
	 * run the LogixERP platform.
	 * 
	 * @param fileName The full path to the XML configuration file.
	 * @throws ApplicationException If an exception was encountered
	 */
	public void build(String fileName) 
			throws ApplicationException;

	/**
	 * Generate all the backend entries required to
	 * run the LogixERP platform.
	 * 
	 * @param configuration The configuration object
	 * @throws ApplicationException If an exception was encountered
	 */
	public void build(IDEConfiguration configuration) 
			throws ApplicationException;

	/**
	 * Process the mapping of entities to modules. Each
	 * {@code EntityData} will be mapped to the appropriate
	 * {@code Module}.
	 * 
	 * @param configuration The configuration object
	 * @throws ApplicationException If an exception was encountered
	 */
	public void processEntityToModule(IDEConfiguration configuration,
			List<Module> modules, List<EntityData> entities) throws ApplicationException;

	/**
	 * Process the mapping between entities and entity fields.
	 * Each {@code EntityField} will be mapped to the appropriate
	 * {@code EntityData}.
	 * 
	 * @param configuration The configuration object
	 * @throws ApplicationException If an exception was encountered
	 */
	public void processEntityFieldToEntity(IDEConfiguration configuration,
			List<EntityData> entities,	List<EntityField> entitiyFields) throws ApplicationException;

	/**
	 * Creates the 3 activities associated with entities. 
	 * The activities created are:
	 * 1. List activity.
	 * 2. Edit activity.
	 * 3. View activity.
	 * 
	 * This method will also create the appropriate
	 * activity groups for the created activities.
	 * 
	 * @param configuration
	 * @param entities
	 * @return
	 * @throws ApplicationException
	 */
	public List<Activity> createActvitiesFromEntities(
			IDEConfiguration configuration, List<EntityData> entities) throws ApplicationException;
	
	public void processUIComponents(IDEConfiguration configuration) throws ApplicationException;
	public void processUserActivities(IDEConfiguration configuration) throws ApplicationException;
}
