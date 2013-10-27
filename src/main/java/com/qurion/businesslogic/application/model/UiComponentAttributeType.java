/**
 *  Business Logic.
 */
package com.qurion.businesslogic.application.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * UiComponentAttributeType 
 * @author Edward Banfa
 */
@Entity
@Table(name="UI_COMPONENT_ATTRIBUTE_TYPE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class UiComponentAttributeType  extends BaseEntity implements java.io.Serializable {
	private String attrName;
	private String attrValue;
	private String description;
	private Set<UiComponentAttribute> uiComponentAttributes;

    public UiComponentAttributeType() {
    }

    public UiComponentAttributeType(String attrName, String description) 
    {
        this.attrName = attrName;
        this.description = description;
    }

    public UiComponentAttributeType(String attrName, String attrValue, String description, Set uiComponentAttributes) 
    {
        this.attrName = attrName;
        this.attrValue = attrValue;
        this.description = description;
        this.uiComponentAttributes = uiComponentAttributes;
    }
    
    @Column(name="ATTR_NAME", nullable=false, length=75)
    public String getAttrName() 
    {
        return this.attrName;
    }
    
    public void setAttrName(String attrName) 
    {
        this.attrName = attrName;
    }
	
    @Column(name="ATTR_VALUE", length=35)
    public String getAttrValue() 
    {
        return this.attrValue;
    }
    
    public void setAttrValue(String attrValue) 
    {
        this.attrValue = attrValue;
    }
	
    @Column(name="DESCRIPTION", nullable=false, length=150)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="uiComponentAttributeType")
    @JsonIgnore
    public Set<UiComponentAttribute> getUiComponentAttributes() 
    {
        return this.uiComponentAttributes;
    }
    
    public void setUiComponentAttributes(Set<UiComponentAttribute> uiComponentAttributes) 
    {
        this.uiComponentAttributes = uiComponentAttributes;
    }			
		
    

}
