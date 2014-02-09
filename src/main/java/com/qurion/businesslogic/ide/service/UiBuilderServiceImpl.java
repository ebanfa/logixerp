/**
 * 
 */
package com.qurion.businesslogic.ide.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.qurion.businesslogic.application.service.AbstractServiceImpl;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.application.util.XMLUtil;
import com.qurion.businesslogic.ide.config.Activities;
import com.qurion.businesslogic.ide.config.BuilderConfiguration;
import com.qurion.businesslogic.ide.config.PageFlows;
import com.qurion.businesslogic.ide.config.PathElement;
import com.qurion.businesslogic.ide.config.Screens;


/**
 * @author Edward Banfa
 *
 */
@Stateless
public class UiBuilderServiceImpl extends AbstractServiceImpl implements UiBuilderService {
	
	@Inject UiPageFlowBuilderService pageFlowBuilderService;
	@Inject UiComponentBuilderService uiComponentBuilderService;
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.ide.service.UIBuilderService#processComponents(com.qurion.businesslogic.ide.config.BuilderConfiguration)
	 */
	@Override
	public void processComponents(
			BuilderConfiguration builderConfiguration) throws ApplicationException 
	{
		processScreenDefintions(builderConfiguration);
		//processPageFlowDefintions(builderConfiguration);
	}

	/**
	 * Process screen definitions.
	 * 
	 * @param builderConfiguration The configuration.
	 * @throws ApplicationException If an exception is encountered.
	 */
	private void processScreenDefintions(BuilderConfiguration configuration)
			throws ApplicationException 
	{
		logger.debug("Processing screen definitions");
		Screens screens = configuration.getScreenList();
		loadUiComponentConfigurations(configuration, screens.getScreens());
	}


	/**
	 * Process page flow definitions.
	 * 
	 * @param builderConfiguration The configuration.
	 * @throws ApplicationException If an exception is encountered.
	 */
	private void processPageFlowDefintions(
			BuilderConfiguration configuration) throws ApplicationException 
	{
		logger.debug("Processing page flow definitions");
		PageFlows flows = configuration.getPageFlowList();
		loadUiComponentConfigurations(configuration, flows.getPageFlows());
	}

	/**
	 * Loads an {@code UiComponent} definition.
	 * 
	 * @param componentType The type of component defined
	 * @param uiCompnentConfigurations Pointers to the definition files.
	 * @throws ApplicationException If an exception is encountered.
	 */
	private void loadUiComponentConfigurations(BuilderConfiguration configuration, 
			List<PathElement> uiCompnentConfigurations)	throws ApplicationException 
	{
		for(PathElement element : uiCompnentConfigurations){

			logger.debug("Loading UiComponent configuration {}", element.getPath());
			processUiComponentConfiguration(configuration, XMLUtil.getW3CDocument(element.getPath()));
		}
	}

	/**
	 * Process a {@code UiComponent} definition document.
	 * 
	 * @param componentType The type of the component defined.
	 * @param uiComponentConfig The XML document.
	 * @throws ApplicationException If an exception is encountered.
	 */
	private void processUiComponentConfiguration(BuilderConfiguration configuration, 
			Document uiComponentConfig) throws ApplicationException 
	{
		logger.debug("Processing UiComponent document {}", uiComponentConfig);
		Element rootUiComponentElement = uiComponentConfig.getDocumentElement();
		logger.debug("Processing rootUiComponentElement {}", rootUiComponentElement.getNodeName());
		rootUiComponentElement.normalize();
		// Page flows are not UI components so we short circuit the normal
		// processing here.
		if(rootUiComponentElement.getNodeName().equals(UiPageFlowBuilderService.PAGE_FLOW));
			//pageFlowBuilderService.buildPageFlow(rootUiComponentElement);
		else {
			NodeList componentsList = rootUiComponentElement.getChildNodes();
			logger.debug("Processing NodeList {} of length {} ", 
					componentsList.toString(), componentsList.getLength());
			for(int i = 0; i < componentsList.getLength(); i++) {
				if(componentsList.item(i) instanceof Element) {
					logger.debug("Processing Node {}", componentsList.item(i).getNodeName());
					uiComponentBuilderService.buildComponent(configuration, null, componentsList.item(i));
				}
			}
		}
	}

}
