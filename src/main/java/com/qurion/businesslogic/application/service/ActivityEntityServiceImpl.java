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

import com.qurion.businesslogic.application.model.Activity;
import com.qurion.businesslogic.application.model.EntityField;
import com.qurion.businesslogic.application.util.ApplicationException;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ActivityEntityServiceImpl 
	extends AbstractEntityServiceImpl<Activity> 
	implements ActivityEntityService
{
	private static final String ENTITY_NAME = "Activity";
	
	private Logger logger = LoggerFactory.getLogger(ActivityEntityServiceImpl.class);
	
	@Inject ModuleEntityService  moduleService;
	@Inject EntityDataEntityService  entityDataService;
	@Inject ActivityTypeEntityService  activityTypeService;
	
	/**
	 * @param entityClass
	 */
	public ActivityEntityServiceImpl() {
		super(Activity.class);
	}

    /* (non-Javadoc)
	 * @see com.qurion.businesslogic.activity.service.Activity#findById(java.lang.Integer)
	 */
	@Override
	public Activity findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.activity.service.Activity#findByCode(java.lang.String)
	 */
	@Override
	public Activity findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.application.service.Activity#findByName(java.lang.String)
	 */
	@Override
	public Activity findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.application.service.Activity#findAll(java.util.Map)
	 */
	@Override
	public List<Activity> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
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
	private void initializeQueryParameters(Integer moduleId, Integer entityDataId, Integer activityTypeId, String name, String description, String activityUrl, Integer sequenceNo, String displayNm, String displayImg, String operationCd, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) {
		queryParameters.clear();
        if(moduleId != null)
            queryParameters.add("module", moduleId.toString());
        if(entityDataId != null)
            queryParameters.add("entityData", entityDataId.toString());
        if(activityTypeId != null)
            queryParameters.add("activityType", activityTypeId.toString());
        if(name != null)
            queryParameters.add("name", name.toString());
        if(description != null)
            queryParameters.add("description", description.toString());
        if(activityUrl != null)
            queryParameters.add("activityUrl", activityUrl.toString());
        if(sequenceNo != null)
            queryParameters.add("sequenceNo", sequenceNo.toString());
        if(displayNm != null)
            queryParameters.add("displayNm", displayNm.toString());
        if(displayImg != null)
            queryParameters.add("displayImg", displayImg.toString());
        if(operationCd != null)
            queryParameters.add("operationCd", operationCd.toString());
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
			MultivaluedMap<String, String> queryParameters,	CriteriaBuilder criteriaBuilder, Root<Activity> root) 
	{
		
		List<Predicate> predicates = new ArrayList<Predicate>() ;
        if (queryParameters.containsKey("module")) {
            Integer moduleId = Integer.valueOf(queryParameters.getFirst("module"));
            predicates.add(criteriaBuilder.equal(root.get("module").get("id"), moduleId));
        }
        if (queryParameters.containsKey("entityData")) {
            Integer entityDataId = Integer.valueOf(queryParameters.getFirst("entityData"));
            predicates.add(criteriaBuilder.equal(root.get("entityData").get("id"), entityDataId));
        }
        if (queryParameters.containsKey("activityType")) {
            Integer activityTypeId = Integer.valueOf(queryParameters.getFirst("activityType"));
            predicates.add(criteriaBuilder.equal(root.get("activityType").get("id"), activityTypeId));
        }
		if (queryParameters.containsKey("name")) {
            String name = queryParameters.getFirst("name");
            predicates.add(criteriaBuilder.equal(root.get("name"), name));
        }
		if (queryParameters.containsKey("description")) {
            String description = queryParameters.getFirst("description");
            predicates.add(criteriaBuilder.equal(root.get("description"), description));
        }
		if (queryParameters.containsKey("activityUrl")) {
            String activityUrl = queryParameters.getFirst("activityUrl");
            predicates.add(criteriaBuilder.equal(root.get("activityUrl"), activityUrl));
        }
		if (queryParameters.containsKey("sequenceNo")) {
            Integer sequenceNo = Integer.valueOf(queryParameters.getFirst("sequenceNo"));
            predicates.add(criteriaBuilder.equal(root.get("sequenceNo"), sequenceNo));
        }
		if (queryParameters.containsKey("displayNm")) {
            String displayNm = queryParameters.getFirst("displayNm");
            predicates.add(criteriaBuilder.equal(root.get("displayNm"), displayNm));
        }
		if (queryParameters.containsKey("displayImg")) {
            String displayImg = queryParameters.getFirst("displayImg");
            predicates.add(criteriaBuilder.equal(root.get("displayImg"), displayImg));
        }
		if (queryParameters.containsKey("operationCd")) {
            String operationCd = queryParameters.getFirst("operationCd");
            predicates.add(criteriaBuilder.equal(root.get("operationCd"), operationCd));
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
