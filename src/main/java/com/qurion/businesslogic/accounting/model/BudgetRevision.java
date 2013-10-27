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

import java.util.Date;
import java.util.Set;

import com.qurion.businesslogic.accounting.model.BudgetRevisionImpact;
import com.qurion.businesslogic.application.model.BaseEntity;

/**
 * BudgetRevision 
 * @author Edward Banfa
 */
@Entity
@Table(name="BUDGET_REVISION"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BudgetRevision  extends BaseEntity implements java.io.Serializable {
	private Budget budget;
	private Date revisionDt;
	private Set<BudgetRevisionImpact> budgetRevisionImpacts;

    public BudgetRevision() {
    }

    public BudgetRevision(Date revisionDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.revisionDt = revisionDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public BudgetRevision(Budget budget, Date revisionDt, Set budgetRevisionImpacts, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.budget = budget;
        this.revisionDt = revisionDt;
        this.budgetRevisionImpacts = budgetRevisionImpacts;
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
    
    @Column(name="REVISION_DT", nullable=false, length=10)
    public Date getRevisionDt() 
    {
        return this.revisionDt;
    }
    
    public void setRevisionDt(Date revisionDt) 
    {
        this.revisionDt = revisionDt;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="budgetRevision")
    @JsonIgnore
    public Set<BudgetRevisionImpact> getBudgetRevisionImpacts() 
    {
        return this.budgetRevisionImpacts;
    }
    
    public void setBudgetRevisionImpacts(Set<BudgetRevisionImpact> budgetRevisionImpacts) 
    {
        this.budgetRevisionImpacts = budgetRevisionImpacts;
    }			
		
    

}
