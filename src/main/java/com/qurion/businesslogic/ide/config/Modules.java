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

	private List<PathElement> modules;

	/**
	 * @return the modules
	 */
	@XmlElement(name="module")
	public List<PathElement> getModules() {
		return modules;
	}

	/**
	 * @param modules the modules to set
	 */
	public void setModules(List<PathElement> modules) {
		this.modules = modules;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Modules [modules=" + modules + "]";
	}
}
