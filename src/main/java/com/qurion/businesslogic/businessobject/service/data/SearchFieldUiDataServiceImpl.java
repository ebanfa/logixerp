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

import com.qurion.businesslogic.application.model.Activity;
import com.qurion.businesslogic.application.model.EntityData;
import com.qurion.businesslogic.application.service.ActivityService;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.application.util.IntegerUtil;
import com.qurion.businesslogic.businessobject.annotation.BusinessObjectSearchFieldUiDataService;
import com.qurion.businesslogic.businessobject.data.BusinessObjectData;
import com.qurion.businesslogic.businessobject.data.BusinessObjectDataImpl;
import com.qurion.businesslogic.businessobject.data.BusinessObjectFieldData;


/**
 * This class is an implementation of {@link UiQueryDataService}
 * that loads all the search fields of a {@link BusinessObjectData}
 * 
 * @author Edward Banfa
 *
 */
@Stateless
@BusinessObjectSearchFieldUiDataService
public class SearchFieldUiDataServiceImpl implements UiQueryDataService {
	
	@Inject ActivityService activityService;
	@Inject private EntityManager entityManager;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final String QUERY = "select e from Activity e where id = ";
	private static final String ACTIVITY_ID_UI_QUERY_PARAM = "uiDataQuery[activityId]";

	
	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.businessobject.service.UiQueryDataService#find(javax.ws.rs.core.MultivaluedMap)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BusinessObjectData> find(Map<String, Object> context)
			throws ApplicationException {
		List<BusinessObjectData> dataList = new ArrayList<BusinessObjectData>();
		BusinessObjectData businessObjectData = new BusinessObjectDataImpl();
		// 1. Get the entity id
		Integer activityId = IntegerUtil.toInteger(
				context.get(ACTIVITY_ID_UI_QUERY_PARAM));
		// 2. Load all user activities for the user
		String queryString = QUERY.concat(activityId.toString());
		logger.debug("Generated search field query {}", queryString);
		Query query = entityManager.createQuery(queryString);
		List<Activity> activityList = (List<Activity>) query.getResultList();
		if(!activityList.isEmpty())
		{
			logger.debug("Found {} activities", activityList.size());
			EntityData entityData = activityList.get(0).getEntityData();
			if(entityData != null)
			{
				businessObjectData.setBusinessObjectName(entityData.getName());
				businessObjectData.setBusinessObjectClassName(entityData.getEntityClassNm());
				logger.debug("Found entity {}", entityData);
				List<BusinessObjectFieldData> searchFields = 
						activityService.getBusinessObjectSearchFields(entityData.getName());
				logger.debug("Found {} fields", searchFields.size());
				// Set the field data as a data value for the business object
				for(BusinessObjectFieldData fieldData: searchFields) 
					businessObjectData.setDataValue(
							fieldData.getFieldName(), fieldData);
			}
			dataList.add(businessObjectData);
		}
		logger.debug("Returning {} search fields", dataList.size());
		return dataList;
	}

}
