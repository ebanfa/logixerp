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

import com.qurion.businesslogic.accounting.model.AccountingTransactionType;

import java.util.Set;

import com.qurion.businesslogic.accounting.model.AccountingTransaction;

import java.util.Set;

import com.qurion.businesslogic.accounting.model.AccountingTransactionType;
import com.qurion.businesslogic.application.model.BaseEntity;

/**
 * AccountingTransactionType 
 * @author Edward Banfa
 */
@Entity
@Table(name="ACCOUNTING_TRANSACTION_TYPE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class AccountingTransactionType  extends BaseEntity implements java.io.Serializable {
	private AccountingTransactionType accountingTransactionType;
	private String name;
	private String description;
	private Set<AccountingTransaction> accountingTransactions;
	private Set<AccountingTransactionType> accountingTransactionTypes;

    public AccountingTransactionType() {
    }

    public AccountingTransactionType(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public AccountingTransactionType(AccountingTransactionType accountingTransactionType, String name, String description, Set accountingTransactions, Set accountingTransactionTypes, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.accountingTransactionType = accountingTransactionType;
        this.name = name;
        this.description = description;
        this.accountingTransactions = accountingTransactions;
        this.accountingTransactionTypes = accountingTransactionTypes;
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
    @JoinColumn(name="PARENT_TY_ID")
    @JsonIgnore
    public AccountingTransactionType getAccountingTransactionType() 
    {
        return this.accountingTransactionType;
    }
    
    public void setAccountingTransactionType(AccountingTransactionType accountingTransactionType)
    {
        this.accountingTransactionType = accountingTransactionType;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="accountingTransactionType")
    @JsonIgnore
    public Set<AccountingTransaction> getAccountingTransactions() 
    {
        return this.accountingTransactions;
    }
    
    public void setAccountingTransactions(Set<AccountingTransaction> accountingTransactions) 
    {
        this.accountingTransactions = accountingTransactions;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="accountingTransactionType")
    @JsonIgnore
    public Set<AccountingTransactionType> getAccountingTransactionTypes() 
    {
        return this.accountingTransactionTypes;
    }
    
    public void setAccountingTransactionTypes(Set<AccountingTransactionType> accountingTransactionTypes) 
    {
        this.accountingTransactionTypes = accountingTransactionTypes;
    }			
		
    

}
