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
import com.qurion.businesslogic.invoice.model.InvoiceItem;
import com.qurion.businesslogic.ordering.model.OrderItem;

import java.math.BigDecimal;

/**
 * OrderItemBilling 
 * @author Edward Banfa
 */
@Entity
@Table(name="ORDER_ITEM_BILLING"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class OrderItemBilling  extends BaseEntity implements java.io.Serializable {
	private InvoiceItem invoiceItem;
	private OrderItem orderItem;
	private Integer quantity;
	private BigDecimal amount;

    public OrderItemBilling() {
    }

    public OrderItemBilling(Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public OrderItemBilling(InvoiceItem invoiceItem, OrderItem orderItem, Integer quantity, BigDecimal amount, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.invoiceItem = invoiceItem;
        this.orderItem = orderItem;
        this.quantity = quantity;
        this.amount = amount;
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
    
    @Column(name="QUANTITY")
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
	
    

}
