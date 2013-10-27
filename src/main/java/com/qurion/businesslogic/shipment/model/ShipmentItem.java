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
import com.qurion.businesslogic.shipment.model.ShipmentItem;
import com.qurion.businesslogic.shipment.model.Shipment;
import com.qurion.businesslogic.product.model.Product;

import java.util.Set;

import com.qurion.businesslogic.shipment.model.ShipmentItemBilling;

import java.util.Set;

import com.qurion.businesslogic.shipment.model.Document;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.OrderShipment;

import java.util.Set;

import com.qurion.businesslogic.shipment.model.ShipmentItemFeature;

import java.util.Set;

import com.qurion.businesslogic.shipment.model.PackagingContent;

import java.util.Set;

import com.qurion.businesslogic.shipment.model.ShipmentItem;

import java.util.Set;

import com.qurion.businesslogic.shipment.model.ItemIssuance;

/**
 * ShipmentItem 
 * @author Edward Banfa
 */
@Entity
@Table(name="SHIPMENT_ITEM"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ShipmentItem  extends BaseEntity implements java.io.Serializable {
	private ShipmentItem shipmentItem;
	private Shipment shipment;
	private Product product;
	private String description;
	private Set<ShipmentItemBilling> shipmentItemBillings;
	private Set<Document> documents;
	private Set<OrderShipment> orderShipments;
	private Set<ShipmentItemFeature> shipmentItemFeatures;
	private Set<PackagingContent> packagingContents;
	private Set<ShipmentItem> shipmentItems;
	private Set<ItemIssuance> itemIssuances;

    public ShipmentItem() {
    }

    public ShipmentItem(String description, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.description = description;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public ShipmentItem(ShipmentItem shipmentItem, Shipment shipment, Product product, String description, Set shipmentItemBillings, Set documents, Set orderShipments, Set shipmentItemFeatures, Set packagingContents, Set shipmentItems, Set itemIssuances, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.shipmentItem = shipmentItem;
        this.shipment = shipment;
        this.product = product;
        this.description = description;
        this.shipmentItemBillings = shipmentItemBillings;
        this.documents = documents;
        this.orderShipments = orderShipments;
        this.shipmentItemFeatures = shipmentItemFeatures;
        this.packagingContents = packagingContents;
        this.shipmentItems = shipmentItems;
        this.itemIssuances = itemIssuances;
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
    @JoinColumn(name="SHIPMENT_ITEM_ID")
    @JsonIgnore
    public ShipmentItem getShipmentItem() 
    {
        return this.shipmentItem;
    }
    
    public void setShipmentItem(ShipmentItem shipmentItem)
    {
        this.shipmentItem = shipmentItem;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SHIPMENT_ID")
    @JsonIgnore
    public Shipment getShipment() 
    {
        return this.shipment;
    }
    
    public void setShipment(Shipment shipment)
    {
        this.shipment = shipment;
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
    
    @Column(name="DESCRIPTION", nullable=false, length=150)
    public String getDescription() 
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="shipmentItem")
    @JsonIgnore
    public Set<ShipmentItemBilling> getShipmentItemBillings() 
    {
        return this.shipmentItemBillings;
    }
    
    public void setShipmentItemBillings(Set<ShipmentItemBilling> shipmentItemBillings) 
    {
        this.shipmentItemBillings = shipmentItemBillings;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="shipmentItem")
    @JsonIgnore
    public Set<Document> getDocuments() 
    {
        return this.documents;
    }
    
    public void setDocuments(Set<Document> documents) 
    {
        this.documents = documents;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="shipmentItem")
    @JsonIgnore
    public Set<OrderShipment> getOrderShipments() 
    {
        return this.orderShipments;
    }
    
    public void setOrderShipments(Set<OrderShipment> orderShipments) 
    {
        this.orderShipments = orderShipments;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="shipmentItem")
    @JsonIgnore
    public Set<ShipmentItemFeature> getShipmentItemFeatures() 
    {
        return this.shipmentItemFeatures;
    }
    
    public void setShipmentItemFeatures(Set<ShipmentItemFeature> shipmentItemFeatures) 
    {
        this.shipmentItemFeatures = shipmentItemFeatures;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="shipmentItem")
    @JsonIgnore
    public Set<PackagingContent> getPackagingContents() 
    {
        return this.packagingContents;
    }
    
    public void setPackagingContents(Set<PackagingContent> packagingContents) 
    {
        this.packagingContents = packagingContents;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="shipmentItem")
    @JsonIgnore
    public Set<ShipmentItem> getShipmentItems() 
    {
        return this.shipmentItems;
    }
    
    public void setShipmentItems(Set<ShipmentItem> shipmentItems) 
    {
        this.shipmentItems = shipmentItems;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="shipmentItem")
    @JsonIgnore
    public Set<ItemIssuance> getItemIssuances() 
    {
        return this.itemIssuances;
    }
    
    public void setItemIssuances(Set<ItemIssuance> itemIssuances) 
    {
        this.itemIssuances = itemIssuances;
    }			
		
    

}
