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

import com.qurion.businesslogic.accounting.model.BudgetItemType;
import com.qurion.businesslogic.accounting.model.Budget;
import com.qurion.businesslogic.accounting.model.BudgetItem;

import java.math.BigDecimal;
import java.util.Set;

import com.qurion.businesslogic.accounting.model.BudgetScenarioApplication;

import java.util.Set;

import com.qurion.businesslogic.hr.model.Position;

import java.util.Set;

import com.qurion.businesslogic.accounting.model.BudgetRevisionImpact;

import java.util.Set;

import com.qurion.businesslogic.accounting.model.BudgetItem;
import com.qurion.businesslogic.application.model.BaseEntity;

/**
 * BudgetItem 
 * @author Edward Banfa
 */
@Entity
@Table(name="BUDGET_ITEM"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BudgetItem  extends BaseEntity implements java.io.Serializable {
	private BudgetItemType budgetItemType;
	private Budget budget;
	private BudgetItem budgetItem;
	private String name;
	private BigDecimal amount;
	private String justification;
	private String purpose;
	private Set<BudgetScenarioApplication> budgetScenarioApplications;
	private Set<Position> positions;
	private Set<BudgetRevisionImpact> budgetRevisionImpacts;
	private Set<BudgetItem> budgetItems;

    public BudgetItem() {
    }

    public BudgetItem(String name, BigDecimal amount, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.amount = amount;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public BudgetItem(BudgetItemType budgetItemType, Budget budget, BudgetItem budgetItem, String name, BigDecimal amount, String justification, String purpose, Set budgetScenarioApplications, Set positions, Set budgetRevisionImpacts, Set budgetItems, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.budgetItemType = budgetItemType;
        this.budget = budget;
        this.budgetItem = budgetItem;
        this.name = name;
        this.amount = amount;
        this.justification = justification;
        this.purpose = purpose;
        this.budgetScenarioApplications = budgetScenarioApplications;
        this.positions = positions;
        this.budgetRevisionImpacts = budgetRevisionImpacts;
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
    @JoinColumn(name="ITEM_TY_ID")
    @JsonIgnore
    public BudgetItemType getBudgetItemType() 
    {
        return this.budgetItemType;
    }
    
    public void setBudgetItemType(BudgetItemType budgetItemType)
    {
        this.budgetItemType = budgetItemType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BUDGET_ID")
    @JsonIgnore
    public Budget getBudget() 
    {
        return this.budget;
    }
    
    public void setBudget(Budget budget)
    {
        this.budget = budget;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARENT_ITEM_ID")
    @JsonIgnore
    public BudgetItem getBudgetItem() 
    {
        return this.budgetItem;
    }
    
    public void setBudgetItem(BudgetItem budgetItem)
    {
        this.budgetItem = budgetItem;
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
	
    @Column(name="AMOUNT", nullable=false)
    public BigDecimal getAmount() 
    {
        return this.amount;
    }
    
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }
	
    @Column(name="JUSTIFICATION", length=150)
    public String getJustification() 
    {
        return this.justification;
    }
    
    public void setJustification(String justification) 
    {
        this.justification = justification;
    }
	
    @Column(name="PURPOSE", length=150)
    public String getPurpose() 
    {
        return this.purpose;
    }
    
    public void setPurpose(String purpose) 
    {
        this.purpose = purpose;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="budgetItem")
    @JsonIgnore
    public Set<BudgetScenarioApplication> getBudgetScenarioApplications() 
    {
        return this.budgetScenarioApplications;
    }
    
    public void setBudgetScenarioApplications(Set<BudgetScenarioApplication> budgetScenarioApplications) 
    {
        this.budgetScenarioApplications = budgetScenarioApplications;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="budgetItem")
    @JsonIgnore
    public Set<Position> getPositions() 
    {
        return this.positions;
    }
    
    public void setPositions(Set<Position> positions) 
    {
        this.positions = positions;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="budgetItem")
    @JsonIgnore
    public Set<BudgetRevisionImpact> getBudgetRevisionImpacts() 
    {
        return this.budgetRevisionImpacts;
    }
    
    public void setBudgetRevisionImpacts(Set<BudgetRevisionImpact> budgetRevisionImpacts) 
    {
        this.budgetRevisionImpacts = budgetRevisionImpacts;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="budgetItem")
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
