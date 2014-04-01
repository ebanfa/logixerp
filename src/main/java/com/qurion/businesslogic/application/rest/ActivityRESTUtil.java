/**
 * 
 */
package com.qurion.businesslogic.application.rest;

import java.util.Map;

import com.qurion.businesslogic.application.model.UiComponent;
import com.qurion.businesslogic.application.model.UiComponentAttribute;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.application.util.ErrorCodes;
import com.qurion.businesslogic.application.util.StringUtil;
import com.qurion.businesslogic.businessobject.data.SearchData;
import com.qurion.businesslogic.businessobject.data.SearchFieldData;

/**
 * @author Edward Banfa
 *
 */
public class ActivityRESTUtil {

	public static final String SEQ = "seq";
	public static final String NAME = "name";
	public static final String OPERATOR = "operator";
	public static final String USER_NAME = "userName";
	public static final String DESCRIPTION = "description";
	public static final String REQUEST_IP_ADDRESS = "requestIPAddress";
	
	public static final String FIELD_DATA_TYPE = "fieldDataType";
	public static final String FIELD_NAME = "fieldName";
	public static final String FIELD_VALUE = "fieldValue";

	
	/**
	 * @param businessObjectName
	 * @param uiDataQuery
	 * @param context
	 * @return
	 * @throws ApplicationException
	 */
	public static SearchData uiDataQueryToBOSearchData(String businessObjectName, 
			UiComponent uiDataQuery, Map<String, Object> context) throws ApplicationException
	{
		SearchData searchData = new SearchData();
		if(StringUtil.isValidString(businessObjectName))
			searchData.setBusinessObjectName(businessObjectName);

		// Process the child components (query parameters)
		for(UiComponent queryParameter : uiDataQuery.getUiComponents()) 
		{
			SearchFieldData fieldData = new SearchFieldData();
			for(UiComponentAttribute attribute: 
				queryParameter.getUiComponentAttributes())
			{
				String attributeName = attribute.getAttrName();
				if(attributeName.equals(OPERATOR) &&  
						StringUtil.isValidString(attribute.getAttrValue())){
					fieldData.setFieldSearchOperator(attribute.getAttrValue());
				}
				else if(attributeName.equals(FIELD_NAME) &&  
						StringUtil.isValidString(attribute.getAttrValue())){
					fieldData.setFieldName(attribute.getAttrValue());
				}
				else if(attributeName.equals(FIELD_DATA_TYPE) &&  
						StringUtil.isValidString(attribute.getAttrValue())){
					fieldData.setFieldDataTypeName(attribute.getAttrValue());
				}
				else if(attributeName.equals(FIELD_VALUE) && 
						StringUtil.isValidString(attribute.getAttrValue()))
				{
					String attributeValue = attribute.getAttrValue();
					// If the attribute value contains a colon, then it
					// is a reference to a context variable, 
					if(attributeValue.contains(StringUtil.COLON)){
						// Remove the colon
						attributeValue = attributeValue.replace(
								StringUtil.COLON, StringUtil.EMPTY_STRING);
						// TODO: This has to be improved
						fieldData.setFieldValue(
								context.get(attributeValue).toString());
					}
					else {
						fieldData.setFieldValue(attributeValue);
					}
				}
				else if(attributeName.equals(SEQ) |
						attributeName.equals(NAME)|
						attributeName.equals(DESCRIPTION)){
				}
				else
					throw new ApplicationException(
							ErrorCodes.ARS_ACTIVITY_NOT_FOUND_CD, 
							ErrorCodes.ARS_INVALID_QUERY_PARAM_MSG);
			}
			// Only add the field data if we have a valid field name
			if(StringUtil.isValidString(fieldData.getFieldName()))
				searchData.addField(fieldData.getFieldName(), fieldData);
		}
		return searchData;
	}
}
