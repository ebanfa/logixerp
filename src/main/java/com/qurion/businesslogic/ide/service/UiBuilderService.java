/**
 * 
 */
package com.qurion.businesslogic.ide.service;

import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.ide.config.BuilderConfiguration;

/**
 * Primary IDE component responsible for building {@code UIComponent}s.
 * Configuration file types supported are screens, activities and page
 * flows.
 * 
 * @author Edward Banfa
 */
public interface UiBuilderService {
	
	/**
	 * Process {@code UiComponent} definitions.
	 * 
	 * @param builderConfiguration The configuration file.
	 * @throws ApplicationException If an exception occurs.
	 */
	public void processComponents(
			BuilderConfiguration builderConfiguration) throws ApplicationException;

}
