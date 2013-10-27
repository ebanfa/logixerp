/**
 * 
 */
package com.qurion.businesslogic.application.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MultivaluedMap;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qurion.businesslogic.application.model.EntityField;
import com.qurion.businesslogic.application.util.ApplicationException;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class EntityFieldEntityServiceImpl 
	extends AbstractEntityServiceImpl<EntityField> 
	implements EntityFieldEntityService
{
	private static final String ENTITY_NAME = "EntityField";
	
	private Logger logger = LoggerFactory.getLogger(EntityFieldEntityServiceImpl.class);
	
	@Inject	EntityDataEntityService  entityDataService;
	@Inject EntityFieldTypeEntityService  entityFieldTypeService;
	@Inject EntityDataEntityService  entityDataByRelatedEntityIdService;
	@Inject EntityDataEntityService  entityDataByEntityIdService;
	
	/**
	 * @param entityClass
	 */
	public EntityFieldEntityServiceImpl() {
		super(EntityField.class);
	}

    /* (non-Javadoc)
	 * @see com.qurion.businesslogic.entityField.service.EntityField#findById(java.lang.Integer)
	 */
	@Override
	public EntityField findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.entityField.service.EntityField#findByCode(java.lang.String)
	 */
	@Override
	public EntityField findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.application.service.EntityField#findByName(java.lang.String)
	 */
	@Override
	public EntityField findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.application.service.EntityField#findAll(java.util.Map)
	 */
	@Override
	public List<EntityField> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
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
	private void initializeQueryParameters(Integer entityFieldTypeId, Integer entityDataByRelatedEntityIdId, Integer entityDataByEntityIdId, String name, String description, String storage, Character primarykeyFg, Character requiredFg, Character searchFieldFg, Character uniqueFg, Character relatedFg, Integer size, Integer maxDigits, Integer decimalPrecision, Integer sequenceNo, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) {
		queryParameters.clear();
        if(entityFieldTypeId != null)
            queryParameters.add("entityFieldType", entityFieldTypeId.toString());
        if(entityDataByRelatedEntityIdId != null)
            queryParameters.add("entityDataByRelatedEntityId", entityDataByRelatedEntityIdId.toString());
        if(entityDataByEntityIdId != null)
            queryParameters.add("entityDataByEntityId", entityDataByEntityIdId.toString());
        if(name != null)
            queryParameters.add("name", name.toString());
        if(description != null)
            queryParameters.add("description", description.toString());
        if(storage != null)
            queryParameters.add("storage", storage.toString());
        if(primarykeyFg != null)
            queryParameters.add("primarykeyFg", primarykeyFg.toString());
        if(requiredFg != null)
            queryParameters.add("requiredFg", requiredFg.toString());
        if(searchFieldFg != null)
            queryParameters.add("searchFieldFg", searchFieldFg.toString());
        if(uniqueFg != null)
            queryParameters.add("uniqueFg", uniqueFg.toString());
        if(relatedFg != null)
            queryParameters.add("relatedFg", relatedFg.toString());
        if(size != null)
            queryParameters.add("size", size.toString());
        if(maxDigits != null)
            queryParameters.add("maxDigits", maxDigits.toString());
        if(decimalPrecision != null)
            queryParameters.add("decimalPrecision", decimalPrecision.toString());
        if(sequenceNo != null)
            queryParameters.add("sequenceNo", sequenceNo.toString());
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
			MultivaluedMap<String, String> queryParameters,	CriteriaBuilder criteriaBuilder, Root<EntityField> root) 
	{
		
		List<Predicate> predicates = new ArrayList<Predicate>() ;
        if (queryParameters.containsKey("entityFieldType")) {
            Integer entityFieldTypeId = Integer.valueOf(queryParameters.getFirst("entityFieldType"));
            predicates.add(criteriaBuilder.equal(root.get("entityFieldType").get("id"), entityFieldTypeId));
        }
        if (queryParameters.containsKey("entityDataByRelatedEntityId")) {
            Integer entityDataByRelatedEntityIdId = Integer.valueOf(queryParameters.getFirst("entityDataByRelatedEntityId"));
            predicates.add(criteriaBuilder.equal(root.get("entityDataByRelatedEntityId").get("id"), entityDataByRelatedEntityIdId));
        }
        if (queryParameters.containsKey("entityDataByEntityId")) {
            Integer entityDataByEntityIdId = Integer.valueOf(queryParameters.getFirst("entityDataByEntityId"));
            predicates.add(criteriaBuilder.equal(root.get("entityDataByEntityId").get("id"), entityDataByEntityIdId));
        }
		if (queryParameters.containsKey("name")) {
            String name = queryParameters.getFirst("name");
            predicates.add(criteriaBuilder.equal(root.get("name"), name));
        }
		if (queryParameters.containsKey("description")) {
            String description = queryParameters.getFirst("description");
            predicates.add(criteriaBuilder.equal(root.get("description"), description));
        }
		if (queryParameters.containsKey("storage")) {
            String storage = queryParameters.getFirst("storage");
            predicates.add(criteriaBuilder.equal(root.get("storage"), storage));
        }
		if (queryParameters.containsKey("primarykeyFg")) {
            String primarykeyFg = queryParameters.getFirst("primarykeyFg");
            predicates.add(criteriaBuilder.equal(root.get("primarykeyFg"), primarykeyFg));
        }
		if (queryParameters.containsKey("requiredFg")) {
            String requiredFg = queryParameters.getFirst("requiredFg");
            predicates.add(criteriaBuilder.equal(root.get("requiredFg"), requiredFg));
        }
		if (queryParameters.containsKey("searchFieldFg")) {
            String searchFieldFg = queryParameters.getFirst("searchFieldFg");
            predicates.add(criteriaBuilder.equal(root.get("searchFieldFg"), searchFieldFg));
        }
		if (queryParameters.containsKey("uniqueFg")) {
            String uniqueFg = queryParameters.getFirst("uniqueFg");
            predicates.add(criteriaBuilder.equal(root.get("uniqueFg"), uniqueFg));
        }
		if (queryParameters.containsKey("relatedFg")) {
            String relatedFg = queryParameters.getFirst("relatedFg");
            predicates.add(criteriaBuilder.equal(root.get("relatedFg"), relatedFg));
        }
		if (queryParameters.containsKey("size")) {
            Integer size = Integer.valueOf(queryParameters.getFirst("size"));
            predicates.add(criteriaBuilder.equal(root.get("size"), size));
        }
		if (queryParameters.containsKey("maxDigits")) {
            Integer maxDigits = Integer.valueOf(queryParameters.getFirst("maxDigits"));
            predicates.add(criteriaBuilder.equal(root.get("maxDigits"), maxDigits));
        }
		if (queryParameters.containsKey("decimalPrecision")) {
            Integer decimalPrecision = Integer.valueOf(queryParameters.getFirst("decimalPrecision"));
            predicates.add(criteriaBuilder.equal(root.get("decimalPrecision"), decimalPrecision));
        }
		if (queryParameters.containsKey("sequenceNo")) {
            Integer sequenceNo = Integer.valueOf(queryParameters.getFirst("sequenceNo"));
            predicates.add(criteriaBuilder.equal(root.get("sequenceNo"), sequenceNo));
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
