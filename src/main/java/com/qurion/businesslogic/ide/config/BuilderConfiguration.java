/**
 * 
 */
package com.qurion.businesslogic.ide.config;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Edward Banfa
 *
 */
public class BuilderConfiguration {
	
	private String overWrite;
	private Modules moduleList;
	private Screens screenList;
	private Activities activityList;
	private PageFlows pageFlowList;
	
	

	/**
	 * @return the overWrite
	 */	
	@XmlAttribute(name="overWrite")
	public String getOverWrite() {
		return overWrite;
	}

	/**
	 * @param overWrite the overWrite to set
	 */
	public void setOverWrite(String overWrite) {
		this.overWrite = overWrite;
	}

	/**
	 * @return the moduleList
	 */
	@XmlElement(name="modules")
	public Modules getModuleList() {
		return moduleList;
	}

	/**
	 * @param moduleList the moduleList to set
	 */
	public void setModuleList(Modules moduleList) {
		this.moduleList = moduleList;
	}

	/**
	 * @return the screenList
	 */
	@XmlElement(name="screens")
	public Screens getScreenList() {
		return screenList;
	}

	/**
	 * @param screenList the screenList to set
	 */
	public void setScreenList(Screens screenList) {
		this.screenList = screenList;
	}

	/**
	 * @return the activityList
	 */
	@XmlElement(name="activities")
	public Activities getActivityList() {
		return activityList;
	}

	/**
	 * @param activityList the activityList to set
	 */
	public void setActivityList(Activities activityList) {
		this.activityList = activityList;
	}

	/**
	 * @return the pageFlowList
	 */
	@XmlElement(name="page-flows")
	public PageFlows getPageFlowList() {
		return pageFlowList;
	}

	/**
	 * @param pageFlowList the pageFlowList to set
	 */
	public void setPageFlowList(PageFlows pageFlowList) {
		this.pageFlowList = pageFlowList;
	}

}
