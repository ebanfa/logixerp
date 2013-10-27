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

import com.qurion.businesslogic.party.model.ContactMechanism;
import com.qurion.businesslogic.party.model.ContactMechanism;
import com.qurion.businesslogic.party.model.Party;
import com.qurion.businesslogic.party.model.Party;
import com.qurion.businesslogic.invoice.model.BillingAccount;

import java.util.Set;

import com.qurion.businesslogic.invoice.model.InvoiceStatus;

import java.util.Set;

import com.qurion.businesslogic.invoice.model.InvoiceTerm;

import java.util.Set;

import com.qurion.businesslogic.invoice.model.InvoiceItem;

import java.util.Set;

import com.qurion.businesslogic.invoice.model.PaymentApplication;

import java.util.Set;

import com.qurion.businesslogic.accounting.model.AccountingTransaction;
import com.qurion.businesslogic.application.model.BaseEntity;

/**
 * Invoice 
 * @author Edward Banfa
 */
@Entity
@Table(name="INVOICE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Invoice  extends BaseEntity implements java.io.Serializable {
	private ContactMechanism contactMechanismByToContMechId;
	private ContactMechanism contactMechanismByFromContMechId;
	private Party partyByToPartyId;
	private Party partyByFromPartyId;
	private BillingAccount billingAccount;
	private String description;
	private String message;
	private Set<InvoiceStatus> invoiceStatuses;
	private Set<InvoiceTerm> invoiceTerms;
	private Set<InvoiceItem> invoiceItems;
	private Set<PaymentApplication> paymentApplications;
	private Set<AccountingTransaction> accountingTransactions;

    public Invoice() {
    }

    public Invoice(Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public Invoice(ContactMechanism contactMechanismByToContMechId, ContactMechanism contactMechanismByFromContMechId, Party partyByToPartyId, Party partyByFromPartyId, BillingAccount billingAccount, String description, String message, Set invoiceStatuses, Set invoiceTerms, Set invoiceItems, Set paymentApplications, Set accountingTransactions, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.contactMechanismByToContMechId = contactMechanismByToContMechId;
        this.contactMechanismByFromContMechId = contactMechanismByFromContMechId;
        this.partyByToPartyId = partyByToPartyId;
        this.partyByFromPartyId = partyByFromPartyId;
        this.billingAccount = billingAccount;
        this.description = description;
        this.message = message;
        this.invoiceStatuses = invoiceStatuses;
        this.invoiceTerms = invoiceTerms;
        this.invoiceItems = invoiceItems;
        this.paymentApplications = paymentApplications;
        this.accountingTransactions = accountingTransactions;
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
    @JoinColumn(name="TO_CONT_MECH_ID")
    @JsonIgnore
    public ContactMechanism getContactMechanismByToContMechId() 
    {
        return this.contactMechanismByToContMechId;
    }
    
    public void setContactMechanismByToContMechId(ContactMechanism contactMechanismByToContMechId)
    {
        this.contactMechanismByToContMechId = contactMechanismByToContMechId;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FROM_CONT_MECH_ID")
    @JsonIgnore
    public ContactMechanism getContactMechanismByFromContMechId() 
    {
        return this.contactMechanismByFromContMechId;
    }
    
    public void setContactMechanismByFromContMechId(ContactMechanism contactMechanismByFromContMechId)
    {
        this.contactMechanismByFromContMechId = contactMechanismByFromContMechId;
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
    @JoinColumn(name="BILLING_ACCT_ID")
    @JsonIgnore
    public BillingAccount getBillingAccount() 
    {
        return this.billingAccount;
    }
    
    public void setBillingAccount(BillingAccount billingAccount)
    {
        this.billingAccount = billingAccount;
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
	
    @Column(name="MESSAGE", length=150)
    public String getMessage() 
    {
        return this.message;
    }
    
    public void setMessage(String message) 
    {
        this.message = message;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="invoice")
    @JsonIgnore
    public Set<InvoiceStatus> getInvoiceStatuses() 
    {
        return this.invoiceStatuses;
    }
    
    public void setInvoiceStatuses(Set<InvoiceStatus> invoiceStatuses) 
    {
        this.invoiceStatuses = invoiceStatuses;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="invoice")
    @JsonIgnore
    public Set<InvoiceTerm> getInvoiceTerms() 
    {
        return this.invoiceTerms;
    }
    
    public void setInvoiceTerms(Set<InvoiceTerm> invoiceTerms) 
    {
        this.invoiceTerms = invoiceTerms;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="invoice")
    @JsonIgnore
    public Set<InvoiceItem> getInvoiceItems() 
    {
        return this.invoiceItems;
    }
    
    public void setInvoiceItems(Set<InvoiceItem> invoiceItems) 
    {
        this.invoiceItems = invoiceItems;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="invoice")
    @JsonIgnore
    public Set<PaymentApplication> getPaymentApplications() 
    {
        return this.paymentApplications;
    }
    
    public void setPaymentApplications(Set<PaymentApplication> paymentApplications) 
    {
        this.paymentApplications = paymentApplications;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="invoice")
    @JsonIgnore
    public Set<AccountingTransaction> getAccountingTransactions() 
    {
        return this.accountingTransactions;
    }
    
    public void setAccountingTransactions(Set<AccountingTransaction> accountingTransactions) 
    {
        this.accountingTransactions = accountingTransactions;
    }			
		
    

}
