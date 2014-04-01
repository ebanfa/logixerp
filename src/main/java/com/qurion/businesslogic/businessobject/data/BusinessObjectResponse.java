package com.qurion.businesslogic.businessobject.data;

import java.util.ArrayList;
import java.util.List;

import com.qurion.businesslogic.application.rest.data.ResponseData;


/**
 * Holds {@link BusinessObjectData} information . 
 * 
 * @author Edward Banfa
 *
 */
public class BusinessObjectResponse extends ResponseData {

	private String businessObjectName;
	private BusinessObjectData data;
	private List<BusinessObjectData> dataList = new ArrayList<BusinessObjectData>();
	private List<BusinessObjectFieldData> dataFields = new ArrayList<BusinessObjectFieldData>();
	
	
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
	/**
	 * @return the data
	 */
	public BusinessObjectData getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(BusinessObjectData data) {
		this.data = data;
	}
	/**
	 * @return the dataList
	 */
	public List<BusinessObjectData> getDataList() {
		return dataList;
	}
	/**
	 * @param dataList the dataList to set
	 */
	public void setDataList(List<BusinessObjectData> dataList) {
		this.dataList = dataList;
	}
	/**
	 * @return the dataFields
	 */
	public List<BusinessObjectFieldData> getDataFields() {
		return dataFields;
	}
	/**
	 * @param entityListFields the dataFields to set
	 */
	public void setDataFields(List<BusinessObjectFieldData> entityListFields) {
		this.dataFields = entityListFields;
	}
	
	
}
