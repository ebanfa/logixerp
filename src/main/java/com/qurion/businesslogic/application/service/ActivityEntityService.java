/**
 * 
 */
package com.qurion.businesslogic.application.service;


import com.qurion.businesslogic.application.model.Activity;
import com.qurion.businesslogic.application.util.ApplicationException;

/**
 * @author Edward Banfa
 *
 */
public interface ActivityEntityService extends AbstractEntityService<Activity>
{
	public static final String LIST_ACTIVITY = "LIST_ACTIVITY";
	public static final String EDIT_ACTIVITY = "EDIT_ACTIVITY";
	public static final String VIEW_ACTIVITY = "VIEW_ACTIVITY";

	public Activity findByActivityURL(String activityURL) throws ApplicationException;
}
