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

import com.qurion.businesslogic.accounting.model.Budget;
import com.qurion.businesslogic.accounting.model.BudgetRoleType;
import com.qurion.businesslogic.application.model.BaseEntity;
import com.qurion.businesslogic.party.model.Party;

/**
 * BudgetRole 
 * @author Edward Banfa
 */
@Entity
@Table(name="BUDGET_ROLE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BudgetRole  extends BaseEntity implements java.io.Serializable {
	private Budget budget;
	private BudgetRoleType budgetRoleType;
	private Party party;
	private String name;
	private String description;

    public BudgetRole() {
    }

    public BudgetRole(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public BudgetRole(Budget budget, BudgetRoleType budgetRoleType, Party party, String name, String description, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.budget = budget;
        this.budgetRoleType = budgetRoleType;
        this.party = party;
        this.name = name;
        this.description = description;
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
    @JoinColumn(name="ROLE_TY_ID")
    @JsonIgnore
    public BudgetRoleType getBudgetRoleType() 
    {
        return this.budgetRoleType;
    }
    
    public void setBudgetRoleType(BudgetRoleType budgetRoleType)
    {
        this.budgetRoleType = budgetRoleType;
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
	
    

}
