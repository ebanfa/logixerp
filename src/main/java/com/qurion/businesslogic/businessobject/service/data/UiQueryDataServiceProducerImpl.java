/**
 * 
 */
package com.qurion.businesslogic.businessobject.service.data;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.qurion.businesslogic.application.util.ApplicationException;
import com.qurion.businesslogic.businessobject.annotation.BusinessObjectEditFieldUiDataService;
import com.qurion.businesslogic.businessobject.annotation.BusinessObjectListUiDataService;
import com.qurion.businesslogic.businessobject.annotation.BusinessObjectSearchFieldUiDataService;
import com.qurion.businesslogic.businessobject.annotation.FavoriteActivitiesUiDataService;
import com.qurion.businesslogic.businessobject.annotation.RegistrationBusinessTypeUiDataService;

/**
 * Returns a {@link UiQueryDataService} with the specified {@code uiQueryDataServiceCode}
 * @author Edward Banfa
 *
 */
@Stateless
public class UiQueryDataServiceProducerImpl implements UiQueryDataServiceProducer {

	@Inject @FavoriteActivitiesUiDataService UiQueryDataService favoriteActivitiesUiDataService;
	@Inject @BusinessObjectListUiDataService UiQueryDataService businessObjectListUiDataService;
	@Inject @BusinessObjectEditFieldUiDataService UiQueryDataService businessObjectEditFieldUiDataService;
	@Inject @RegistrationBusinessTypeUiDataService UiQueryDataService registrationBusinessTypeUiDataService;
	@Inject @BusinessObjectSearchFieldUiDataService UiQueryDataService businessObjectSearchFieldUiDataService;

	/* (non-Javadoc)
	 * @see com.qurion.businesslogic.businessobject.service.UiQueryDataServiceProducer#getUiQueryDataService(java.lang.String)
	 */
	@Override
	public UiQueryDataService getUiQueryDataService(
			String uiQueryDataServiceCode) throws ApplicationException {
		if(uiQueryDataServiceCode.equals(BO_SEARCH_FIELD_UDQ_SERVICE))
			return businessObjectSearchFieldUiDataService;
		if(uiQueryDataServiceCode.equals(BO_EDIT_FIELD_UDQ_SERVICE))
			return businessObjectEditFieldUiDataService;
		if(uiQueryDataServiceCode.equals(FAVORITE_ACTIVITIES_UDQ_SERVICE))
			return favoriteActivitiesUiDataService;
		if(uiQueryDataServiceCode.equals(BO_LIST_UDQ_SERVICE))
			return businessObjectListUiDataService;
		if(uiQueryDataServiceCode.equals(REG_BUSINESS_TY_OPTIONS_UDQ_SERVICE))
			return registrationBusinessTypeUiDataService;
		return null;
	}}
