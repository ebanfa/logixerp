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
import com.qurion.businesslogic.fixedasset.model.FixedAsset;
import com.qurion.businesslogic.invoice.model.Payment;
import com.qurion.businesslogic.party.model.PartyRole;
import com.qurion.businesslogic.party.model.Party;
import com.qurion.businesslogic.party.model.Party;
import com.qurion.businesslogic.invoice.model.Invoice;

import java.util.Date;
import java.util.Date;
import java.util.Set;

import com.qurion.businesslogic.accounting.model.AccountingTransactionDetail;
import com.qurion.businesslogic.application.model.BaseEntity;

/**
 * AccountingTransaction 
 * @author Edward Banfa
 */
@Entity
@Table(name="ACCOUNTING_TRANSACTION"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class AccountingTransaction  extends BaseEntity implements java.io.Serializable {
	private AccountingTransactionType accountingTransactionType;
	private FixedAsset fixedAsset;
	private Payment payment;
	private PartyRole partyRole;
	private Party partyByToPartyId;
	private Party partyByFromPartyId;
	private Invoice invoice;
	private String name;
	private String description;
	private Date transactionDt;
	private Date entryDt;
	private Set<AccountingTransactionDetail> accountingTransactionDetails;

    public AccountingTransaction() {
    }

    public AccountingTransaction(String name, String description, Date transactionDt, Date entryDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.description = description;
        this.transactionDt = transactionDt;
        this.entryDt = entryDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public AccountingTransaction(AccountingTransactionType accountingTransactionType, FixedAsset fixedAsset, Payment payment, PartyRole partyRole, Party partyByToPartyId, Party partyByFromPartyId, Invoice invoice, String name, String description, Date transactionDt, Date entryDt, Set accountingTransactionDetails, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.accountingTransactionType = accountingTransactionType;
        this.fixedAsset = fixedAsset;
        this.payment = payment;
        this.partyRole = partyRole;
        this.partyByToPartyId = partyByToPartyId;
        this.partyByFromPartyId = partyByFromPartyId;
        this.invoice = invoice;
        this.name = name;
        this.description = description;
        this.transactionDt = transactionDt;
        this.entryDt = entryDt;
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
    @JoinColumn(name="TRANSACTION_TY_ID")
    @JsonIgnore
    public AccountingTransactionType getAccountingTransactionType() 
    {
        return this.accountingTransactionType;
    }
    
    public void setAccountingTransactionType(AccountingTransactionType accountingTransactionType)
    {
        this.accountingTransactionType = accountingTransactionType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FIXED_ASSET_ID")
    @JsonIgnore
    public FixedAsset getFixedAsset() 
    {
        return this.fixedAsset;
    }
    
    public void setFixedAsset(FixedAsset fixedAsset)
    {
        this.fixedAsset = fixedAsset;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PAYMENT_ID")
    @JsonIgnore
    public Payment getPayment() 
    {
        return this.payment;
    }
    
    public void setPayment(Payment payment)
    {
        this.payment = payment;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARTY_ROLE_ID")
    @JsonIgnore
    public PartyRole getPartyRole() 
    {
        return this.partyRole;
    }
    
    public void setPartyRole(PartyRole partyRole)
    {
        this.partyRole = partyRole;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TO_PARTY_ID")
    @JsonIgnore
    public Party getPartyByToPartyId() 
    {
        return this.partyByToPartyId;
    }
    
    public void setPartyByToPartyId(Party partyByToPartyId)
    {
        this.partyByToPartyId = partyByToPartyId;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FROM_PARTY_ID")
    @JsonIgnore
    public Party getPartyByFromPartyId() 
    {
        return this.partyByFromPartyId;
    }
    
    public void setPartyByFromPartyId(Party partyByFromPartyId)
    {
        this.partyByFromPartyId = partyByFromPartyId;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="INVOICE_ID")
    @JsonIgnore
    public Invoice getInvoice() 
    {
        return this.invoice;
    }
    
    public void setInvoice(Invoice invoice)
    {
        this.invoice = invoice;
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
	
    @Column(name="DESCRIPTION", nullable=false, length=150)
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
	
    @Column(name="ENTRY_DT", nullable=false, length=10)
    public Date getEntryDt() 
    {
        return this.entryDt;
    }
    
    public void setEntryDt(Date entryDt) 
    {
        this.entryDt = entryDt;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="accountingTransaction")
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
