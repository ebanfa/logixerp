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
import com.qurion.businesslogic.ordering.model.Requirement;
import com.qurion.businesslogic.ordering.model.RequirementStatusType;

import java.util.Date;

/**
 * RequirementStatus 
 * @author Edward Banfa
 */
@Entity
@Table(name="REQUIREMENT_STATUS"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class RequirementStatus  extends BaseEntity implements java.io.Serializable {
	private Requirement requirement;
	private RequirementStatusType requirementStatusType;
	private String name;
	private Date statusDt;

    public RequirementStatus() {
    }

    public RequirementStatus(String name, Date statusDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.statusDt = statusDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public RequirementStatus(Requirement requirement, RequirementStatusType requirementStatusType, String name, Date statusDt, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.requirement = requirement;
        this.requirementStatusType = requirementStatusType;
        this.name = name;
        this.statusDt = statusDt;
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
    @JoinColumn(name="REQ_ID")
    @JsonIgnore
    public Requirement getRequirement() 
    {
        return this.requirement;
    }
    
    public void setRequirement(Requirement requirement)
    {
        this.requirement = requirement;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="REQ_STAT_TY_ID")
    @JsonIgnore
    public RequirementStatusType getRequirementStatusType() 
    {
        return this.requirementStatusType;
    }
    
    public void setRequirementStatusType(RequirementStatusType requirementStatusType)
    {
        this.requirementStatusType = requirementStatusType;
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
	
    @Column(name="STATUS_DT", nullable=false, length=10)
    public Date getStatusDt() 
    {
        return this.statusDt;
    }
    
    public void setStatusDt(Date statusDt) 
    {
        this.statusDt = statusDt;
    }
	
    

}
