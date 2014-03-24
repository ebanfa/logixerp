/**
 * 
 */
package com.qurion.businesslogic.businessobject.service.data;

import com.qurion.businesslogic.application.util.ApplicationException;

/**
 * @author Edward Banfa
 *
 */
public interface UiQueryDataServiceProducer {

	public static final String BO_LIST_UDQ_SERVICE = "BusinessObjectListUiDataService";
	public static final String FAVORITE_ACTIVITIES_UDQ_SERVICE = "FavoriteActivitiesUiDataService";
	public static final String BO_EDIT_FIELD_UDQ_SERVICE = "BusinessObjectEditFieldUiDataService";
	public static final String BO_SEARCH_FIELD_UDQ_SERVICE = "BusinessObjectSearchFieldUiDataService";

	public UiQueryDataService getUiQueryDataService(String uiQueryDataServiceCode) 
			throws ApplicationException;
}
