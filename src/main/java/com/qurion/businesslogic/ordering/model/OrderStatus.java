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
import com.qurion.businesslogic.ordering.model.Orders;
import com.qurion.businesslogic.ordering.model.OrderItem;
import com.qurion.businesslogic.ordering.model.OrderStatusType;

import java.util.Date;

/**
 * OrderStatus 
 * @author Edward Banfa
 */
@Entity
@Table(name="ORDER_STATUS"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class OrderStatus  extends BaseEntity implements java.io.Serializable {
	private Orders orders;
	private OrderItem orderItem;
	private OrderStatusType orderStatusType;
	private String description;
	private Date statusDatetimeDt;

    public OrderStatus() {
    }

    public OrderStatus(Date statusDatetimeDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.statusDatetimeDt = statusDatetimeDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public OrderStatus(Orders orders, OrderItem orderItem, OrderStatusType orderStatusType, String description, Date statusDatetimeDt, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.orders = orders;
        this.orderItem = orderItem;
        this.orderStatusType = orderStatusType;
        this.description = description;
        this.statusDatetimeDt = statusDatetimeDt;
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
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ORDER_STAT_TY_ID")
    @JsonIgnore
    public OrderStatusType getOrderStatusType() 
    {
        return this.orderStatusType;
    }
    
    public void setOrderStatusType(OrderStatusType orderStatusType)
    {
        this.orderStatusType = orderStatusType;
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
	
    @Column(name="STATUS_DATETIME_DT", nullable=false, length=19)
    public Date getStatusDatetimeDt() 
    {
        return this.statusDatetimeDt;
    }
    
    public void setStatusDatetimeDt(Date statusDatetimeDt) 
    {
        this.statusDatetimeDt = statusDatetimeDt;
    }
	
    

}
