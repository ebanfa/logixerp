/**
 * 
 */
package com.qurion.businesslogic.businessobject.util;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qurion.businesslogic.application.model.EntityData;
import com.qurion.businesslogic.application.model.BaseEntity;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.application.util.DateUtil;
import com.qurion.businesslogic.application.util.EntityUtil;
import com.qurion.businesslogic.application.util.ErrorCodes;
import com.qurion.businesslogic.application.util.ExceptionUtil;
import com.qurion.businesslogic.application.util.PropertyUtil;
import com.qurion.businesslogic.application.util.StringUtil;
import com.qurion.businesslogic.businessobject.data.BusinessObjectData;
import com.qurion.businesslogic.businessobject.data.BusinessObjectDataImpl;
import com.qurion.businesslogic.businessobject.data.BusinessObjectFieldData;
import com.qurion.businesslogic.businessobject.data.BusinessObjectFieldDataImpl;

/**
 * @author Edward Banfa
 *
 */
public class BusinessObjectUtil {
	
	public static final String ID_FIELD_NAME = "id";
	public static final String SET_METHOD_PREFIX = "set";
	private static Logger logger = LoggerFactory.getLogger(BusinessObjectUtil.class);
	
	public static BusinessObjectData clone(BusinessObjectData businessObject){
		BusinessObjectData clone = new BusinessObjectDataImpl();
		clone.setBusinessObjectClassName(businessObject.getBusinessObjectClassName());
		clone.setBusinessObjectName(businessObject.getBusinessObjectName());
		clone.setProcessCategoryCode(businessObject.getProcessCategoryCode());
		clone.setValid(businessObject.isValid());
		clone.setProcessed(businessObject.isProcessed());
		clone.setRouted(businessObject.isRouted());
		for(String dataValueName: businessObject.getDataValues().keySet())
			clone.setDataValue(dataValueName, businessObject.getDataValues().get(dataValueName));
		return clone;
	}

	public static BaseEntity businessObjectToEntityInstance(
			BusinessObjectData businessObjectData) throws ApplicationException {
		// Create a new instance of the business object
		Object object = 
				EntityUtil.newInstance(businessObjectData.getBusinessObjectClassName());
		prepareBusinessObject(businessObjectData);
		copyDataFromBusinessObject(businessObjectData, object);
		return (BaseEntity) object;
	}

