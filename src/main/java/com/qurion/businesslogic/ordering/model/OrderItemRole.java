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
import com.qurion.businesslogic.party.model.Party;
import com.qurion.businesslogic.ordering.model.OrderItemRoleType;
import com.qurion.businesslogic.ordering.model.OrderItem;

/**
 * OrderItemRole 
 * @author Edward Banfa
 */
@Entity
@Table(name="ORDER_ITEM_ROLE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class OrderItemRole  extends BaseEntity implements java.io.Serializable {
	private Party party;
	private OrderItemRoleType orderItemRoleType;
	private OrderItem orderItem;
	private Integer perContrib;
	private String name;

    public OrderItemRole() {
    }

    public OrderItemRole(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public OrderItemRole(Party party, OrderItemRoleType orderItemRoleType, OrderItem orderItem, Integer perContrib, String name, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.party = party;
        this.orderItemRoleType = orderItemRoleType;
        this.orderItem = orderItem;
        this.perContrib = perContrib;
        this.name = name;
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
    @JoinColumn(name="PARTY_ID")
    @JsonIgnore
    public Party getParty() 
    {
        return this.party;
    }
    
    public void setParty(Party party)
    {
        this.party = party;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ORDER_ITEM_ROLE_TY_ID")
    @JsonIgnore
    public OrderItemRoleType getOrderItemRoleType() 
    {
        return this.orderItemRoleType;
    }
    
    public void setOrderItemRoleType(OrderItemRoleType orderItemRoleType)
    {
        this.orderItemRoleType = orderItemRoleType;
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
    
    @Column(name="PER_CONTRIB")
    public Integer getPerContrib() 
    {
        return this.perContrib;
    }
    
    public void setPerContrib(Integer perContrib) 
    {
        this.perContrib = perContrib;
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
	
    

}
