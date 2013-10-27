/**
 *  Business Logic.
 */
package com.qurion.businesslogic.hr.model;

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
import com.qurion.businesslogic.invoice.model.InvoiceItem;
import com.qurion.businesslogic.hr.model.TimeEntry;

/**
 * TimeEntryBilling 
 * @author Edward Banfa
 */
@Entity
@Table(name="TIME_ENTRY_BILLING"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class TimeEntryBilling  extends BaseEntity implements java.io.Serializable {
	private InvoiceItem invoiceItem;
	private TimeEntry timeEntry;

    public TimeEntryBilling() {
    }

    public TimeEntryBilling(Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public TimeEntryBilling(InvoiceItem invoiceItem, TimeEntry timeEntry, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.invoiceItem = invoiceItem;
        this.timeEntry = timeEntry;
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
    @JoinColumn(name="TIME_ENTRY_ID")
    @JsonIgnore
    public TimeEntry getTimeEntry() 
    {
        return this.timeEntry;
    }
    
    public void setTimeEntry(TimeEntry timeEntry)
    {
        this.timeEntry = timeEntry;
    }
    
    

}
