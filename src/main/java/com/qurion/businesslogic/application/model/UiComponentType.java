/**
 *  Business Logic.
 */
package com.qurion.businesslogic.application.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.qurion.businesslogic.application.model.UiComponentType;

import java.util.Set;

import com.qurion.businesslogic.application.model.UiComponentType;

/**
 * UiComponentType 
 * @author Edward Banfa
 */
@Entity
@Table(name="UI_COMPONENT_TYPE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class UiComponentType  extends BaseEntity implements java.io.Serializable {
	private UiComponentType uiComponentType;
	private String name;
	private String description;
	private Set<UiComponentType> uiComponentTypes;

    public UiComponentType() {
    }

    public UiComponentType(String name, String description) 
    {
        this.name = name;
        this.description = description;
    }

    public UiComponentType(UiComponentType uiComponentType, String name, String description, Set uiComponentTypes) 
    {
        this.uiComponentType = uiComponentType;
        this.name = name;
        this.description = description;
        this.uiComponentTypes = uiComponentTypes;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARENT_TY_ID")
    @JsonIgnore
    public UiComponentType getUiComponentType() 
    {
        return this.uiComponentType;
    }
    
    public void setUiComponentType(UiComponentType uiComponentType)
    {
        this.uiComponentType = uiComponentType;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="uiComponentType")
    @JsonIgnore
    public Set<UiComponentType> getUiComponentTypes() 
    {
        return this.uiComponentTypes;
    }
    
    public void setUiComponentTypes(Set<UiComponentType> uiComponentTypes) 
    {
        this.uiComponentTypes = uiComponentTypes;
    }			
		
    

}
