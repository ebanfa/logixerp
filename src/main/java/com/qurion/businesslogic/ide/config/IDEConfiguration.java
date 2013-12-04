/**
 * 
 */
package com.qurion.businesslogic.ide.config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Edward Banfa
 *
 */
@XmlRootElement(name = "ide")
public class IDEConfiguration 
{
	private BuilderConfiguration builderConfiguration;

	/**
	 * @return the builderConfiguration
	 */
	@XmlElement(name="builder")
	public BuilderConfiguration getBuilderConfiguration() {
		return builderConfiguration;
	}

	/**
	 * @param builderConfiguration the builderConfiguration to set
	 */
	public void setBuilderConfiguration(BuilderConfiguration builderConfiguration) {
		this.builderConfiguration = builderConfiguration;
	}

}
