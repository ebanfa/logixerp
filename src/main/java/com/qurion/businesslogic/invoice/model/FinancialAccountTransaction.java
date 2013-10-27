/**
 *  Business Logic.
 */
package com.qurion.businesslogic.invoice.model;

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

import com.qurion.businesslogic.application.model.BaseEntity;
import com.qurion.businesslogic.invoice.model.FinancialAccount;
import com.qurion.businesslogic.invoice.model.FinancialAccountTransactionType;
import com.qurion.businesslogic.party.model.Party;

import java.util.Date;
import java.util.Date;

/**
 * FinancialAccountTransaction 
 * @author Edward Banfa
 */
@Entity
@Table(name="FINANCIAL_ACCOUNT_TRANSACTION"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class FinancialAccountTransaction  extends BaseEntity implements java.io.Serializable {
	private FinancialAccount financialAccount;
	private FinancialAccountTransactionType financialAccountTransactionType;
	private Party party;
	private String name;
	private String description;
	private Date transactionDt;
	private Date entryDt;

    public FinancialAccountTransaction() {
    }

    public FinancialAccountTransaction(String name, Date transactionDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.transactionDt = transactionDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public FinancialAccountTransaction(FinancialAccount financialAccount, FinancialAccountTransactionType financialAccountTransactionType, Party party, String name, String description, Date transactionDt, Date entryDt, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.financialAccount = financialAccount;
        this.financialAccountTransactionType = financialAccountTransactionType;
        this.party = party;
        this.name = name;
        this.description = description;
        this.transactionDt = transactionDt;
        this.entryDt = entryDt;
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
    @JoinColumn(name="FIN_ACCT_ID")
    @JsonIgnore
    public FinancialAccount getFinancialAccount() 
    {
        return this.financialAccount;
    }
    
    public void setFinancialAccount(FinancialAccount financialAccount)
    {
        this.financialAccount = financialAccount;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FIN_TXN_TY_ID")
    @JsonIgnore
    public FinancialAccountTransactionType getFinancialAccountTransactionType() 
    {
        return this.financialAccountTransactionType;
    }
    
    public void setFinancialAccountTransactionType(FinancialAccountTransactionType financialAccountTransactionType)
    {
        this.financialAccountTransactionType = financialAccountTransactionType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARTY_ID")
    @JsonIgnore
    public Party getParty() 
    {
        return this.party;
    }
    
    public void setParty(Party party)
    {
        this.party = party;
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
	
    @Column(name="TRANSACTION_DT", nullable=false, length=10)
    public Date getTransactionDt() 
    {
        return this.transactionDt;
    }
    
    public void setTransactionDt(Date transactionDt) 
    {
        this.transactionDt = transactionDt;
    }
	
    @Column(name="ENTRY_DT", length=10)
    public Date getEntryDt() 
    {
        return this.entryDt;
    }
    
    public void setEntryDt(Date entryDt) 
    {
        this.entryDt = entryDt;
    }
	
    

}
