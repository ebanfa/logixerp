/**
 * 
 */
package com.qurion.businesslogic.ide.config.models;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author Edward Banfa
 *
 */
public class FieldConfig {

	private String code;
	private String name;
	private String description;
	private String primaryKeyFlag;
	private String requiredFlag;
	private String searchFieldFlag;
	private String listFieldFlag;
	private String viewFieldFlag;
	private String editFieldFlag;
	private String createFieldFlag;
	private String deleteFieldFlag;
	private String uniqueFlag;
	private String relatedFlag;
	private String fieldType;
	
	/**
	 * @return the code
	 */
	@XmlAttribute(name="code")
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the name
	 */
	@XmlAttribute(name="name")
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the description
	 */
	@XmlAttribute(name="description")
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the primaryKeyFlag
	 */
	@XmlAttribute(name="primaryKeyFg")
	public String getPrimaryKeyFlag() {
		return primaryKeyFlag;
	}
	/**
	 * @param primaryKeyFlag the primaryKeyFlag to set
	 */
	public void setPrimaryKeyFlag(String primaryKeyFlag) {
		this.primaryKeyFlag = primaryKeyFlag;
	}
	/**
	 * @return the requiredFlag
	 */
	@XmlAttribute(name="requiredFg")
	public String getRequiredFlag() {
		return requiredFlag;
	}
	/**
	 * @param requiredFlag the requiredFlag to set
	 */
	public void setRequiredFlag(String requiredFlag) {
		this.requiredFlag = requiredFlag;
	}
	/**
	 * @return the searchFieldFlag
	 */
	@XmlAttribute(name="searchFieldFg")
	public String getSearchFieldFlag() {
		return searchFieldFlag;
	}
	/**
	 * @param searchFieldFlag the searchFieldFlag to set
	 */
	public void setSearchFieldFlag(String searchFieldFlag) {
		this.searchFieldFlag = searchFieldFlag;
	}
	/**
	 * @return the listFieldFlag
	 */
	@XmlAttribute(name="listFieldFg")
	public String getListFieldFlag() {
		return listFieldFlag;
	}
	/**
	 * @param listFieldFlag the listFieldFlag to set
	 */
	public void setListFieldFlag(String listFieldFlag) {
		this.listFieldFlag = listFieldFlag;
	}
	/**
	 * @return the viewFieldFlag
	 */
	@XmlAttribute(name="viewFieldFg")
	public String getViewFieldFlag() {
		return viewFieldFlag;
	}
	/**
	 * @param viewFieldFlag the viewFieldFlag to set
	 */
	public void setViewFieldFlag(String viewFieldFlag) {
		this.viewFieldFlag = viewFieldFlag;
	}
	/**
	 * @return the editFieldFlag
	 */
	@XmlAttribute(name="editFieldFg")
	public String getEditFieldFlag() {
		return editFieldFlag;
	}
	/**
	 * @param editFieldFlag the editFieldFlag to set
	 */
	public void setEditFieldFlag(String editFieldFlag) {
		this.editFieldFlag = editFieldFlag;
	}
	/**
	 * @return the createFieldFlag
	 */
	@XmlAttribute(name="createFieldFg")
	public String getCreateFieldFlag() {
		return createFieldFlag;
	}
	/**
	 * @param createFieldFlag the createFieldFlag to set
	 */
	public void setCreateFieldFlag(String createFieldFlag) {
		this.createFieldFlag = createFieldFlag;
	}
	/**
	 * @return the deleteFieldFlag
	 */
	@XmlAttribute(name="deleteFieldFg")
	public String getDeleteFieldFlag() {
		return deleteFieldFlag;
	}
	/**
	 * @param deleteFieldFlag the deleteFieldFlag to set
	 */
	public void setDeleteFieldFlag(String deleteFieldFlag) {
		this.deleteFieldFlag = deleteFieldFlag;
	}
	/**
	 * @return the uniqueFlag
	 */
	@XmlAttribute(name="uniqueFg")
	public String getUniqueFlag() {
		return uniqueFlag;
	}
	/**
	 * @param uniqueFlag the uniqueFlag to set
	 */
	public void setUniqueFlag(String uniqueFlag) {
		this.uniqueFlag = uniqueFlag;
	}
	/**
	 * @return the relatedFlag
	 */
	@XmlAttribute(name="relatedFg")
	public String getRelatedFlag() {
		return relatedFlag;
	}
	/**
	 * @param relatedFlag the relatedFlag to set
	 */
	public void setRelatedFlag(String relatedFlag) {
		this.relatedFlag = relatedFlag;
	}
	/**
	 * @return the fieldType
	 */
	@XmlAttribute(name="fieldType")
	public String getFieldType() {
		return fieldType;
	}
	/**
	 * @param fieldType the fieldType to set
	 */
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

}
