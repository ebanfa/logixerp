/**
 *  Business Logic.
 */
package com.qurion.businesslogic.shipment.model;

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
import com.qurion.businesslogic.product.model.InventoryItem;
import com.qurion.businesslogic.product.model.Product;
import com.qurion.businesslogic.shipment.model.ShipmentPackage;
import com.qurion.businesslogic.ordering.model.RejectionReason;
import com.qurion.businesslogic.ordering.model.OrderItem;

import java.util.Date;
import java.util.Set;

import com.qurion.businesslogic.shipment.model.ShippmentReceiptRole;

/**
 * ShipmentReceipt 
 * @author Edward Banfa
 */
@Entity
@Table(name="SHIPMENT_RECEIPT"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ShipmentReceipt  extends BaseEntity implements java.io.Serializable {
	private InventoryItem inventoryItem;
	private Product product;
	private ShipmentPackage shipmentPackage;
	private RejectionReason rejectionReason;
	private OrderItem orderItem;
	private String name;
	private String description;
	private Integer quantAccepted;
	private Integer quantRejected;
	private Date receivedDt;
	private Set<ShippmentReceiptRole> shippmentReceiptRoles;

    public ShipmentReceipt() {
    }

    public ShipmentReceipt(String name, String description, Integer quantAccepted, Integer quantRejected, Date receivedDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.description = description;
        this.quantAccepted = quantAccepted;
        this.quantRejected = quantRejected;
        this.receivedDt = receivedDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public ShipmentReceipt(InventoryItem inventoryItem, Product product, ShipmentPackage shipmentPackage, RejectionReason rejectionReason, OrderItem orderItem, String name, String description, Integer quantAccepted, Integer quantRejected, Date receivedDt, Set shippmentReceiptRoles, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.inventoryItem = inventoryItem;
        this.product = product;
        this.shipmentPackage = shipmentPackage;
        this.rejectionReason = rejectionReason;
        this.orderItem = orderItem;
        this.name = name;
        this.description = description;
        this.quantAccepted = quantAccepted;
        this.quantRejected = quantRejected;
        this.receivedDt = receivedDt;
        this.shippmentReceiptRoles = shippmentReceiptRoles;
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
    @JoinColumn(name="PACKAGE_ID")
    @JsonIgnore
    public ShipmentPackage getShipmentPackage() 
    {
        return this.shipmentPackage;
    }
    
    public void setShipmentPackage(ShipmentPackage shipmentPackage)
    {
        this.shipmentPackage = shipmentPackage;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="REJECT_REASON_ID")
    @JsonIgnore
    public RejectionReason getRejectionReason() 
    {
        return this.rejectionReason;
    }
    
    public void setRejectionReason(RejectionReason rejectionReason)
    {
        this.rejectionReason = rejectionReason;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ORDER_ITEM_ID")
    @JsonIgnore
    public OrderItem getOrderItem() 
    {
        return this.orderItem;
    }
    
    public void setOrderItem(OrderItem orderItem)
    {
        this.orderItem = orderItem;
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
	
    @Column(name="QUANT_ACCEPTED", nullable=false)
    public Integer getQuantAccepted() 
    {
        return this.quantAccepted;
    }
    
    public void setQuantAccepted(Integer quantAccepted) 
    {
        this.quantAccepted = quantAccepted;
    }
	
    @Column(name="QUANT_REJECTED", nullable=false)
    public Integer getQuantRejected() 
    {
        return this.quantRejected;
    }
    
    public void setQuantRejected(Integer quantRejected) 
    {
        this.quantRejected = quantRejected;
    }
	
    @Column(name="RECEIVED_DT", nullable=false, length=19)
    public Date getReceivedDt() 
    {
        return this.receivedDt;
    }
    
    public void setReceivedDt(Date receivedDt) 
    {
        this.receivedDt = receivedDt;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="shipmentReceipt")
    @JsonIgnore
    public Set<ShippmentReceiptRole> getShippmentReceiptRoles() 
    {
        return this.shippmentReceiptRoles;
    }
    
    public void setShippmentReceiptRoles(Set<ShippmentReceiptRole> shippmentReceiptRoles) 
    {
        this.shippmentReceiptRoles = shippmentReceiptRoles;
    }			
		
    

}
