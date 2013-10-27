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

import java.util.Set;

import com.qurion.businesslogic.accounting.model.BudgetScenarioApplication;

import java.util.Set;

import com.qurion.businesslogic.accounting.model.BudgetScenarioRule;
import com.qurion.businesslogic.application.model.BaseEntity;

/**
 * BudgetScenario 
 * @author Edward Banfa
 */
@Entity
@Table(name="BUDGET_SCENARIO"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BudgetScenario  extends BaseEntity implements java.io.Serializable {
	private String description;
	private Set<BudgetScenarioApplication> budgetScenarioApplications;
	private Set<BudgetScenarioRule> budgetScenarioRules;

    public BudgetScenario() {
    }

    public BudgetScenario(Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public BudgetScenario(String description, Set budgetScenarioApplications, Set budgetScenarioRules, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.description = description;
        this.budgetScenarioApplications = budgetScenarioApplications;
        this.budgetScenarioRules = budgetScenarioRules;
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
    
    @Column(name="DESCRIPTION", length=150)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="budgetScenario")
    @JsonIgnore
    public Set<BudgetScenarioApplication> getBudgetScenarioApplications() 
    {
        return this.budgetScenarioApplications;
    }
    
    public void setBudgetScenarioApplications(Set<BudgetScenarioApplication> budgetScenarioApplications) 
    {
        this.budgetScenarioApplications = budgetScenarioApplications;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="budgetScenario")
    @JsonIgnore
    public Set<BudgetScenarioRule> getBudgetScenarioRules() 
    {
        return this.budgetScenarioRules;
    }
    
    public void setBudgetScenarioRules(Set<BudgetScenarioRule> budgetScenarioRules) 
    {
        this.budgetScenarioRules = budgetScenarioRules;
    }			
		
    

}
