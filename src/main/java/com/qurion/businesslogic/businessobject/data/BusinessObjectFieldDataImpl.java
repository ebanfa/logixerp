/**
 * 
 */
package com.qurion.businesslogic.businessobject.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Edward Banfa
 *
 */
public class BusinessObjectFieldDataImpl  implements BusinessObjectFieldData{
	
	
	private String fieldName;
	private Boolean required;
	private Object fieldValue;
	private Object fieldText;
	private String fieldDataType;
	private Integer fieldSequence;
	private String fieldDescription;
	private String relatedBusinessObjectName;
    private Boolean listFieldFg;
    private Boolean viewFieldFg;
    private Boolean editFieldFg;
    private Boolean createFieldFg;
    private Boolean deleteFieldFg;
	private Boolean searchFieldFg;
	private List<BusinessObjectData> fieldOptions = new ArrayList<BusinessObjectData>();
	
	/**
	 * @param fieldName
	 * @param fieldValue
	 * @param fieldDataType
	 */
	public BusinessObjectFieldDataImpl(String fieldName, Object fieldValue,
			String fieldDataType) {
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		this.fieldDataType = fieldDataType;
	}
	public BusinessObjectFieldDataImpl() {
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
	 * @return the fieldDataType
	 */
	public String getFieldDataType() {
		return fieldDataType;
	}
	/**
	 * @param fieldDataType the fieldDataType to set
	 */
	public void setFieldDataType(String fieldDataType) {
		this.fieldDataType = fieldDataType;
	}
	/**
	 * @return the fieldValue
	 */
	public Object getFieldValue() {
		return fieldValue;
	}
	/**
	 * @param fieldValue the fieldValue to set
	 */
	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}
	/**
	 * @return the fieldSequence
	 */
	public Integer getFieldSequence() {
		return fieldSequence;
	}
	/**
	 * @param fieldSequence the fieldSequence to set
	 */
	public void setFieldSequence(Integer fieldSequence) {
		this.fieldSequence = fieldSequence;
	}
	/**
	 * @return the fieldDecription
	 */
	public String getFieldDescription() {
		return fieldDescription;
	}
	/**
	 * @param fieldDecription the fieldDecription to set
	 */
	public void setFieldDescription(String fieldDecription) {
		this.fieldDescription = fieldDecription;
	}
	
	/**
	 * @return the required
	 */
	public Boolean getRequired() {
		return required;
	}
	/**
	 * @param required the required to set
	 */
	public void setRequired(Boolean required) {
		this.required = required;
	}
	
	/**
	 * @return the relatedBusinessObjectName
	 */
	public String getRelatedBusinessObjectName() {
		return relatedBusinessObjectName;
	}
	/**
	 * @param relatedBusinessObjectName the relatedBusinessObjectName to set
	 */
	public void setRelatedBusinessObjectName(String relatedBusinessObjectName) {
		this.relatedBusinessObjectName = relatedBusinessObjectName;
	}
	
	/**
	 * @return the fieldText
	 */
	public Object getFieldText() {
		return fieldText;
	}
	/**
	 * @param fieldText the fieldText to set
	 */
	public void setFieldText(Object fieldText) {
		this.fieldText = fieldText;
	}
	/**
	 * @return the listFieldFg
	 */
	public Boolean getListFieldFg() {
		return listFieldFg;
	}
	/**
	 * @param listFieldFg the listFieldFg to set
	 */
	public void setListFieldFg(Boolean listFieldFg) {
		this.listFieldFg = listFieldFg;
	}
	/**
	 * @return the viewFieldFg
	 */
	public Boolean getViewFieldFg() {
		return viewFieldFg;
	}
	/**
	 * @param viewFieldFg the viewFieldFg to set
	 */
	public void setViewFieldFg(Boolean viewFieldFg) {
		this.viewFieldFg = viewFieldFg;
	}
	/**
	 * @return the editFieldFg
	 */
	public Boolean getEditFieldFg() {
		return editFieldFg;
	}
	/**
	 * @param editFieldFg the editFieldFg to set
	 */
	public void setEditFieldFg(Boolean editFieldFg) {
		this.editFieldFg = editFieldFg;
	}
	/**
	 * @return the createFieldFg
	 */
	public Boolean getCreateFieldFg() {
		return createFieldFg;
	}
	/**
	 * @param createFieldFg the createFieldFg to set
	 */
	public void setCreateFieldFg(Boolean createFieldFg) {
		this.createFieldFg = createFieldFg;
	}
	/**
	 * @return the deleteFieldFg
	 */
	public Boolean getDeleteFieldFg() {
		return deleteFieldFg;
	}
	/**
	 * @param deleteFieldFg the deleteFieldFg to set
	 */
	public void setDeleteFieldFg(Boolean deleteFieldFg) {
		this.deleteFieldFg = deleteFieldFg;
	}
	
	public void setSearchFieldFg(Boolean searchFieldFg) {
		this.searchFieldFg = searchFieldFg;
	}
	/**
	 * @return the searchFieldFg
	 */
	public Boolean getSearchFieldFg() {
		return searchFieldFg;
	}
	/**
	 * @return the fieldOptions
	 */
	public List<BusinessObjectData> getFieldOptions() {
		return fieldOptions;
	}
	/**
	 * @param fieldOptions the fieldOptions to set
	 */
	public void setFieldOptions(List<BusinessObjectData> fieldOptions) {
		this.fieldOptions = fieldOptions;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BusinessObjectFieldDataImpl [fieldName=" + fieldName
				+ ", required=" + required + ", fieldValue=" + fieldValue
				+ ", fieldText=" + fieldText + ", fieldDataType="
				+ fieldDataType + ", relatedBusinessObjectName="+  relatedBusinessObjectName + "]";
	}

}
