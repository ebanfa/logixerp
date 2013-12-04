/**
 * 
 */
package com.qurion.businesslogic.application.service;


import com.qurion.businesslogic.application.model.ActivityType;

/**
 * @author Edward Banfa
 *
 */
public interface ActivityTypeEntityService extends AbstractEntityService<ActivityType>
{
	public final static String LIST_ACTIVITY_CD_PREFIX = "LIST_";
	public static final String EDIT_ACTIVITY_CD_PREFIX = "EDIT_";
	public static final String VIEW_ACTIVITY_CD_PREFIX = "VIEW_";
	
	public final static String LIST_ACTIVITY_TY_CD = "LIST_ACTIVITY";
	public static final String EDIT_ACTIVITY_TY_CD = "EDIT_ACTIVITY";
	public static final String VIEW_ACTIVITY_TY_CD = "VIEW_ACTIVITY";
}
