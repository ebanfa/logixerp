/**
 * 
 */
package com.qurion.businesslogic.ide.config.models;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Edward Banfa
 *
 */
@XmlRootElement(name = "module")
public class ModuleConfig {

	private String code;
	private String name;
	private String description;
	private String displayName;
	private String displayFlag;
	private Integer sequenceNo;
	private List<EntityConfig> entityConfigs;

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
	 * @return the sequenceNo
	 */
	@XmlAttribute(name="seqNo")
	public Integer getSequenceNo() {
		return sequenceNo;
	}

	/**
	 * @param sequenceNo the sequenceNo to set
	 */
	public void setSequenceNo(Integer sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	/**
	 * @return the entityConfigs
	 */
	public List<EntityConfig> getEntityConfigs() {
		return entityConfigs;
	}

	/**
	 * @param entityConfigs the entityConfigs to set
	 */
	public void setEntityConfigs(List<EntityConfig> entityConfigs) {
		this.entityConfigs = entityConfigs;
	}

	/**
	 * @return the entities
	 */
	@XmlElement(name="entity")
	public List<EntityConfig> getEntities() {
		return entityConfigs;
	}

	/**
	 * @param entityConfigs the entities to set
	 */
	public void setEntities(List<EntityConfig> entityConfigs) {
		this.entityConfigs = entityConfigs;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ModuleConfig [code=" + code + ", name=" + name
				+ ", entityConfigs=" + entityConfigs.size() + "]";
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
}
