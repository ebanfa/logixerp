/**
 * 
 */
package com.qurion.businesslogic.businessobject.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qurion.businesslogic.application.model.EntityData;
import com.qurion.businesslogic.application.service.EntityDataEntityService;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.application.util.EntityUtil;
import com.qurion.businesslogic.application.util.ErrorCodes;
import com.qurion.businesslogic.application.util.ExceptionUtil;
import com.qurion.businesslogic.businessobject.data.BusinessObjectData;
import com.qurion.businesslogic.businessobject.data.BusinessObjectDataImpl;
import com.qurion.businesslogic.businessobject.data.BusinessObjectFieldData;
import com.qurion.businesslogic.businessobject.data.SearchData;
import com.qurion.businesslogic.businessobject.util.BusinessObjectUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class BusinessObjectSearchServiceImpl implements	BusinessObjectSearchService {
	
	@Inject private EntityManager entityManager;
	@Inject EntityDataEntityService applicationEntityService;
	@Inject BusinessObjectQueryBuilderService queryBuilderService;
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.businessobject.service.process.BusinessObjectSearchService#find(com.qurion.businesslogic.businessobject.util.BusinessObjectSearchInfo)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<BusinessObjectData> find(SearchData searchData, List<BusinessObjectFieldData> fieldsWanted)
			throws ApplicationException 
	{
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(
				new Object[]{searchData, fieldsWanted}, "BusinessObjectSearchService.findById");
		
		List<BusinessObjectData> dataList = new ArrayList<BusinessObjectData>();
		try {
			String queryString = queryBuilderService.buildQuery(searchData);
			Query query = entityManager.createQuery(queryString);
			List<Object> businessObjects = query.getResultList();
			
			for(Object businessObject : businessObjects){
				BusinessObjectData businessObjectData = new BusinessObjectDataImpl();
				BusinessObjectUtil.copyDataToBusinessObject(businessObjectData, businessObject, fieldsWanted);
				businessObjectData.setBusinessObjectName(searchData.getBusinesObjectName());
				dataList.add(businessObjectData);
			}
			logger.debug("Received {} business objects", businessObjects.size());
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionUtil.logAndProcessException(e, ErrorCodes.BOSS_BUSINESS_OBJ_QUERY_ERROR_CD);
		}
		return dataList;
	}
	
	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.businessobject.service.process.BusinessObjectSearchService#findById(com.qurion.businesslogic.businessobject.util.SearchData)
	 */
	@Override
	public BusinessObjectData findById(String businessObjectName, 
			Integer businessObjectId, List<BusinessObjectFieldData> fieldsWanted) throws ApplicationException 
	{
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(
				new Object[]{businessObjectName, businessObjectId, fieldsWanted}, "BusinessObjectSearchService.findById");
		
		BusinessObjectData businessObjectData = null;
		try {
			EntityData entity = 
					applicationEntityService.findByName(businessObjectName);
			businessObjectData = new BusinessObjectDataImpl();
			// Get the class of the entity.
			Object businessObject = entityManager.find(EntityUtil.getEntityClass(entity), businessObjectId);
			BusinessObjectUtil.copyDataToBusinessObject(businessObjectData, businessObject, fieldsWanted);
			businessObjectData.setBusinessObjectName(businessObjectName);
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.BOSS_SINGLE_BUSINESS_OBJ_QUERY_ERROR_CD);
		}
		return businessObjectData;
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.businessobject.service.process.BusinessObjectSearchService#findSingle(com.qurion.businesslogic.businessobject.data.SearchData, java.util.List)
	 */
	@Override
	public BusinessObjectData findSingle(SearchData searchData, List<BusinessObjectFieldData> fieldsWanted)
			throws ApplicationException 
	{
		BusinessObjectData businessObjectData = null;
		try {
			List<BusinessObjectData> listOfBusinessObjectData = this.find(searchData, fieldsWanted);
			// return the first element if the list is not empty
			// else return null
			if (!listOfBusinessObjectData.isEmpty())
				return listOfBusinessObjectData.get(0);
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.BOSS_SINGLE_BUSINESS_OBJ_QUERY_ERROR_CD);
		}
		return businessObjectData;
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.businessobject.service.process.BusinessObjectSearchService#findByCode(java.lang.String, java.lang.String, java.util.List)
	 */
	@Override
	public BusinessObjectData findByCode(String businessObjectName,	String businessObjectCode, 
			List<BusinessObjectFieldData> fieldsWanted) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.businessobject.service.process.BusinessObjectSearchService#findAll(java.lang.String)
	 */
	@Override
	public List<BusinessObjectData> findAll(String businessObjectName)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}
}
