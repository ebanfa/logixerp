/**
 * 
 */
package com.qurion.businesslogic.application.service;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import com.qurion.businesslogic.application.model.EntityField;
import com.qurion.businesslogic.application.util.ApplicationException;

/**
 * @author Edward Banfa 
 *
 */
public interface AbstractEntityService<M> {

	public static final String ENTITY_INSTANCE_NOT_FOUND = 
			"BaseEntityService.ENTITY_INSTANCE_NOT_FOUND";

	public final static String SYSTEM_USR_NAME = "System";
	public static final Character ENTITY_STATUS_ACTIVE = 'A';
	public static final Character ENTITY_STATUS_INACTIVE = 'I';
	
	public M findById(Integer id) throws ApplicationException;
	
	public M findByCode(String code) throws ApplicationException;
	
	public M findByName(String name) throws ApplicationException;
    
    public List<EntityField> getEntityFields() throws ApplicationException;

	public List<M> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException;
	
	public List<M> findByCriteria(Map<String, String> searchCriteria) throws ApplicationException;
	
}
