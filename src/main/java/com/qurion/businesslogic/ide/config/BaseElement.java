/**
 * 
 */
package com.qurion.businesslogic.ide.config;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author Edward Banfa
 *
 */
public class BaseElement {

	private String name;

	/**
	 * @return the name
	 */
	@XmlAttribute(name="name")
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BaseElement [name=" + name + "]";
	}
}
