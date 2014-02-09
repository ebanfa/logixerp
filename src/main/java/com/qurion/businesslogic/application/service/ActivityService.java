/**
 * 
 */
package com.qurion.businesslogic.application.service;

import java.util.List;

import com.qurion.businesslogic.application.model.Activity;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.businessobject.data.BusinessObjectData;
import com.qurion.businesslogic.businessobject.data.BusinessObjectFieldData;

/**
 * @author Edward Banfa
 *
 */
public interface ActivityService {
	
	public List<BusinessObjectFieldData> getEntityListFields(String entityName) 
			throws ApplicationException;

	public List<BusinessObjectFieldData> getEntityEditFields(String entityName) 
			throws ApplicationException;

	public List<BusinessObjectFieldData> getEntityViewFields(String entityName) 
			throws ApplicationException;
	
	public List<BusinessObjectFieldData> getEntitySearchFields(String entityName)
			throws ApplicationException;
	
	public Activity findByActivityURL(String activityURL) throws ApplicationException;
	
	public List<BusinessObjectData> getUserModules(String userName) throws ApplicationException;
	
	public List<BusinessObjectData> getUserActivities(String userName) throws ApplicationException;

	public boolean isListActivity(Activity activity);

	public boolean isViewActivity(Activity activity);

	public boolean isEditActivity(Activity activity);

}
