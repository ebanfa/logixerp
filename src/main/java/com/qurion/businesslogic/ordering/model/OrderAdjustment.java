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

import java.math.BigDecimal;

/**
 * OrderAdjustment 
 * @author Edward Banfa
 */
@Entity
@Table(name="ORDER_ADJUSTMENT"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class OrderAdjustment  extends BaseEntity implements java.io.Serializable {
	private Orders orders;
	private OrderItem orderItem;
	private Integer ordAdjTyId;
	private BigDecimal amount;
	private Integer percentage;
	private String name;
	private String description;

    public OrderAdjustment() {
    }

    public OrderAdjustment(Integer ordAdjTyId, String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.ordAdjTyId = ordAdjTyId;
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public OrderAdjustment(Orders orders, OrderItem orderItem, Integer ordAdjTyId, BigDecimal amount, Integer percentage, String name, String description, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.orders = orders;
        this.orderItem = orderItem;
        this.ordAdjTyId = ordAdjTyId;
        this.amount = amount;
        this.percentage = percentage;
        this.name = name;
        this.description = description;
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
    
    @Column(name="ORD_ADJ_TY_ID", nullable=false)
    public Integer getOrdAdjTyId() 
    {
        return this.ordAdjTyId;
    }
    
    public void setOrdAdjTyId(Integer ordAdjTyId) 
    {
        this.ordAdjTyId = ordAdjTyId;
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
	
    @Column(name="PERCENTAGE")
    public Integer getPercentage() 
    {
        return this.percentage;
    }
    
    public void setPercentage(Integer percentage) 
    {
        this.percentage = percentage;
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
	
    

}
