/**
 * 
 */
package com.qurion.businesslogic.application.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MultivaluedMap;

import org.joda.time.DateTime; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qurion.businesslogic.application.model.UiComponentAttribute;
import com.qurion.businesslogic.application.model.EntityField;
import com.qurion.businesslogic.application.service.EntityDataEntityService;
import com.qurion.businesslogic.application.service.AbstractEntityServiceImpl;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.application.model.UiComponent;
import com.qurion.businesslogic.application.service.UiComponentEntityService;
import com.qurion.businesslogic.application.model.UiComponentAttributeType;
import com.qurion.businesslogic.application.service.UiComponentAttributeTypeEntityService;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class UiComponentAttributeEntityServiceImpl 
	extends AbstractEntityServiceImpl<UiComponentAttribute> 
	implements UiComponentAttributeEntityService
{
	private static final String ENTITY_NAME = "UiComponentAttribute";
	
	private Logger logger = LoggerFactory.getLogger(UiComponentAttributeEntityServiceImpl.class);
	
	@Inject	EntityDataEntityService  entityDataService;
	@Inject UiComponentEntityService  uiComponentService;
	@Inject UiComponentAttributeTypeEntityService  uiComponentAttributeTypeService;
	
	/**
	 * @param entityClass
	 */
	public UiComponentAttributeEntityServiceImpl() {
		super(UiComponentAttribute.class);
	}

    /* (non-Javadoc)
	 * @see com.qurion.businesslogic.uiComponentAttribute.service.UiComponentAttribute#findById(java.lang.Integer)
	 */
	@Override
	public UiComponentAttribute findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.uiComponentAttribute.service.UiComponentAttribute#findByCode(java.lang.String)
	 */
	@Override
	public UiComponentAttribute findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.application.service.UiComponentAttribute#findByName(java.lang.String)
	 */
	@Override
	public UiComponentAttribute findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.application.service.UiComponentAttribute#findAll(java.util.Map)
	 */
	@Override
	public List<UiComponentAttribute> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
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
	private void initializeQueryParameters(Integer uiComponentId, Integer uiComponentAttributeTypeId, String attrName, String attrValue, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) {
		queryParameters.clear();
        if(uiComponentId != null)
            queryParameters.add("uiComponent", uiComponentId.toString());
        if(uiComponentAttributeTypeId != null)
            queryParameters.add("uiComponentAttributeType", uiComponentAttributeTypeId.toString());
        if(attrName != null)
            queryParameters.add("attrName", attrName.toString());
        if(attrValue != null)
            queryParameters.add("attrValue", attrValue.toString());
        if(id != null)
            queryParameters.add("id", id.toString());
        if(code != null)
            queryParameters.add("code", code.toString());
        if(effectiveDt != null)
            queryParameters.add("effectiveDt", effectiveDt.toString());
        if(recSt != null)
            queryParameters.add("recSt", recSt.toString());
        if(versionNo != null)
            queryParameters.add("versionNo", versionNo.toString());
        if(rowTs != null)
            queryParameters.add("rowTs", rowTs.toString());
        if(createdDt != null)
            queryParameters.add("createdDt", createdDt.toString());
        if(createdByUsr != null)
            queryParameters.add("createdByUsr", createdByUsr.toString());
        if(lastModifiedDt != null)
            queryParameters.add("lastModifiedDt", lastModifiedDt.toString());
        if(lastModifiedUsr != null)
            queryParameters.add("lastModifiedUsr", lastModifiedUsr.toString());
		
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.application.service.AbstractEntityService#extractPredicates(javax.ws.rs.core.MultivaluedMap, javax.persistence.criteria.CriteriaBuilder, javax.persistence.criteria.Root)
	 */
	@Override
	protected Predicate[] extractPredicates(
			MultivaluedMap<String, String> queryParameters,	CriteriaBuilder criteriaBuilder, Root<UiComponentAttribute> root) 
	{
		
		List<Predicate> predicates = new ArrayList<Predicate>() ;
        if (queryParameters.containsKey("uiComponent")) {
            Integer uiComponentId = Integer.valueOf(queryParameters.getFirst("uiComponent"));
            predicates.add(criteriaBuilder.equal(root.get("uiComponent").get("id"), uiComponentId));
        }
        if (queryParameters.containsKey("uiComponentAttributeType")) {
            Integer uiComponentAttributeTypeId = Integer.valueOf(queryParameters.getFirst("uiComponentAttributeType"));
            predicates.add(criteriaBuilder.equal(root.get("uiComponentAttributeType").get("id"), uiComponentAttributeTypeId));
        }
		if (queryParameters.containsKey("attrName")) {
            String attrName = queryParameters.getFirst("attrName");
            predicates.add(criteriaBuilder.equal(root.get("attrName"), attrName));
        }
		if (queryParameters.containsKey("code")) {
            String attrName = queryParameters.getFirst("code");
            predicates.add(criteriaBuilder.equal(root.get("code"), attrName));
        }
		if (queryParameters.containsKey("attrValue")) {
            String attrValue = queryParameters.getFirst("attrValue");
            predicates.add(criteriaBuilder.equal(root.get("attrValue"), attrValue));
        }
		if (queryParameters.containsKey("id")) {
            Integer id = Integer.valueOf(queryParameters.getFirst("id"));
            predicates.add(criteriaBuilder.equal(root.get("id"), id));
        }
		if (queryParameters.containsKey("code")) {
            String code = queryParameters.getFirst("code");
            predicates.add(criteriaBuilder.equal(root.get("code"), code));
        }
		if (queryParameters.containsKey("effectiveDt")) {
			DateTime effectiveDt = new DateTime(queryParameters.getFirst("effectiveDt"));
            predicates.add(criteriaBuilder.equal(root.get("effectiveDt"), effectiveDt.toDate()));
        }
		if (queryParameters.containsKey("recSt")) {
            String recSt = queryParameters.getFirst("recSt");
            predicates.add(criteriaBuilder.equal(root.get("recSt"), recSt));
        }
		if (queryParameters.containsKey("versionNo")) {
            Integer versionNo = Integer.valueOf(queryParameters.getFirst("versionNo"));
            predicates.add(criteriaBuilder.equal(root.get("versionNo"), versionNo));
        }
		if (queryParameters.containsKey("rowTs")) {
			DateTime rowTs = new DateTime(queryParameters.getFirst("rowTs"));
            predicates.add(criteriaBuilder.equal(root.get("rowTs"), rowTs.toDate()));
        }
		if (queryParameters.containsKey("createdDt")) {
			DateTime createdDt = new DateTime(queryParameters.getFirst("createdDt"));
            predicates.add(criteriaBuilder.equal(root.get("createdDt"), createdDt.toDate()));
        }
		if (queryParameters.containsKey("createdByUsr")) {
            String createdByUsr = queryParameters.getFirst("createdByUsr");
            predicates.add(criteriaBuilder.equal(root.get("createdByUsr"), createdByUsr));
        }
		if (queryParameters.containsKey("lastModifiedDt")) {
			DateTime lastModifiedDt = new DateTime(queryParameters.getFirst("lastModifiedDt"));
            predicates.add(criteriaBuilder.equal(root.get("lastModifiedDt"), lastModifiedDt.toDate()));
        }
		if (queryParameters.containsKey("lastModifiedUsr")) {
            String lastModifiedUsr = queryParameters.getFirst("lastModifiedUsr");
            predicates.add(criteriaBuilder.equal(root.get("lastModifiedUsr"), lastModifiedUsr));
        }
        return predicates.toArray(new Predicate[]{});
	}
}
