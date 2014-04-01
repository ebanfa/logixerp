/**
 * 
 */
package com.qurion.businesslogic.application.rest.data;

import com.qurion.businesslogic.businessobject.data.BusinessObjectData;

/**
 * This class encapsulates the response
 * to an activity request.
 * An activity is associated with the following:
 * 1. An entity instance
 * 2. A business process instance
 * 3. A user interface component instance.
 * 
 * @author Edward Banfa
 *
 */
public class ActivityResponse extends UiResponse {

	// A business process maybe associated
	// with a specific activity
	private BusinessObjectData processData;
	// The activity object data
	private BusinessObjectData activityData;
	
	/**
	 * @return the processData
	 */
	public BusinessObjectData getProcessData() {
		return processData;
	}
	/**
	 * @param processData the processData to set
	 */
	public void setProcessData(BusinessObjectData processData) {
		this.processData = processData;
	}
	/**
	 * @return the activityData
	 */
	public BusinessObjectData getActivityData() {
		return activityData;
	}
	/**
	 * @param activityData the activityData to set
	 */
	public void setActivityData(BusinessObjectData activityData) {
		this.activityData = activityData;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ActivityResponse [activityData=" + activityData + "]";
	}
}
