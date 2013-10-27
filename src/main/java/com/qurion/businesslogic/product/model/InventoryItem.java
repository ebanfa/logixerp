/**
 *  Business Logic.
 */
package com.qurion.businesslogic.product.model;

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
import com.qurion.businesslogic.product.model.InventoryItemType;
import com.qurion.businesslogic.product.model.Product;
import com.qurion.businesslogic.fixedasset.model.Facility;
import com.qurion.businesslogic.product.model.InventoryItemStatus;
import com.qurion.businesslogic.product.model.Container;
import com.qurion.businesslogic.product.model.Lot;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortInventoryProduced;

import java.util.Set;

import com.qurion.businesslogic.product.model.InventoryItemVariance;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortInventoryAssignment;

import java.util.Set;

import com.qurion.businesslogic.invoice.model.InvoiceItem;

import java.util.Set;

import com.qurion.businesslogic.shipment.model.ShipmentReceipt;

import java.util.Set;

import com.qurion.businesslogic.shipment.model.ItemIssuance;

import java.util.Set;

import com.qurion.businesslogic.shipment.model.PickListItem;

/**
 * InventoryItem 
 * @author Edward Banfa
 */
@Entity
@Table(name="INVENTORY_ITEM"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class InventoryItem  extends BaseEntity implements java.io.Serializable {
	private InventoryItemType inventoryItemType;
	private Product product;
	private Facility facility;
	private InventoryItemStatus inventoryItemStatus;
	private Container container;
	private Lot lot;
	private String description;
	private Integer quantityOnHand;
	private String serialNum;
	private Set<WorkEffortInventoryProduced> workEffortInventoryProduceds;
	private Set<InventoryItemVariance> inventoryItemVariances;
	private Set<WorkEffortInventoryAssignment> workEffortInventoryAssignments;
	private Set<InvoiceItem> invoiceItems;
	private Set<ShipmentReceipt> shipmentReceipts;
	private Set<ItemIssuance> itemIssuances;
	private Set<PickListItem> pickListItems;

    public InventoryItem() {
    }

    public InventoryItem(Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public InventoryItem(InventoryItemType inventoryItemType, Product product, Facility facility, InventoryItemStatus inventoryItemStatus, Container container, Lot lot, String description, Integer quantityOnHand, String serialNum, Set workEffortInventoryProduceds, Set inventoryItemVariances, Set workEffortInventoryAssignments, Set invoiceItems, Set shipmentReceipts, Set itemIssuances, Set pickListItems, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.inventoryItemType = inventoryItemType;
        this.product = product;
        this.facility = facility;
        this.inventoryItemStatus = inventoryItemStatus;
        this.container = container;
        this.lot = lot;
        this.description = description;
        this.quantityOnHand = quantityOnHand;
        this.serialNum = serialNum;
        this.workEffortInventoryProduceds = workEffortInventoryProduceds;
        this.inventoryItemVariances = inventoryItemVariances;
        this.workEffortInventoryAssignments = workEffortInventoryAssignments;
        this.invoiceItems = invoiceItems;
        this.shipmentReceipts = shipmentReceipts;
        this.itemIssuances = itemIssuances;
        this.pickListItems = pickListItems;
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
    public InventoryItemType getInventoryItemType() 
    {
        return this.inventoryItemType;
    }
    
    public void setInventoryItemType(InventoryItemType inventoryItemType)
    {
        this.inventoryItemType = inventoryItemType;
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
    @JoinColumn(name="FACILITY_ID")
    @JsonIgnore
    public Facility getFacility() 
    {
        return this.facility;
    }
    
    public void setFacility(Facility facility)
    {
        this.facility = facility;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="STATUS_ID")
    @JsonIgnore
    public InventoryItemStatus getInventoryItemStatus() 
    {
        return this.inventoryItemStatus;
    }
    
    public void setInventoryItemStatus(InventoryItemStatus inventoryItemStatus)
    {
        this.inventoryItemStatus = inventoryItemStatus;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CONTAINER_ID")
    @JsonIgnore
    public Container getContainer() 
    {
        return this.container;
    }
    
    public void setContainer(Container container)
    {
        this.container = container;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="LOT_ID")
    @JsonIgnore
    public Lot getLot() 
    {
        return this.lot;
    }
    
    public void setLot(Lot lot)
    {
        this.lot = lot;
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
	
    @Column(name="QUANTITY_ON_HAND")
    public Integer getQuantityOnHand() 
    {
        return this.quantityOnHand;
    }
    
    public void setQuantityOnHand(Integer quantityOnHand) 
    {
        this.quantityOnHand = quantityOnHand;
    }
	
    @Column(name="SERIAL_NUM", length=35)
    public String getSerialNum() 
    {
        return this.serialNum;
    }
    
    public void setSerialNum(String serialNum) 
    {
        this.serialNum = serialNum;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="inventoryItem")
    @JsonIgnore
    public Set<WorkEffortInventoryProduced> getWorkEffortInventoryProduceds() 
    {
        return this.workEffortInventoryProduceds;
    }
    
    public void setWorkEffortInventoryProduceds(Set<WorkEffortInventoryProduced> workEffortInventoryProduceds) 
    {
        this.workEffortInventoryProduceds = workEffortInventoryProduceds;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="inventoryItem")
    @JsonIgnore
    public Set<InventoryItemVariance> getInventoryItemVariances() 
    {
        return this.inventoryItemVariances;
    }
    
    public void setInventoryItemVariances(Set<InventoryItemVariance> inventoryItemVariances) 
    {
        this.inventoryItemVariances = inventoryItemVariances;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="inventoryItem")
    @JsonIgnore
    public Set<WorkEffortInventoryAssignment> getWorkEffortInventoryAssignments() 
    {
        return this.workEffortInventoryAssignments;
    }
    
    public void setWorkEffortInventoryAssignments(Set<WorkEffortInventoryAssignment> workEffortInventoryAssignments) 
    {
        this.workEffortInventoryAssignments = workEffortInventoryAssignments;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="inventoryItem")
    @JsonIgnore
    public Set<InvoiceItem> getInvoiceItems() 
    {
        return this.invoiceItems;
    }
    
    public void setInvoiceItems(Set<InvoiceItem> invoiceItems) 
    {
        this.invoiceItems = invoiceItems;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="inventoryItem")
    @JsonIgnore
    public Set<ShipmentReceipt> getShipmentReceipts() 
    {
        return this.shipmentReceipts;
    }
    
    public void setShipmentReceipts(Set<ShipmentReceipt> shipmentReceipts) 
    {
        this.shipmentReceipts = shipmentReceipts;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="inventoryItem")
    @JsonIgnore
    public Set<ItemIssuance> getItemIssuances() 
    {
        return this.itemIssuances;
    }
    
    public void setItemIssuances(Set<ItemIssuance> itemIssuances) 
    {
        this.itemIssuances = itemIssuances;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="inventoryItem")
    @JsonIgnore
    public Set<PickListItem> getPickListItems() 
    {
        return this.pickListItems;
    }
    
    public void setPickListItems(Set<PickListItem> pickListItems) 
    {
        this.pickListItems = pickListItems;
    }			
		
    

}
