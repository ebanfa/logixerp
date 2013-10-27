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
import com.qurion.businesslogic.shipment.model.Shipment;
import com.qurion.businesslogic.shipment.model.ShipmentStatusType;

import java.util.Date;

/**
 * ShipmentStatus 
 * @author Edward Banfa
 */
@Entity
@Table(name="SHIPMENT_STATUS"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ShipmentStatus  extends BaseEntity implements java.io.Serializable {
	private Shipment shipment;
	private ShipmentStatusType shipmentStatusType;
	private Date statusDt;

    public ShipmentStatus() {
    }

    public ShipmentStatus(Date statusDt, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.statusDt = statusDt;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public ShipmentStatus(Shipment shipment, ShipmentStatusType shipmentStatusType, Date statusDt, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.shipment = shipment;
        this.shipmentStatusType = shipmentStatusType;
        this.statusDt = statusDt;
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
    @JoinColumn(name="SHIPMENT_STATUS_TY_ID")
    @JsonIgnore
    public ShipmentStatusType getShipmentStatusType() 
    {
        return this.shipmentStatusType;
    }
    
    public void setShipmentStatusType(ShipmentStatusType shipmentStatusType)
    {
        this.shipmentStatusType = shipmentStatusType;
    }
    
    @Column(name="STATUS_DT", nullable=false, length=10)
    public Date getStatusDt() 
    {
        return this.statusDt;
    }
    
    public void setStatusDt(Date statusDt) 
    {
        this.statusDt = statusDt;
    }
	
    

}
