/**
 * 
 */
package com.qurion.businesslogic.application.rest.data;

/**
 * @author Edward Banfa
 *
 */
public class UiComponentQueryConfigData {
	
	public static final String DATA_QUERY_TY_QUERY = "query";
	public static final String DATA_QUERY_TY_SERVICE = "service";
	
	private String dataQueryType;
	private String businessObjectName;
	
	/**
	 * @param dataQueryType
	 * @param businessObjectName
	 */
	public UiComponentQueryConfigData(String dataQueryType, String businessObjectName) {
		this.dataQueryType = dataQueryType;
		this.businessObjectName = businessObjectName;
	}
	
	public UiComponentQueryConfigData() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the dataQueryType
	 */
	public String getDataQueryType() {
		return dataQueryType;
	}
	/**
	 * @param dataQueryType the dataQueryType to set
	 */
	public void setDataQueryType(String dataQueryType) {
		this.dataQueryType = dataQueryType;
	}
	/**
	 * @return the businessObjectName
	 */
	public String getBusinessObjectName() {
		return businessObjectName;
	}
	/**
	 * @param businessObjectName the businessObjectName to set
	 */
	public void setBusinessObjectName(String businessObjectName) {
		this.businessObjectName = businessObjectName;
	}

}
