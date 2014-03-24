/**
 * 
 */
package com.qurion.businesslogic.businessobject.data;

/**
 * Information on a field in a search query
 * 
 * @author Edward Banfa
 */
public class SearchFieldData {
	
	private String fieldName;
	private String fieldValue;
	private String fieldDataTypeName;
	private String fieldSearchOperator;
	
	/**
	 * @param fieldName
	 * @param fieldValue
	 * @param fieldDataTypeName
	 * @param fieldSearchOperator
	 */
	public SearchFieldData(String fieldName, String fieldValue,
			String fieldDataTypeName, String fieldSearchOperator) {
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		this.fieldDataTypeName = fieldDataTypeName;
		this.fieldSearchOperator = fieldSearchOperator;
	}
	
	public SearchFieldData() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}
	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	/**
	 * @return the fieldValue
	 */
	public String getFieldValue() {
		return fieldValue;
	}
	/**
	 * @param fieldValue the fieldValue to set
	 */
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	/**
	 * @return the fieldDataTypeName
	 */
	public String getFieldDataTypeName() {
		return fieldDataTypeName;
	}
	/**
	 * @param fieldDataTypeName the fieldDataTypeName to set
	 */
	public void setFieldDataTypeName(String fieldDataTypeName) {
		this.fieldDataTypeName = fieldDataTypeName;
	}
	/**
	 * @return the fieldSearchOperator
	 */
	public String getFieldSearchOperator() {
		return fieldSearchOperator;
	}
	/**
	 * @param fieldSearchOperator the fieldSearchOperator to set
	 */
	public void setFieldSearchOperator(String fieldSearchOperator) {
		this.fieldSearchOperator = fieldSearchOperator;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SearchFieldData [fieldName=" + fieldName + ", fieldValue="
				+ fieldValue + ", fieldDataTypeName=" + fieldDataTypeName
				+ ", fieldSearchOperator=" + fieldSearchOperator + "]";
	}
	
	
}
