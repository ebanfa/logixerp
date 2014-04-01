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

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qurion.businesslogic.application.model.EntityField;
import com.qurion.businesslogic.application.model.Universe;
import com.qurion.businesslogic.application.util.ApplicationException;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class UniverseEntityServiceImpl 
	extends AbstractEntityServiceImpl<Universe> 
	implements UniverseEntityService
{
	private static final String ENTITY_NAME = "Universe";
	
	private Logger logger = LoggerFactory.getLogger(UniverseEntityServiceImpl.class);
	
	@Inject	EntityDataEntityService  entityDataService;

	/**
	 * @param entityClass
	 */
	public UniverseEntityServiceImpl() {
		super(Universe.class);
	}

    /* (non-Javadoc)
	 * @see com.qurion.businesslogic.universe.service.Universe#findById(java.lang.Integer)
	 */
	@Override
	public Universe findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.universe.service.Universe#findByCode(java.lang.String)
	 */
	@Override
	public Universe findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.application.service.Universe#findByName(java.lang.String)
	 */
	@Override
	public Universe findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.application.service.Universe#findAll(java.util.Map)
	 */
	@Override
	public List<Universe> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
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
	private void initializeQueryParameters(Integer universeTypeId, Integer universeId, String businessNo, String name, String description) {
		queryParameters.clear();
        if(universeTypeId != null)
            queryParameters.add("universeType", universeTypeId.toString());
        if(universeId != null)
            queryParameters.add("universe", universeId.toString());
        if(businessNo != null)
            queryParameters.add("businessNo", businessNo.toString());
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
			MultivaluedMap<String, String> queryParameters,	CriteriaBuilder criteriaBuilder, Root<Universe> root) 
	{
		
		List<Predicate> predicates = new ArrayList<Predicate>() ;
        if (queryParameters.containsKey("universeType")) {
            Integer universeTypeId = Integer.valueOf(queryParameters.getFirst("universeType"));
            predicates.add(criteriaBuilder.equal(root.get("universeType").get("id"), universeTypeId));
        }
        if (queryParameters.containsKey("universe")) {
            Integer universeId = Integer.valueOf(queryParameters.getFirst("universe"));
            predicates.add(criteriaBuilder.equal(root.get("universe").get("id"), universeId));
        }
		if (queryParameters.containsKey("businessNo")) {
            String businessNo = queryParameters.getFirst("businessNo");
            predicates.add(criteriaBuilder.equal(root.get("businessNo"), businessNo));
        }
		if (queryParameters.containsKey("name")) {
            String name = queryParameters.getFirst("name");
            predicates.add(criteriaBuilder.equal(root.get("name"), name));
        }
		if (queryParameters.containsKey("description")) {
            String description = queryParameters.getFirst("description");
            predicates.add(criteriaBuilder.equal(root.get("description"), description));
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
