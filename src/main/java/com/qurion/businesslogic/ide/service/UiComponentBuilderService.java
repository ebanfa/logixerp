/**
 * 
 */
package com.qurion.businesslogic.ide.service;

import java.util.Map;

import org.w3c.dom.Node;

import com.qurion.businesslogic.application.model.UiComponent;
import com.qurion.businesslogic.application.model.UiComponentType;
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
	
	public static final Object ACTIVITY = "activity";
	public static final Object URL_ATTRIBUTE = "url";
	public static final Object NAME_ATTRIBUTE = "name";
	public static final Object CODE_ATTRIBUTE = "code";
	public static final Object MODULE_ATTRIBUTE = "module";
	public static final Object DESCRIPTION_ATTRIBUTE = "description";
	public static final Object DISPLAY_NAME_ATTRIBUTE = "displayName";
	public static final Object DISPLAY_IMAGE_ATTRIBUTE = "displayImg";
	public static final Object ACTIVITY_TY_ATTRIBUTE = "activityType";

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
	public UiComponent createComponent(BuilderConfiguration configuration, 
			UiComponentType componentType, UiComponent parentComponent, Map<String, String> attributesMap) throws ApplicationException;

}
