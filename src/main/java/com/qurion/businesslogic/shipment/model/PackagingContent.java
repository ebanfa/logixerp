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
import com.qurion.businesslogic.shipment.model.ShipmentPackage;

/**
 * PackagingContent 
 * @author Edward Banfa
 */
@Entity
@Table(name="PACKAGING_CONTENT"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PackagingContent  extends BaseEntity implements java.io.Serializable {
	private ShipmentItem shipmentItem;
	private ShipmentPackage shipmentPackage;
	private Integer quantity;

    public PackagingContent() {
    }

    public PackagingContent(Integer quantity, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.quantity = quantity;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public PackagingContent(ShipmentItem shipmentItem, ShipmentPackage shipmentPackage, Integer quantity, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.shipmentItem = shipmentItem;
        this.shipmentPackage = shipmentPackage;
        this.quantity = quantity;
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
    @JoinColumn(name="PACKAGE_ID")
    @JsonIgnore
    public ShipmentPackage getShipmentPackage() 
    {
        return this.shipmentPackage;
    }
    
    public void setShipmentPackage(ShipmentPackage shipmentPackage)
    {
        this.shipmentPackage = shipmentPackage;
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
	
    

}
