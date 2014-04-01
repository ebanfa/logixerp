/**
 * 
 */
package com.qurion.businesslogic.businessobject.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qurion.businesslogic.application.model.BaseEntity;
import com.qurion.businesslogic.application.model.EntityData;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.application.util.ConfigUtil;
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

	public static final String JAVA_DATE_FIELD_TYPE = "Date";
	public static final String ID_FIELD_NAME = "id";
	public static final String CODE_DATA_VALUE = "code";
	public static final String NAME_DATA_VALUE = "name";
	public static final String REC_ST_DATA_VALUE = "recSt";
	public static final String CREATED_DT_DATA_VALUE = "createdDt";
	public static final String EFFECTIVE_DT_DATA_VALUE = "effectiveDt";
	public static final String DESCRIPTION_DATA_VALUE = "description";
	public static final String CREATED_BY_USR_DATA_VALUE = "createdByUsr";

	public static final String DATE_FIELD_TYPE = "DATE";
	public static final String CHAR_FIELD_TYPE = "CHAR";
	public static final String STATUS_FIELD_TYPE = "STATUS";
	
	public static final String GET_METHOD_PREFIX = "get";
	public static final String SET_METHOD_PREFIX = "set";
	public static final Object CODE_FIELD_TYPE = "CODE";
	public static final Object NAME_FIELD_TYPE = "NAME";
	public static final Object TEXT_FIELD_TYPE = "TEXT";
	public static final Object LARGE_TEXT_FIELD_TYPE = "LARGE_TEXT";
	
	private static Logger logger = LoggerFactory.getLogger(BusinessObjectUtil.class);
	
	/**
	 * @param businessObjectData
	 * @return
	 * @throws ApplicationException
	 */
	public static BaseEntity businessObjectToEntityInstance(
			BusinessObjectData businessObjectData) throws ApplicationException 
	{
		logger.debug("Converting business object to entity instance {}", businessObjectData);
		// Create a new instance of the business object
		// and copy the data from the BO into it
		Object object = 
				EntityUtil.newInstance(businessObjectData.getBusinessObjectClassName());
		prepareBusinessObject(businessObjectData);
		copyDataFromBusinessObject(businessObjectData, object);
		return (BaseEntity) object;
	}
	
	/**
	 * @param businessObjectData
	 * @return
	 */
	public static BusinessObjectData prepareBusinessObject(BusinessObjectData businessObjectData) 
	{
		logger.debug("Preparing business object");
		if(businessObjectData == null)
			businessObjectData = new BusinessObjectDataImpl();
		
		String date = DateUtil.convertJavaDateToString(new Date());
		
		if(businessObjectData.getDataValue(CODE_DATA_VALUE) == null)  {
			businessObjectData.setDataValue(CODE_DATA_VALUE, DateUtil.getCurrentTime());
		}
		if(businessObjectData.getDataValue(EFFECTIVE_DT_DATA_VALUE) == null) {
			businessObjectData.setDataValue(EFFECTIVE_DT_DATA_VALUE, 
					new BusinessObjectFieldDataImpl(EFFECTIVE_DT_DATA_VALUE, date, null));
		}
		if(businessObjectData.getDataValue(CREATED_DT_DATA_VALUE) == null) {
			businessObjectData.setDataValue(CREATED_DT_DATA_VALUE, 
					new BusinessObjectFieldDataImpl(CREATED_DT_DATA_VALUE, date, null));
		}
		return businessObjectData;
	}
	
	/**
	 * @param businessObjectData
	 * @param object
	 * @throws ApplicationException
	 */
	@SuppressWarnings("unchecked")
	public static void copyDataFromBusinessObject(BusinessObjectData businessObjectData, Object object) 
			throws ApplicationException 
	{
		logger.debug("Copying data from business object to entity instance {}", object);
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{
				businessObjectData,	object}, "BusinessObjectUtil.copyDataFromBusinessObject");
		// Get the class of the business object
		Class<? extends BaseEntity> businessObjectClass = (Class<? extends BaseEntity>) object.getClass();
		Method[] methods = businessObjectClass.getMethods();
		Map<String, Object> dataValues = businessObjectData.getDataValues();
		
		for(String fieldName : dataValues.keySet()) 
		{
			BusinessObjectFieldData fieldData = 
					(BusinessObjectFieldData) dataValues.get(fieldName);
			// Ignore null fields
			if(fieldData == null)
				continue;
			// Ignore relationship fields
			if(StringUtil.isValidString(fieldData.getRelatedBusinessObjectName()))
				continue;
			// Copy field data
			logger.debug("Copying data from business object field "
					.concat("{} to entity instance"), fieldData.getFieldName());
			copyDataFromBusinessObjectField(object, methods, fieldData);
		}
	}

	/**
	 * @param object
	 * @param methods
	 * @param dataValues
	 * @param fieldName
	 */
	private static void copyDataFromBusinessObjectField(Object object,
			Method[] methods, BusinessObjectFieldData fieldData) 
	{
		String setMethodName = SET_METHOD_PREFIX.concat(
				StringUtil.capitalizeFirstLetter(fieldData.getFieldName()));
		logger.debug("Using method name {}", setMethodName);
		// Loop through all the methods
		for(Method method: methods)
		{
			if(method.getName().equals(setMethodName))
				invokeEntityInstanceMethod(object, fieldData, method);
		}
	}

	/**
	 * @param object
	 * @param fieldData
	 * @param method
	 */
	@SuppressWarnings("rawtypes")
	private static void invokeEntityInstanceMethod(Object object,
			BusinessObjectFieldData fieldData, Method method) 
	{
		logger.debug("Invoking method {} for field {} with value {}",
				method.getName(), fieldData.getFieldName(), fieldData.getFieldValue());
		Class paramType = method.getParameterTypes()[0];
		try 
		{
			if(fieldData.getFieldValue() != null)
			{
				Object value = PropertyUtil.stringToActualType(
						paramType, fieldData.getFieldValue().toString());
				method.invoke(object, value);
			}
		} catch (Exception e) {
			ExceptionUtil.logException(e);
		}
	}
	
	/**
	 * @param toBusinessObject
	 * @param fromObject
	 * @param fieldsToCopy
	 * @throws ApplicationException
	 */
	@SuppressWarnings("unchecked")
	public static void copyDataToBusinessObject(BusinessObjectData toBusinessObject, 
			Object fromObject, List<BusinessObjectFieldData> fieldsToCopy) throws ApplicationException {
		
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{
				toBusinessObject,	fromObject, fieldsToCopy}, "copyDataToBusinessObject");
		// Get the class of the business object
		Class<? extends BaseEntity> businessObjectClass = 
				(Class<? extends BaseEntity>) fromObject.getClass();
		toBusinessObject.setBusinessObjectClassName(businessObjectClass.getName());
		
		// Get all the methods in the class of the business object/entity
		Method[] methods = businessObjectClass.getMethods();
		// Loop through each method
		for(Method method: methods)
			for(BusinessObjectFieldData fieldData: fieldsToCopy)
				if(method.getName().equalsIgnoreCase(GET_METHOD_PREFIX + fieldData.getFieldName()))
					copyObjectFieldToBusinessObject(toBusinessObject, fromObject, fieldData, method);
	}

	/**
	 * @param toBusinessObject
	 * @param fromObject
	 * @param fieldData
	 * @param method
	 * @throws ApplicationException
	 */
	private static void copyObjectFieldToBusinessObject(
			BusinessObjectData toBusinessObject, Object fromObject, 
			BusinessObjectFieldData fieldData, Method method) throws ApplicationException 
	{
		// Get the type of the arguments
		if(method.getParameterTypes().length == 0){
			try 
			{
				BusinessObjectFieldData fieldDataValue = new BusinessObjectFieldDataImpl();
				fieldDataValue.setFieldName(fieldData.getFieldName());
				fieldDataValue.setFieldSequence(fieldData.getFieldSequence());
				fieldDataValue.setRequired(fieldData.getRequired());
				fieldDataValue.setFieldDescription(fieldData.getFieldDescription());
				fieldDataValue.setFieldDataType(fieldData.getFieldDataType());
				// Invoke
				Object returnValue = method.invoke(fromObject, new Object[0]);
				if(returnValue != null)
					copyObjectFieldValueToBusinessObject(toBusinessObject, fromObject, 
							returnValue, method.getGenericReturnType(), fieldDataValue);
				// Set the data value for the business object
				toBusinessObject.setDataValue(fieldData.getFieldName(), fieldDataValue);
			} catch (Exception e) {
				e.printStackTrace();
				ExceptionUtil.logAndProcessException(e, ErrorCodes.BOU_DATA_COPY_TO_BO_ERROR_CD);
			}
		}
	}

	/**
	 * @param toBusinessObject
	 * @param fromObject
	 * @param method
	 * @param fieldDataValue
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static void copyObjectFieldValueToBusinessObject(
			BusinessObjectData toBusinessObject, Object fromObject, Object value, Type typeOfValue, 
			BusinessObjectFieldData fieldDataValue)	throws IllegalAccessException, InvocationTargetException 
	{
			if(typeOfValue.toString().contains(EntityUtil.BASE_PACKAGE_NM))
			{
				fieldDataValue.setRelatedBusinessObjectName(
						extractEntityNameFromTypeName(typeOfValue.toString()));
				BaseEntity relatedEntityInstance = (BaseEntity) value;
				if(relatedEntityInstance != null) {
					fieldDataValue.setFieldValue(relatedEntityInstance.getId());
					fieldDataValue.setFieldText(relatedEntityInstance.getCode());
				}
			}
			else {
				if(fieldDataValue.getFieldName().equals(ID_FIELD_NAME))
					toBusinessObject.setId((Integer) value);
				if(typeOfValue.toString().contains(JAVA_DATE_FIELD_TYPE)){
					fieldDataValue.setFieldValue(DateUtil.convertJavaDateToString((Date)value));
				}
				else {
					fieldDataValue.setFieldValue(value);
				}
			}
	}

	public static BusinessObjectData prepareBusinessObject(String businessObjectName, 
			String businessObjectClassName, String processCategoryCode, Map<String, Object> dataValues) 
	{
		BusinessObjectData businessObjectData = new BusinessObjectDataImpl();
		businessObjectData.setBusinessObjectName(businessObjectName);
		businessObjectData.setBusinessObjectClassName(businessObjectClassName);
		businessObjectData.setProcessCategoryCode(processCategoryCode);
		businessObjectData.setDataValues(dataValues);
		businessObjectData.setDataValue(NAME_DATA_VALUE, 
				businessObjectName.concat("@").concat(DateUtil.getCurrentTime()));
		businessObjectData.setDataValue(CODE_DATA_VALUE, DateUtil.getCurrentTime());
		businessObjectData.setDataValue(EFFECTIVE_DT_DATA_VALUE, new Date());
		businessObjectData.setDataValue(CREATED_DT_DATA_VALUE, new Date());
		businessObjectData.setDataValue(CREATED_BY_USR_DATA_VALUE, ConfigUtil.SYSTEM_USR_NAME);
		businessObjectData.setDataValue(REC_ST_DATA_VALUE, 'A');
		return businessObjectData;
	}
	

	/**
	 * For business objects that do not have a name
	 * and a description field. Currently we just
	 * use the value of the code field to substitute 
	 * for the missing fields
	 * 
	 * @param dataList
	 */
	public static List<BusinessObjectData> prepareListableItems(List<BusinessObjectData> dataList) {
		for(BusinessObjectData objectData: dataList)
		{
			Map<String, Object> dataValues = objectData.getDataValues();

			BusinessObjectFieldData codeFieldData = 
					(BusinessObjectFieldData) dataValues.get(CODE_DATA_VALUE);
			if(!dataValues.containsKey(NAME_DATA_VALUE))
			{
				// Whats the use of going through the process
				// if the code field is null. 
				if(codeFieldData != null){
					BusinessObjectFieldData nameFieldData = 
							new BusinessObjectFieldDataImpl(
									NAME_DATA_VALUE,
									codeFieldData.getFieldValue(),
									codeFieldData.getFieldDataType());
					dataValues.put(NAME_DATA_VALUE, nameFieldData);
				}
			}
			if(!dataValues.containsKey(DESCRIPTION_DATA_VALUE))
			{
				if(codeFieldData != null){
					BusinessObjectFieldData descriptionFieldData = 
							new BusinessObjectFieldDataImpl(
									DESCRIPTION_DATA_VALUE,
									codeFieldData.getFieldValue(),
									codeFieldData.getFieldDataType());
					dataValues.put(DESCRIPTION_DATA_VALUE, descriptionFieldData);
				}
			}
		}
		return dataList;
	}
	
	public static String extractEntityNameFromTypeName(String typeString)
	{
		String entityName = "";
		String[] firstSplit = typeString.split(StringUtil.WHITE_SPACE);
		if(firstSplit.length > 1)
			entityName = firstSplit[1];
		if(entityName.contains("."))
			firstSplit = entityName.split("\\.");
		if(firstSplit.length > 1)
			entityName = firstSplit[firstSplit.length - 1];
		return entityName;
	}

	/**
	 * @param entity
	 * @param entityListFields
	 * @return
	 * @throws ApplicationException
	 */
	public static BusinessObjectData createBusinessObject(EntityData entity,
			List<BusinessObjectFieldData> entityListFields) throws ApplicationException 
	{
		BusinessObjectData businessObjectData = new BusinessObjectDataImpl();
		businessObjectData.setBusinessObjectName(entity.getName());
		businessObjectData.setBusinessObjectClassName(entity.getEntityClassNm());
		businessObjectData.setProcessCategoryCode(entity.getModule().getCode());
		
		Map<String, Object> dataValues = new HashMap<String, Object>();
		for(BusinessObjectFieldData fieldData : entityListFields) 
			dataValues.put(fieldData.getFieldName(), fieldData);
		
		businessObjectData.setDataValues(dataValues);
		return businessObjectData;
	}
	
	/**
	 * @param businessObject
	 * @return
	 */
	public static BusinessObjectData clone(BusinessObjectData businessObject)
	{
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
	

}
