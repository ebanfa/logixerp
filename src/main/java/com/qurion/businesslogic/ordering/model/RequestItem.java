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
import com.qurion.businesslogic.ordering.model.Request;

import java.util.Date;
import java.math.BigDecimal;
import java.util.Set;

import com.qurion.businesslogic.ordering.model.OrderItem;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.QuoteItem;

/**
 * RequestItem 
 * @author Edward Banfa
 */
@Entity
@Table(name="REQUEST_ITEM"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class RequestItem  extends BaseEntity implements java.io.Serializable {
	private Request request;
	private Date requiredByDt;
	private Integer quantity;
	private BigDecimal maxAmt;
	private String description;
	private Set<OrderItem> orderItems;
	private Set<QuoteItem> quoteItems;

    public RequestItem() {
    }

    public RequestItem(Date requiredByDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.requiredByDt = requiredByDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public RequestItem(Request request, Date requiredByDt, Integer quantity, BigDecimal maxAmt, String description, Set orderItems, Set quoteItems, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.request = request;
        this.requiredByDt = requiredByDt;
        this.quantity = quantity;
        this.maxAmt = maxAmt;
        this.description = description;
        this.orderItems = orderItems;
        this.quoteItems = quoteItems;
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
    @JoinColumn(name="REQ_ID")
    @JsonIgnore
    public Request getRequest() 
    {
        return this.request;
    }
    
    public void setRequest(Request request)
    {
        this.request = request;
    }
    
    @Column(name="REQUIRED_BY_DT", nullable=false, length=10)
    public Date getRequiredByDt() 
    {
        return this.requiredByDt;
    }
    
    public void setRequiredByDt(Date requiredByDt) 
    {
        this.requiredByDt = requiredByDt;
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
	
    @Column(name="MAX_AMT")
    public BigDecimal getMaxAmt() 
    {
        return this.maxAmt;
    }
    
    public void setMaxAmt(BigDecimal maxAmt) 
    {
        this.maxAmt = maxAmt;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="requestItem")
    @JsonIgnore
    public Set<OrderItem> getOrderItems() 
    {
        return this.orderItems;
    }
    
    public void setOrderItems(Set<OrderItem> orderItems) 
    {
        this.orderItems = orderItems;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="requestItem")
    @JsonIgnore
    public Set<QuoteItem> getQuoteItems() 
    {
        return this.quoteItems;
    }
    
    public void setQuoteItems(Set<QuoteItem> quoteItems) 
    {
        this.quoteItems = quoteItems;
    }			
		
    

}
