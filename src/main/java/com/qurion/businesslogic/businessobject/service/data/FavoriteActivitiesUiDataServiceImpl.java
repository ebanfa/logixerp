/**
 * 
 */
package com.qurion.businesslogic.businessobject.service.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qurion.businesslogic.application.service.ActivityService;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.application.util.EntityUtil;
import com.qurion.businesslogic.application.util.ErrorCodes;
import com.qurion.businesslogic.application.util.StringUtil;
import com.qurion.businesslogic.businessobject.annotation.FavoriteActivitiesUiDataService;
import com.qurion.businesslogic.businessobject.data.BusinessObjectData;
import com.qurion.businesslogic.businessobject.data.BusinessObjectDataImpl;
import com.qurion.businesslogic.businessobject.util.BusinessObjectQueryConstants;
import com.qurion.businesslogic.businessobject.util.BusinessObjectUtil;

/**
 * This class loads all the favorite activities for a given user.
 * 
 * @author Edward Banfa
 *
 */
@Stateless
@FavoriteActivitiesUiDataService
public class FavoriteActivitiesUiDataServiceImpl implements UiQueryDataService {
	
	@Inject ActivityService activityService;
	@Inject private EntityManager entityManager;
	
	public static final String USER_NAME = "userName";
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final String QUERY = "select e.activity from UserActivity e where e.systemUser.userNm = ";

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.businessobject.service.uidata.UiQueryDataService#find(javax.ws.rs.core.MultivaluedMap)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BusinessObjectData> find(Map<String, Object> context)
			throws ApplicationException {
		List<BusinessObjectData> dataList = new ArrayList<BusinessObjectData>();
		// 1. Get the user name
		Object userName = context.get(USER_NAME);
		if(userName == null)
			throw new ApplicationException(
					ErrorCodes.UI_DATA_QUERY_ERROR_CD, 
					ErrorCodes.FAUDS_INVALID_USER_NAME_ERROR_MSG);
		
		if(!StringUtil.isValidString(userName.toString()))
			throw new ApplicationException(
					ErrorCodes.UI_DATA_QUERY_ERROR_CD, 
					ErrorCodes.FAUDS_INVALID_USER_NAME_ERROR_MSG);
		// 2. Load all user activities for the user
		String queryString = QUERY.concat(
				BusinessObjectQueryConstants.SINGLE_QUOTES_BEGIN.concat(
				userName.toString().concat(BusinessObjectQueryConstants.SINGLE_QUOTES_END)));
		
		logger.debug("Generated favorite activities query {}", queryString);
		Query query = entityManager.createQuery(queryString);
		List<Object> businessObjects = query.getResultList();
		for(Object businessObject : businessObjects)
		{
			BusinessObjectData businessObjectData = new BusinessObjectDataImpl();
			// Convert into a business object data
			BusinessObjectUtil.copyDataToBusinessObject(businessObjectData, 
					businessObject, activityService.getBusinessObjectFields(EntityUtil.ACTIVITY_BO_NM));
			businessObjectData.setBusinessObjectName(EntityUtil.ACTIVITY_BO_NM);
			dataList.add(businessObjectData);
		}
		return dataList;
	}

}
