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
import com.qurion.businesslogic.application.service.AbstractEntityService;
import com.qurion.businesslogic.businessobject.data.BusinessObjectFieldData;
import com.qurion.businesslogic.businessobject.util.BusinessObjectClasses;

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
	public static final String PROCESS_BO_NM = "BusinessProcess";
	public static final String ACTIVITY_BO_NM = "Activity";
	public static final String ENTITY_FIELD_BO_NM = "EntityField";
	public static final String SYSTEM_USER_BO_NM = "SystemUser";
	
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
	public static void invokeMethodOnEntityInstance(BaseEntity entityInstance, 
			BusinessObjectFieldData fieldData, Object data) throws ApplicationException 
	{
		returnOrThrowIfParamsArrayContainsNull(
				new Object[]{entityInstance, fieldData.getFieldName(), data}, "invokeMethodOnEntityInstance");
		try 
		{
			Class<? extends BaseEntity> clazz = entityInstance.getClass();
			for(Method m :clazz.getMethods())
				logger.debug("Method {}", m.getName());
			if(data instanceof BaseEntity) {

				Method method = clazz.getMethod(
						getFieldSetMethodName(fieldData.getFieldName()), 
						new Class[]{new BusinessObjectClasses()
						.getEntityNames().get(fieldData.getRelatedBusinessObjectName())});
				method.invoke(entityInstance, data);
			}
			else {

				Method method = clazz.getMethod(
						getFieldSetMethodName(fieldData.getFieldName()), new Class[]{data.getClass()});
				method.invoke(entityInstance, data);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(USR_TARGET_INVOCATION_ERROR, e.getMessage());
		}
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

	
	public static BaseEntity initializeFields(BaseEntity entity) {
		entity.setCreatedDt(DateUtil.getCurrentDate());
		entity.setEffectiveDt(DateUtil.getCurrentDate());
		entity.setRecSt(AbstractEntityService.ENTITY_STATUS_ACTIVE);
		entity.setCreatedByUsr(AbstractEntityService.SYSTEM_USR_NAME);
		return entity;
	}

}
