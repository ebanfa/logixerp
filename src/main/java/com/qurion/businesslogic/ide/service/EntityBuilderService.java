/**
 * 
 */
package com.qurion.businesslogic.ide.service;

import java.util.List;

import com.qurion.businesslogic.application.model.EntityData;
import com.qurion.businesslogic.application.model.EntityField;
import com.qurion.businesslogic.application.model.Module;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.ide.config.BuilderConfiguration;

/**
 * Builder for entity specific back end entries.
 * Maps entities to the right modules and fields to the
 * right entities.
 * 
 * @author Edward Banfa
 *
 */
public interface EntityBuilderService {
	
	
	/**
	 * Map {@code EntityData}s to {@code Module}.
	 * 
	 * @param builderConfiguration Configuration information.
	 * @param modules The modules being processed.
	 * @param entities The entities we are mapping to modules.
	 * @throws ApplicationException If an exception was encountered.
	 */
	public void processEntityToModule(BuilderConfiguration builderConfiguration,
			List<Module> modules, List<EntityData> entities) throws ApplicationException;
	
	/**
	 * Map {@code EntityField}s to {@code EntityData}s. 
	 * 
	 * @param builderConfiguration Configuration information.
	 * @param entities The entities we are processing.
	 * @param entityFields The entity fields we are mapping to entities.
	 * @throws ApplicationException If an exception was encountered.
	 */
	public void processEntityFieldToEntity(BuilderConfiguration builderConfiguration,
			List<EntityData> entities, List<EntityField> entityFields) throws ApplicationException;

}
