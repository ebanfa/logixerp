/**
 * 
 */
package com.qurion.businesslogic.businessobject.data;

import java.util.List;

/**
 * @author Edward Banfa
 *
 */
public interface BusinessObjectFieldData {
	
	/**
	 * @return the fieldName
	 */
	public String getFieldName() ;
	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName);
	/**
	 * @return the fieldValue
	 */
	public Object getFieldValue();
	
	/**
	 * @param fieldValue the fieldValue to set
	 */
	public void setFieldValue(Object fieldValue) ;
	
	/**
	 * @return the fieldText
	 */
	public Object getFieldText() ;
	
	/**
	 * @param fieldText the fieldText to set
	 */
	public void setFieldText(Object fieldText) ;
	
	/**
	 * @return the fieldDataType
	 */
	public String getFieldDataType() ;
	
	/**
	 * @param fieldDataType the fieldDataType to set
	 */
	public void setFieldDataType(String fieldDataType) ;
	
	/**
	 * @return the fieldSequence
	 */
	public Integer getFieldSequence() ;
	/**
	 * @param fieldSequence the fieldSequence to set
	 */
	public void setFieldSequence(Integer fieldSequence);
	
	/**
	 * @return the fieldDecription
	 */
	public String getFieldDescription() ;
	
	/**
	 * @param fieldDecription the fieldDecription to set
	 */
	public void setFieldDescription(String fieldDecription);
	
	/**
	 * @return the required
	 */
	public Boolean getRequired() ;
	
	/**
	 * @param required the required to set
	 */
	public void setRequired(Boolean required);
	
	/**
	 * @return the listFieldFg
	 */
	public Boolean getListFieldFg() ;
	/**
	 * @param listFieldFg the listFieldFg to set
	 */
	public void setListFieldFg(Boolean listFieldFg) ;
	/**
	 * @return the viewFieldFg
	 */
	public Boolean getViewFieldFg();
	/**
	 * @param viewFieldFg the viewFieldFg to set
	 */
	public void setViewFieldFg(Boolean viewFieldFg) ;
	/**
	 * @return the editFieldFg
	 */
	public Boolean getEditFieldFg();
	/**
	 * @param editFieldFg the editFieldFg to set
	 */
	public void setEditFieldFg(Boolean editFieldFg);
	/**
	 * @return the createFieldFg
	 */
	public Boolean getCreateFieldFg() ;
	/**
	 * @param createFieldFg the createFieldFg to set
	 */
	public void setCreateFieldFg(Boolean createFieldFg);
	/**
	 * @return the deleteFieldFg
	 */
	public Boolean getDeleteFieldFg();
	/**
	 * @param deleteFieldFg the deleteFieldFg to set
	 */
	public void setDeleteFieldFg(Boolean deleteFieldFg);

	/**
	 * @return the relatedBusinessObjectName
	 */
	public String getRelatedBusinessObjectName() ;
	
	/**
	 * @param relatedBusinessObjectName the relatedBusinessObjectName to set
	 */
	public void setRelatedBusinessObjectName(String relatedBusinessObjectName) ;
	

	/**
	 * @param searchFieldFg
	 */
	public void setSearchFieldFg(Boolean searchFieldFg);
	
	/**
	 * @return
	 */
	public Boolean getSearchFieldFg() ;
	
	/**
	 * @return
	 */
	public List<BusinessObjectData> getFieldOptions();
	
	/**
	 * @param fieldOptions
	 */
	public void setFieldOptions(List<BusinessObjectData> fieldOptions);
	
}
