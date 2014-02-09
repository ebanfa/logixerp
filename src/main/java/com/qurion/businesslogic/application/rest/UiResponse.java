/**
 * 
 */
package com.qurion.businesslogic.application.rest;

import com.qurion.businesslogic.businessobject.data.BusinessObjectResponse;

/**
 * @author Edward Banfa
 *
 */
public class UiResponse extends BusinessObjectResponse {
	
	private UiComponentData uiComponentData;

	/**
	 * @return the uiComponentData
	 */
	public UiComponentData getUiComponentData() {
		return uiComponentData;
	}

	/**
	 * @param uiComponentData the uiComponentData to set
	 */
	public void setUiComponentData(UiComponentData uiComponentData) {
		this.uiComponentData = uiComponentData;
	}

}
