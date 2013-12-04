/**
 * 
 */
package com.qurion.businesslogic.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qurion.businesslogic.application.model.EntityField;
import com.qurion.businesslogic.application.util.ApplicationException;

/**
 * @author Edward Banfa 
 *
 */
public abstract class AbstractEntityServiceImpl<M> extends AbstractServiceImpl {

    private Class<M> ENTITY_CLASS;
	protected static final String ID_CRITERIA = "id";
	protected static final String CODE_CRITERIA = "code";
	protected static final String NAME_CRITERIA = "name";
	private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject protected MultivaluedMap<String, String> queryParameters;

    /**
     * Default constructor
     */
    public AbstractEntityServiceImpl() {}

    /**
     * Parameterized constructor
     * @param ENTITY_CLASS
     */
    public AbstractEntityServiceImpl(Class<M> entityClass) {
        this.ENTITY_CLASS = entityClass;
    }

    /**
     * @param id
     * @return
     */
    protected M getSingleInstance(Integer id)  throws ApplicationException{
        return getEntityManager().find(ENTITY_CLASS, id);
    }
    
    /**
     * @param code
     * @return
     */
    protected M findInstanceByCode(String code) throws ApplicationException
    {
    	logger.debug("Finding instance of {} with code {}", ENTITY_CLASS, code);
    	queryParameters.clear();
    	queryParameters.add(CODE_CRITERIA, code);
    	List<M> instances = findAllInstances(queryParameters);
    	if(instances.isEmpty()) return null;
    	return instances.get(0);
    }
    
    /**
     * @param searchCriteria
     * @return
     */
    public List<M> findByCriteria(Map<String, String> searchCriteria) throws ApplicationException{
    	queryParameters.clear();
    	for(Entry<String, String> entry: searchCriteria.entrySet())
    	{
    		queryParameters.add(entry.getKey(), entry.getValue());
    	}
    	return findAllInstances(queryParameters);
    }
    
    /**
     * @param code
     * @return
     */
    protected M findInstanceByName(String name) throws ApplicationException
    {
    	queryParameters.clear();
    	queryParameters.add(NAME_CRITERIA, name);
    	List<M> instances = findAllInstances(queryParameters);
    	if(instances.isEmpty()) return null;
    	return instances.get(0);
    }
    
    /**
     * @param queryParameters
     * @return
     */
    public List<M> findAllInstances(MultivaluedMap<String, String> queryParameters) throws ApplicationException
    {
    	if(queryParameters == null){
    		queryParameters = this.queryParameters; 
    		queryParameters.clear();
    	}
        final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<M> criteriaQuery = criteriaBuilder.createQuery(ENTITY_CLASS);
        TypedQuery<M> query = 
        		initCriteria(queryParameters, criteriaBuilder, criteriaQuery);
        initQuery(queryParameters, query);
        //query.setMaxResults(50);
		return query.getResultList();
    }
    
    public List<EntityField> getEntitySearchFields(String entityName) throws ApplicationException
    {
    	String qlString = "SELECT o FROM ApplicationEntityField o WHERE " +
    			"o.searchFieldFg = :searchFieldFg and o.applicationEntity.name = :name";
    	Query query = getEntityManager().createQuery(qlString);
    	query.setParameter("searchFieldFg", new Character('Y'));
    	query.setParameter("name", entityName);
    	List<EntityField> fields = query.getResultList();
    	return fields;
    }
  
	/**
	 * @param queryParameters
	 * @param criteriaBuilder
	 * @param criteriaQuery
	 * @return
	 */
	private TypedQuery<M> initCriteria(
			MultivaluedMap<String, String> queryParameters,
			final CriteriaBuilder criteriaBuilder,
			final CriteriaQuery<M> criteriaQuery) {
		Root<M> root = criteriaQuery.from(ENTITY_CLASS);
        Predicate[] predicates = extractPredicates(queryParameters, criteriaBuilder, root);
        
        criteriaQuery.select(criteriaQuery.getSelection()).where(predicates);
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
        TypedQuery<M> query = getEntityManager().createQuery(criteriaQuery);
		return query;
	}

	/**
	 * @param queryParameters
	 * @param query
	 * @throws NumberFormatException
	 */
	private void initQuery(MultivaluedMap<String, String> queryParameters,
			TypedQuery<M> query) throws NumberFormatException {
		if (queryParameters.containsKey("first")) 
        {
        	Integer firstRecord = Integer.parseInt(queryParameters.getFirst("first"))-1;
        	query.setFirstResult(firstRecord);
        }
        if (queryParameters.containsKey("maxResults")) 
        {
        	Integer maxResults = Integer.parseInt(queryParameters.getFirst("maxResults"));
        	query.setMaxResults(maxResults);
        }
	}
    
    /**
     * <p>
     *     Subclasses may choose to expand the set of supported query parameters (for adding more filtering
     *     criteria on search and count) by overriding this method.
     * </p>
     * @param queryParameters - the HTTP query parameters received by the endpoint
     * @param criteriaBuilder - @{link CriteriaBuilder} used by the invoker
     * @param root  @{link Root} used by the invoker
     * @return a list of {@link Predicate}s that will added as query parameters
     */
    protected Predicate[] extractPredicates(MultivaluedMap<String, String> queryParameters, 
    		CriteriaBuilder criteriaBuilder, Root<M> root) {
    	List<Predicate> predicates = new ArrayList<Predicate>() ;
        if (queryParameters.containsKey(CODE_CRITERIA)) {
            String code = queryParameters.getFirst(CODE_CRITERIA);
            //predicates.add(criteriaBuilder.equal(root.get(CODE_CRITERIA), code));
            predicates.add(criteriaBuilder.like(root.<String>get(CODE_CRITERIA), code));
        }
        if (queryParameters.containsKey(NAME_CRITERIA)) {
            String name = queryParameters.getFirst(NAME_CRITERIA);
            predicates.add(criteriaBuilder.equal(root.get(NAME_CRITERIA), name));
        }
        for(String CRITERIA : queryParameters.keySet())
        {
        	if(CRITERIA.equals(CODE_CRITERIA));
        	else if(CRITERIA.equals(NAME_CRITERIA));
        	else {
        		if(!CRITERIA.contains(".")){
            		String VALUE = queryParameters.getFirst(CRITERIA);
                    predicates.add(criteriaBuilder.equal(root.get(CRITERIA), VALUE));
        		}
        	}
        }
        return predicates.toArray(new Predicate[]{});
    }
    
}