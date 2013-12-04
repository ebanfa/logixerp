/**
 * 
 */
package com.qurion.businesslogic.application.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qurion.businesslogic.application.model.EntityField;
import com.qurion.businesslogic.application.model.UiComponent;
import com.qurion.businesslogic.application.util.ApplicationException;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class UiComponentEntityServiceImpl 
	extends AbstractEntityServiceImpl<UiComponent> 
	implements UiComponentEntityService
{
	private static final String ENTITY_NAME = "UiComponent";
	
	private Logger logger = LoggerFactory.getLogger(UiComponentEntityServiceImpl.class);
	
	@Inject	EntityDataEntityService  entityDataService;
	@Inject UiComponentTypeEntityService  uiComponentTypeService;
	
	/**
	 * @param entityClass
	 */
	public UiComponentEntityServiceImpl() {
		super(UiComponent.class);
	}

    /* (non-Javadoc)
	 * @see com.qurion.businesslogic.uiComponent.service.UiComponent#findById(java.lang.Integer)
	 */
	@Override
	public UiComponent findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.uiComponent.service.UiComponent#findByCode(java.lang.String)
	 */
	@Override
	public UiComponent findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.application.service.UiComponent#findByName(java.lang.String)
	 */
	@Override
	public UiComponent findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.application.service.UiComponent#findAll(java.util.Map)
	 */
	@Override
	public List<UiComponent> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.application.service.AbstractEntityService#getEntityFields()
	 */
	@Override
	public List<EntityField> getEntityFields() throws ApplicationException {
		return entityDataService.getFieldsForEntity(ENTITY_NAME);
	}
	
	
	/**
	 * 
	 */
	private void initializeQueryParameters(Integer uiComponentTypeId, Integer uiComponentId, String name, String description) {
		queryParameters.clear();
        if(uiComponentTypeId != null)
            queryParameters.add("uiComponentType", uiComponentTypeId.toString());
        if(uiComponentId != null)
            queryParameters.add("uiComponent", uiComponentId.toString());
        if(name != null)
            queryParameters.add("name", name.toString());
        if(description != null)
            queryParameters.add("description", description.toString());
		
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.application.service.AbstractEntityService#extractPredicates(javax.ws.rs.core.MultivaluedMap, javax.persistence.criteria.CriteriaBuilder, javax.persistence.criteria.Root)
	 */
	@Override
	protected Predicate[] extractPredicates(
			MultivaluedMap<String, String> queryParameters,	CriteriaBuilder criteriaBuilder, Root<UiComponent> root) 
	{
		
		List<Predicate> predicates = new ArrayList<Predicate>() ;
        if (queryParameters.containsKey("uiComponentType")) {
            Integer uiComponentTypeId = Integer.valueOf(queryParameters.getFirst("uiComponentType"));
            predicates.add(criteriaBuilder.equal(root.get("uiComponentType").get("id"), uiComponentTypeId));
        }
        if (queryParameters.containsKey("uiComponent")) {
            Integer uiComponentId = Integer.valueOf(queryParameters.getFirst("uiComponent"));
            predicates.add(criteriaBuilder.equal(root.get("uiComponent").get("id"), uiComponentId));
        }
		if (queryParameters.containsKey("name")) {
            String name = queryParameters.getFirst("name");
            predicates.add(criteriaBuilder.equal(root.get("name"), name));
        }
		if (queryParameters.containsKey("code")) {
            String name = queryParameters.getFirst("code");
            predicates.add(criteriaBuilder.equal(root.get("code"), name));
        }
		if (queryParameters.containsKey("description")) {
            String description = queryParameters.getFirst("description");
            predicates.add(criteriaBuilder.equal(root.get("description"), description));
        }
        return predicates.toArray(new Predicate[]{});
	}
}
