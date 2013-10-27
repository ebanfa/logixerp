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
import com.qurion.businesslogic.product.model.ContainerType;
import com.qurion.businesslogic.fixedasset.model.Facility;

import java.util.Set;

import com.qurion.businesslogic.product.model.InventoryItem;

/**
 * Container 
 * @author Edward Banfa
 */
@Entity
@Table(name="CONTAINER"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Container  extends BaseEntity implements java.io.Serializable {
	private ContainerType containerType;
	private Facility facility;
	private String description;
	private Set<InventoryItem> inventoryItems;

    public Container() {
    }

    public Container(Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public Container(ContainerType containerType, Facility facility, String description, Set inventoryItems, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.containerType = containerType;
        this.facility = facility;
        this.description = description;
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
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CONTAINER_TY_ID")
    @JsonIgnore
    public ContainerType getContainerType() 
    {
        return this.containerType;
    }
    
    public void setContainerType(ContainerType containerType)
    {
        this.containerType = containerType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FACILITY_ID")
    @JsonIgnore
    public Facility getFacility() 
    {
        return this.facility;
    }
    
    public void setFacility(Facility facility)
    {
        this.facility = facility;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="container")
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
