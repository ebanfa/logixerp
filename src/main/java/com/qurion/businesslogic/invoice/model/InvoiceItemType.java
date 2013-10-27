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
import com.qurion.businesslogic.invoice.model.InvoiceItemType;

import java.util.Set;

import com.qurion.businesslogic.invoice.model.InvoiceItemType;

import java.util.Set;

import com.qurion.businesslogic.invoice.model.InvoiceItem;

/**
 * InvoiceItemType 
 * @author Edward Banfa
 */
@Entity
@Table(name="INVOICE_ITEM_TYPE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class InvoiceItemType  extends BaseEntity implements java.io.Serializable {
	private InvoiceItemType invoiceItemType;
	private String name;
	private String description;
	private Set<InvoiceItemType> invoiceItemTypes;
	private Set<InvoiceItem> invoiceItems;

    public InvoiceItemType() {
    }

    public InvoiceItemType(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public InvoiceItemType(InvoiceItemType invoiceItemType, String name, String description, Set invoiceItemTypes, Set invoiceItems, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.invoiceItemType = invoiceItemType;
        this.name = name;
        this.description = description;
        this.invoiceItemTypes = invoiceItemTypes;
        this.invoiceItems = invoiceItems;
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
    @JoinColumn(name="PARENT_ITEM_TY_ID")
    @JsonIgnore
    public InvoiceItemType getInvoiceItemType() 
    {
        return this.invoiceItemType;
    }
    
    public void setInvoiceItemType(InvoiceItemType invoiceItemType)
    {
        this.invoiceItemType = invoiceItemType;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="invoiceItemType")
    @JsonIgnore
    public Set<InvoiceItemType> getInvoiceItemTypes() 
    {
        return this.invoiceItemTypes;
    }
    
    public void setInvoiceItemTypes(Set<InvoiceItemType> invoiceItemTypes) 
    {
        this.invoiceItemTypes = invoiceItemTypes;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="invoiceItemType")
    @JsonIgnore
    public Set<InvoiceItem> getInvoiceItems() 
    {
        return this.invoiceItems;
    }
    
    public void setInvoiceItems(Set<InvoiceItem> invoiceItems) 
    {
        this.invoiceItems = invoiceItems;
    }			
		
    

}
