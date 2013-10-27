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
import com.qurion.businesslogic.invoice.model.InvoiceItem;
import com.qurion.businesslogic.product.model.InventoryItem;
import com.qurion.businesslogic.product.model.Product;
import com.qurion.businesslogic.invoice.model.InvoiceItem;
import com.qurion.businesslogic.product.model.ProductFeature;
import com.qurion.businesslogic.invoice.model.Invoice;

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.Set;

import com.qurion.businesslogic.shipment.model.ShipmentItemBilling;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.OrderItemBilling;

import java.util.Set;

import com.qurion.businesslogic.invoice.model.InvoiceTerm;

import java.util.Set;

import com.qurion.businesslogic.invoice.model.InvoiceItem;

import java.util.Set;

import com.qurion.businesslogic.invoice.model.InvoiceItem;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortBilling;

import java.util.Set;

import com.qurion.businesslogic.hr.model.TimeEntryBilling;

import java.util.Set;

import com.qurion.businesslogic.invoice.model.PaymentApplication;

/**
 * InvoiceItem 
 * @author Edward Banfa
 */
@Entity
@Table(name="INVOICE_ITEM"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class InvoiceItem  extends BaseEntity implements java.io.Serializable {
	private InvoiceItemType invoiceItemType;
	private InvoiceItem invoiceItemByAdjByItemId;
	private InventoryItem inventoryItem;
	private Product product;
	private InvoiceItem invoiceItemBySoldWithItemId;
	private ProductFeature productFeature;
	private Invoice invoice;
	private String name;
	private String itemDescription;
	private Character taxableFg;
	private Integer quantity;
	private BigDecimal amount;
	private BigDecimal unitPrice;
	private Set<ShipmentItemBilling> shipmentItemBillings;
	private Set<OrderItemBilling> orderItemBillings;
	private Set<InvoiceTerm> invoiceTerms;
	private Set<InvoiceItem> invoiceItemsForSoldWithItemId;
	private Set<InvoiceItem> invoiceItemsForAdjByItemId;
	private Set<WorkEffortBilling> workEffortBillings;
	private Set<TimeEntryBilling> timeEntryBillings;
	private Set<PaymentApplication> paymentApplications;

    public InvoiceItem() {
    }

    public InvoiceItem(String name, Character taxableFg, Integer quantity, BigDecimal unitPrice, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.taxableFg = taxableFg;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public InvoiceItem(InvoiceItemType invoiceItemType, InvoiceItem invoiceItemByAdjByItemId, InventoryItem inventoryItem, Product product, InvoiceItem invoiceItemBySoldWithItemId, ProductFeature productFeature, Invoice invoice, String name, String itemDescription, Character taxableFg, Integer quantity, BigDecimal amount, BigDecimal unitPrice, Set shipmentItemBillings, Set orderItemBillings, Set invoiceTerms, Set invoiceItemsForSoldWithItemId, Set invoiceItemsForAdjByItemId, Set workEffortBillings, Set timeEntryBillings, Set paymentApplications, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.invoiceItemType = invoiceItemType;
        this.invoiceItemByAdjByItemId = invoiceItemByAdjByItemId;
        this.inventoryItem = inventoryItem;
        this.product = product;
        this.invoiceItemBySoldWithItemId = invoiceItemBySoldWithItemId;
        this.productFeature = productFeature;
        this.invoice = invoice;
        this.name = name;
        this.itemDescription = itemDescription;
        this.taxableFg = taxableFg;
        this.quantity = quantity;
        this.amount = amount;
        this.unitPrice = unitPrice;
        this.shipmentItemBillings = shipmentItemBillings;
        this.orderItemBillings = orderItemBillings;
        this.invoiceTerms = invoiceTerms;
        this.invoiceItemsForSoldWithItemId = invoiceItemsForSoldWithItemId;
        this.invoiceItemsForAdjByItemId = invoiceItemsForAdjByItemId;
        this.workEffortBillings = workEffortBillings;
        this.timeEntryBillings = timeEntryBillings;
        this.paymentApplications = paymentApplications;
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
    @JoinColumn(name="ITEM_TY_ID")
    @JsonIgnore
    public InvoiceItemType getInvoiceItemType() 
    {
        return this.invoiceItemType;
    }
    
    public void setInvoiceItemType(InvoiceItemType invoiceItemType)
    {
        this.invoiceItemType = invoiceItemType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ADJ_BY_ITEM_ID")
    @JsonIgnore
    public InvoiceItem getInvoiceItemByAdjByItemId() 
    {
        return this.invoiceItemByAdjByItemId;
    }
    
    public void setInvoiceItemByAdjByItemId(InvoiceItem invoiceItemByAdjByItemId)
    {
        this.invoiceItemByAdjByItemId = invoiceItemByAdjByItemId;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="INVENTORY_ITEM_ID")
    @JsonIgnore
    public InventoryItem getInventoryItem() 
    {
        return this.inventoryItem;
    }
    
    public void setInventoryItem(InventoryItem inventoryItem)
    {
        this.inventoryItem = inventoryItem;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROD_ID")
    @JsonIgnore
    public Product getProduct() 
    {
        return this.product;
    }
    
    public void setProduct(Product product)
    {
        this.product = product;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SOLD_WITH_ITEM_ID")
    @JsonIgnore
    public InvoiceItem getInvoiceItemBySoldWithItemId() 
    {
        return this.invoiceItemBySoldWithItemId;
    }
    
    public void setInvoiceItemBySoldWithItemId(InvoiceItem invoiceItemBySoldWithItemId)
    {
        this.invoiceItemBySoldWithItemId = invoiceItemBySoldWithItemId;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PROD_FEAT_ID")
    @JsonIgnore
    public ProductFeature getProductFeature() 
    {
        return this.productFeature;
    }
    
    public void setProductFeature(ProductFeature productFeature)
    {
        this.productFeature = productFeature;
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
	
    @Column(name="ITEM_DESCRIPTION", length=150)
    public String getItemDescription() 
    {
        return this.itemDescription;
    }
    
    public void setItemDescription(String itemDescription) 
    {
        this.itemDescription = itemDescription;
    }
	
    @Column(name="TAXABLE_FG", nullable=false, length=1)
    public Character getTaxableFg() 
    {
        return this.taxableFg;
    }
    
    public void setTaxableFg(Character taxableFg) 
    {
        this.taxableFg = taxableFg;
    }
	
    @Column(name="QUANTITY", nullable=false)
    public Integer getQuantity() 
    {
        return this.quantity;
    }
    
    public void setQuantity(Integer quantity) 
    {
        this.quantity = quantity;
    }
	
    @Column(name="AMOUNT")
    public BigDecimal getAmount() 
    {
        return this.amount;
    }
    
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }
	
    @Column(name="UNIT_PRICE", nullable=false)
    public BigDecimal getUnitPrice() 
    {
        return this.unitPrice;
    }
    
    public void setUnitPrice(BigDecimal unitPrice) 
    {
        this.unitPrice = unitPrice;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="invoiceItem")
    @JsonIgnore
    public Set<ShipmentItemBilling> getShipmentItemBillings() 
    {
        return this.shipmentItemBillings;
    }
    
    public void setShipmentItemBillings(Set<ShipmentItemBilling> shipmentItemBillings) 
    {
        this.shipmentItemBillings = shipmentItemBillings;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="invoiceItem")
    @JsonIgnore
    public Set<OrderItemBilling> getOrderItemBillings() 
    {
        return this.orderItemBillings;
    }
    
    public void setOrderItemBillings(Set<OrderItemBilling> orderItemBillings) 
    {
        this.orderItemBillings = orderItemBillings;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="invoiceItem")
    @JsonIgnore
    public Set<InvoiceTerm> getInvoiceTerms() 
    {
        return this.invoiceTerms;
    }
    
    public void setInvoiceTerms(Set<InvoiceTerm> invoiceTerms) 
    {
        this.invoiceTerms = invoiceTerms;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="invoiceItemsForSoldWithItemId")
    @JsonIgnore
    public Set<InvoiceItem> getInvoiceItemsForSoldWithItemId() 
    {
        return this.invoiceItemsForSoldWithItemId;
    }
    
    public void setInvoiceItemsForSoldWithItemId(Set<InvoiceItem> invoiceItemsForSoldWithItemId) 
    {
        this.invoiceItemsForSoldWithItemId = invoiceItemsForSoldWithItemId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="invoiceItemsForAdjByItemId")
    @JsonIgnore
    public Set<InvoiceItem> getInvoiceItemsForAdjByItemId() 
    {
        return this.invoiceItemsForAdjByItemId;
    }
    
    public void setInvoiceItemsForAdjByItemId(Set<InvoiceItem> invoiceItemsForAdjByItemId) 
    {
        this.invoiceItemsForAdjByItemId = invoiceItemsForAdjByItemId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="invoiceItem")
    @JsonIgnore
    public Set<WorkEffortBilling> getWorkEffortBillings() 
    {
        return this.workEffortBillings;
    }
    
    public void setWorkEffortBillings(Set<WorkEffortBilling> workEffortBillings) 
    {
        this.workEffortBillings = workEffortBillings;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="invoiceItem")
    @JsonIgnore
    public Set<TimeEntryBilling> getTimeEntryBillings() 
    {
        return this.timeEntryBillings;
    }
    
    public void setTimeEntryBillings(Set<TimeEntryBilling> timeEntryBillings) 
    {
        this.timeEntryBillings = timeEntryBillings;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="invoiceItem")
    @JsonIgnore
    public Set<PaymentApplication> getPaymentApplications() 
    {
        return this.paymentApplications;
    }
    
    public void setPaymentApplications(Set<PaymentApplication> paymentApplications) 
    {
        this.paymentApplications = paymentApplications;
    }			
		
    

}
