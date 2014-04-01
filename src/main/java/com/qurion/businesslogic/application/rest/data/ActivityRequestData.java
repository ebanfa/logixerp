/**
 * 
 */
package com.qurion.businesslogic.application.rest.data;

import com.qurion.businesslogic.businessobject.data.BusinessObjectDataImpl;

/**
 * @author Edward Banfa
 *
 */
public class ActivityRequestData extends RequestData 
{
	private String universe;
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

	/**
	 * @return the universe
	 */
	public String getUniverse() {
		return universe;
	}

	/**
	 * @param universe the universe to set
	 */
	public void setUniverse(String universe) {
		this.universe = universe;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ActivityRequestData [universe=" + universe + ", activityURL="
				+ activityURL + ", businessObjectData=" + businessObjectData
				+ "]";
	}

}
