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
import com.qurion.businesslogic.fixedasset.model.Facility;
import com.qurion.businesslogic.party.model.Party;
import com.qurion.businesslogic.fixedasset.model.FixedAsset;
import com.qurion.businesslogic.shipment.model.Shipment;
import com.qurion.businesslogic.fixedasset.model.Facility;
import com.qurion.businesslogic.shipment.model.ShipmentMethodType;

import java.util.Date;
import java.util.Date;
import java.util.Date;
import java.util.Date;

/**
 * ShipmentRouteSegment 
 * @author Edward Banfa
 */
@Entity
@Table(name="SHIPMENT_ROUTE_SEGMENT"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ShipmentRouteSegment  extends BaseEntity implements java.io.Serializable {
	private Facility facilityByToFaciltyId;
	private Party party;
	private FixedAsset fixedAsset;
	private Shipment shipment;
	private Facility facilityByFromFaciltyId;
	private ShipmentMethodType shipmentMethodType;
	private String name;
	private String description;
	private Date actualStartDt;
	private Date actualArrivalDt;
	private Date estimatedStartDt;
	private Date estimatedArrivalDt;
	private Integer startMileage;
	private Integer endMileage;
	private Integer fuelUsed;

    public ShipmentRouteSegment() {
    }

    public ShipmentRouteSegment(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public ShipmentRouteSegment(Facility facilityByToFaciltyId, Party party, FixedAsset fixedAsset, Shipment shipment, Facility facilityByFromFaciltyId, ShipmentMethodType shipmentMethodType, String name, String description, Date actualStartDt, Date actualArrivalDt, Date estimatedStartDt, Date estimatedArrivalDt, Integer startMileage, Integer endMileage, Integer fuelUsed, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.facilityByToFaciltyId = facilityByToFaciltyId;
        this.party = party;
        this.fixedAsset = fixedAsset;
        this.shipment = shipment;
        this.facilityByFromFaciltyId = facilityByFromFaciltyId;
        this.shipmentMethodType = shipmentMethodType;
        this.name = name;
        this.description = description;
        this.actualStartDt = actualStartDt;
        this.actualArrivalDt = actualArrivalDt;
        this.estimatedStartDt = estimatedStartDt;
        this.estimatedArrivalDt = estimatedArrivalDt;
        this.startMileage = startMileage;
        this.endMileage = endMileage;
        this.fuelUsed = fuelUsed;
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
    @JoinColumn(name="TO_FACILTY_ID")
    @JsonIgnore
    public Facility getFacilityByToFaciltyId() 
    {
        return this.facilityByToFaciltyId;
    }
    
    public void setFacilityByToFaciltyId(Facility facilityByToFaciltyId)
    {
        this.facilityByToFaciltyId = facilityByToFaciltyId;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SHIPPED_BY_PARTY_ID")
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
    @JoinColumn(name="FIXED_ASSET_ID")
    @JsonIgnore
    public FixedAsset getFixedAsset() 
    {
        return this.fixedAsset;
    }
    
    public void setFixedAsset(FixedAsset fixedAsset)
    {
        this.fixedAsset = fixedAsset;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SHIPMENT_ID")
    @JsonIgnore
    public Shipment getShipment() 
    {
        return this.shipment;
    }
    
    public void setShipment(Shipment shipment)
    {
        this.shipment = shipment;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FROM_FACILTY_ID")
    @JsonIgnore
    public Facility getFacilityByFromFaciltyId() 
    {
        return this.facilityByFromFaciltyId;
    }
    
    public void setFacilityByFromFaciltyId(Facility facilityByFromFaciltyId)
    {
        this.facilityByFromFaciltyId = facilityByFromFaciltyId;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="SHIPMENT_METHOD_TY_ID")
    @JsonIgnore
    public ShipmentMethodType getShipmentMethodType() 
    {
        return this.shipmentMethodType;
    }
    
    public void setShipmentMethodType(ShipmentMethodType shipmentMethodType)
    {
        this.shipmentMethodType = shipmentMethodType;
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
	
    @Column(name="ACTUAL_START_DT", length=19)
    public Date getActualStartDt() 
    {
        return this.actualStartDt;
    }
    
    public void setActualStartDt(Date actualStartDt) 
    {
        this.actualStartDt = actualStartDt;
    }
	
    @Column(name="ACTUAL_ARRIVAL_DT", length=19)
    public Date getActualArrivalDt() 
    {
        return this.actualArrivalDt;
    }
    
    public void setActualArrivalDt(Date actualArrivalDt) 
    {
        this.actualArrivalDt = actualArrivalDt;
    }
	
    @Column(name="ESTIMATED_START_DT", length=19)
    public Date getEstimatedStartDt() 
    {
        return this.estimatedStartDt;
    }
    
    public void setEstimatedStartDt(Date estimatedStartDt) 
    {
        this.estimatedStartDt = estimatedStartDt;
    }
	
    @Column(name="ESTIMATED_ARRIVAL_DT", length=19)
    public Date getEstimatedArrivalDt() 
    {
        return this.estimatedArrivalDt;
    }
    
    public void setEstimatedArrivalDt(Date estimatedArrivalDt) 
    {
        this.estimatedArrivalDt = estimatedArrivalDt;
    }
	
    @Column(name="START_MILEAGE")
    public Integer getStartMileage() 
    {
        return this.startMileage;
    }
    
    public void setStartMileage(Integer startMileage) 
    {
        this.startMileage = startMileage;
    }
	
    @Column(name="END_MILEAGE")
    public Integer getEndMileage() 
    {
        return this.endMileage;
    }
    
    public void setEndMileage(Integer endMileage) 
    {
        this.endMileage = endMileage;
    }
	
    @Column(name="FUEL_USED")
    public Integer getFuelUsed() 
    {
        return this.fuelUsed;
    }
    
    public void setFuelUsed(Integer fuelUsed) 
    {
        this.fuelUsed = fuelUsed;
    }
	
    

}
