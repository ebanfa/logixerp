/**
 * 
 */
package com.qurion.businesslogic.application.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qurion.businesslogic.application.model.BaseEntity;
import com.qurion.businesslogic.application.model.EntityData;
import com.qurion.businesslogic.application.model.EntityField;

/**
 * @author Edward Banfa 
 *
 */
public class EntityUtil {

	public static final String SET_METH_NAME_SUFFIX = "set";
	public static Object NULL_VALUE = null;
	public static Logger logger = LoggerFactory.getLogger(EntityUtil.class);
	public static final String ENTITY_REQ_CLASS_SUFFIX = "Request";
	public static final String ENTITY_REQUEST_PACKAGE_NAME = ".request.";
	public static final String APP_BASE_PACKAGE_NAME = "com.qurion.businesslogic.";
	public static final String INVALID_ENTITY_CODE = "BaseDataLoader.INVALID_ENTITY_CODE";
	public static final String ENTITY_CLASS_NOT_FOUND = "BaseDataLoader.ENTITY_CLASS_NOT_FOUND";
	public static final String RELATED_REQ_ID_SUFFIX = "Id";
	public static final String USR_TARGET_INVOCATION_ERROR = "BaseDataLoader.USR_TARGET_INVOCATION_ERROR";
	public static final String INVALID_ENTITY_REQUEST_CLASS = "BaseDataLoader.INVALID_ENTITY_REQUEST_CLASS";
	public static final String UNSUPPORTED_TARGET_FIELD_TYPE = "BaseDataLoader.UNSUPPORTED_TARGET_FIELD_TYPE";
	public static final String USR_WRONG_TARGET_FIELD_TYPE = "BaseDataLoader.USR_WRONG_TARGET_FIELD_TYPE";
	public static final String BASE_PACKAGE_NM = "com.qurion.businesslogic";
	public static final String RELATIONSHIP_FIELD_CODE = "RELATIONSHIP";
	
	/**
	 * @param object
	 * @param execptionCode
	 * @param exceptionMsg
	 * @return
	 * @throws ApplicationException
	 */
	public static Object returnOrThrowIfNull(Object object, String execptionCode, String exceptionMsg) throws ApplicationException
	{
		if(object != null) return object;
		else throw new ApplicationException(execptionCode, exceptionMsg);
	}

	/**
	 * @param objects
	 * @param execptionCode
	 * @param exceptionMsg
	 * @return
	 * @throws ApplicationException
	 */
	public static Object[] returnOrThrowIfContainsNull(Object[] objects, String execptionCode, String exceptionMsg) 
			throws ApplicationException
	{
		for(int i=0; i < objects.length; i++)
			if(objects[i] == null)
				throw new ApplicationException(execptionCode, exceptionMsg);
		return objects;
	}
	/**
	 * @param objects
	 * @return
	 * @throws ApplicationException
	 */
	public static Object[] returnOrThrowIfParamsArrayContainsNull(Object[] objects) 
			throws ApplicationException
	{
		
		return returnOrThrowIfContainsNull(objects, 
				ErrorCodes.BPS_SERVICE_EXECUTION_ERROR_CD, ErrorCodes.BPS_NULL_PARAMETER_ERROR_MSG);
	}
	
	public static Object[] returnOrThrowIfParamsArrayContainsNull(Object[] objects, String message) 
			throws ApplicationException
	{
		
		return returnOrThrowIfContainsNull(objects, 
				ErrorCodes.BPS_SERVICE_EXECUTION_ERROR_CD, message);
	}

	/**
	 * Default code string that can be used as a value for the code property on an entity
	 * @return
	 */
	public static String generateDefaultEntityCode() {
		return DateUtil.getCurrentTimeInMilliSeconds().toString();
	}
	
	
	public static Class<? extends BaseEntity> getEntityClass(EntityData entity) throws ApplicationException
	{
		returnOrThrowIfParamsArrayContainsNull(new Object[]{entity}, "getEntityClass(EntityData entity)");
		String className =  entity.getEntityClassNm(); 
		return getEntityClass(className);
	}
	
	public static Class<? extends BaseEntity> getEntityClass(String className) throws ApplicationException 
	{
		returnOrThrowIfParamsArrayContainsNull(new Object[]{className}, "getEntityClass(String className)");
		Class<? extends BaseEntity> entityClass = null;
		try {
			entityClass = (Class<? extends BaseEntity>) Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new ApplicationException(ErrorCodes.EU_ENTITY_CLASS_NOT_FOUND_ERROR_CD,  e.getMessage());
		}
		return entityClass;
	}

	/**
	 * @param entityInstanceClass
	 * @param entityRequestInstance
	 * @param field
	 * @param dataType
	 * @param data
	 * @throws ApplicationException
	 */
	public static void invokeMethodOnEntityInstance(BaseEntity entityInstance, String fieldName, Object data) throws ApplicationException {
		returnOrThrowIfParamsArrayContainsNull(
				new Object[]{entityInstance, fieldName, data}, "invokeMethodOnEntityInstance");
		try 
		{
			String setFieldMethodNm = getFieldSetMethodName(fieldName);
			Method method = 
					entityInstance.getClass().getDeclaredMethod(
							setFieldMethodNm, new Class[]{data.getClass()});
			logger.debug("Executing method {} with field setter name {} on class {} for field {} of type {} with data {}", 
					method.getName(), setFieldMethodNm, entityInstance.getClass().getName(), fieldName, data.getClass(), data);
			// Cast the data into the required type and  invoke the method on the instance
			invokeMethodOnEntity(entityInstance, data, method);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(USR_TARGET_INVOCATION_ERROR, e.getMessage());
		}
	}

	public static void invokeMethodOnEntity(BaseEntity entityInstance, Object data, Method method) 
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,	ApplicationException {
		method.invoke(entityInstance, data);
		/*if(data instanceof String)
			method.invoke(entityInstance, (String) data);
		if(data instanceof Integer)
			method.invoke(entityInstance, (Integer) data);
		if(data instanceof BigDecimal)
			method.invoke(entityInstance, (BigDecimal) data);
		if(data instanceof Date)
			method.invoke(entityInstance, (Date) data);
		else
			throw new ApplicationException(USR_WRONG_TARGET_FIELD_TYPE);*/
	}

	

	/**
	 * @param field
	 */
	public static String getFieldSetMethodName(String fieldName) 
	{
		if(StringUtil.isValidString(fieldName)){
			fieldName = StringUtil.capitalizeFirstLetter(fieldName);
			return SET_METH_NAME_SUFFIX.concat(fieldName);
		}
		return fieldName;
	}
	

	public static Object newInstance(String className) {
		try {
			return Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Boolean isRelationshipField(EntityField field) throws ApplicationException{
		returnOrThrowIfParamsArrayContainsNull(new Object[]{field}, "EntityUtil.isRelationshipField");
		if(field.getEntityFieldType() != null)
			if(field.getEntityFieldType().getCode().equals(RELATIONSHIP_FIELD_CODE))
				return true;
		return false;
	}

}
