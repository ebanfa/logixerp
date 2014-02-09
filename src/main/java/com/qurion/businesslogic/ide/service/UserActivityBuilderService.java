/**
 * 
 */
package com.qurion.businesslogic.ide.service;

import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.ide.config.BuilderConfiguration;

/**
 * @author Edward Banfa
 *
 */
public interface UserActivityBuilderService {

	public static final String ACTIVITY_URL_ATTRIBUTE = "activityURL";
	public static final String DISPLAY_NM_ATTRIBUTE = "displayName";
	public static final String DISPLAY_IMG_ATTRIBUTE = "displayImg";
	public static final String MODULE_CODE_ATTRIBUTE = "moduleCode";
	public static final String ENTITY_CODE_ATTRIBUTE = "entityCode";
	public static final String ACTIVITY_TY_CODE_ATTRIBUTE = "activityType";

	/**
	 * Load and process all the activity XML definitions
	 * 
	 * @param configuration
	 * @throws ApplicationException
	 */
	public void processActivityDefintions(BuilderConfiguration configuration)
		throws ApplicationException;

}
