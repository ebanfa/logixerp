/**
 * 
 */
package com.qurion.businesslogic.application.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qurion.businesslogic.businessobject.data.BusinessObjectData;

/**
 * @author Edward Banfa
 *
 */
public class UiComponentData  {

	private String name;
	private String code;
	private String type;
	private String description;
	private Integer sequenceNo;
	private String uiQueryDataDescriptor;
	private Map<String, String> attributes = new HashMap<String, String>();
	private List<UiComponentData> components = new ArrayList<UiComponentData>();
	private List<BusinessObjectData> uiQueryData = new ArrayList<BusinessObjectData>();
	
	/**
	 * @return the name
	 */
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
	 * @return the code
	 */
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the components
	 */
	public List<UiComponentData> getComponents() {
		return components;
	}
	/**
	 * @param components the components to set
	 */
	public void setComponents(List<UiComponentData> components) {
		this.components = components;
	}
	/**
	 * @return the attributes
	 */
	public Map<String, String> getAttributes() {
		return attributes;
	}
	/**
	 * @return the description
	 */
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
	 * @param attributes the attributes to set
	 */
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UiComponentData [name=" + name + "]";
	}
	/**
	 * @return the sequenceNo
	 */
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
	 * @return the uiQueryData
	 */
	public List<BusinessObjectData> getUiQueryData() {
		return uiQueryData;
	}
	/**
	 * @param uiQueryData the uiQueryData to set
	 */
	public void setUiQueryData(List<BusinessObjectData> uiQueryData) {
		this.uiQueryData = uiQueryData;
	}
	/**
	 * @return the uiQueryDataDescriptor
	 */
	public String getUiQueryDataDescriptor() {
		return uiQueryDataDescriptor;
	}
	/**
	 * @param uiQueryDataDescriptor the uiQueryDataDescriptor to set
	 */
	public void setUiQueryDataDescriptor(String uiQueryDataDescriptor) {
		this.uiQueryDataDescriptor = uiQueryDataDescriptor;
	}
}
