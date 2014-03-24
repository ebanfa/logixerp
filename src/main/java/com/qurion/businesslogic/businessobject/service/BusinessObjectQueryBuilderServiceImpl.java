/**
 * 
 */
package com.qurion.businesslogic.businessobject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qurion.businesslogic.application.model.BaseEntity;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.application.util.DateUtil;
import com.qurion.businesslogic.application.util.EntityUtil;
import com.qurion.businesslogic.application.util.IntegerUtil;
import com.qurion.businesslogic.application.util.StringUtil;
import com.qurion.businesslogic.businessobject.data.SearchData;
import com.qurion.businesslogic.businessobject.data.SearchFieldData;
import com.qurion.businesslogic.businessobject.util.BusinessObjectClasses;
import com.qurion.businesslogic.businessobject.util.BusinessObjectQueryConstants;
import com.qurion.businesslogic.businessobject.util.BusinessObjectUtil;

/**
 * @author Edward Banfa
 *
 */
public class BusinessObjectQueryBuilderServiceImpl implements BusinessObjectQueryBuilderService 
{
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Inject private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.businessobject.service.process.BusinessObjectQueryBuilderService#buildQuery(com.qurion.businesslogic.businessobject.data.SearchData)
	 */
	@Override
	public String buildQuery(SearchData data) throws ApplicationException 
	{
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(
				new Object[]{}, "BusinessObjectQueryBuilderService.buildQuery");
		logger.debug("Building business object query string from search data {}", data);
		
		String query = BusinessObjectQueryConstants.E_SELECT.concat(
				BusinessObjectQueryConstants.SINGLE_WHITESPACE);
		
		query = query.concat(data.getBusinessObjectName());
		query = query.concat(BusinessObjectQueryConstants.SINGLE_WHITESPACE);
		query = query.concat(BusinessObjectQueryConstants.AS_E_SELECT);
		Map<String, SearchFieldData> searchFields = data.getSearchFields();

		Integer currentFieldIndex = 0;
		String fieldQueryExpression = "";
		for (String fieldName : searchFields.keySet())
		{
			currentFieldIndex++;
			// The expression for the field we are processing
			SearchFieldData field = searchFields.get(fieldName);
			String fieldValue = field.getFieldValue();
			if(!StringUtil.isValidString(fieldValue))
				continue;
			// Default search operator
			String fieldOperator = BusinessObjectQueryConstants.EQUALS_TO_SYM;
			// TODO: Fix this
			/*if(field.getFieldDataTypeName() == "STRING" | 
					field.getFieldDataTypeName() == "DATE")*/
				fieldValue = BusinessObjectQueryConstants.SINGLE_QUOTES_BEGIN.concat(
						fieldValue.concat(BusinessObjectQueryConstants.SINGLE_QUOTES_END));
			// Process the search operator symbol for this field
			// so for GREATER_THAN the generated query will be
			// fieldName > fieldValue
			if(field.getFieldSearchOperator() == BusinessObjectQueryConstants.GREATER_THAN)
				fieldOperator = BusinessObjectQueryConstants.GREATER_THAN_SYM;
			else if(field.getFieldSearchOperator() == BusinessObjectQueryConstants.LESS_THAN)
				fieldOperator = BusinessObjectQueryConstants.LESS_THAN_SYM;
			else if(field.getFieldSearchOperator() == BusinessObjectQueryConstants.LIKE)
				fieldOperator = BusinessObjectQueryConstants.LIKE_SYM;
			// Skip the inter-field operator for the first field we 
			// are adding
			if(StringUtil.isValidString(fieldQueryExpression)) {
				fieldQueryExpression = 
						fieldQueryExpression.concat(BusinessObjectQueryConstants.SINGLE_WHITESPACE);
				fieldQueryExpression = 
						fieldQueryExpression.concat(BusinessObjectQueryConstants.FIELD_OP_AND);
			}
			fieldQueryExpression = 
					fieldQueryExpression.concat(BusinessObjectQueryConstants.SINGLE_WHITESPACE);
			fieldQueryExpression = 
					fieldQueryExpression.concat(fieldName);
			fieldQueryExpression = 
					fieldQueryExpression.concat(BusinessObjectQueryConstants.SINGLE_WHITESPACE);
			fieldQueryExpression = 
					fieldQueryExpression.concat(fieldOperator);
			fieldQueryExpression = 
					fieldQueryExpression.concat(BusinessObjectQueryConstants.SINGLE_WHITESPACE);
			fieldQueryExpression = 
					fieldQueryExpression.concat(fieldValue);
		}
		if(StringUtil.isValidString(fieldQueryExpression)){
			query = query.concat(BusinessObjectQueryConstants.SINGLE_WHITESPACE);
			query = query.concat(BusinessObjectQueryConstants.E_WHERE);
			query = query.concat(fieldQueryExpression);
		}
		logger.debug("The generated query: {}", query);
		return query;
	}
	
	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.businessobject.service.BusinessObjectQueryBuilderService#getQuery(com.qurion.businesslogic.businessobject.data.SearchData)
	 */
	@SuppressWarnings("unchecked")
	public TypedQuery<BaseEntity> getQuery(SearchData searchData) throws ApplicationException
	{
		logger.debug("Processing search data {}", searchData);
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        BusinessObjectClasses businessObjectClasses = new BusinessObjectClasses();
        Class<BaseEntity> businessObjectClass = 
		businessObjectClasses.getEntityNames().get(searchData.getBusinessObjectName());
	    final CriteriaQuery<BaseEntity> criteriaQuery = 
	    		criteriaBuilder.createQuery(businessObjectClass);
	    Root<BaseEntity> root = criteriaQuery.from(businessObjectClass);

    	List<Predicate> predicates = new ArrayList<Predicate>() ;
    	for(String CRITERIA : searchData.getSearchFields().keySet())
        {
    		SearchFieldData field = 
    				searchData.getSearchFields().get(CRITERIA);
    		if(field.getFieldValue()!=null) {
    			// Field names that contain a dot are considered to be
    			// relationship fields
    			Expression<String> expresion;
        		if(field.getFieldName().contains(StringUtil.DECIMAL_SYMBOL))
        		{
        			// Parse the relationship field name and the target field
        			// Replacing with colon due to some issues with calling split with 
        			// the dot character which is a regex character
        			String fieldName = field.getFieldName().replace(
        					StringUtil.DECIMAL_SYMBOL, StringUtil.COLON);
        			String[] fieldNames = fieldName.split(StringUtil.COLON);
        			// The first index is the relationship field and the second
        			// index is the target field
        			expresion = root.get(fieldNames[0]).get(fieldNames[1]);
        		}
        		else {
        			expresion = root.get(field.getFieldName());
        		}
        		if(field.getFieldSearchOperator().equals(BusinessObjectQueryConstants.EQUALS_TO))
        			predicates.add(criteriaBuilder.equal(expresion, getFieldValue(field)));
        		else if(field.getFieldSearchOperator().equals(BusinessObjectQueryConstants.LIKE))
        			predicates.add(criteriaBuilder.like(expresion, getFieldValue(field).toString()));
    		}
        }
	    
	    criteriaQuery.select(criteriaQuery.getSelection()).where(predicates.toArray(new Predicate[]{}));
	    TypedQuery<BaseEntity> query = entityManager.createQuery(criteriaQuery);
	        
        return query;
	}
	
	private Object getFieldValue(SearchFieldData fieldData) throws ApplicationException
	{
		if(StringUtil.isValidString(fieldData.getFieldDataTypeName()))
		{
			if(fieldData.getFieldDataTypeName().equals("Character")){
				return new Character(fieldData.getFieldValue().charAt(0));
			}
			if(fieldData.getFieldDataTypeName().equals(BusinessObjectUtil.CHAR_FIELD_TYPE)){
				return new Character(fieldData.getFieldValue().charAt(0));
			}
			if(fieldData.getFieldDataTypeName().equals(BusinessObjectUtil.STATUS_FIELD_TYPE)){
				return new Character(fieldData.getFieldValue().charAt(0));
			}
			if(fieldData.getFieldDataTypeName().equals("Integer")){
				return IntegerUtil.toInteger(fieldData.getFieldValue());
			}
			if(fieldData.getFieldDataTypeName().equals(BusinessObjectUtil.DATE_FIELD_TYPE)){
				return DateUtil.convertStringToJavaDate(fieldData.getFieldValue());
			}
		}
			return fieldData.getFieldValue();
	}


}
