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
import com.qurion.businesslogic.accounting.model.BudgetItemType;
import com.qurion.businesslogic.application.model.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * BudgetScenarioRule 
 * @author Edward Banfa
 */
@Entity
@Table(name="BUDGET_SCENARIO_RULE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BudgetScenarioRule  extends BaseEntity implements java.io.Serializable {
	private BudgetScenario budgetScenario;
	private BudgetItemType budgetItemType;
	private BigDecimal amtChange;
	private Integer percentageChange;
	private Date efectiveDt;

    public BudgetScenarioRule() {
    }

    public BudgetScenarioRule(Date efectiveDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.efectiveDt = efectiveDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public BudgetScenarioRule(BudgetScenario budgetScenario, BudgetItemType budgetItemType, BigDecimal amtChange, Integer percentageChange, Date efectiveDt, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.budgetScenario = budgetScenario;
        this.budgetItemType = budgetItemType;
        this.amtChange = amtChange;
        this.percentageChange = percentageChange;
        this.efectiveDt = efectiveDt;
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
    @JoinColumn(name="BUDGET_ITEM_TY_ID")
    @JsonIgnore
    public BudgetItemType getBudgetItemType() 
    {
        return this.budgetItemType;
    }
    
    public void setBudgetItemType(BudgetItemType budgetItemType)
    {
        this.budgetItemType = budgetItemType;
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
	
    @Column(name="EFECTIVE_DT", nullable=false, length=10)
    public Date getEfectiveDt() 
    {
        return this.efectiveDt;
    }
    
    public void setEfectiveDt(Date efectiveDt) 
    {
        this.efectiveDt = efectiveDt;
    }
	
    

}
