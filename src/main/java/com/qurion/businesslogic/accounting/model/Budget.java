/**
 *  Business Logic.
 */
package com.qurion.businesslogic.accounting.model;

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

import com.qurion.businesslogic.businessdata.model.StandardTimePeriod;

import java.util.Set;

import com.qurion.businesslogic.accounting.model.BudgetRevision;

import java.util.Set;

import com.qurion.businesslogic.accounting.model.BudgetScenarioApplication;

import java.util.Set;

import com.qurion.businesslogic.accounting.model.BudgetRole;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.RequirementBudgetApplication;

import java.util.Set;

import com.qurion.businesslogic.accounting.model.BudgetStatus;

import java.util.Set;

import com.qurion.businesslogic.accounting.model.BudgetReview;

import java.util.Set;

import com.qurion.businesslogic.invoice.model.PaymentBudgetApplication;

import java.util.Set;

import com.qurion.businesslogic.accounting.model.BudgetItem;
import com.qurion.businesslogic.application.model.BaseEntity;

/**
 * Budget 
 * @author Edward Banfa
 */
@Entity
@Table(name="BUDGET"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Budget  extends BaseEntity implements java.io.Serializable {
	private StandardTimePeriod standardTimePeriod;
	private String name;
	private String description;
	private Set<BudgetRevision> budgetRevisions;
	private Set<BudgetScenarioApplication> budgetScenarioApplications;
	private Set<BudgetRole> budgetRoles;
	private Set<RequirementBudgetApplication> requirementBudgetApplications;
	private Set<BudgetStatus> budgetStatuses;
	private Set<BudgetReview> budgetReviews;
	private Set<PaymentBudgetApplication> paymentBudgetApplications;
	private Set<BudgetItem> budgetItems;

    public Budget() {
    }

    public Budget(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public Budget(StandardTimePeriod standardTimePeriod, String name, String description, Set budgetRevisions, Set budgetScenarioApplications, Set budgetRoles, Set requirementBudgetApplications, Set budgetStatuses, Set budgetReviews, Set paymentBudgetApplications, Set budgetItems, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.standardTimePeriod = standardTimePeriod;
        this.name = name;
        this.description = description;
        this.budgetRevisions = budgetRevisions;
        this.budgetScenarioApplications = budgetScenarioApplications;
        this.budgetRoles = budgetRoles;
        this.requirementBudgetApplications = requirementBudgetApplications;
        this.budgetStatuses = budgetStatuses;
        this.budgetReviews = budgetReviews;
        this.paymentBudgetApplications = paymentBudgetApplications;
        this.budgetItems = budgetItems;
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
    @JoinColumn(name="PERIOD_ID")
    @JsonIgnore
    public StandardTimePeriod getStandardTimePeriod() 
    {
        return this.standardTimePeriod;
    }
    
    public void setStandardTimePeriod(StandardTimePeriod standardTimePeriod)
    {
        this.standardTimePeriod = standardTimePeriod;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="budget")
    @JsonIgnore
    public Set<BudgetRevision> getBudgetRevisions() 
    {
        return this.budgetRevisions;
    }
    
    public void setBudgetRevisions(Set<BudgetRevision> budgetRevisions) 
    {
        this.budgetRevisions = budgetRevisions;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="budget")
    @JsonIgnore
    public Set<BudgetScenarioApplication> getBudgetScenarioApplications() 
    {
        return this.budgetScenarioApplications;
    }
    
    public void setBudgetScenarioApplications(Set<BudgetScenarioApplication> budgetScenarioApplications) 
    {
        this.budgetScenarioApplications = budgetScenarioApplications;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="budget")
    @JsonIgnore
    public Set<BudgetRole> getBudgetRoles() 
    {
        return this.budgetRoles;
    }
    
    public void setBudgetRoles(Set<BudgetRole> budgetRoles) 
    {
        this.budgetRoles = budgetRoles;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="budget")
    @JsonIgnore
    public Set<RequirementBudgetApplication> getRequirementBudgetApplications() 
    {
        return this.requirementBudgetApplications;
    }
    
    public void setRequirementBudgetApplications(Set<RequirementBudgetApplication> requirementBudgetApplications) 
    {
        this.requirementBudgetApplications = requirementBudgetApplications;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="budget")
    @JsonIgnore
    public Set<BudgetStatus> getBudgetStatuses() 
    {
        return this.budgetStatuses;
    }
    
    public void setBudgetStatuses(Set<BudgetStatus> budgetStatuses) 
    {
        this.budgetStatuses = budgetStatuses;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="budget")
    @JsonIgnore
    public Set<BudgetReview> getBudgetReviews() 
    {
        return this.budgetReviews;
    }
    
    public void setBudgetReviews(Set<BudgetReview> budgetReviews) 
    {
        this.budgetReviews = budgetReviews;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="budget")
    @JsonIgnore
    public Set<PaymentBudgetApplication> getPaymentBudgetApplications() 
    {
        return this.paymentBudgetApplications;
    }
    
    public void setPaymentBudgetApplications(Set<PaymentBudgetApplication> paymentBudgetApplications) 
    {
        this.paymentBudgetApplications = paymentBudgetApplications;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="budget")
    @JsonIgnore
    public Set<BudgetItem> getBudgetItems() 
    {
        return this.budgetItems;
    }
    
    public void setBudgetItems(Set<BudgetItem> budgetItems) 
    {
        this.budgetItems = budgetItems;
    }			
		
    

}
