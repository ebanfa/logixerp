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

import com.qurion.businesslogic.hr.model.PositionType;
import com.qurion.businesslogic.accounting.model.BudgetItem;
import com.qurion.businesslogic.application.model.BaseEntity;
import com.qurion.businesslogic.hr.model.PositionStatusType;
import com.qurion.businesslogic.party.model.Party;

import java.util.Date;
import java.util.Date;
import java.util.Date;
import java.util.Date;
import java.util.Set;

import com.qurion.businesslogic.hr.model.EmploymentApplication;

import java.util.Set;

import com.qurion.businesslogic.hr.model.PositionResponsibility;

import java.util.Set;

import com.qurion.businesslogic.hr.model.PerformanceReview;

import java.util.Set;

import com.qurion.businesslogic.hr.model.PositionFulfillment;

import java.util.Set;

import com.qurion.businesslogic.hr.model.PositionReportingStructure;

import java.util.Set;

import com.qurion.businesslogic.hr.model.PositionReportingStructure;

/**
 * Position 
 * @author Edward Banfa
 */
@Entity
@Table(name="POSITION"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Position  extends BaseEntity implements java.io.Serializable {
	private PositionType positionType;
	private BudgetItem budgetItem;
	private PositionStatusType positionStatusType;
	private Party party;
	private String name;
	private String description;
	private Date estFromDt;
	private Date estToDt;
	private Character salaryFg;
	private Character exempt;
	private Character fulltimeFg;
	private Character temporaryFg;
	private Date actaulFromDt;
	private Date actualToDt;
	private Set<EmploymentApplication> employmentApplications;
	private Set<PositionResponsibility> positionResponsibilities;
	private Set<PerformanceReview> performanceReviews;
	private Set<PositionFulfillment> positionFulfillments;
	private Set<PositionReportingStructure> positionReportingStructuresForFromPositionId;
	private Set<PositionReportingStructure> positionReportingStructuresForToPositionId;

    public Position() {
    }

    public Position(String name, Date estFromDt, Character salaryFg, Character exempt, Character fulltimeFg, Character temporaryFg, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.estFromDt = estFromDt;
        this.salaryFg = salaryFg;
        this.exempt = exempt;
        this.fulltimeFg = fulltimeFg;
        this.temporaryFg = temporaryFg;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public Position(PositionType positionType, BudgetItem budgetItem, PositionStatusType positionStatusType, Party party, String name, String description, Date estFromDt, Date estToDt, Character salaryFg, Character exempt, Character fulltimeFg, Character temporaryFg, Date actaulFromDt, Date actualToDt, Set employmentApplications, Set positionResponsibilities, Set performanceReviews, Set positionFulfillments, Set positionReportingStructuresForFromPositionId, Set positionReportingStructuresForToPositionId, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.positionType = positionType;
        this.budgetItem = budgetItem;
        this.positionStatusType = positionStatusType;
        this.party = party;
        this.name = name;
        this.description = description;
        this.estFromDt = estFromDt;
        this.estToDt = estToDt;
        this.salaryFg = salaryFg;
        this.exempt = exempt;
        this.fulltimeFg = fulltimeFg;
        this.temporaryFg = temporaryFg;
        this.actaulFromDt = actaulFromDt;
        this.actualToDt = actualToDt;
        this.employmentApplications = employmentApplications;
        this.positionResponsibilities = positionResponsibilities;
        this.performanceReviews = performanceReviews;
        this.positionFulfillments = positionFulfillments;
        this.positionReportingStructuresForFromPositionId = positionReportingStructuresForFromPositionId;
        this.positionReportingStructuresForToPositionId = positionReportingStructuresForToPositionId;
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
    @JoinColumn(name="POSITION_TY_ID")
    @JsonIgnore
    public PositionType getPositionType() 
    {
        return this.positionType;
    }
    
    public void setPositionType(PositionType positionType)
    {
        this.positionType = positionType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BUDGET_ITEM_ID")
    @JsonIgnore
    public BudgetItem getBudgetItem() 
    {
        return this.budgetItem;
    }
    
    public void setBudgetItem(BudgetItem budgetItem)
    {
        this.budgetItem = budgetItem;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="STATUS_TY_ID")
    @JsonIgnore
    public PositionStatusType getPositionStatusType() 
    {
        return this.positionStatusType;
    }
    
    public void setPositionStatusType(PositionStatusType positionStatusType)
    {
        this.positionStatusType = positionStatusType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ORGANIZATION_ID")
    @JsonIgnore
    public Party getParty() 
    {
        return this.party;
    }
    
    public void setParty(Party party)
    {
        this.party = party;
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
	
    @Column(name="EST_FROM_DT", nullable=false, length=10)
    public Date getEstFromDt() 
    {
        return this.estFromDt;
    }
    
    public void setEstFromDt(Date estFromDt) 
    {
        this.estFromDt = estFromDt;
    }
	
    @Column(name="EST_TO_DT", length=10)
    public Date getEstToDt() 
    {
        return this.estToDt;
    }
    
    public void setEstToDt(Date estToDt) 
    {
        this.estToDt = estToDt;
    }
	
    @Column(name="SALARY_FG", nullable=false, length=1)
    public Character getSalaryFg() 
    {
        return this.salaryFg;
    }
    
    public void setSalaryFg(Character salaryFg) 
    {
        this.salaryFg = salaryFg;
    }
	
    @Column(name="EXEMPT", nullable=false, length=1)
    public Character getExempt() 
    {
        return this.exempt;
    }
    
    public void setExempt(Character exempt) 
    {
        this.exempt = exempt;
    }
	
    @Column(name="FULLTIME_FG", nullable=false, length=1)
    public Character getFulltimeFg() 
    {
        return this.fulltimeFg;
    }
    
    public void setFulltimeFg(Character fulltimeFg) 
    {
        this.fulltimeFg = fulltimeFg;
    }
	
    @Column(name="TEMPORARY_FG", nullable=false, length=1)
    public Character getTemporaryFg() 
    {
        return this.temporaryFg;
    }
    
    public void setTemporaryFg(Character temporaryFg) 
    {
        this.temporaryFg = temporaryFg;
    }
	
    @Column(name="ACTAUL_FROM_DT", length=10)
    public Date getActaulFromDt() 
    {
        return this.actaulFromDt;
    }
    
    public void setActaulFromDt(Date actaulFromDt) 
    {
        this.actaulFromDt = actaulFromDt;
    }
	
    @Column(name="ACTUAL_TO_DT", length=10)
    public Date getActualToDt() 
    {
        return this.actualToDt;
    }
    
    public void setActualToDt(Date actualToDt) 
    {
        this.actualToDt = actualToDt;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="position")
    @JsonIgnore
    public Set<EmploymentApplication> getEmploymentApplications() 
    {
        return this.employmentApplications;
    }
    
    public void setEmploymentApplications(Set<EmploymentApplication> employmentApplications) 
    {
        this.employmentApplications = employmentApplications;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="position")
    @JsonIgnore
    public Set<PositionResponsibility> getPositionResponsibilities() 
    {
        return this.positionResponsibilities;
    }
    
    public void setPositionResponsibilities(Set<PositionResponsibility> positionResponsibilities) 
    {
        this.positionResponsibilities = positionResponsibilities;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="position")
    @JsonIgnore
    public Set<PerformanceReview> getPerformanceReviews() 
    {
        return this.performanceReviews;
    }
    
    public void setPerformanceReviews(Set<PerformanceReview> performanceReviews) 
    {
        this.performanceReviews = performanceReviews;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="position")
    @JsonIgnore
    public Set<PositionFulfillment> getPositionFulfillments() 
    {
        return this.positionFulfillments;
    }
    
    public void setPositionFulfillments(Set<PositionFulfillment> positionFulfillments) 
    {
        this.positionFulfillments = positionFulfillments;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="positionByFromPositionId")
    @JsonIgnore
    public Set<PositionReportingStructure> getPositionReportingStructuresForFromPositionId() 
    {
        return this.positionReportingStructuresForFromPositionId;
    }
    
    public void setPositionReportingStructuresForFromPositionId(Set<PositionReportingStructure> positionReportingStructuresForFromPositionId) 
    {
        this.positionReportingStructuresForFromPositionId = positionReportingStructuresForFromPositionId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="positionByToPositionId")
    @JsonIgnore
    public Set<PositionReportingStructure> getPositionReportingStructuresForToPositionId() 
    {
        return this.positionReportingStructuresForToPositionId;
    }
    
    public void setPositionReportingStructuresForToPositionId(Set<PositionReportingStructure> positionReportingStructuresForToPositionId) 
    {
        this.positionReportingStructuresForToPositionId = positionReportingStructuresForToPositionId;
    }			
		
    

}
