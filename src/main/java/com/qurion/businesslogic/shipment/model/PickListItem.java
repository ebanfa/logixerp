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
import com.qurion.businesslogic.product.model.InventoryItem;
import com.qurion.businesslogic.shipment.model.PickList;

import java.util.Set;

import com.qurion.businesslogic.shipment.model.ItemIssuance;

/**
 * PickListItem 
 * @author Edward Banfa
 */
@Entity
@Table(name="PICK_LIST_ITEM"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PickListItem  extends BaseEntity implements java.io.Serializable {
	private InventoryItem inventoryItem;
	private PickList pickList;
	private Integer quantity;
	private Set<ItemIssuance> itemIssuances;

    public PickListItem() {
    }

    public PickListItem(Integer quantity, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.quantity = quantity;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public PickListItem(InventoryItem inventoryItem, PickList pickList, Integer quantity, Set itemIssuances, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.inventoryItem = inventoryItem;
        this.pickList = pickList;
        this.quantity = quantity;
        this.itemIssuances = itemIssuances;
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
    @JoinColumn(name="PICK_LIST_ID")
    @JsonIgnore
    public PickList getPickList() 
    {
        return this.pickList;
    }
    
    public void setPickList(PickList pickList)
    {
        this.pickList = pickList;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="pickListItem")
    @JsonIgnore
    public Set<ItemIssuance> getItemIssuances() 
    {
        return this.itemIssuances;
    }
    
    public void setItemIssuances(Set<ItemIssuance> itemIssuances) 
    {
        this.itemIssuances = itemIssuances;
    }			
		
    

}
