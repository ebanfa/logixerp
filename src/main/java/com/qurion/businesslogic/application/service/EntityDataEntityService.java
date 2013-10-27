/**
 * 
 */
package com.qurion.businesslogic.application.service;


import java.util.List;

import com.qurion.businesslogic.application.model.EntityData;
import com.qurion.businesslogic.application.model.EntityField;

/**
 * @author Edward Banfa
 *
 */
public interface EntityDataEntityService extends AbstractEntityService<EntityData>
{

	List<EntityField> getFieldsForEntity(String entityName);

}
