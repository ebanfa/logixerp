/**
 * 
 */
package com.qurion.businesslogic.ide.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qurion.businesslogic.application.model.EntityData;
import com.qurion.businesslogic.application.model.EntityField;
import com.qurion.businesslogic.application.model.EntityFieldType;
import com.qurion.businesslogic.application.model.Module;
import com.qurion.businesslogic.application.model.Universe;
import com.qurion.businesslogic.application.service.AbstractServiceImpl;
import com.qurion.businesslogic.application.service.EntityDataEntityService;
import com.qurion.businesslogic.application.service.EntityFieldEntityService;
import com.qurion.businesslogic.application.service.EntityFieldTypeEntityService;
import com.qurion.businesslogic.application.service.UniverseEntityService;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.application.util.EntityUtil;
import com.qurion.businesslogic.application.util.ErrorCodes;
import com.qurion.businesslogic.application.util.ExceptionUtil;
import com.qurion.businesslogic.application.util.XMLUtil;
import com.qurion.businesslogic.ide.config.BuilderConfiguration;
import com.qurion.businesslogic.ide.config.Modules;
import com.qurion.businesslogic.ide.config.PathElement;
import com.qurion.businesslogic.ide.config.models.EntityConfig;
import com.qurion.businesslogic.ide.config.models.FieldConfig;
import com.qurion.businesslogic.ide.config.models.ModuleConfig;
import com.qurion.businesslogic.ide.util.BuilderUtil;

/**
 * Default implementation for {@code EntityBuilderService}.
 * 
 * @author Edward Banfa
 *
 */
