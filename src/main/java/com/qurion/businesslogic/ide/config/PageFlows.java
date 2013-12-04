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
public class PageFlows {
	
	private List<PathElement> pageFlows;

	/**
	 * @return the pageFlowList
	 */
	@XmlElement(name="page-flow")
	public List<PathElement> getPageFlows() {
		return pageFlows;
	}

	/**
	 * @param pageFlowList the pageFlowList to set
	 */
	public void setPageFlows(List<PathElement> pageFlows) {
		this.pageFlows = pageFlows;
	}

}
