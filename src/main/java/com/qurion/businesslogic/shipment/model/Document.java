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
import com.qurion.businesslogic.shipment.model.ShipmentPackage;
import com.qurion.businesslogic.shipment.model.ShipmentItem;
import com.qurion.businesslogic.shipment.model.Shipment;
import com.qurion.businesslogic.shipment.model.DocumentType;

/**
 * Document 
 * @author Edward Banfa
 */
@Entity
@Table(name="DOCUMENT"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Document  extends BaseEntity implements java.io.Serializable {
	private ShipmentPackage shipmentPackage;
	private ShipmentItem shipmentItem;
	private Shipment shipment;
	private DocumentType documentType;
	private String name;
	private String description;

    public Document() {
    }

    public Document(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public Document(ShipmentPackage shipmentPackage, ShipmentItem shipmentItem, Shipment shipment, DocumentType documentType, String name, String description, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.shipmentPackage = shipmentPackage;
        this.shipmentItem = shipmentItem;
        this.shipment = shipment;
        this.documentType = documentType;
        this.name = name;
        this.description = description;
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
    @JoinColumn(name="SHIPMENT_PACKAGE_ID")
    @JsonIgnore
    public ShipmentPackage getShipmentPackage() 
    {
        return this.shipmentPackage;
    }
    
    public void setShipmentPackage(ShipmentPackage shipmentPackage)
    {
        this.shipmentPackage = shipmentPackage;
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
    @JoinColumn(name="DOC_TY_ID")
    @JsonIgnore
    public DocumentType getDocumentType() 
    {
        return this.documentType;
    }
    
    public void setDocumentType(DocumentType documentType)
    {
        this.documentType = documentType;
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
	
    

}
