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

import com.qurion.businesslogic.accounting.model.AccountingTransaction;
import com.qurion.businesslogic.accounting.model.AccountingTransactionDetail;

import java.util.Set;

import com.qurion.businesslogic.accounting.model.AccountingTransactionDetail;
import com.qurion.businesslogic.application.model.BaseEntity;

/**
 * AccountingTransactionDetail 
 * @author Edward Banfa
 */
@Entity
@Table(name="ACCOUNTING_TRANSACTION_DETAIL"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class AccountingTransactionDetail  extends BaseEntity implements java.io.Serializable {
	private AccountingTransaction accountingTransaction;
	private AccountingTransactionDetail accountingTransactionDetail;
	private String name;
	private String description;
	private Character debitCreditFg;
	private Set<AccountingTransactionDetail> accountingTransactionDetails;

    public AccountingTransactionDetail() {
    }

    public AccountingTransactionDetail(String name, Character debitCreditFg, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.debitCreditFg = debitCreditFg;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public AccountingTransactionDetail(AccountingTransaction accountingTransaction, AccountingTransactionDetail accountingTransactionDetail, String name, String description, Character debitCreditFg, Set accountingTransactionDetails, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.accountingTransaction = accountingTransaction;
        this.accountingTransactionDetail = accountingTransactionDetail;
        this.name = name;
        this.description = description;
        this.debitCreditFg = debitCreditFg;
        this.accountingTransactionDetails = accountingTransactionDetails;
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
    @JoinColumn(name="TRANSACTION_ID")
    @JsonIgnore
    public AccountingTransaction getAccountingTransaction() 
    {
        return this.accountingTransaction;
    }
    
    public void setAccountingTransaction(AccountingTransaction accountingTransaction)
    {
        this.accountingTransaction = accountingTransaction;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ASSOCIATED_DETAIL_ID")
    @JsonIgnore
    public AccountingTransactionDetail getAccountingTransactionDetail() 
    {
        return this.accountingTransactionDetail;
    }
    
    public void setAccountingTransactionDetail(AccountingTransactionDetail accountingTransactionDetail)
    {
        this.accountingTransactionDetail = accountingTransactionDetail;
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
	
    @Column(name="DEBIT_CREDIT_FG", nullable=false, length=1)
    public Character getDebitCreditFg() 
    {
        return this.debitCreditFg;
    }
    
    public void setDebitCreditFg(Character debitCreditFg) 
    {
        this.debitCreditFg = debitCreditFg;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="accountingTransactionDetail")
    @JsonIgnore
    public Set<AccountingTransactionDetail> getAccountingTransactionDetails() 
    {
        return this.accountingTransactionDetails;
    }
    
    public void setAccountingTransactionDetails(Set<AccountingTransactionDetail> accountingTransactionDetails) 
    {
        this.accountingTransactionDetails = accountingTransactionDetails;
    }			
		
    

}
