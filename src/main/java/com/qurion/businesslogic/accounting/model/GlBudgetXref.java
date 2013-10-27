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
import com.qurion.businesslogic.accounting.model.GeneralLedgerAccount;
import com.qurion.businesslogic.application.model.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Date;

/**
 * GlBudgetXref 
 * @author Edward Banfa
 */
@Entity
@Table(name="GL_BUDGET_XREF"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class GlBudgetXref  extends BaseEntity implements java.io.Serializable {
	private BudgetItemType budgetItemType;
	private GeneralLedgerAccount generalLedgerAccount;
	private BigDecimal amount;
	private Date fromDt;
	private Date toDt;
	private Integer allocPercentage;

    public GlBudgetXref() {
    }

    public GlBudgetXref(BigDecimal amount, Date fromDt, Integer allocPercentage, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.amount = amount;
        this.fromDt = fromDt;
        this.allocPercentage = allocPercentage;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public GlBudgetXref(BudgetItemType budgetItemType, GeneralLedgerAccount generalLedgerAccount, BigDecimal amount, Date fromDt, Date toDt, Integer allocPercentage, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.budgetItemType = budgetItemType;
        this.generalLedgerAccount = generalLedgerAccount;
        this.amount = amount;
        this.fromDt = fromDt;
        this.toDt = toDt;
        this.allocPercentage = allocPercentage;
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
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="GL_ACCOUNT_ID")
    @JsonIgnore
    public GeneralLedgerAccount getGeneralLedgerAccount() 
    {
        return this.generalLedgerAccount;
    }
    
    public void setGeneralLedgerAccount(GeneralLedgerAccount generalLedgerAccount)
    {
        this.generalLedgerAccount = generalLedgerAccount;
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
	
    @Column(name="FROM_DT", nullable=false, length=10)
    public Date getFromDt() 
    {
        return this.fromDt;
    }
    
    public void setFromDt(Date fromDt) 
    {
        this.fromDt = fromDt;
    }
	
    @Column(name="TO_DT", length=10)
    public Date getToDt() 
    {
        return this.toDt;
    }
    
    public void setToDt(Date toDt) 
    {
        this.toDt = toDt;
    }
	
    @Column(name="ALLOC_PERCENTAGE", nullable=false)
    public Integer getAllocPercentage() 
    {
        return this.allocPercentage;
    }
    
    public void setAllocPercentage(Integer allocPercentage) 
    {
        this.allocPercentage = allocPercentage;
    }
	
    

}