public class EntityBuilderServiceImpl extends AbstractServiceImpl 
	implements EntityBuilderService {
	
	public static final String STORAGE_TABLE = "table";
	@Inject UniverseEntityService universeEntityService;
	@Inject EntityDataEntityService entityDataEntityService;
	@Inject EntityFieldEntityService fieldEntityService;
	@Inject EntityFieldTypeEntityService fieldTypeEntityService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private List<EntityFieldType> fieldTypes =  new ArrayList<EntityFieldType>();

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.application.ide.EntityBuilderService#processEntityToModule(com.qurion.businesslogic.application.ide.BuilderConfiguration, java.util.List, java.util.List)
	 */
	@Override
	public void processEntityToModule(
			BuilderConfiguration builderConfiguration, List<Module> modules,
			List<EntityData> entities) throws ApplicationException 
	{
		for(Module module : modules)
			createEntityModuleMapping(builderConfiguration, module, entities);
		
	}

	/**
	 * Maps {@code EntityData}'s to {@code Module}. 
	 * The code of the entity is used to resolve the parent
	 * module. The entity code is of the form: {@code MODULENAME_ENTITYNAME}.
	 * 
	 * @param builderConfiguration Build configuration
	 * @param module The module we are mapping entities to.
	 * @param entities The entities we are cross checking.
	 * @throws ApplicationException If an exception is encountered
	 */
	private void createEntityModuleMapping(
			BuilderConfiguration builderConfiguration, Module module,
			List<EntityData> entities) throws ApplicationException 
	{
		logger.debug("Mapping {} entities to module {}", entities.size(), module.getCode());
		fieldTypes = fieldTypeEntityService.findAll(null);
		for(EntityData entity : entities) 
			if(BuilderUtil.doesEntityBelongToModule(entity, module))
			{
				try {
					entity.setModule(module);
					//entity.setUniverse(module.getUniverse());
					getEntityManager().persist(entity);
				} catch (Exception e) {
					ExceptionUtil.processException(e, ErrorCodes.ENTITY_TO_MODULE_MAP_ERROR_CD);
				}
			}
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.application.ide.EntityBuilderService#processEntityFieldToEntity(com.qurion.businesslogic.application.ide.BuilderConfiguration, java.util.List, java.util.List)
	 */
	@Override
	public void processEntityFieldToEntity(
			BuilderConfiguration builderConfiguration,
			List<EntityData> entities, List<EntityField> entityFields) throws ApplicationException 
	{
		for(EntityData entity : entities) 
			createEntityFieldToEntityMapping(builderConfiguration, entity, entityFields);
		
		for(EntityField entityField: entityFields){
			if(entityField.getEntityFieldType().getCode().equals("RELATIONSHIP")){
				for(EntityData entity : entities) {
					if(entity.getName().equals(entityField.getDescription())){
						entityField.setEntityDataByRelatedEntityId(entity);
						//entityField.setUniverse(entity.getUniverse());
						getEntityManager().persist(entityField);
					}
				}
			}
		}
	}

	/**
	 * Maps {@code EntityData} to {@code EntityData}. Uses the code of
	 * field to resolve the parent entity. The code of the field is in the
	 * form: {@code ENTITYNAME_FIELDNAME}
	 * 
	 * @param builderConfiguration
	 * @param entity
	 * @param entityFields
	 * @throws ApplicationException
	 */
	private void createEntityFieldToEntityMapping(BuilderConfiguration builderConfiguration, 
			EntityData entity, List<EntityField> entityFields) throws ApplicationException 
	{
		logger.debug("Mapping {} fields to entity {}", entityFields.size(), entity.getCode());
		for(EntityField entityField : entityFields) 
			if(BuilderUtil.doesFieldBelongToEntity(entityField, entity))
			{
				try {
					entityField.setEntityDataByEntityId(entity);
					this.processEntityFieldType(entityField);
					getEntityManager().persist(entityField);
				} catch (Exception e) {
					ExceptionUtil.processException(e, ErrorCodes.ENTITYFIELD_TO_ENTITY_MAP_ERROR_CD);
				}
			}
	}
	
	private void processEntityFieldType(EntityField field)
	{
		for(EntityFieldType fieldType: fieldTypes)
			if(BuilderUtil.doesFieldBelongToFieldType(field, fieldType))
	        	field.setEntityFieldType(fieldType);
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.ide.service.EntityBuilderService#processModules(com.qurion.businesslogic.ide.config.BuilderConfiguration)
	 */
	@Override
	public List<Module> processModules(BuilderConfiguration builderConfiguration)
			throws ApplicationException {

		Universe universe = universeEntityService.findByCode("MULTIVERSE");
		List<ModuleConfig> configuaration = 
				loadConfiguration(builderConfiguration);
		// Processed the loaded configuration
		List<Module> modules = 
				buildModules(builderConfiguration, universe, configuaration);
		this.buildEntityFieldRelationship(
				modules, configuaration, builderConfiguration);
		return modules;
	}

	private void buildEntityFieldRelationship(List<Module> modules,
			List<ModuleConfig> configuaration,
			BuilderConfiguration builderConfiguration) throws ApplicationException 
			{
		logger.debug("Processing entity field relationships");
		for(ModuleConfig moduleConfig: configuaration)
		{
			for(EntityConfig entityConfig: moduleConfig.getEntities()){
				for(FieldConfig fieldConfig: entityConfig.getFieldConfigs()){
					if(fieldConfig.getFieldType().equals("RELATIONSHIP")) 
					{
						logger.debug("Processing relationship {} for field{}", 
								fieldConfig.getRelatedEntityName(), fieldConfig.getName());
						String relatedEntityName = fieldConfig.getRelatedEntityName();
						EntityData entityData = entityDataEntityService.findByName(relatedEntityName);
						EntityField entityField = fieldEntityService.findByCode(fieldConfig.getCode());
						entityField.setEntityDataByRelatedEntityId(entityData);
						getEntityManager().merge(entityField);
					}
				}
			}
		}
		
	}

	/**
	 * @param builderConfiguration
	 * @return
	 * @throws ApplicationException
	 */
	private List<ModuleConfig> loadConfiguration(
			BuilderConfiguration builderConfiguration)
			throws ApplicationException {
		Modules modulesConfig = builderConfiguration.getModuleList();
		List<ModuleConfig> configuaration = new ArrayList<ModuleConfig>();
		// The configuration files for the modules to be built
		for(PathElement element: modulesConfig.getModules()) 
		{
			ModuleConfig moduleConfig =
					(ModuleConfig) XMLUtil.getJAXBObject(element.getPath(), ModuleConfig.class);
			configuaration.add(moduleConfig);
		}
		return configuaration;
	}

	/**
	 * @param builderConfiguration
	 * @param modules
	 * @param universe
	 * @param configuaration
	 * @throws ApplicationException
	 */
	private List<Module> buildModules(BuilderConfiguration builderConfiguration,
			Universe universe, List<ModuleConfig> configuaration) throws ApplicationException {

		List<Module> modules = new ArrayList<Module>();
		for(ModuleConfig moduleConfig: configuaration) {
			
			Module module = 
					this.createModule(moduleConfig, universe, builderConfiguration);
			this.processEntitiesInModule(module, moduleConfig, builderConfiguration);
			this.getEntityManager().refresh(module);
			modules.add(module);
		}
		return modules;
	}
	

	private List<EntityData> processEntitiesInModule(Module module,
			ModuleConfig moduleConfig, BuilderConfiguration builderConfiguration) throws ApplicationException {

		List<EntityData> entities = new ArrayList<EntityData>();
		List<EntityConfig> entitiesConfig = moduleConfig.getEntities();
		for(EntityConfig entityConfig: entitiesConfig){
			EntityData entity = 
					this.createEntity(module, entityConfig, builderConfiguration);
			List<FieldConfig> fieldConfigs = entityConfig.getFieldConfigs();
			for(FieldConfig fieldConfig: fieldConfigs) 
			{
				EntityField field = 
						this.createEntityField(entity, fieldConfig, builderConfiguration);
			}
			this.getEntityManager().refresh(entity);
			entities.add(entity);
		}
		return entities;
	}

	private EntityField createEntityField(EntityData entity,
			FieldConfig fieldConfig, BuilderConfiguration builderConfiguration) throws ApplicationException {
		EntityField entityField = new EntityField();
		EntityFieldType fieldType = 
				fieldTypeEntityService.findByCode(fieldConfig.getFieldType());
		entityField = 
				(EntityField) EntityUtil.initializeFields(entityField);
		entityField.setEntityFieldType(fieldType);
		entityField.setEntityDataByEntityId(entity);
		entityField.setUniverse(entity.getUniverse());
		entityField.setCode(fieldConfig.getCode());
		entityField.setName(fieldConfig.getName());
		entityField.setDescription(fieldConfig.getDescription());
		entityField.setEditFieldFg(fieldConfig.getEditFieldFlag().charAt(0));
		entityField.setCreateFieldFg(fieldConfig.getCreateFieldFlag().charAt(0));
		entityField.setDeleteFieldFg(fieldConfig.getDeleteFieldFlag().charAt(0));
		entityField.setListFieldFg(fieldConfig.getListFieldFlag().charAt(0));
		entityField.setViewFieldFg(fieldConfig.getViewFieldFlag().charAt(0));
		entityField.setUniqueFg(fieldConfig.getUniqueFlag().charAt(0));
		entityField.setPrimarykeyFg(fieldConfig.getPrimaryKeyFlag().charAt(0));
		entityField.setRequiredFg(fieldConfig.getRequiredFlag().charAt(0));
		entityField.setSearchFieldFg(fieldConfig.getSearchFieldFlag().charAt(0));
		entityField.setSequenceNo(fieldConfig.getSequenceNo());
		entityField.setStorage(STORAGE_TABLE);
		getEntityManager().persist(entityField);
		return entityField;
	}

	private EntityData createEntity(Module module, EntityConfig entityConfig,
			BuilderConfiguration builderConfiguration) 
	{
		EntityData entity = new EntityData();
		entity = (EntityData) EntityUtil.initializeFields(entity);
		entity.setCode(entityConfig.getCode());
		entity.setName(entityConfig.getName());
		entity.setDescription(entityConfig.getDescription());
		entity.setDisplayNm(entityConfig.getDisplayName());
		entity.setDisplayNmPlural(entityConfig.getDisplayNamePlural());
		entity.setEntityClassNm(entityConfig.getEntityClassName());
		entity.setDbName(entityConfig.getDbName());
		entity.setHasTable(entityConfig.getHasTable().charAt(0));
		entity.setUniverse(module.getUniverse());
		entity.setModule(module);
		getEntityManager().persist(entity);
		return entity;
	}

	private Module createModule(ModuleConfig moduleConfig,
			Universe universe, BuilderConfiguration builderConfiguration) throws ApplicationException 
	{
		logger.debug("Processing module configuration {}", moduleConfig);
		Module module = new Module();
		module = (Module) EntityUtil.initializeFields(module);
		module.setCode(moduleConfig.getCode());
		module.setName(moduleConfig.getName());
		module.setUniverse(universe);
		module.setDescription(moduleConfig.getDescription());
		module.setDisplayFg(moduleConfig.getDisplayFlag().charAt(0));
		module.setDisplayNm(moduleConfig.getDisplayName());
		module.setSequenceNo(moduleConfig.getSequenceNo());
		getEntityManager().persist(module);
		return module;
	}

}
