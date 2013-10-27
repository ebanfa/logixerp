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

import com.qurion.businesslogic.application.model.UiComponent;

import java.util.Set;

import com.qurion.businesslogic.application.model.UiComponentAttribute;

import java.util.Set;

import com.qurion.businesslogic.application.model.UiComponent;

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
	private UiComponent uiComponent;
	private Integer uiCompTyId;
	private Integer entityId;
	private Integer entityFieldId;
	private String name;
	private String targetNm;
	private String description;
	private Set<UiComponentAttribute> uiComponentAttributes;
	private Set<UiComponent> uiComponents;

    public UiComponent() {
    }

    public UiComponent(Integer uiCompTyId, String name, String description) 
    {
        this.uiCompTyId = uiCompTyId;
        this.name = name;
        this.description = description;
    }

    public UiComponent(UiComponent uiComponent, Integer uiCompTyId, Integer entityId, Integer entityFieldId, String name, String targetNm, String description, Set uiComponentAttributes, Set uiComponents) 
    {
        this.uiComponent = uiComponent;
        this.uiCompTyId = uiCompTyId;
        this.entityId = entityId;
        this.entityFieldId = entityFieldId;
        this.name = name;
        this.targetNm = targetNm;
        this.description = description;
        this.uiComponentAttributes = uiComponentAttributes;
        this.uiComponents = uiComponents;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARENT_COMP_ID")
    @JsonIgnore
    public UiComponent getUiComponent() 
    {
        return this.uiComponent;
    }
    
    public void setUiComponent(UiComponent uiComponent)
    {
        this.uiComponent = uiComponent;
    }
    
    @Column(name="UI_COMP_TY_ID", nullable=false)
    public Integer getUiCompTyId() 
    {
        return this.uiCompTyId;
    }
    
    public void setUiCompTyId(Integer uiCompTyId) 
    {
        this.uiCompTyId = uiCompTyId;
    }
	
    @Column(name="ENTITY_ID")
    public Integer getEntityId() 
    {
        return this.entityId;
    }
    
    public void setEntityId(Integer entityId) 
    {
        this.entityId = entityId;
    }
	
    @Column(name="ENTITY_FIELD_ID")
    public Integer getEntityFieldId() 
    {
        return this.entityFieldId;
    }
    
    public void setEntityFieldId(Integer entityFieldId) 
    {
        this.entityFieldId = entityFieldId;
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
	
    @Column(name="TARGET_NM", length=35)
    public String getTargetNm() 
    {
        return this.targetNm;
    }
    
    public void setTargetNm(String targetNm) 
    {
        this.targetNm = targetNm;
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
		
    

}
