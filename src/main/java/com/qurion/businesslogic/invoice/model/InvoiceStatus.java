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
import com.qurion.businesslogic.invoice.model.InvoiceStatusType;
import com.qurion.businesslogic.invoice.model.Invoice;

/**
 * InvoiceStatus 
 * @author Edward Banfa
 */
@Entity
@Table(name="INVOICE_STATUS"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class InvoiceStatus  extends BaseEntity implements java.io.Serializable {
	private InvoiceStatusType invoiceStatusType;
	private Invoice invoice;

    public InvoiceStatus() {
    }

    public InvoiceStatus(Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public InvoiceStatus(InvoiceStatusType invoiceStatusType, Invoice invoice, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.invoiceStatusType = invoiceStatusType;
        this.invoice = invoice;
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
    @JoinColumn(name="INVOICE_STAT_TY_ID")
    @JsonIgnore
    public InvoiceStatusType getInvoiceStatusType() 
    {
        return this.invoiceStatusType;
    }
    
    public void setInvoiceStatusType(InvoiceStatusType invoiceStatusType)
    {
        this.invoiceStatusType = invoiceStatusType;
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
    
    

}
