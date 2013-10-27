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
import com.qurion.businesslogic.businessdata.model.TermType;
import com.qurion.businesslogic.invoice.model.InvoiceItem;
import com.qurion.businesslogic.invoice.model.Invoice;

/**
 * InvoiceTerm 
 * @author Edward Banfa
 */
@Entity
@Table(name="INVOICE_TERM"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class InvoiceTerm  extends BaseEntity implements java.io.Serializable {
	private TermType termType;
	private InvoiceItem invoiceItem;
	private Invoice invoice;
	private Integer termValue;

    public InvoiceTerm() {
    }

    public InvoiceTerm(Integer termValue, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.termValue = termValue;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public InvoiceTerm(TermType termType, InvoiceItem invoiceItem, Invoice invoice, Integer termValue, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.termType = termType;
        this.invoiceItem = invoiceItem;
        this.invoice = invoice;
        this.termValue = termValue;
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
    @JoinColumn(name="TERM_TY_ID")
    @JsonIgnore
    public TermType getTermType() 
    {
        return this.termType;
    }
    
    public void setTermType(TermType termType)
    {
        this.termType = termType;
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
    
    @Column(name="TERM_VALUE", nullable=false)
    public Integer getTermValue() 
    {
        return this.termValue;
    }
    
    public void setTermValue(Integer termValue) 
    {
        this.termValue = termValue;
    }
	
    

}
