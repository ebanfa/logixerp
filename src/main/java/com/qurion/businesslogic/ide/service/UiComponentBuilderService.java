/**
 * 
 */
package com.qurion.businesslogic.ide.service;

import java.util.Map;

import org.w3c.dom.Node;

import com.qurion.businesslogic.application.model.UiComponent;
import com.qurion.businesslogic.application.model.UiComponentType;
import com.qurion.businesslogic.application.model.Universe;
import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.ide.config.BuilderConfiguration;

/**
 * Responsible for building {@code UiComponent}s.
 * NOTE: Component names are unique.
 * 
 * @author Edward Banfa
 *
 */
public interface UiComponentBuilderService {
	
	public static final String ACTIVITY = "activity";
	public static final String BO_DATA_PROCESSOR = "bo-data-processor";
	public static final String COMP_TY_EVENT_HANDLER = "event-handler";
	public static final String COMP_TY_UI_DATA_QUERY = "ui-data-query";
	public static final String COMP_TY_UI_DATA_QUERY_PARAM = "ui-data-query-parameter";
	public static final String URL_ATTRIBUTE = "url";
	public static final String NAME_ATTRIBUTE = "name";
	public static final String CODE_ATTRIBUTE = "code";
	public static final String SEQUENCE_ATTRIBUTE = "seq";
	public static final String MODULE_ATTRIBUTE = "moduleCode";
	public static final String DESCRIPTION_ATTRIBUTE = "description";
	public static final String DISPLAY_NAME_ATTRIBUTE = "displayName";
	public static final String DISPLAY_IMAGE_ATTRIBUTE = "displayImg";
	public static final String ACTIVITY_TY_ATTRIBUTE = "activityType";
	public static final String ACTIVITY_URL_ATTRIBUTE = "activityURL";
	public static final String ACTIVITY_COMPONENT_TYPE = "activity";
	public static final String UNIVERSE_CODE_ATTRIBUTE = "universeCode";
	

	/**
	 * @param name
	 * @return
	 * @throws ApplicationException
	 */
	public UiComponentType getComponentType(String name) throws ApplicationException ;

	/**
	 * @param configuration
	 * @param parentComponent
	 * @param componentNode
	 * @throws ApplicationException
	 */
	public void buildComponent(BuilderConfiguration configuration,
			UiComponent parentComponent, Node componentNode) throws ApplicationException; 
	
	/**
	 * @param configuration
	 * @param componentType
	 * @param parentComponent
	 * @param attributesMap
	 * @return
	 * @throws ApplicationException
	 */
	public UiComponent createComponent(BuilderConfiguration configuration, Universe universe, 
			UiComponentType componentType, UiComponent parentComponent, Map<String, String> attributesMap) throws ApplicationException;

}
