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
import com.qurion.businesslogic.workeffort.model.WorkEffortType;
import com.qurion.businesslogic.workeffort.model.WorkEffortPurposeType;
import com.qurion.businesslogic.fixedasset.model.Facility;

import java.util.Date;
import java.util.Date;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Date;
import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkRequirementFulfillment;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortPartyAssignement;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortInventoryProduced;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortDeliverableProduced;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortAssetAssignment;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortInventoryAssignment;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkOrderItemFulfillment;

import java.util.Set;

import com.qurion.businesslogic.hr.model.TimeEntry;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortAssociation;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortStatus;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortPartyAssignment;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortAssociation;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.QuoteItem;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortBilling;

/**
 * WorkEffort 
 * @author Edward Banfa
 */
@Entity
@Table(name="WORK_EFFORT"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class WorkEffort  extends BaseEntity implements java.io.Serializable {
	private WorkEffortType workEffortType;
	private WorkEffortPurposeType workEffortPurposeType;
	private Facility facility;
	private String name;
	private String description;
	private Date scheduledStartDt;
	private Date scheduledCompDt;
	private BigDecimal totDollarsAllowed;
	private Integer totHoursAllowed;
	private Date actualStartDt;
	private Date actualCompDt;
	private Integer actualHours;
	private String specialTerms;
	private Set<WorkRequirementFulfillment> workRequirementFulfillments;
	private Set<WorkEffortPartyAssignement> workEffortPartyAssignements;
	private Set<WorkEffortInventoryProduced> workEffortInventoryProduceds;
	private Set<WorkEffortDeliverableProduced> workEffortDeliverableProduceds;
	private Set<WorkEffortAssetAssignment> workEffortAssetAssignments;
	private Set<WorkEffortInventoryAssignment> workEffortInventoryAssignments;
	private Set<WorkOrderItemFulfillment> workOrderItemFulfillments;
	private Set<TimeEntry> timeEntries;
	private Set<WorkEffortAssociation> workEffortAssociationsForToWrkEffortId;
	private Set<WorkEffortStatus> workEffortStatuses;
	private Set<WorkEffortPartyAssignment> workEffortPartyAssignments;
	private Set<WorkEffortAssociation> workEffortAssociationsForFromWrkEffortId;
	private Set<QuoteItem> quoteItems;
	private Set<WorkEffortBilling> workEffortBillings;

    public WorkEffort() {
    }

    public WorkEffort(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public WorkEffort(WorkEffortType workEffortType, WorkEffortPurposeType workEffortPurposeType, Facility facility, String name, String description, Date scheduledStartDt, Date scheduledCompDt, BigDecimal totDollarsAllowed, Integer totHoursAllowed, Date actualStartDt, Date actualCompDt, Integer actualHours, String specialTerms, Set workRequirementFulfillments, Set workEffortPartyAssignements, Set workEffortInventoryProduceds, Set workEffortDeliverableProduceds, Set workEffortAssetAssignments, Set workEffortInventoryAssignments, Set workOrderItemFulfillments, Set timeEntries, Set workEffortAssociationsForToWrkEffortId, Set workEffortStatuses, Set workEffortPartyAssignments, Set workEffortAssociationsForFromWrkEffortId, Set quoteItems, Set workEffortBillings, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.workEffortType = workEffortType;
        this.workEffortPurposeType = workEffortPurposeType;
        this.facility = facility;
        this.name = name;
        this.description = description;
        this.scheduledStartDt = scheduledStartDt;
        this.scheduledCompDt = scheduledCompDt;
        this.totDollarsAllowed = totDollarsAllowed;
        this.totHoursAllowed = totHoursAllowed;
        this.actualStartDt = actualStartDt;
        this.actualCompDt = actualCompDt;
        this.actualHours = actualHours;
        this.specialTerms = specialTerms;
        this.workRequirementFulfillments = workRequirementFulfillments;
        this.workEffortPartyAssignements = workEffortPartyAssignements;
        this.workEffortInventoryProduceds = workEffortInventoryProduceds;
        this.workEffortDeliverableProduceds = workEffortDeliverableProduceds;
        this.workEffortAssetAssignments = workEffortAssetAssignments;
        this.workEffortInventoryAssignments = workEffortInventoryAssignments;
        this.workOrderItemFulfillments = workOrderItemFulfillments;
        this.timeEntries = timeEntries;
        this.workEffortAssociationsForToWrkEffortId = workEffortAssociationsForToWrkEffortId;
        this.workEffortStatuses = workEffortStatuses;
        this.workEffortPartyAssignments = workEffortPartyAssignments;
        this.workEffortAssociationsForFromWrkEffortId = workEffortAssociationsForFromWrkEffortId;
        this.quoteItems = quoteItems;
        this.workEffortBillings = workEffortBillings;
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
    @JoinColumn(name="WRK_EFFORT_TY_ID")
    @JsonIgnore
    public WorkEffortType getWorkEffortType() 
    {
        return this.workEffortType;
    }
    
    public void setWorkEffortType(WorkEffortType workEffortType)
    {
        this.workEffortType = workEffortType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="WRK_EFFORT_PURP_TY_ID")
    @JsonIgnore
    public WorkEffortPurposeType getWorkEffortPurposeType() 
    {
        return this.workEffortPurposeType;
    }
    
    public void setWorkEffortPurposeType(WorkEffortPurposeType workEffortPurposeType)
    {
        this.workEffortPurposeType = workEffortPurposeType;
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
	
    @Column(name="SCHEDULED_START_DT", length=10)
    public Date getScheduledStartDt() 
    {
        return this.scheduledStartDt;
    }
    
    public void setScheduledStartDt(Date scheduledStartDt) 
    {
        this.scheduledStartDt = scheduledStartDt;
    }
	
    @Column(name="SCHEDULED_COMP_DT", length=10)
    public Date getScheduledCompDt() 
    {
        return this.scheduledCompDt;
    }
    
    public void setScheduledCompDt(Date scheduledCompDt) 
    {
        this.scheduledCompDt = scheduledCompDt;
    }
	
    @Column(name="TOT_DOLLARS_ALLOWED")
    public BigDecimal getTotDollarsAllowed() 
    {
        return this.totDollarsAllowed;
    }
    
    public void setTotDollarsAllowed(BigDecimal totDollarsAllowed) 
    {
        this.totDollarsAllowed = totDollarsAllowed;
    }
	
    @Column(name="TOT_HOURS_ALLOWED")
    public Integer getTotHoursAllowed() 
    {
        return this.totHoursAllowed;
    }
    
    public void setTotHoursAllowed(Integer totHoursAllowed) 
    {
        this.totHoursAllowed = totHoursAllowed;
    }
	
    @Column(name="ACTUAL_START_DT", length=19)
    public Date getActualStartDt() 
    {
        return this.actualStartDt;
    }
    
    public void setActualStartDt(Date actualStartDt) 
    {
        this.actualStartDt = actualStartDt;
    }
	
    @Column(name="ACTUAL_COMP_DT", length=19)
    public Date getActualCompDt() 
    {
        return this.actualCompDt;
    }
    
    public void setActualCompDt(Date actualCompDt) 
    {
        this.actualCompDt = actualCompDt;
    }
	
    @Column(name="ACTUAL_HOURS")
    public Integer getActualHours() 
    {
        return this.actualHours;
    }
    
    public void setActualHours(Integer actualHours) 
    {
        this.actualHours = actualHours;
    }
	
    @Column(name="SPECIAL_TERMS", length=150)
    public String getSpecialTerms() 
    {
        return this.specialTerms;
    }
    
    public void setSpecialTerms(String specialTerms) 
    {
        this.specialTerms = specialTerms;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="workEffort")
    @JsonIgnore
    public Set<WorkRequirementFulfillment> getWorkRequirementFulfillments() 
    {
        return this.workRequirementFulfillments;
    }
    
    public void setWorkRequirementFulfillments(Set<WorkRequirementFulfillment> workRequirementFulfillments) 
    {
        this.workRequirementFulfillments = workRequirementFulfillments;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="workEffort")
    @JsonIgnore
    public Set<WorkEffortPartyAssignement> getWorkEffortPartyAssignements() 
    {
        return this.workEffortPartyAssignements;
    }
    
    public void setWorkEffortPartyAssignements(Set<WorkEffortPartyAssignement> workEffortPartyAssignements) 
    {
        this.workEffortPartyAssignements = workEffortPartyAssignements;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="workEffort")
    @JsonIgnore
    public Set<WorkEffortInventoryProduced> getWorkEffortInventoryProduceds() 
    {
        return this.workEffortInventoryProduceds;
    }
    
    public void setWorkEffortInventoryProduceds(Set<WorkEffortInventoryProduced> workEffortInventoryProduceds) 
    {
        this.workEffortInventoryProduceds = workEffortInventoryProduceds;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="workEffort")
    @JsonIgnore
    public Set<WorkEffortDeliverableProduced> getWorkEffortDeliverableProduceds() 
    {
        return this.workEffortDeliverableProduceds;
    }
    
    public void setWorkEffortDeliverableProduceds(Set<WorkEffortDeliverableProduced> workEffortDeliverableProduceds) 
    {
        this.workEffortDeliverableProduceds = workEffortDeliverableProduceds;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="workEffort")
    @JsonIgnore
    public Set<WorkEffortAssetAssignment> getWorkEffortAssetAssignments() 
    {
        return this.workEffortAssetAssignments;
    }
    
    public void setWorkEffortAssetAssignments(Set<WorkEffortAssetAssignment> workEffortAssetAssignments) 
    {
        this.workEffortAssetAssignments = workEffortAssetAssignments;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="workEffort")
    @JsonIgnore
    public Set<WorkEffortInventoryAssignment> getWorkEffortInventoryAssignments() 
    {
        return this.workEffortInventoryAssignments;
    }
    
    public void setWorkEffortInventoryAssignments(Set<WorkEffortInventoryAssignment> workEffortInventoryAssignments) 
    {
        this.workEffortInventoryAssignments = workEffortInventoryAssignments;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="workEffort")
    @JsonIgnore
    public Set<WorkOrderItemFulfillment> getWorkOrderItemFulfillments() 
    {
        return this.workOrderItemFulfillments;
    }
    
    public void setWorkOrderItemFulfillments(Set<WorkOrderItemFulfillment> workOrderItemFulfillments) 
    {
        this.workOrderItemFulfillments = workOrderItemFulfillments;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="workEffort")
    @JsonIgnore
    public Set<TimeEntry> getTimeEntries() 
    {
        return this.timeEntries;
    }
    
    public void setTimeEntries(Set<TimeEntry> timeEntries) 
    {
        this.timeEntries = timeEntries;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="workEffortByToWrkEffortId")
    @JsonIgnore
    public Set<WorkEffortAssociation> getWorkEffortAssociationsForToWrkEffortId() 
    {
        return this.workEffortAssociationsForToWrkEffortId;
    }
    
    public void setWorkEffortAssociationsForToWrkEffortId(Set<WorkEffortAssociation> workEffortAssociationsForToWrkEffortId) 
    {
        this.workEffortAssociationsForToWrkEffortId = workEffortAssociationsForToWrkEffortId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="workEffort")
    @JsonIgnore
    public Set<WorkEffortStatus> getWorkEffortStatuses() 
    {
        return this.workEffortStatuses;
    }
    
    public void setWorkEffortStatuses(Set<WorkEffortStatus> workEffortStatuses) 
    {
        this.workEffortStatuses = workEffortStatuses;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="workEffort")
    @JsonIgnore
    public Set<WorkEffortPartyAssignment> getWorkEffortPartyAssignments() 
    {
        return this.workEffortPartyAssignments;
    }
    
    public void setWorkEffortPartyAssignments(Set<WorkEffortPartyAssignment> workEffortPartyAssignments) 
    {
        this.workEffortPartyAssignments = workEffortPartyAssignments;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="workEffortByFromWrkEffortId")
    @JsonIgnore
    public Set<WorkEffortAssociation> getWorkEffortAssociationsForFromWrkEffortId() 
    {
        return this.workEffortAssociationsForFromWrkEffortId;
    }
    
    public void setWorkEffortAssociationsForFromWrkEffortId(Set<WorkEffortAssociation> workEffortAssociationsForFromWrkEffortId) 
    {
        this.workEffortAssociationsForFromWrkEffortId = workEffortAssociationsForFromWrkEffortId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="workEffort")
    @JsonIgnore
    public Set<QuoteItem> getQuoteItems() 
    {
        return this.quoteItems;
    }
    
    public void setQuoteItems(Set<QuoteItem> quoteItems) 
    {
        this.quoteItems = quoteItems;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="workEffort")
    @JsonIgnore
    public Set<WorkEffortBilling> getWorkEffortBillings() 
    {
        return this.workEffortBillings;
    }
    
    public void setWorkEffortBillings(Set<WorkEffortBilling> workEffortBillings) 
    {
        this.workEffortBillings = workEffortBillings;
    }			
		
    

}
