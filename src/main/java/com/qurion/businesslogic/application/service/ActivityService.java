/**
 * 
 */
package com.qurion.businesslogic.application.service;

import java.util.List;

import com.qurion.businesslogic.application.model.Activity;
import com.qurion.businesslogic.application.model.EntityData;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.businessobject.data.BusinessObjectData;
import com.qurion.businesslogic.businessobject.data.BusinessObjectFieldData;

/**
 * @author Edward Banfa
 *
 */
public interface ActivityService {
	
	public EntityData getEntityData(String entityName) throws ApplicationException; 
	
	public List<BusinessObjectFieldData> getBusinessObjectListFields(String entityName) 
			throws ApplicationException;

	public List<BusinessObjectFieldData> getBusinessObjectEditFields(String entityName) 
			throws ApplicationException;

	public List<BusinessObjectFieldData> getBusinessObjectViewFields(String entityName) 
			throws ApplicationException;

	public List<BusinessObjectFieldData> getBusinessObjectCreateFields(String entityName) 
			throws ApplicationException;
	
	public List<BusinessObjectFieldData> getBusinessObjectDeleteFields(String entityName) 
			throws ApplicationException;
	
	public List<BusinessObjectFieldData> getBusinessObjectSearchFields(String entityName)
			throws ApplicationException;

	public List<BusinessObjectFieldData> getBusinessObjectFields(String entityName) 
			throws ApplicationException;
	
	public Activity findByActivityURL(String activityURL) throws ApplicationException;
	
	public List<BusinessObjectData> getUserModules(String userName) throws ApplicationException;
	
	public List<BusinessObjectData> getUserActivities(String userName) throws ApplicationException;

	public boolean isListActivity(Activity activity);

	public boolean isViewActivity(Activity activity);

	public boolean isEditActivity(Activity activity);

}
