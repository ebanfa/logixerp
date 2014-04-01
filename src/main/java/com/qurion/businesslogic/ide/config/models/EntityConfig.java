/**
 * 
 */
package com.qurion.businesslogic.ide.config.models;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Edward Banfa
 *
 */
public class EntityConfig {
	
	/**
	 * @return the dbName
	 */
	@XmlAttribute(name="dbName")
	public String getDbName() {
		return dbName;
	}
	/**
	 * @param dbName the dbName to set
	 */
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	/**
	 * @return the hasTable
	 */
	@XmlAttribute(name="hasTable")
	public String getHasTable() {
		return hasTable;
	}
	/**
	 * @param hasTable the hasTable to set
	 */
	public void setHasTable(String hasTable) {
		this.hasTable = hasTable;
	}
	
	/**
	 * @return the fieldConfigs
	 */
	@XmlElement(name="field")
	public List<FieldConfig> getFieldConfigs() {
		return fieldConfigs;
	}
	/**
	 * @param fieldConfigs the fieldConfigs to set
	 */
	public void setFieldConfigs(List<FieldConfig> fieldConfigs) {
		this.fieldConfigs = fieldConfigs;
	}
	private String code;
	private String name;
	private String dbName;
	private String hasTable;
	private String description;
	private String displayFlag;
	private String displayName;
	private String displayNamePlural;
	private String entityClassName;
	private List<FieldConfig> fieldConfigs;
	
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
	 * @return the displayFlag
	 */
	@XmlAttribute(name="displayFg")
	public String getDisplayFlag() {
		return displayFlag;
	}
	/**
	 * @param displayFlag the displayFlag to set
	 */
	public void setDisplayFlag(String displayFlag) {
		this.displayFlag = displayFlag;
	}
	/**
	 * @return the displayName
	 */
	@XmlAttribute(name="displayNm")
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	/**
	 * @return the displayNamePlural
	 */
	@XmlAttribute(name="displayNmPl")
	public String getDisplayNamePlural() {
		return displayNamePlural;
	}
	/**
	 * @param displayNamePlural the displayNamePlural to set
	 */
	public void setDisplayNamePlural(String displayNamePlural) {
		this.displayNamePlural = displayNamePlural;
	}
	/**
	 * @return the entityClassName
	 */
	@XmlAttribute(name="entityClassNm")
	public String getEntityClassName() {
		return entityClassName;
	}
	/**
	 * @param entityClassName the entityClassName to set
	 */
	public void setEntityClassName(String entityClassName) {
		this.entityClassName = entityClassName;
	}
	/**
	 * @return the fields
	 */
	public List<FieldConfig> getFields() {
		return fieldConfigs;
	}
	/**
	 * @param fieldConfigs the fields to set
	 */
	public void setFields(List<FieldConfig> fieldConfigs) {
		this.fieldConfigs = fieldConfigs;
	}
}
