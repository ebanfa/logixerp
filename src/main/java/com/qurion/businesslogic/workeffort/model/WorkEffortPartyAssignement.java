/**
 *  Business Logic.
 */
package com.qurion.businesslogic.workeffort.model;

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
import com.qurion.businesslogic.fixedasset.model.Facility;
import com.qurion.businesslogic.workeffort.model.WorkEffortRoleType;
import com.qurion.businesslogic.party.model.Party;
import com.qurion.businesslogic.workeffort.model.WorkEffort;

import java.util.Date;
import java.util.Date;
import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortAssignmentRate;

/**
 * WorkEffortPartyAssignement 
 * @author Edward Banfa
 */
@Entity
@Table(name="WORK_EFFORT_PARTY_ASSIGNEMENT"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class WorkEffortPartyAssignement  extends BaseEntity implements java.io.Serializable {
	private Facility facility;
	private WorkEffortRoleType workEffortRoleType;
	private Party party;
	private WorkEffort workEffort;
	private Date fromDt;
	private Date toDt;
	private Set<WorkEffortAssignmentRate> workEffortAssignmentRates;

    public WorkEffortPartyAssignement() {
    }

    public WorkEffortPartyAssignement(Date fromDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.fromDt = fromDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public WorkEffortPartyAssignement(Facility facility, WorkEffortRoleType workEffortRoleType, Party party, WorkEffort workEffort, Date fromDt, Date toDt, Set workEffortAssignmentRates, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.facility = facility;
        this.workEffortRoleType = workEffortRoleType;
        this.party = party;
        this.workEffort = workEffort;
        this.fromDt = fromDt;
        this.toDt = toDt;
        this.workEffortAssignmentRates = workEffortAssignmentRates;
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
    @JoinColumn(name="FACILITY_ID")
    @JsonIgnore
    public Facility getFacility() 
    {
        return this.facility;
    }
    
    public void setFacility(Facility facility)
    {
        this.facility = facility;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ROLE_TY_ID")
    @JsonIgnore
    public WorkEffortRoleType getWorkEffortRoleType() 
    {
        return this.workEffortRoleType;
    }
    
    public void setWorkEffortRoleType(WorkEffortRoleType workEffortRoleType)
    {
        this.workEffortRoleType = workEffortRoleType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARTY_ID")
    @JsonIgnore
    public Party getParty() 
    {
        return this.party;
    }
    
    public void setParty(Party party)
    {
        this.party = party;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="WRK_EFFORT_ID")
    @JsonIgnore
    public WorkEffort getWorkEffort() 
    {
        return this.workEffort;
    }
    
    public void setWorkEffort(WorkEffort workEffort)
    {
        this.workEffort = workEffort;
    }
    
    @Column(name="FROM_DT", nullable=false, length=10)
    public Date getFromDt() 
    {
        return this.fromDt;
    }
    
    public void setFromDt(Date fromDt) 
    {
        this.fromDt = fromDt;
    }
	
    @Column(name="TO_DT", length=10)
    public Date getToDt() 
    {
        return this.toDt;
    }
    
    public void setToDt(Date toDt) 
    {
        this.toDt = toDt;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="workEffortPartyAssignement")
    @JsonIgnore
    public Set<WorkEffortAssignmentRate> getWorkEffortAssignmentRates() 
    {
        return this.workEffortAssignmentRates;
    }
    
    public void setWorkEffortAssignmentRates(Set<WorkEffortAssignmentRate> workEffortAssignmentRates) 
    {
        this.workEffortAssignmentRates = workEffortAssignmentRates;
    }			
		
    

}
