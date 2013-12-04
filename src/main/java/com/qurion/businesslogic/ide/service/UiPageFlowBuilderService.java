/**
 * 
 */
package com.qurion.businesslogic.ide.service;

import org.w3c.dom.Element;

import com.qurion.businesslogic.application.util.ApplicationException;

/**
 * Responsible for building page flows.
 * 
 * @author Edward Banfa
 *
 */
public interface UiPageFlowBuilderService {
	
	public static final String PAGE_FLOW = "PAGE_FLOW";
	
	/**
	 * @param rootUiComponentElement
	 * @throws ApplicationException
	 */
	public void buildPageFlow(Element rootUiComponentElement) throws ApplicationException;

}
