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

import com.qurion.businesslogic.accounting.model.BudgetScenario;
import com.qurion.businesslogic.accounting.model.Budget;
import com.qurion.businesslogic.accounting.model.BudgetItem;
import com.qurion.businesslogic.application.model.BaseEntity;

import java.math.BigDecimal;

/**
 * BudgetScenarioApplication 
 * @author Edward Banfa
 */
@Entity
@Table(name="BUDGET_SCENARIO_APPLICATION"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BudgetScenarioApplication  extends BaseEntity implements java.io.Serializable {
	private BudgetScenario budgetScenario;
	private Budget budget;
	private BudgetItem budgetItem;
	private BigDecimal amtChange;
	private Integer percentageChange;

    public BudgetScenarioApplication() {
    }

    public BudgetScenarioApplication(Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public BudgetScenarioApplication(BudgetScenario budgetScenario, Budget budget, BudgetItem budgetItem, BigDecimal amtChange, Integer percentageChange, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.budgetScenario = budgetScenario;
        this.budget = budget;
        this.budgetItem = budgetItem;
        this.amtChange = amtChange;
        this.percentageChange = percentageChange;
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
    @JoinColumn(name="BUDGET_SCENARIO_ID")
    @JsonIgnore
    public BudgetScenario getBudgetScenario() 
    {
        return this.budgetScenario;
    }
    
    public void setBudgetScenario(BudgetScenario budgetScenario)
    {
        this.budgetScenario = budgetScenario;
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
    
    @Column(name="AMT_CHANGE")
    public BigDecimal getAmtChange() 
    {
        return this.amtChange;
    }
    
    public void setAmtChange(BigDecimal amtChange) 
    {
        this.amtChange = amtChange;
    }
	
    @Column(name="PERCENTAGE_CHANGE")
    public Integer getPercentageChange() 
    {
        return this.percentageChange;
    }
    
    public void setPercentageChange(Integer percentageChange) 
    {
        this.percentageChange = percentageChange;
    }
	
    

}
