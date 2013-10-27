/**
 *  Business Logic.
 */
package com.qurion.businesslogic.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * UiComponentAttribute 
 * @author Edward Banfa
 */
@Entity
@Table(name="UI_COMPONENT_ATTRIBUTE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class UiComponentAttribute  extends BaseEntity implements java.io.Serializable {
	private UiComponent uiComponent;
	private UiComponentAttributeType uiComponentAttributeType;
	private String attrName;
	private String attrValue;

    public UiComponentAttribute() {
    }

    public UiComponentAttribute(String attrName) 
    {
        this.attrName = attrName;
    }

    public UiComponentAttribute(UiComponent uiComponent, UiComponentAttributeType uiComponentAttributeType, String attrName, String attrValue) 
    {
        this.uiComponent = uiComponent;
        this.uiComponentAttributeType = uiComponentAttributeType;
        this.attrName = attrName;
        this.attrValue = attrValue;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="UI_COMPONENT_ID")
    @JsonIgnore
    public UiComponent getUiComponent() 
    {
        return this.uiComponent;
    }
    
    public void setUiComponent(UiComponent uiComponent)
    {
        this.uiComponent = uiComponent;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="UI_COMP_ATTR_TY_ID")
    @JsonIgnore
    public UiComponentAttributeType getUiComponentAttributeType() 
    {
        return this.uiComponentAttributeType;
    }
    
    public void setUiComponentAttributeType(UiComponentAttributeType uiComponentAttributeType)
    {
        this.uiComponentAttributeType = uiComponentAttributeType;
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
	
    

}
