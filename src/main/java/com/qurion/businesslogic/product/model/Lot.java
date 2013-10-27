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

import java.util.Date;
import java.util.Set;

import com.qurion.businesslogic.product.model.InventoryItem;

/**
 * Lot 
 * @author Edward Banfa
 */
@Entity
@Table(name="LOT"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Lot  extends BaseEntity implements java.io.Serializable {
	private String name;
	private String description;
	private Integer quantity;
	private Date expiryDt;
	private Set<InventoryItem> inventoryItems;

    public Lot() {
    }

    public Lot(String name, Integer quantity, Date expiryDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.quantity = quantity;
        this.expiryDt = expiryDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public Lot(String name, String description, Integer quantity, Date expiryDt, Set inventoryItems, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.expiryDt = expiryDt;
        this.inventoryItems = inventoryItems;
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
	
    @Column(name="QUANTITY", nullable=false)
    public Integer getQuantity() 
    {
        return this.quantity;
    }
    
    public void setQuantity(Integer quantity) 
    {
        this.quantity = quantity;
    }
	
    @Column(name="EXPIRY_DT", nullable=false, length=10)
    public Date getExpiryDt() 
    {
        return this.expiryDt;
    }
    
    public void setExpiryDt(Date expiryDt) 
    {
        this.expiryDt = expiryDt;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="lot")
    @JsonIgnore
    public Set<InventoryItem> getInventoryItems() 
    {
        return this.inventoryItems;
    }
    
    public void setInventoryItems(Set<InventoryItem> inventoryItems) 
    {
        this.inventoryItems = inventoryItems;
    }			
		
    

}
