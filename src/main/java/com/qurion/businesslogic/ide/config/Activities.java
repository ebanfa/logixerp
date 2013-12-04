/**
 * 
 */
package com.qurion.businesslogic.ide.config;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author Edward Banfa
 *
 */
public class Activities {

	private List<PathElement> activities;

	/**
	 * @return the activities
	 */
	@XmlElement(name="activity")
	public List<PathElement> getActivities() {
		return activities;
	}

	/**
	 * @param activities the activities to set
	 */
	public void setActivities(List<PathElement> activities) {
		this.activities = activities;
	}
}
