/**
 *  Business Logic.
 */
package com.qurion.businesslogic.fixedasset.model;

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
import com.qurion.businesslogic.fixedasset.model.FacilityType;

import java.util.Set;

import com.qurion.businesslogic.product.model.InventoryItem;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortPartyAssignement;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffort;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.Requirement;

import java.util.Set;

import com.qurion.businesslogic.product.model.Container;

import java.util.Set;

import com.qurion.businesslogic.shipment.model.ShipmentRouteSegment;

import java.util.Set;

import com.qurion.businesslogic.shipment.model.ShipmentRouteSegment;

/**
 * Facility 
 * @author Edward Banfa
 */
@Entity
@Table(name="FACILITY"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Facility  extends BaseEntity implements java.io.Serializable {
	private FacilityType facilityType;
	private String name;
	private String description;
	private Set<InventoryItem> inventoryItems;
	private Set<WorkEffortPartyAssignement> workEffortPartyAssignements;
	private Set<WorkEffort> workEfforts;
	private Set<Requirement> requirements;
	private Set<Container> containers;
	private Set<ShipmentRouteSegment> shipmentRouteSegmentsForToFaciltyId;
	private Set<ShipmentRouteSegment> shipmentRouteSegmentsForFromFaciltyId;

    public Facility() {
    }

    public Facility(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public Facility(FacilityType facilityType, String name, String description, Set inventoryItems, Set workEffortPartyAssignements, Set workEfforts, Set requirements, Set containers, Set shipmentRouteSegmentsForToFaciltyId, Set shipmentRouteSegmentsForFromFaciltyId, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.facilityType = facilityType;
        this.name = name;
        this.description = description;
        this.inventoryItems = inventoryItems;
        this.workEffortPartyAssignements = workEffortPartyAssignements;
        this.workEfforts = workEfforts;
        this.requirements = requirements;
        this.containers = containers;
        this.shipmentRouteSegmentsForToFaciltyId = shipmentRouteSegmentsForToFaciltyId;
        this.shipmentRouteSegmentsForFromFaciltyId = shipmentRouteSegmentsForFromFaciltyId;
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
    @JoinColumn(name="FACILITY_TY_ID")
    @JsonIgnore
    public FacilityType getFacilityType() 
    {
        return this.facilityType;
    }
    
    public void setFacilityType(FacilityType facilityType)
    {
        this.facilityType = facilityType;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="facility")
    @JsonIgnore
    public Set<InventoryItem> getInventoryItems() 
    {
        return this.inventoryItems;
    }
    
    public void setInventoryItems(Set<InventoryItem> inventoryItems) 
    {
        this.inventoryItems = inventoryItems;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="facility")
    @JsonIgnore
    public Set<WorkEffortPartyAssignement> getWorkEffortPartyAssignements() 
    {
        return this.workEffortPartyAssignements;
    }
    
    public void setWorkEffortPartyAssignements(Set<WorkEffortPartyAssignement> workEffortPartyAssignements) 
    {
        this.workEffortPartyAssignements = workEffortPartyAssignements;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="facility")
    @JsonIgnore
    public Set<WorkEffort> getWorkEfforts() 
    {
        return this.workEfforts;
    }
    
    public void setWorkEfforts(Set<WorkEffort> workEfforts) 
    {
        this.workEfforts = workEfforts;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="facility")
    @JsonIgnore
    public Set<Requirement> getRequirements() 
    {
        return this.requirements;
    }
    
    public void setRequirements(Set<Requirement> requirements) 
    {
        this.requirements = requirements;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="facility")
    @JsonIgnore
    public Set<Container> getContainers() 
    {
        return this.containers;
    }
    
    public void setContainers(Set<Container> containers) 
    {
        this.containers = containers;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="facilityByToFaciltyId")
    @JsonIgnore
    public Set<ShipmentRouteSegment> getShipmentRouteSegmentsForToFaciltyId() 
    {
        return this.shipmentRouteSegmentsForToFaciltyId;
    }
    
    public void setShipmentRouteSegmentsForToFaciltyId(Set<ShipmentRouteSegment> shipmentRouteSegmentsForToFaciltyId) 
    {
        this.shipmentRouteSegmentsForToFaciltyId = shipmentRouteSegmentsForToFaciltyId;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="facilityByFromFaciltyId")
    @JsonIgnore
    public Set<ShipmentRouteSegment> getShipmentRouteSegmentsForFromFaciltyId() 
    {
        return this.shipmentRouteSegmentsForFromFaciltyId;
    }
    
    public void setShipmentRouteSegmentsForFromFaciltyId(Set<ShipmentRouteSegment> shipmentRouteSegmentsForFromFaciltyId) 
    {
        this.shipmentRouteSegmentsForFromFaciltyId = shipmentRouteSegmentsForFromFaciltyId;
    }			
		
    

}
