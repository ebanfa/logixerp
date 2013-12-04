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
public class Modules {

	private List<BaseElement> modules;

	/**
	 * @return the modules
	 */
	@XmlElement(name="module")
	public List<BaseElement> getModules() {
		return modules;
	}

	/**
	 * @param modules the modules to set
	 */
	public void setModules(List<BaseElement> modules) {
		this.modules = modules;
	}
}
