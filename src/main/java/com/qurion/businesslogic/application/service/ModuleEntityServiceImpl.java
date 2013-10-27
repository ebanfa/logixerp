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
import com.qurion.businesslogic.application.model.Module;
import com.qurion.businesslogic.application.util.ApplicationException;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ModuleEntityServiceImpl 
	extends AbstractEntityServiceImpl<Module> 
	implements ModuleEntityService
{
	private static final String ENTITY_NAME = "Module";
	
	private Logger logger = LoggerFactory.getLogger(ModuleEntityServiceImpl.class);
	
	
	/**
	 * @param entityClass
	 */
	public ModuleEntityServiceImpl() {
		super(Module.class);
	}

    /* (non-Javadoc)
	 * @see com.qurion.businesslogic.module.service.Module#findById(java.lang.Integer)
	 */
	@Override
	public Module findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.module.service.Module#findByCode(java.lang.String)
	 */
	@Override
	public Module findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.application.service.Module#findByName(java.lang.String)
	 */
	@Override
	public Module findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.application.service.Module#findAll(java.util.Map)
	 */
	@Override
	public List<Module> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.application.service.AbstractEntityService#getEntityFields()
	 */
	@Override
	public List<EntityField> getEntityFields() throws ApplicationException {
		//return entityDataService.getFieldsForEntity(ENTITY_NAME);
		return null;
	}
	
	
	/**
	 * 
	 */
	private void initializeQueryParameters(String name, String description, Integer sequenceNo, String displayNm, String displayImg, Character displayFg, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) {
		queryParameters.clear();
        if(name != null)
            queryParameters.add("name", name.toString());
        if(description != null)
            queryParameters.add("description", description.toString());
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
			MultivaluedMap<String, String> queryParameters,	CriteriaBuilder criteriaBuilder, Root<Module> root) 
	{
		
		List<Predicate> predicates = new ArrayList<Predicate>() ;
		if (queryParameters.containsKey("name")) {
            String name = queryParameters.getFirst("name");
            predicates.add(criteriaBuilder.equal(root.get("name"), name));
        }
		if (queryParameters.containsKey("description")) {
            String description = queryParameters.getFirst("description");
            predicates.add(criteriaBuilder.equal(root.get("description"), description));
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
