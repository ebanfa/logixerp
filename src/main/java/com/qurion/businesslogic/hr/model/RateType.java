/**
 *  Business Logic.
 */
package com.qurion.businesslogic.hr.model;

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

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortAssignmentRate;

import java.util.Set;

import com.qurion.businesslogic.hr.model.PartyRate;

import java.util.Set;

import com.qurion.businesslogic.hr.model.PositionTypeRate;

/**
 * RateType 
 * @author Edward Banfa
 */
@Entity
@Table(name="RATE_TYPE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class RateType  extends BaseEntity implements java.io.Serializable {
	private String name;
	private String description;
	private Set<WorkEffortAssignmentRate> workEffortAssignmentRates;
	private Set<PartyRate> partyRates;
	private Set<PositionTypeRate> positionTypeRates;

    public RateType() {
    }

    public RateType(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public RateType(String name, String description, Set workEffortAssignmentRates, Set partyRates, Set positionTypeRates, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.name = name;
        this.description = description;
        this.workEffortAssignmentRates = workEffortAssignmentRates;
        this.partyRates = partyRates;
        this.positionTypeRates = positionTypeRates;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="rateType")
    @JsonIgnore
    public Set<WorkEffortAssignmentRate> getWorkEffortAssignmentRates() 
    {
        return this.workEffortAssignmentRates;
    }
    
    public void setWorkEffortAssignmentRates(Set<WorkEffortAssignmentRate> workEffortAssignmentRates) 
    {
        this.workEffortAssignmentRates = workEffortAssignmentRates;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="rateType")
    @JsonIgnore
    public Set<PartyRate> getPartyRates() 
    {
        return this.partyRates;
    }
    
    public void setPartyRates(Set<PartyRate> partyRates) 
    {
        this.partyRates = partyRates;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="rateType")
    @JsonIgnore
    public Set<PositionTypeRate> getPositionTypeRates() 
    {
        return this.positionTypeRates;
    }
    
    public void setPositionTypeRates(Set<PositionTypeRate> positionTypeRates) 
    {
        this.positionTypeRates = positionTypeRates;
    }			
		
    

}
