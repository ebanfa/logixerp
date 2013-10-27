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
import com.qurion.businesslogic.ordering.model.OrderType;

import java.util.Date;
import java.util.Date;
import java.util.Set;

import com.qurion.businesslogic.ordering.model.OrderItem;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.OrderRole;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.OrderStatus;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.OrderContactMechanism;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.OrderTerm;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.OrderAdjustment;

/**
 * Orders 
 * @author Edward Banfa
 */
@Entity
@Table(name="ORDERS"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Orders  extends BaseEntity implements java.io.Serializable {
	private OrderType orderType;
	private String name;
	private Date orderDt;
	private Date entryDt;
	private Set<OrderItem> orderItems;
	private Set<OrderRole> orderRoles;
	private Set<OrderStatus> orderStatuses;
	private Set<OrderContactMechanism> orderContactMechanisms;
	private Set<OrderTerm> orderTerms;
	private Set<OrderAdjustment> orderAdjustments;

    public Orders() {
    }

    public Orders(String name, Date orderDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.orderDt = orderDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public Orders(OrderType orderType, String name, Date orderDt, Date entryDt, Set orderItems, Set orderRoles, Set orderStatuses, Set orderContactMechanisms, Set orderTerms, Set orderAdjustments, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.orderType = orderType;
        this.name = name;
        this.orderDt = orderDt;
        this.entryDt = entryDt;
        this.orderItems = orderItems;
        this.orderRoles = orderRoles;
        this.orderStatuses = orderStatuses;
        this.orderContactMechanisms = orderContactMechanisms;
        this.orderTerms = orderTerms;
        this.orderAdjustments = orderAdjustments;
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
    @JoinColumn(name="ORDER_TY_ID")
    @JsonIgnore
    public OrderType getOrderType() 
    {
        return this.orderType;
    }
    
    public void setOrderType(OrderType orderType)
    {
        this.orderType = orderType;
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
	
    @Column(name="ORDER_DT", nullable=false, length=10)
    public Date getOrderDt() 
    {
        return this.orderDt;
    }
    
    public void setOrderDt(Date orderDt) 
    {
        this.orderDt = orderDt;
    }
	
    @Column(name="ENTRY_DT", length=10)
    public Date getEntryDt() 
    {
        return this.entryDt;
    }
    
    public void setEntryDt(Date entryDt) 
    {
        this.entryDt = entryDt;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="orders")
    @JsonIgnore
    public Set<OrderItem> getOrderItems() 
    {
        return this.orderItems;
    }
    
    public void setOrderItems(Set<OrderItem> orderItems) 
    {
        this.orderItems = orderItems;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="orders")
    @JsonIgnore
    public Set<OrderRole> getOrderRoles() 
    {
        return this.orderRoles;
    }
    
    public void setOrderRoles(Set<OrderRole> orderRoles) 
    {
        this.orderRoles = orderRoles;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="orders")
    @JsonIgnore
    public Set<OrderStatus> getOrderStatuses() 
    {
        return this.orderStatuses;
    }
    
    public void setOrderStatuses(Set<OrderStatus> orderStatuses) 
    {
        this.orderStatuses = orderStatuses;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="orders")
    @JsonIgnore
    public Set<OrderContactMechanism> getOrderContactMechanisms() 
    {
        return this.orderContactMechanisms;
    }
    
    public void setOrderContactMechanisms(Set<OrderContactMechanism> orderContactMechanisms) 
    {
        this.orderContactMechanisms = orderContactMechanisms;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="orders")
    @JsonIgnore
    public Set<OrderTerm> getOrderTerms() 
    {
        return this.orderTerms;
    }
    
    public void setOrderTerms(Set<OrderTerm> orderTerms) 
    {
        this.orderTerms = orderTerms;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="orders")
    @JsonIgnore
    public Set<OrderAdjustment> getOrderAdjustments() 
    {
        return this.orderAdjustments;
    }
    
    public void setOrderAdjustments(Set<OrderAdjustment> orderAdjustments) 
    {
        this.orderAdjustments = orderAdjustments;
    }			
		
    

}
