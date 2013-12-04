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
	private String name;
	private String description;

    public UiComponentType() {
    }

    public UiComponentType(String name, String description) 
    {
        this.name = name;
        this.description = description;
    }

    public UiComponentType(UiComponentType uiComponentType, String name, String description, Set uiComponentTypes) 
    {
        this.name = name;
        this.description = description;
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
		
}
