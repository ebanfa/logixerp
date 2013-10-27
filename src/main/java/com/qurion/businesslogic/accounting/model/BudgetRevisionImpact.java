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

import com.qurion.businesslogic.accounting.model.BudgetItem;
import com.qurion.businesslogic.accounting.model.BudgetRevision;
import com.qurion.businesslogic.application.model.BaseEntity;

/**
 * BudgetRevisionImpact 
 * @author Edward Banfa
 */
@Entity
@Table(name="BUDGET_REVISION_IMPACT"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class BudgetRevisionImpact  extends BaseEntity implements java.io.Serializable {
	private BudgetItem budgetItem;
	private BudgetRevision budgetRevision;
	private Character addDeleteFg;
	private String revisionReason;

    public BudgetRevisionImpact() {
    }

    public BudgetRevisionImpact(Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public BudgetRevisionImpact(BudgetItem budgetItem, BudgetRevision budgetRevision, Character addDeleteFg, String revisionReason, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.budgetItem = budgetItem;
        this.budgetRevision = budgetRevision;
        this.addDeleteFg = addDeleteFg;
        this.revisionReason = revisionReason;
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
    @JoinColumn(name="BUDGET_REVISION_ID")
    @JsonIgnore
    public BudgetRevision getBudgetRevision() 
    {
        return this.budgetRevision;
    }
    
    public void setBudgetRevision(BudgetRevision budgetRevision)
    {
        this.budgetRevision = budgetRevision;
    }
    
    @Column(name="ADD_DELETE_FG", length=1)
    public Character getAddDeleteFg() 
    {
        return this.addDeleteFg;
    }
    
    public void setAddDeleteFg(Character addDeleteFg) 
    {
        this.addDeleteFg = addDeleteFg;
    }
	
    @Column(name="REVISION_REASON", length=150)
    public String getRevisionReason() 
    {
        return this.revisionReason;
    }
    
    public void setRevisionReason(String revisionReason) 
    {
        this.revisionReason = revisionReason;
    }
	
    

}
