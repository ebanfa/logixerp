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

import com.qurion.businesslogic.accounting.model.BudgetScenarioRule;

import java.util.Set;

import com.qurion.businesslogic.accounting.model.BudgetItem;

import java.util.Set;

import com.qurion.businesslogic.accounting.model.GlBudgetXref;
import com.qurion.businesslogic.application.model.BaseEntity;

/**
 * BudgetItemType 
 * @author Edward Banfa
 */
@Entity
@Table(name="BUDGET_ITEM_TYPE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BudgetItemType  extends BaseEntity implements java.io.Serializable {
	private String name;
	private String description;
	private Set<BudgetScenarioRule> budgetScenarioRules;
	private Set<BudgetItem> budgetItems;
	private Set<GlBudgetXref> glBudgetXrefs;

    public BudgetItemType() {
    }

    public BudgetItemType(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public BudgetItemType(String name, String description, Set budgetScenarioRules, Set budgetItems, Set glBudgetXrefs, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.name = name;
        this.description = description;
        this.budgetScenarioRules = budgetScenarioRules;
        this.budgetItems = budgetItems;
        this.glBudgetXrefs = glBudgetXrefs;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="budgetItemType")
    @JsonIgnore
    public Set<BudgetScenarioRule> getBudgetScenarioRules() 
    {
        return this.budgetScenarioRules;
    }
    
    public void setBudgetScenarioRules(Set<BudgetScenarioRule> budgetScenarioRules) 
    {
        this.budgetScenarioRules = budgetScenarioRules;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="budgetItemType")
    @JsonIgnore
    public Set<BudgetItem> getBudgetItems() 
    {
        return this.budgetItems;
    }
    
    public void setBudgetItems(Set<BudgetItem> budgetItems) 
    {
        this.budgetItems = budgetItems;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="budgetItemType")
    @JsonIgnore
    public Set<GlBudgetXref> getGlBudgetXrefs() 
    {
        return this.glBudgetXrefs;
    }
    
    public void setGlBudgetXrefs(Set<GlBudgetXref> glBudgetXrefs) 
    {
        this.glBudgetXrefs = glBudgetXrefs;
    }			
		
    

}
