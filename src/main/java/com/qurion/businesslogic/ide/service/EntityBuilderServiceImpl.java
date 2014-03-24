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
import com.qurion.businesslogic.application.service.AbstractServiceImpl;
import com.qurion.businesslogic.application.service.EntityFieldTypeEntityService;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.application.util.ErrorCodes;
import com.qurion.businesslogic.application.util.ExceptionUtil;
import com.qurion.businesslogic.ide.config.BuilderConfiguration;
import com.qurion.businesslogic.ide.util.BuilderUtil;

/**
 * Default implementation for {@code EntityBuilderService}.
 * 
 * @author Edward Banfa
 *
 */
public class EntityBuilderServiceImpl extends AbstractServiceImpl 
	implements EntityBuilderService {
	
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

}