	private static void copyDataFromBusinessObject(BusinessObjectData businessObjectData, Object object) throws ApplicationException {
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{
				businessObjectData,	object}, "BusinessObjectUtil.copyDataFromBusinessObject");
		// Get the class of the business object
		Class<? extends BaseEntity> businessObjectClass = (Class<? extends BaseEntity>) object.getClass();
		//
		logger.debug("Copying data from {}" , businessObjectData);
		Method[] methods = businessObjectClass.getMethods();
		Map<String, Object> dataValues = businessObjectData.getDataValues();
		for(String fieldName : dataValues.keySet()) 
		{
			String setMethodName = SET_METHOD_PREFIX + StringUtil.capitalizeFirstLetter(fieldName);
			BusinessObjectFieldData fieldData = (BusinessObjectFieldData) dataValues.get(fieldName);
			// Ignore relationship fields
			if(fieldData != null && 
					!StringUtil.isValidString(fieldData.getRelatedBusinessObjectName())) {
				// Loop through all the methods
				for(Method method: methods)
				{
					if(method.getName().equals(setMethodName)){
						Class paramType = method.getParameterTypes()[0];
						try {
							if(fieldData.getFieldValue() != null)
							{
								logger.debug("Invoking method {} with parameter "
										+ "type {}", method.getName(), paramType.getName());
								Object value = PropertyUtil.stringToActualType(
										paramType, fieldData.getFieldValue().toString());
								method.invoke(object, value);
							}
						} catch (Exception e) {
							ExceptionUtil.logException(e);
						}
					}
				}
			}
		}
	}
	public static void copyDataToBusinessObject(BusinessObjectData toBusinessObject, Object fromObject)  
			throws ApplicationException 
	{
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{
				toBusinessObject,	fromObject}, "BusinessObjectUtil.copyDataToBusinessObject");// Get the class of the business object
		Class<? extends BaseEntity> businessObjectClass = (Class<? extends BaseEntity>) fromObject.getClass();
		// Get all the methods in the class of the business object/entity
		Method[] methods = businessObjectClass.getMethods();
		// Simple filter for the methods we are interested in
		String setterMethodPrefix = "get";
		// Loop through each method
		for(Method method: methods){
			// filter
			if(method.getName().startsWith(setterMethodPrefix) && isPropertyMethod(method)){
				// Get the type of the arguments
				if(method.getParameterTypes().length == 0){
					logger.debug("Invoking method {} ", method.getName());
					try {
						// Invoke
						Object returnValue = method.invoke(fromObject, new Object[0]);
						if(returnValue != null)
							toBusinessObject.setDataValue(
									propertyNameFromMethodName(method.getName()), returnValue);
					} catch (Exception e) {
						ExceptionUtil.logAndProcessException(e, ErrorCodes.BOU_DATA_COPY_TO_BO_ERROR_CD);
					}
				}
			}
		}
	}
	/**
	 * @param name
	 * @return
	 * @throws ApplicationException
	 */
	private static String propertyNameFromMethodName(String name) throws ApplicationException 
	{
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{}, 
				"BusinessObjectUtil.propertyNameFromMethodName");
		if(name.length() > 3)
			return StringUtil.unCapitalizeFirstLetter(name.substring(3));
		return name;
	}

	/**
	 * @param method
	 * @return
	 */
	private static boolean isPropertyMethod(Method method) {
		if(method.getName().equals("getClass"))
			return false;
		return true;
	}

	/**
	 * @param toBusinessObject
	 * @param fromObject
	 * @param fieldsToCopy
	 * @throws ApplicationException
	 */
	public static void copyDataToBusinessObject(BusinessObjectData toBusinessObject, 
			Object fromObject, List<BusinessObjectFieldData> fieldsToCopy) throws ApplicationException {
		
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{
				toBusinessObject,	fromObject, fieldsToCopy}, "copyDataToBusinessObject");
		// Get the class of the business object
		Class<? extends BaseEntity> businessObjectClass = (Class<? extends BaseEntity>) fromObject.getClass();
		toBusinessObject.setBusinessObjectClassName(businessObjectClass.getName());
		// Get all the methods in the class of the business object/entity
		Method[] methods = businessObjectClass.getMethods();
		// Loop through each method
		for(Method method: methods)
		{
			for(BusinessObjectFieldData fieldData: fieldsToCopy)
			{
				if(method.getName().equalsIgnoreCase("get" + fieldData.getFieldName()))
				{
					//logger.debug("The method.getGenericReturnType() {}", method.getGenericReturnType());
					// Get the type of the arguments
					if(method.getParameterTypes().length == 0){
						try {
							BusinessObjectFieldData fieldDataValue = new BusinessObjectFieldDataImpl();
							fieldDataValue.setFieldName(fieldData.getFieldName());
							fieldDataValue.setFieldSequence(fieldData.getFieldSequence());
							fieldDataValue.setRequired(fieldData.getRequired());
							fieldDataValue.setFieldDescription(fieldData.getFieldDescription());
							// Invoke
							Object returnValue = method.invoke(fromObject, new Object[0]);
							if(returnValue != null)
							{
								Type typeOfField = method.getGenericReturnType();
								if(typeOfField.toString().contains(EntityUtil.BASE_PACKAGE_NM))
								{
									//logger.debug("Processing relationship field {} of type {}", 
											//fieldData.getFieldName(), extractEntityNameFromTypeName(typeOfField.toString()));
									fieldDataValue.setRelatedBusinessObjectName(
											extractEntityNameFromTypeName(typeOfField.toString()));
									BaseEntity relatedEntityInstance = (BaseEntity) returnValue;
									if(relatedEntityInstance != null) {
										fieldDataValue.setFieldValue(relatedEntityInstance.getId());
										fieldDataValue.setFieldText(relatedEntityInstance.getCode());
										fieldDataValue.setFieldDataType(fieldData.getFieldDataType());
									}
								}
								else {
									if(fieldData.getFieldName().equals(ID_FIELD_NAME))
										toBusinessObject.setId((Integer) returnValue);
									if(typeOfField.toString().contains("Date")){
										Date formattedDate = 
												DateUtil.convertStringToJavaDate(DateUtil.DB_DATE_FORMAT, returnValue.toString());
										DateTime dateTime = new DateTime(formattedDate);
										String formattedDateString = DateTimeFormat.forPattern(DateUtil.DEFAULT_DATE_FORMAT).print(dateTime);
										//logger.debug("Processing non relationship field {}", fieldData.getFieldName());
										fieldDataValue.setFieldValue(formattedDateString);
									}
									else {
										//logger.debug("Processing non relationship field {}", fieldData.getFieldName());
										fieldDataValue.setFieldValue(returnValue);
									}
									fieldDataValue.setFieldDataType(fieldData.getFieldDataType());
								}
							}
							toBusinessObject.setDataValue(fieldData.getFieldName(), fieldDataValue);
						} catch (Exception e) {
							ExceptionUtil.logAndProcessException(e, ErrorCodes.BOU_DATA_COPY_TO_BO_ERROR_CD);
						}
					}
				}
			}
		}
		
	}

	/**
	 * @param businessObjectData
	 * @return
	 */
	public static BusinessObjectData prepareBusinessObject(BusinessObjectData businessObjectData) {
		if(businessObjectData == null)
			businessObjectData = new BusinessObjectDataImpl();
		if(businessObjectData.getDataValue("code") == null)
			businessObjectData.setDataValue("code", DateUtil.getCurrentTime());
		if(businessObjectData.getDataValue("effectiveDt") == null)
			businessObjectData.setDataValue("effectiveDt", new Date());
		if(businessObjectData.getDataValue("createdDt") == null)
			businessObjectData.setDataValue("createdDt", new Date());
		return businessObjectData;
	}
	
	public static BusinessObjectData prepareBusinessObject(String businessObjectName, 
			String businessObjectClassName, String processCategoryCode, Map<String, Object> dataValues) {
		
		BusinessObjectData businessObjectData = new BusinessObjectDataImpl();
		businessObjectData.setBusinessObjectName(businessObjectName);
		businessObjectData.setBusinessObjectClassName(businessObjectClassName);
		businessObjectData.setProcessCategoryCode(processCategoryCode);
		businessObjectData.setDataValues(dataValues);
		businessObjectData.setDataValue("name", businessObjectName.concat("@").concat(DateUtil.getCurrentTime()));
		businessObjectData.setDataValue("code", DateUtil.getCurrentTime());
		businessObjectData.setDataValue("effectiveDt", new Date());
		businessObjectData.setDataValue("createdDt", new Date());
		businessObjectData.setDataValue("createdByUsr", "System");
		businessObjectData.setDataValue("recSt", 'A');
		return businessObjectData;
	}
	
	public static String extractEntityNameFromTypeName(String typeString)
	{
		String entityName = "";
		String[] firstSplit = typeString.split(" ");
		if(firstSplit.length > 1)
			entityName = firstSplit[1];
		if(entityName.contains("."))
			firstSplit = entityName.split("\\.");
		if(firstSplit.length > 1)
			entityName = firstSplit[firstSplit.length - 1];
		return entityName;
	}

	public static BusinessObjectData createBusinessObject(EntityData entity,
			List<BusinessObjectFieldData> entityListFields) throws ApplicationException 
	{
		BusinessObjectData businessObjectData = new BusinessObjectDataImpl();
		businessObjectData.setBusinessObjectName(entity.getName());
		businessObjectData.setBusinessObjectClassName(entity.getEntityClassNm());
		businessObjectData.setProcessCategoryCode(entity.getModule().getCode());
		Map<String, Object> dataValues = new HashMap<String, Object>();
		for(BusinessObjectFieldData fieldData : entityListFields){
			dataValues.put(fieldData.getFieldName(), fieldData);
		}
		businessObjectData.setDataValues(dataValues);
		return businessObjectData;
	}
	

}
