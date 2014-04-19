/**
 * 
 */
package com.qurion.businesslogic.ide.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qurion.businesslogic.application.model.Activity;
import com.qurion.businesslogic.application.model.EntityData;
import com.qurion.businesslogic.application.model.EntityField;
import com.qurion.businesslogic.application.model.Module;
import com.qurion.businesslogic.application.service.ActivityEntityService;
import com.qurion.businesslogic.application.service.EntityDataEntityService;
import com.qurion.businesslogic.application.service.EntityFieldEntityService;
import com.qurion.businesslogic.application.service.ModuleEntityService;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.application.util.EntityUtil;
import com.qurion.businesslogic.application.util.PropertyUtil;
import com.qurion.businesslogic.application.util.XMLUtil;
import com.qurion.businesslogic.businessobject.util.BusinessObjectUtil;
import com.qurion.businesslogic.ide.config.BuilderConfiguration;
import com.qurion.businesslogic.ide.config.IDEConfiguration;
import com.qurion.businesslogic.ide.config.Modules;
import com.qurion.businesslogic.ide.config.PathElement;
import com.qurion.businesslogic.ide.config.models.ModuleConfig;

/**
 * Default implementation of the IDE Builder Service.
 * 
 * @author Edward Banfa
 */
@Stateless
public class IDEBuilderServiceImpl implements IDEBuilderService {

	// Builders
	@Inject UiBuilderService uiBuilder;
	@Inject EntityBuilderService entityBuilderService;
	@Inject UserActivityBuilderService userActivityBuilderService;
	@Inject EntityActivityBuilderService entityActivityBuilderService;
	
	@Inject ModuleEntityService moduleEntityService;
	@Inject ActivityEntityService activityEntityService;
	@Inject EntityDataEntityService entityDataEntityService;
	@Inject EntityFieldEntityService entityFieldEntityService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.application.ide.IDEBuilderService#build(java.lang.String)
	 */
	@Override
	public void build(String fileName) throws ApplicationException 
	{
		IDEConfiguration configuration = 
				(IDEConfiguration) XMLUtil.getJAXBObject(fileName, IDEConfiguration.class);
		build(configuration);
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.application.ide.IDEBuilderService#build(com.qurion.businesslogic.application.ide.IDEConfiguration)
	 */
	@Override
	public void build(IDEConfiguration configuration)
			throws ApplicationException 
	{
		BuilderConfiguration builderConfiguration = 
				configuration.getBuilderConfiguration();
		// Map entities to their parent modules
		logger.debug("Builder configuration: {}", builderConfiguration);
		
		List<Module> modules = entityBuilderService.processModules(builderConfiguration);
		// Create activities for each entity
		for(Module module: modules) {
			List<EntityData> entitiesInModule = new ArrayList<EntityData>();
			entitiesInModule.addAll(module.getEntityDatas());
			this.createActvitiesFromEntities(configuration, entitiesInModule);
		}
		this.processUIComponents(configuration);
		//this.processUserActivities(configuration);
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.application.ide.IDEBuilderService#processEntityToModule(java.util.List, java.util.List)
	 */
	@Override
	public void processEntityToModule(IDEConfiguration configuration, 
			List<Module> modules, List<EntityData> entities) throws ApplicationException 
	{
		entityBuilderService.processEntityToModule(
				configuration.getBuilderConfiguration(), modules, entities);
	}
	
	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.application.ide.IDEBuilderService#processEntityFieldToEntity(java.util.List, java.util.List)
	 */
	@Override
	public void processEntityFieldToEntity(IDEConfiguration configuration,
			List<EntityData> entities,	List<EntityField> entityFields) throws ApplicationException 
	{
		entityBuilderService.processEntityFieldToEntity(
				configuration.getBuilderConfiguration(), entities, entityFields);
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.application.ide.IDEBuilderService#createActvitiesFromEntities(com.qurion.businesslogic.application.ide.config.IDEConfiguration, java.util.List)
	 */
	@Override
	public List<Activity> createActvitiesFromEntities(
			IDEConfiguration configuration,	List<EntityData> entities) throws ApplicationException 
	{
		return entityActivityBuilderService.createActvitiesFromEntities(
					configuration.getBuilderConfiguration(), entities);
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.ide.service.IDEBuilderService#processUIComponents(com.qurion.businesslogic.ide.config.IDEConfiguration)
	 */
	@Override
	public void processUIComponents(IDEConfiguration configuration)
			throws ApplicationException 
	{
		uiBuilder.processComponents(configuration.getBuilderConfiguration());
	}
	
	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.ide.service.IDEBuilderService#processUserActivities(com.qurion.businesslogic.ide.config.IDEConfiguration)
	 */
	public void processUserActivities(IDEConfiguration configuration) 
			throws ApplicationException 
	{
		userActivityBuilderService.processActivityDefintions(
				configuration.getBuilderConfiguration());
	}

	/**
	 * Load the modules specified in the 
	 * configuration object.
	 * 
	 * @param builderConfiguration The configuration object.
	 * @return The list of loaded modules.
	 * @throws ApplicationException If an exception was encountered.
	 */
	private List<Module> loadModules(BuilderConfiguration builderConfiguration) 
			throws ApplicationException
	{
		List<Module> modules = new ArrayList<Module>();
		Modules moduleListConfig = builderConfiguration.getModuleList();
		// Loop through the module config elements and load the module entity
		// by using the name attribute of the config element as the entity code
		for(PathElement moduleConfig: moduleListConfig.getModules())
		{
			//Module module = moduleEntityService.findByCode(moduleConfig.getName());
			//if(module != null)	modules.add(module);
		}
		return modules;
	}
}
