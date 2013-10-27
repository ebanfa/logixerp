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
import com.qurion.businesslogic.ordering.model.OrderItem;
import com.qurion.businesslogic.ordering.model.OrderItem;

/**
 * OrderItemAssociation 
 * @author Edward Banfa
 */
@Entity
@Table(name="ORDER_ITEM_ASSOCIATION"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class OrderItemAssociation  extends BaseEntity implements java.io.Serializable {
	private OrderItem orderItemByToOrderItemId;
	private OrderItem orderItemByFromOrderItemId;

    public OrderItemAssociation() {
    }

    public OrderItemAssociation(Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public OrderItemAssociation(OrderItem orderItemByToOrderItemId, OrderItem orderItemByFromOrderItemId, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.orderItemByToOrderItemId = orderItemByToOrderItemId;
        this.orderItemByFromOrderItemId = orderItemByFromOrderItemId;
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
    @JoinColumn(name="TO_ORDER_ITEM_ID")
    @JsonIgnore
    public OrderItem getOrderItemByToOrderItemId() 
    {
        return this.orderItemByToOrderItemId;
    }
    
    public void setOrderItemByToOrderItemId(OrderItem orderItemByToOrderItemId)
    {
        this.orderItemByToOrderItemId = orderItemByToOrderItemId;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FROM_ORDER_ITEM_ID")
    @JsonIgnore
    public OrderItem getOrderItemByFromOrderItemId() 
    {
        return this.orderItemByFromOrderItemId;
    }
    
    public void setOrderItemByFromOrderItemId(OrderItem orderItemByFromOrderItemId)
    {
        this.orderItemByFromOrderItemId = orderItemByFromOrderItemId;
    }
    
    

}
