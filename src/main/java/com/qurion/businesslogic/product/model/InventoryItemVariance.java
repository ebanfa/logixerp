/**
 *  Business Logic.
 */
package com.qurion.businesslogic.product.model;

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
import com.qurion.businesslogic.product.model.InventoryItem;
import com.qurion.businesslogic.product.model.Reason;

import java.util.Date;

/**
 * InventoryItemVariance 
 * @author Edward Banfa
 */
@Entity
@Table(name="INVENTORY_ITEM_VARIANCE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class InventoryItemVariance  extends BaseEntity implements java.io.Serializable {
	private InventoryItem inventoryItem;
	private Reason reason;
	private Integer quantity;
	private Date physicalInventoryDt;

    public InventoryItemVariance() {
    }

    public InventoryItemVariance(Integer quantity, Date physicalInventoryDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.quantity = quantity;
        this.physicalInventoryDt = physicalInventoryDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public InventoryItemVariance(InventoryItem inventoryItem, Reason reason, Integer quantity, Date physicalInventoryDt, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.inventoryItem = inventoryItem;
        this.reason = reason;
        this.quantity = quantity;
        this.physicalInventoryDt = physicalInventoryDt;
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
    @JoinColumn(name="INVENTORY_ITEM_ID")
    @JsonIgnore
    public InventoryItem getInventoryItem() 
    {
        return this.inventoryItem;
    }
    
    public void setInventoryItem(InventoryItem inventoryItem)
    {
        this.inventoryItem = inventoryItem;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="REASON_ID")
    @JsonIgnore
    public Reason getReason() 
    {
        return this.reason;
    }
    
    public void setReason(Reason reason)
    {
        this.reason = reason;
    }
    
    @Column(name="QUANTITY", nullable=false)
    public Integer getQuantity() 
    {
        return this.quantity;
    }
    
    public void setQuantity(Integer quantity) 
    {
        this.quantity = quantity;
    }
	
    @Column(name="PHYSICAL_INVENTORY_DT", nullable=false, length=10)
    public Date getPhysicalInventoryDt() 
    {
        return this.physicalInventoryDt;
    }
    
    public void setPhysicalInventoryDt(Date physicalInventoryDt) 
    {
        this.physicalInventoryDt = physicalInventoryDt;
    }
	
    

}
