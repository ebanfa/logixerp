/**
 *  Business Logic.
 */
package com.qurion.businesslogic.application.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * UiComponent 
 * @author Edward Banfa
 */
@Entity
@Table(name="UI_COMPONENT"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class UiComponent  extends BaseEntity implements java.io.Serializable {
	private UiComponentType uiComponentType;
	private UiComponent uiComponent;
	private String name;
	private String description;
    private Integer sequenceNo;
    private Set<Activity> activities = new HashSet<Activity>(0);
	private Set<UiComponent> uiComponents = new HashSet<UiComponent>(0);
	private Set<UiComponentAttribute> uiComponentAttributes = new HashSet<UiComponentAttribute>(0);
	

    public UiComponent() {
    }

    public UiComponent(String name, String description) 
    {
        this.name = name;
        this.description = description;
    }

    public UiComponent(UiComponentType uiComponentType, UiComponent uiComponent, String name, 
    		String description, Set<UiComponentAttribute> uiComponentAttributes, Set<UiComponent> uiComponents) 
    {
        this.uiComponentType = uiComponentType;
        this.uiComponent = uiComponent;
        this.name = name;
        this.description = description;
        this.uiComponentAttributes = uiComponentAttributes;
        this.uiComponents = uiComponents;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="UI_COMP_TY_ID")
    @JsonIgnore
    public UiComponentType getUiComponentType() 
    {
        return this.uiComponentType;
    }
    
    public void setUiComponentType(UiComponentType uiComponentType)
    {
        this.uiComponentType = uiComponentType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARENT_COMP_ID", nullable=true )
    @JsonIgnore
    public UiComponent getUiComponent() 
    {
        return this.uiComponent;
    }
    
    public void setUiComponent(UiComponent uiComponent)
    {
        this.uiComponent = uiComponent;
    }
    
    @Column(name="NAME", nullable=false, length=75)
    public String getName() 
    {
        return this.name;
    }
    
    public void setName(String name) 
    {
        this.name = name;
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
    
    @Column(name="SEQUENCE_NO")
    public Integer getSequenceNo() {
        return this.sequenceNo;
    }
    
    public void setSequenceNo(Integer sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="uiComponent")
    @JsonIgnore
    public Set<UiComponentAttribute> getUiComponentAttributes() 
    {
        return this.uiComponentAttributes;
    }
    
    public void setUiComponentAttributes(Set<UiComponentAttribute> uiComponentAttributes) 
    {
        this.uiComponentAttributes = uiComponentAttributes;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="uiComponent")
    @JsonIgnore
    public Set<UiComponent> getUiComponents() 
    {
        return this.uiComponents;
    }
    
    public void setUiComponents(Set<UiComponent> uiComponents) 
    {
        this.uiComponents = uiComponents;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="uiComponent")
    public Set<Activity> getActivities() {
        return this.activities;
    }
    
    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }
}

