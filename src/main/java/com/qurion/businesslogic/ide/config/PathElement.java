package com.qurion.businesslogic.ide.config;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author Edward Banfa
 *
 */
public class PathElement {
	
	private String path;

	/**
	 * @return the path
	 */
	@XmlAttribute(name="path")
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PathElement [path=" + path + "]";
	}

}
