/**
 *  Business Logic.
 */
package com.qurion.businesslogic.ordering.model;

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

import com.qurion.businesslogic.application.model.BaseEntity;
import com.qurion.businesslogic.ordering.model.RequirementType;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.RequirementType;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.Requirement;

/**
 * RequirementType 
 * @author Edward Banfa
 */
@Entity
@Table(name="REQUIREMENT_TYPE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class RequirementType  extends BaseEntity implements java.io.Serializable {
	private RequirementType requirementType;
	private String name;
	private String description;
	private Set<RequirementType> requirementTypes;
	private Set<Requirement> requirements;

    public RequirementType() {
    }

    public RequirementType(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public RequirementType(RequirementType requirementType, String name, String description, Set requirementTypes, Set requirements, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.requirementType = requirementType;
        this.name = name;
        this.description = description;
        this.requirementTypes = requirementTypes;
        this.requirements = requirements;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.versionNo = versionNo;
        this.rowTs = rowTs;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
        this.lastModifiedDt = lastModifiedDt;
        this.lastModifiedUsr = lastModifiedUsr;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="REQ_TY_ID")
    @JsonIgnore
    public RequirementType getRequirementType() 
    {
        return this.requirementType;
    }
    
    public void setRequirementType(RequirementType requirementType)
    {
        this.requirementType = requirementType;
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
	
    @Column(name="DESCRIPTION", length=150)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="requirementType")
    @JsonIgnore
    public Set<RequirementType> getRequirementTypes() 
    {
        return this.requirementTypes;
    }
    
    public void setRequirementTypes(Set<RequirementType> requirementTypes) 
    {
        this.requirementTypes = requirementTypes;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="requirementType")
    @JsonIgnore
    public Set<Requirement> getRequirements() 
    {
        return this.requirements;
    }
    
    public void setRequirements(Set<Requirement> requirements) 
    {
        this.requirements = requirements;
    }			
		
    

}
