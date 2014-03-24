/**
 * 
 */
package com.qurion.businesslogic.application.rest;

import com.qurion.businesslogic.businessobject.data.BusinessObjectDataImpl;

/**
 * @author Edward Banfa
 *
 */
public class ActivityRequestData extends RequestData {
	
	private String activityURL;
	private BusinessObjectDataImpl businessObjectData;

	/**
	 * @return the businessObjectData
	 */
	public BusinessObjectDataImpl getBusinessObjectData() {
		return businessObjectData;
	}

	/**
	 * @param businessObjectData the businessObjectData to set
	 */
	public void setBusinessObjectData(BusinessObjectDataImpl businessObjectData) {
		this.businessObjectData = businessObjectData;
	}

	/**
	 * @return the activityURL
	 */
	public String getActivityURL() {
		return activityURL;
	}

	/**
	 * @param activityURL the activityURL to set
	 */
	public void setActivityURL(String activityURL) {
		this.activityURL = activityURL;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ActivityRequestData [activityURL=" + activityURL
				+ ", businessObjectData=" + businessObjectData
				+ ", getUserName()=" + getUserName()
				+ ", getRequestIPAddress()=" + getRequestIPAddress()
				+ ", getMethod()=" + getMethod() + "]";
	}

}
