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
import com.qurion.businesslogic.accounting.model.BudgetStatusType;
import com.qurion.businesslogic.application.model.BaseEntity;

import java.util.Date;

/**
 * BudgetStatus 
 * @author Edward Banfa
 */
@Entity
@Table(name="BUDGET_STATUS"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BudgetStatus  extends BaseEntity implements java.io.Serializable {
	private Budget budget;
	private BudgetStatusType budgetStatusType;
	private String comment;
	private Date statusDt;

    public BudgetStatus() {
    }

    public BudgetStatus(Date statusDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.statusDt = statusDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public BudgetStatus(Budget budget, BudgetStatusType budgetStatusType, String comment, Date statusDt, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.budget = budget;
        this.budgetStatusType = budgetStatusType;
        this.comment = comment;
        this.statusDt = statusDt;
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
    @JoinColumn(name="STATUS_TY_ID")
    @JsonIgnore
    public BudgetStatusType getBudgetStatusType() 
    {
        return this.budgetStatusType;
    }
    
    public void setBudgetStatusType(BudgetStatusType budgetStatusType)
    {
        this.budgetStatusType = budgetStatusType;
    }
    
    @Column(name="COMMENT", length=150)
    public String getComment() 
    {
        return this.comment;
    }
    
    public void setComment(String comment) 
    {
        this.comment = comment;
    }
	
    @Column(name="STATUS_DT", nullable=false, length=10)
    public Date getStatusDt() 
    {
        return this.statusDt;
    }
    
    public void setStatusDt(Date statusDt) 
    {
        this.statusDt = statusDt;
    }
	
    

}
