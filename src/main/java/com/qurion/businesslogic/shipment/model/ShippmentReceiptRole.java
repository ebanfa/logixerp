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
import com.qurion.businesslogic.shipment.model.ShipmentReceipt;
import com.qurion.businesslogic.shipment.model.ShipmentReceiptRoleType;
import com.qurion.businesslogic.party.model.Party;

/**
 * ShippmentReceiptRole 
 * @author Edward Banfa
 */
@Entity
@Table(name="SHIPPMENT_RECEIPT_ROLE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ShippmentReceiptRole  extends BaseEntity implements java.io.Serializable {
	private ShipmentReceipt shipmentReceipt;
	private ShipmentReceiptRoleType shipmentReceiptRoleType;
	private Party party;
	private String name;
	private String description;

    public ShippmentReceiptRole() {
    }

    public ShippmentReceiptRole(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public ShippmentReceiptRole(ShipmentReceipt shipmentReceipt, ShipmentReceiptRoleType shipmentReceiptRoleType, Party party, String name, String description, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.shipmentReceipt = shipmentReceipt;
        this.shipmentReceiptRoleType = shipmentReceiptRoleType;
        this.party = party;
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
    @JoinColumn(name="RECEIPT_ID")
    @JsonIgnore
    public ShipmentReceipt getShipmentReceipt() 
    {
        return this.shipmentReceipt;
    }
    
    public void setShipmentReceipt(ShipmentReceipt shipmentReceipt)
    {
        this.shipmentReceipt = shipmentReceipt;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RECEIPT_ROLE_TY_ID")
    @JsonIgnore
    public ShipmentReceiptRoleType getShipmentReceiptRoleType() 
    {
        return this.shipmentReceiptRoleType;
    }
    
    public void setShipmentReceiptRoleType(ShipmentReceiptRoleType shipmentReceiptRoleType)
    {
        this.shipmentReceiptRoleType = shipmentReceiptRoleType;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PARTY_ID")
    @JsonIgnore
    public Party getParty() 
    {
        return this.party;
    }
    
    public void setParty(Party party)
    {
        this.party = party;
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
