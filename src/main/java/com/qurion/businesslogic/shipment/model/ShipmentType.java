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
import com.qurion.businesslogic.shipment.model.ShipmentType;

import java.util.Set;

import com.qurion.businesslogic.shipment.model.ShipmentType;

import java.util.Set;

import com.qurion.businesslogic.shipment.model.Shipment;

/**
 * ShipmentType 
 * @author Edward Banfa
 */
@Entity
@Table(name="SHIPMENT_TYPE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ShipmentType  extends BaseEntity implements java.io.Serializable {
	private ShipmentType shipmentType;
	private String name;
	private String description;
	private Set<ShipmentType> shipmentTypes;
	private Set<Shipment> shipments;

    public ShipmentType() {
    }

    public ShipmentType(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public ShipmentType(ShipmentType shipmentType, String name, String description, Set shipmentTypes, Set shipments, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.shipmentType = shipmentType;
        this.name = name;
        this.description = description;
        this.shipmentTypes = shipmentTypes;
        this.shipments = shipments;
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
    @JoinColumn(name="PARENT_SHIPMENT_TY_ID")
    @JsonIgnore
    public ShipmentType getShipmentType() 
    {
        return this.shipmentType;
    }
    
    public void setShipmentType(ShipmentType shipmentType)
    {
        this.shipmentType = shipmentType;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="shipmentType")
    @JsonIgnore
    public Set<ShipmentType> getShipmentTypes() 
    {
        return this.shipmentTypes;
    }
    
    public void setShipmentTypes(Set<ShipmentType> shipmentTypes) 
    {
        this.shipmentTypes = shipmentTypes;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="shipmentType")
    @JsonIgnore
    public Set<Shipment> getShipments() 
    {
        return this.shipments;
    }
    
    public void setShipments(Set<Shipment> shipments) 
    {
        this.shipments = shipments;
    }			
		
    

}
