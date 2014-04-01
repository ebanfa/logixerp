/**
 * 
 */
package com.qurion.businesslogic.businessobject.service.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qurion.businesslogic.application.rest.ActivityRESTService;
import com.qurion.businesslogic.application.service.ActivityService;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.application.util.StringUtil;
import com.qurion.businesslogic.businessobject.annotation.RegistrationBusinessTypeUiDataService;
import com.qurion.businesslogic.businessobject.data.BusinessObjectData;
import com.qurion.businesslogic.businessobject.data.BusinessObjectFieldData;
import com.qurion.businesslogic.businessobject.data.SearchData;
import com.qurion.businesslogic.businessobject.data.SearchFieldData;
import com.qurion.businesslogic.businessobject.service.BusinessObjectSearchService;
import com.qurion.businesslogic.businessobject.util.BusinessObjectQueryConstants;
import com.qurion.businesslogic.businessobject.util.BusinessObjectUtil;

/**
 * @author Edward Banfa
 *
 */
@RegistrationBusinessTypeUiDataService
public class RegistrationBusinessTypeUiDataServiceImpl implements
		UiQueryDataService {

	public static final String PARTY_TYPE = "PartyType";
	@Inject ActivityService activityService;
	@Inject BusinessObjectSearchService businessObjectSearchService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.businessobject.service.uidata.UiQueryDataService#find(javax.ws.rs.core.MultivaluedMap)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BusinessObjectData> find(Map<String, Object> context)
			throws ApplicationException {
		logger.debug("Executing business object list ui query service");
		List<BusinessObjectData> dataList = new ArrayList<BusinessObjectData>();
		// 1. Get the business object from the context
		BusinessObjectData businessObjectData = 
				(BusinessObjectData)context.get(ActivityRESTService.ACTIVITY_BO_PARAM_NM);
		// All the fields of the business object this is used to 
		// determine the data type of each search field below
		// Assuming that all search fields reference only fields of the
		// business object
		List<BusinessObjectFieldData> businessObjectFields = 
				activityService.getBusinessObjectFields(PARTY_TYPE);
		// 2. Get the business object name and create a search data
		SearchData searchData = new SearchData(PARTY_TYPE);
		// 2. Loop through all the data values for each
		for(String fieldName: businessObjectData.getDataValues().keySet()){
			// 4. Create a search field data and add to the search data
			Object fieldValue = businessObjectData.getDataValues().get(fieldName);
			if(fieldValue != null){
				// Currently text search is not supported
				// TODO: Support non text searches
				if(StringUtil.isValidString(fieldValue.toString())){
					SearchFieldData searchFieldData = new SearchFieldData(fieldName, 
							fieldValue.toString(), null, BusinessObjectQueryConstants.LIKE);
					
					// Loop through all the fields of the business object and
					// if the name matches with the name of the current search field
					// the we set the data type of the search field to that of the 
					// business object field
					for(BusinessObjectFieldData businessObjectField: businessObjectFields){
						if(businessObjectField.getFieldName().equals(fieldName))
							searchFieldData.setFieldDataTypeName(businessObjectField.getFieldDataType());
					}
					// Only use text fields for search
					if(searchFieldData.getFieldDataTypeName().equals(BusinessObjectUtil.CODE_FIELD_TYPE) |
							searchFieldData.getFieldDataTypeName().equals(BusinessObjectUtil.NAME_FIELD_TYPE)|
							searchFieldData.getFieldDataTypeName().equals(BusinessObjectUtil.TEXT_FIELD_TYPE)|
							searchFieldData.getFieldDataTypeName().equals(BusinessObjectUtil.LARGE_TEXT_FIELD_TYPE))
					{
						searchData.addField(fieldName, searchFieldData);
					}
				}
			}
		}
		// 5 Call the Business Object Service to perform search and return results
		dataList = businessObjectSearchService.find(searchData, 
				activityService.getBusinessObjectListFields(businessObjectData.getBusinessObjectName()));
		
		logger.debug("Executed business object list ui query service with list of {}", context.size());
		return BusinessObjectUtil.prepareListableItems(dataList);
	}

}
