/**
 * 
 */
package com.qurion.businesslogic.businessobject.data;

import java.util.HashMap;
import java.util.Map;


/**
 * Contains all the information needed to 
 * search for {@link BusinessObjectData}
 * 
 * @author Edward Banfa
 *
 */
public class SearchData 
{
	private String businessObjectName;
	private Integer businessObjectId;
	private Map<String, SearchFieldData> searchFields = 
			new HashMap<String, SearchFieldData>();

	/**
	 * 
	 */
	public SearchData() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param businesObjectName
	 */
	public SearchData(String businesObjectName) {
		this.businessObjectName = businesObjectName;
	}
	
	/**
	 * @return the businesObjectName
	 */
	public String getBusinessObjectName() {
		return businessObjectName;
	}

	/**
	 * @param businesObjectName the businesObjectName to set
	 */
	public void setBusinessObjectName(String businesObjectName) {
		this.businessObjectName = businesObjectName;
	}
	
	public void addField(String fieldName, SearchFieldData field)
	{
		this.searchFields.put(fieldName, field);
	}
	
	public SearchFieldData getField(String fieldName)
	{
		return this.searchFields.get(fieldName);
	}

	/**
	 * @return the searchFields
	 */
	public Map<String, SearchFieldData> getSearchFields() {
		return searchFields;
	}

	/**
	 * @param searchFields the searchFields to set
	 */
	public void setSearchFields(Map<String, SearchFieldData> searchFields) {
		this.searchFields = searchFields;
	}

	/**
	 * @return the businessObjectId
	 */
	public Integer getBusinessObjectId() {
		return businessObjectId;
	}

	/**
	 * @param businessObjectId the businessObjectId to set
	 */
	public void setBusinessObjectId(Integer businessObjectId) {
		this.businessObjectId = businessObjectId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SearchData [businesObjectName=" + businessObjectName
				+ ", businessObjectId=" + businessObjectId + ", searchFields="
				+ searchFields + "]";
	}

	/**
	 * @return
	 */
	public boolean isEmpty() {
		if(this.searchFields.isEmpty())
			return true;
		return false;
	}

}
