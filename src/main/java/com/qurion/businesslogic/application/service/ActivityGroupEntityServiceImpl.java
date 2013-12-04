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

import com.qurion.businesslogic.application.model.ActivityGroup;
import com.qurion.businesslogic.application.model.EntityField;
import com.qurion.businesslogic.application.util.ApplicationException;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ActivityGroupEntityServiceImpl 
	extends AbstractEntityServiceImpl<ActivityGroup> 
	implements ActivityGroupEntityService
{
	private static final String ENTITY_NAME = "ActivityGroup";
	
	private Logger logger = LoggerFactory.getLogger(ActivityGroupEntityServiceImpl.class);
	
	@Inject	EntityDataEntityService  entityDataService;
	@Inject ActivityGroupTypeEntityService  activityGroupTypeService;
	@Inject ModuleEntityService  moduleService;
	
	/**
	 * @param entityClass
	 */
	public ActivityGroupEntityServiceImpl() {
		super(ActivityGroup.class);
	}

    /* (non-Javadoc)
	 * @see com.qurion.businesslogic.activityGroup.service.ActivityGroup#findById(java.lang.Integer)
	 */
	@Override
	public ActivityGroup findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.activityGroup.service.ActivityGroup#findByCode(java.lang.String)
	 */
	@Override
	public ActivityGroup findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.application.service.ActivityGroup#findByName(java.lang.String)
	 */
	@Override
	public ActivityGroup findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.application.service.ActivityGroup#findAll(java.util.Map)
	 */
	@Override
	public List<ActivityGroup> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
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
	private void initializeQueryParameters(Integer activityGroupTypeId, Integer moduleId, Integer activityGroupId, String name, String description, String groupUrl, Integer sequenceNo, String displayNm, String displayImg, Character displayFg, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) {
		queryParameters.clear();
        if(activityGroupTypeId != null)
            queryParameters.add("activityGroupType", activityGroupTypeId.toString());
        if(moduleId != null)
            queryParameters.add("module", moduleId.toString());
        if(activityGroupId != null)
            queryParameters.add("activityGroup", activityGroupId.toString());
        if(name != null)
            queryParameters.add("name", name.toString());
        if(description != null)
            queryParameters.add("description", description.toString());
        if(groupUrl != null)
            queryParameters.add("groupUrl", groupUrl.toString());
        if(sequenceNo != null)
            queryParameters.add("sequenceNo", sequenceNo.toString());
        if(displayNm != null)
            queryParameters.add("displayNm", displayNm.toString());
        if(displayImg != null)
            queryParameters.add("displayImg", displayImg.toString());
        if(displayFg != null)
            queryParameters.add("displayFg", displayFg.toString());
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
			MultivaluedMap<String, String> queryParameters,	CriteriaBuilder criteriaBuilder, Root<ActivityGroup> root) 
	{
		
		List<Predicate> predicates = new ArrayList<Predicate>() ;
        if (queryParameters.containsKey("activityGroupType")) {
            Integer activityGroupTypeId = Integer.valueOf(queryParameters.getFirst("activityGroupType"));
            predicates.add(criteriaBuilder.equal(root.get("activityGroupType").get("id"), activityGroupTypeId));
        }
        if (queryParameters.containsKey("module")) {
            Integer moduleId = Integer.valueOf(queryParameters.getFirst("module"));
            predicates.add(criteriaBuilder.equal(root.get("module").get("id"), moduleId));
        }
        if (queryParameters.containsKey("activityGroup")) {
            Integer activityGroupId = Integer.valueOf(queryParameters.getFirst("activityGroup"));
            predicates.add(criteriaBuilder.equal(root.get("activityGroup").get("id"), activityGroupId));
        }
		if (queryParameters.containsKey("name")) {
            String name = queryParameters.getFirst("name");
            predicates.add(criteriaBuilder.equal(root.get("name"), name));
        }
		if (queryParameters.containsKey("description")) {
            String description = queryParameters.getFirst("description");
            predicates.add(criteriaBuilder.equal(root.get("description"), description));
        }
		if (queryParameters.containsKey("groupUrl")) {
            String groupUrl = queryParameters.getFirst("groupUrl");
            predicates.add(criteriaBuilder.equal(root.get("groupUrl"), groupUrl));
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
		if (queryParameters.containsKey("displayFg")) {
            String displayFg = queryParameters.getFirst("displayFg");
            predicates.add(criteriaBuilder.equal(root.get("displayFg"), displayFg));
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
