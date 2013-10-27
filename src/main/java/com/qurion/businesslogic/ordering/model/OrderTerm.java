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
import com.qurion.businesslogic.businessdata.model.TermType;
import com.qurion.businesslogic.ordering.model.Orders;
import com.qurion.businesslogic.ordering.model.OrderItem;

/**
 * OrderTerm 
 * @author Edward Banfa
 */
@Entity
@Table(name="ORDER_TERM"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class OrderTerm  extends BaseEntity implements java.io.Serializable {
	private TermType termType;
	private Orders orders;
	private OrderItem orderItem;
	private Integer termValue;
	private String description;

    public OrderTerm() {
    }

    public OrderTerm(Integer termValue, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.termValue = termValue;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public OrderTerm(TermType termType, Orders orders, OrderItem orderItem, Integer termValue, String description, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.termType = termType;
        this.orders = orders;
        this.orderItem = orderItem;
        this.termValue = termValue;
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
    @JoinColumn(name="TERM_TY_ID")
    @JsonIgnore
    public TermType getTermType() 
    {
        return this.termType;
    }
    
    public void setTermType(TermType termType)
    {
        this.termType = termType;
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
    
    @Column(name="TERM_VALUE", nullable=false)
    public Integer getTermValue() 
    {
        return this.termValue;
    }
    
    public void setTermValue(Integer termValue) 
    {
        this.termValue = termValue;
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
