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
public class Screens {

	private List<PathElement> screens;

	/**
	 * @return the screens
	 */
	@XmlElement(name="screen")
	public List<PathElement> getScreens() {
		return screens;
	}

	/**
	 * @param screens the screens to set
	 */
	public void setScreens(List<PathElement> screens) {
		this.screens = screens;
	}
}
