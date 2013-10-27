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

import java.util.Date;
import java.util.Date;
import java.util.Date;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.qurion.businesslogic.shipment.model.ShipmentRouteSegment;

import java.util.Set;

import com.qurion.businesslogic.shipment.model.Document;

import java.util.Set;

import com.qurion.businesslogic.shipment.model.ShipmentStatus;

import java.util.Set;

import com.qurion.businesslogic.shipment.model.ShipmentItem;

/**
 * Shipment 
 * @author Edward Banfa
 */
@Entity
@Table(name="SHIPMENT"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Shipment  extends BaseEntity implements java.io.Serializable {
	private ShipmentType shipmentType;
	private String name;
	private String description;
	private Date estimatedShipDt;
	private Date estimatedReadyDt;
	private Date estimatedArrivalDt;
	private BigDecimal estimatedShipCost;
	private BigDecimal actualShipCost;
	private Date latestCancelDt;
	private String handlingInstructions;
	private Set<ShipmentRouteSegment> shipmentRouteSegments;
	private Set<Document> documents;
	private Set<ShipmentStatus> shipmentStatuses;
	private Set<ShipmentItem> shipmentItems;

    public Shipment() {
    }

    public Shipment(String name, Date estimatedShipDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.estimatedShipDt = estimatedShipDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public Shipment(ShipmentType shipmentType, String name, String description, Date estimatedShipDt, Date estimatedReadyDt, Date estimatedArrivalDt, BigDecimal estimatedShipCost, BigDecimal actualShipCost, Date latestCancelDt, String handlingInstructions, Set shipmentRouteSegments, Set documents, Set shipmentStatuses, Set shipmentItems, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.shipmentType = shipmentType;
        this.name = name;
        this.description = description;
        this.estimatedShipDt = estimatedShipDt;
        this.estimatedReadyDt = estimatedReadyDt;
        this.estimatedArrivalDt = estimatedArrivalDt;
        this.estimatedShipCost = estimatedShipCost;
        this.actualShipCost = actualShipCost;
        this.latestCancelDt = latestCancelDt;
        this.handlingInstructions = handlingInstructions;
        this.shipmentRouteSegments = shipmentRouteSegments;
        this.documents = documents;
        this.shipmentStatuses = shipmentStatuses;
        this.shipmentItems = shipmentItems;
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
    @JoinColumn(name="SHIPMENT_TY_ID")
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
	
    @Column(name="ESTIMATED_SHIP_DT", nullable=false, length=10)
    public Date getEstimatedShipDt() 
    {
        return this.estimatedShipDt;
    }
    
    public void setEstimatedShipDt(Date estimatedShipDt) 
    {
        this.estimatedShipDt = estimatedShipDt;
    }
	
    @Column(name="ESTIMATED_READY_DT", length=10)
    public Date getEstimatedReadyDt() 
    {
        return this.estimatedReadyDt;
    }
    
    public void setEstimatedReadyDt(Date estimatedReadyDt) 
    {
        this.estimatedReadyDt = estimatedReadyDt;
    }
	
    @Column(name="ESTIMATED_ARRIVAL_DT", length=10)
    public Date getEstimatedArrivalDt() 
    {
        return this.estimatedArrivalDt;
    }
    
    public void setEstimatedArrivalDt(Date estimatedArrivalDt) 
    {
        this.estimatedArrivalDt = estimatedArrivalDt;
    }
	
    @Column(name="ESTIMATED_SHIP_COST")
    public BigDecimal getEstimatedShipCost() 
    {
        return this.estimatedShipCost;
    }
    
    public void setEstimatedShipCost(BigDecimal estimatedShipCost) 
    {
        this.estimatedShipCost = estimatedShipCost;
    }
	
    @Column(name="ACTUAL_SHIP_COST")
    public BigDecimal getActualShipCost() 
    {
        return this.actualShipCost;
    }
    
    public void setActualShipCost(BigDecimal actualShipCost) 
    {
        this.actualShipCost = actualShipCost;
    }
	
    @Column(name="LATEST_CANCEL_DT", length=10)
    public Date getLatestCancelDt() 
    {
        return this.latestCancelDt;
    }
    
    public void setLatestCancelDt(Date latestCancelDt) 
    {
        this.latestCancelDt = latestCancelDt;
    }
	
    @Column(name="HANDLING_INSTRUCTIONS", length=150)
    public String getHandlingInstructions() 
    {
        return this.handlingInstructions;
    }
    
    public void setHandlingInstructions(String handlingInstructions) 
    {
        this.handlingInstructions = handlingInstructions;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="shipment")
    @JsonIgnore
    public Set<ShipmentRouteSegment> getShipmentRouteSegments() 
    {
        return this.shipmentRouteSegments;
    }
    
    public void setShipmentRouteSegments(Set<ShipmentRouteSegment> shipmentRouteSegments) 
    {
        this.shipmentRouteSegments = shipmentRouteSegments;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="shipment")
    @JsonIgnore
    public Set<Document> getDocuments() 
    {
        return this.documents;
    }
    
    public void setDocuments(Set<Document> documents) 
    {
        this.documents = documents;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="shipment")
    @JsonIgnore
    public Set<ShipmentStatus> getShipmentStatuses() 
    {
        return this.shipmentStatuses;
    }
    
    public void setShipmentStatuses(Set<ShipmentStatus> shipmentStatuses) 
    {
        this.shipmentStatuses = shipmentStatuses;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="shipment")
    @JsonIgnore
    public Set<ShipmentItem> getShipmentItems() 
    {
        return this.shipmentItems;
    }
    
    public void setShipmentItems(Set<ShipmentItem> shipmentItems) 
    {
        this.shipmentItems = shipmentItems;
    }			
		
    

}
