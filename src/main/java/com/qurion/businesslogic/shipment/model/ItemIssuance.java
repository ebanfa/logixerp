/**
 *  Business Logic.
 */
package com.qurion.businesslogic.shipment.model;

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
import com.qurion.businesslogic.shipment.model.ShipmentItem;
import com.qurion.businesslogic.product.model.InventoryItem;
import com.qurion.businesslogic.shipment.model.PickListItem;

import java.util.Date;

/**
 * ItemIssuance 
 * @author Edward Banfa
 */
@Entity
@Table(name="ITEM_ISSUANCE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ItemIssuance  extends BaseEntity implements java.io.Serializable {
	private ShipmentItem shipmentItem;
	private InventoryItem inventoryItem;
	private PickListItem pickListItem;
	private Integer quantity;
	private Date issusedDt;

    public ItemIssuance() {
    }

    public ItemIssuance(Integer quantity, Date issusedDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.quantity = quantity;
        this.issusedDt = issusedDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public ItemIssuance(ShipmentItem shipmentItem, InventoryItem inventoryItem, PickListItem pickListItem, Integer quantity, Date issusedDt, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.shipmentItem = shipmentItem;
        this.inventoryItem = inventoryItem;
        this.pickListItem = pickListItem;
        this.quantity = quantity;
        this.issusedDt = issusedDt;
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
    @JoinColumn(name="SHIPMENT_ITEM_ID")
    @JsonIgnore
    public ShipmentItem getShipmentItem() 
    {
        return this.shipmentItem;
    }
    
    public void setShipmentItem(ShipmentItem shipmentItem)
    {
        this.shipmentItem = shipmentItem;
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
    @JoinColumn(name="PICK_LIST_ITEM_ID")
    @JsonIgnore
    public PickListItem getPickListItem() 
    {
        return this.pickListItem;
    }
    
    public void setPickListItem(PickListItem pickListItem)
    {
        this.pickListItem = pickListItem;
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
	
    @Column(name="ISSUSED_DT", nullable=false, length=19)
    public Date getIssusedDt() 
    {
        return this.issusedDt;
    }
    
    public void setIssusedDt(Date issusedDt) 
    {
        this.issusedDt = issusedDt;
    }
	
    

}
