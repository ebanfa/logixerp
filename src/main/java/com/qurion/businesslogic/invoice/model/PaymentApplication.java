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
import com.qurion.businesslogic.invoice.model.Payment;
import com.qurion.businesslogic.invoice.model.BillingAccount;
import com.qurion.businesslogic.invoice.model.InvoiceItem;
import com.qurion.businesslogic.invoice.model.Invoice;

import java.math.BigDecimal;

/**
 * PaymentApplication 
 * @author Edward Banfa
 */
@Entity
@Table(name="PAYMENT_APPLICATION"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PaymentApplication  extends BaseEntity implements java.io.Serializable {
	private Payment payment;
	private BillingAccount billingAccount;
	private InvoiceItem invoiceItem;
	private Invoice invoice;
	private String description;
	private BigDecimal amountApplied;

    public PaymentApplication() {
    }

    public PaymentApplication(BigDecimal amountApplied, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.amountApplied = amountApplied;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public PaymentApplication(Payment payment, BillingAccount billingAccount, InvoiceItem invoiceItem, Invoice invoice, String description, BigDecimal amountApplied, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.payment = payment;
        this.billingAccount = billingAccount;
        this.invoiceItem = invoiceItem;
        this.invoice = invoice;
        this.description = description;
        this.amountApplied = amountApplied;
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
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="INVOICE_ITEM_ID")
    @JsonIgnore
    public InvoiceItem getInvoiceItem() 
    {
        return this.invoiceItem;
    }
    
    public void setInvoiceItem(InvoiceItem invoiceItem)
    {
        this.invoiceItem = invoiceItem;
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
    
    @Column(name="DESCRIPTION", length=150)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
	
    @Column(name="AMOUNT_APPLIED", nullable=false)
    public BigDecimal getAmountApplied() 
    {
        return this.amountApplied;
    }
    
    public void setAmountApplied(BigDecimal amountApplied) 
    {
        this.amountApplied = amountApplied;
    }
	
    

}
