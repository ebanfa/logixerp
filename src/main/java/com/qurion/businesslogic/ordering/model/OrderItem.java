/**
 *  Business Logic.
 */
package com.qurion.businesslogic.ordering.model;

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
import com.qurion.businesslogic.product.model.Product;
import com.qurion.businesslogic.ordering.model.Orders;
import com.qurion.businesslogic.ordering.model.RequestItem;
import com.qurion.businesslogic.product.model.ProductFeature;
import com.qurion.businesslogic.ordering.model.OrderItemType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.qurion.businesslogic.shipment.model.ShipmentReceipt;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.OrderItemContactMechanism;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.OrderStatus;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.OrderShipment;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.OrderItemBilling;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkOrderItemFulfillment;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.OrderItemRole;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.OrderItemAssociation;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.OrderTerm;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.OrderRequirementCommitment;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.OrderAdjustment;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.OrderItemAssociation;

/**
 * OrderItem 
 * @author Edward Banfa
 */
@Entity
@Table(name="ORDER_ITEM"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class OrderItem  extends BaseEntity implements java.io.Serializable {
	private Product product;
	private Orders orders;
	private RequestItem requestItem;
	private ProductFeature productFeature;
	private OrderItemType orderItemType;
	private Integer quantityId;
	private BigDecimal unitPrice;
	private String description;
	private String remarks;
	private Date estimatedDelDt;
	private String shippingInstr;
	private Set<ShipmentReceipt> shipmentReceipts;
	private Set<OrderItemContactMechanism> orderItemContactMechanisms;
	private Set<OrderStatus> orderStatuses;
	private Set<OrderShipment> orderShipments;
	private Set<OrderItemBilling> orderItemBillings;
	private Set<WorkOrderItemFulfillment> workOrderItemFulfillments;
	private Set<OrderItemRole> orderItemRoles;
	private Set<OrderItemAssociation> orderItemAssociationsForToOrderItemId;
	private Set<OrderTerm> orderTerms;
	private Set<OrderRequirementCommitment> orderRequirementCommitments;
	private Set<OrderAdjustment> orderAdjustments;
	private Set<OrderItemAssociation> orderItemAssociationsForFromOrderItemId;

    public OrderItem() {
    }

    public OrderItem(Integer quantityId, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.quantityId = quantityId;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public OrderItem(Product product, Orders orders, RequestItem requestItem, ProductFeature productFeature, OrderItemType orderItemType, Integer quantityId, BigDecimal unitPrice, String description, String remarks, Date estimatedDelDt, String shippingInstr, Set shipmentReceipts, Set orderItemContactMechanisms, Set orderStatuses, Set orderShipments, Set orderItemBillings, Set workOrderItemFulfillments, Set orderItemRoles, Set orderItemAssociationsForToOrderItemId, Set orderTerms, Set orderRequirementCommitments, Set orderAdjustments, Set orderItemAssociationsForFromOrderItemId, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.product = product;
        this.orders = orders;
        this.requestItem = requestItem;
        this.productFeature = productFeature;
        this.orderItemType = orderItemType;
        this.quantityId = quantityId;
        this.unitPrice = unitPrice;
        this.description = description;
        this.remarks = remarks;
        this.estimatedDelDt = estimatedDelDt;
        this.shippingInstr = shippingInstr;
        this.shipmentReceipts = shipmentReceipts;
        this.orderItemContactMechanisms = orderItemContactMechanisms;
        this.orderStatuses = orderStatuses;
        this.orderShipments = orderShipments;
        this.orderItemBillings = orderItemBillings;
        this.workOrderItemFulfillments = workOrderItemFulfillments;
        this.orderItemRoles = orderItemRoles;
        this.orderItemAssociationsForToOrderItemId = orderItemAssociationsForToOrderItemId;
        this.orderTerms = orderTerms;
        this.orderRequirementCommitments = orderRequirementCommitments;
        this.orderAdjustments = orderAdjustments;
        this.orderItemAssociationsForFromOrderItemId = orderItemAssociationsForFromOrderItemId;
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
    @JoinColumn(name="ORDER_ID")
    @JsonIgnore
    public Orders getOrders() 
    {
        return this.orders;
    }
    
    public void setOrders(Orders orders)
    {
        this.orders = orders;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="REQ_ITEM_ID")
    @JsonIgnore
    public RequestItem getRequestItem() 
    {
        return this.requestItem;
    }
    
    public void setRequestItem(RequestItem requestItem)
    {
        this.requestItem = requestItem;
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
    @JoinColumn(name="ORDER_ITEM_TY_ID")
    @JsonIgnore
    public OrderItemType getOrderItemType() 
    {
        return this.orderItemType;
    }
    
    public void setOrderItemType(OrderItemType orderItemType)
    {
        this.orderItemType = orderItemType;
    }
    
    @Column(name="QUANTITY_ID", nullable=false)
    public Integer getQuantityId() 
    {
        return this.quantityId;
    }
    
    public void setQuantityId(Integer quantityId) 
    {
        this.quantityId = quantityId;
    }
	
    @Column(name="UNIT_PRICE")
    public BigDecimal getUnitPrice() 
    {
        return this.unitPrice;
    }
    
    public void setUnitPrice(BigDecimal unitPrice) 
    {
        this.unitPrice = unitPrice;
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
	
    @Column(name="REMARKS", length=150)
    public String getRemarks() 
    {
        return this.remarks;
    }
    
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }
	
    @Column(name="ESTIMATED_DEL_DT", length=10)
    public Date getEstimatedDelDt() 
    {
        return this.estimatedDelDt;
    }
    
    public void setEstimatedDelDt(Date estimatedDelDt) 
    {
        this.estimatedDelDt = estimatedDelDt;
    }
	
    @Column(name="SHIPPING_INSTR", length=150)
    public String getShippingInstr() 
    {
        return this.shippingInstr;
    }
    
    public void setShippingInstr(String shippingInstr) 
    {
        this.shippingInstr = shippingInstr;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="orderItem")
    @JsonIgnore
    public Set<ShipmentReceipt> getShipmentReceipts() 
    {
        return this.shipmentReceipts;
    }
    
    public void setShipmentReceipts(Set<ShipmentReceipt> shipmentReceipts) 
    {
        this.shipmentReceipts = shipmentReceipts;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="orderItem")
    @JsonIgnore
    public Set<OrderItemContactMechanism> getOrderItemContactMechanisms() 
    {
        return this.orderItemContactMechanisms;
    }
    
    public void setOrderItemContactMechanisms(Set<OrderItemContactMechanism> orderItemContactMechanisms) 
    {
        this.orderItemContactMechanisms = orderItemContactMechanisms;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="orderItem")
    @JsonIgnore
    public Set<OrderStatus> getOrderStatuses() 
    {
        return this.orderStatuses;
    }
    
    public void setOrderStatuses(Set<OrderStatus> orderStatuses) 
    {
        this.orderStatuses = orderStatuses;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="orderItem")
    @JsonIgnore
    public Set<OrderShipment> getOrderShipments() 
    {
        return this.orderShipments;
    }
    
    public void setOrderShipments(Set<OrderShipment> orderShipments) 
    {
        this.orderShipments = orderShipments;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="orderItem")
    @JsonIgnore
    public Set<OrderItemBilling> getOrderItemBillings() 
    {
        return this.orderItemBillings;
    }
    
    public void setOrderItemBillings(Set<OrderItemBilling> orderItemBillings) 
    {
        this.orderItemBillings = orderItemBillings;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="orderItem")
    @JsonIgnore
    public Set<WorkOrderItemFulfillment> getWorkOrderItemFulfillments() 
    {
        return this.workOrderItemFulfillments;
    }
    
    public void setWorkOrderItemFulfillments(Set<WorkOrderItemFulfillment> workOrderItemFulfillments) 
    {
        this.workOrderItemFulfillments = workOrderItemFulfillments;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="orderItem")
    @JsonIgnore
    public Set<OrderItemRole> getOrderItemRoles() 
    {
        return this.orderItemRoles;
    }
    
    public void setOrderItemRoles(Set<OrderItemRole> orderItemRoles) 
    {
        this.orderItemRoles = orderItemRoles;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="orderItemByToOrderItemId")
    @JsonIgnore
    public Set<OrderItemAssociation> getOrderItemAssociationsForToOrderItemId() 
    {
        return this.orderItemAssociationsForToOrderItemId;
    }
    
    public void setOrderItemAssociationsForToOrderItemId(Set<OrderItemAssociation> orderItemAssociationsForToOrderItemId) 
    {
        this.orderItemAssociationsForToOrderItemId = orderItemAssociationsForToOrderItemId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="orderItem")
    @JsonIgnore
    public Set<OrderTerm> getOrderTerms() 
    {
        return this.orderTerms;
    }
    
    public void setOrderTerms(Set<OrderTerm> orderTerms) 
    {
        this.orderTerms = orderTerms;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="orderItem")
    @JsonIgnore
    public Set<OrderRequirementCommitment> getOrderRequirementCommitments() 
    {
        return this.orderRequirementCommitments;
    }
    
    public void setOrderRequirementCommitments(Set<OrderRequirementCommitment> orderRequirementCommitments) 
    {
        this.orderRequirementCommitments = orderRequirementCommitments;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="orderItem")
    @JsonIgnore
    public Set<OrderAdjustment> getOrderAdjustments() 
    {
        return this.orderAdjustments;
    }
    
    public void setOrderAdjustments(Set<OrderAdjustment> orderAdjustments) 
    {
        this.orderAdjustments = orderAdjustments;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="orderItemByFromOrderItemId")
    @JsonIgnore
    public Set<OrderItemAssociation> getOrderItemAssociationsForFromOrderItemId() 
    {
        return this.orderItemAssociationsForFromOrderItemId;
    }
    
    public void setOrderItemAssociationsForFromOrderItemId(Set<OrderItemAssociation> orderItemAssociationsForFromOrderItemId) 
    {
        this.orderItemAssociationsForFromOrderItemId = orderItemAssociationsForFromOrderItemId;
    }			
		
    

}
