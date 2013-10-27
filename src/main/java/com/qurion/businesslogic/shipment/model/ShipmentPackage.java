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

import java.util.Set;

import com.qurion.businesslogic.shipment.model.ShipmentReceipt;

import java.util.Set;

import com.qurion.businesslogic.shipment.model.Document;

import java.util.Set;

import com.qurion.businesslogic.shipment.model.PackagingContent;

/**
 * ShipmentPackage 
 * @author Edward Banfa
 */
@Entity
@Table(name="SHIPMENT_PACKAGE"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ShipmentPackage  extends BaseEntity implements java.io.Serializable {
	private String name;
	private String description;
	private Set<ShipmentReceipt> shipmentReceipts;
	private Set<Document> documents;
	private Set<PackagingContent> packagingContents;

    public ShipmentPackage() {
    }

    public ShipmentPackage(String name, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public ShipmentPackage(String name, String description, Set shipmentReceipts, Set documents, Set packagingContents, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.name = name;
        this.description = description;
        this.shipmentReceipts = shipmentReceipts;
        this.documents = documents;
        this.packagingContents = packagingContents;
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
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="shipmentPackage")
    @JsonIgnore
    public Set<ShipmentReceipt> getShipmentReceipts() 
    {
        return this.shipmentReceipts;
    }
    
    public void setShipmentReceipts(Set<ShipmentReceipt> shipmentReceipts) 
    {
        this.shipmentReceipts = shipmentReceipts;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="shipmentPackage")
    @JsonIgnore
    public Set<Document> getDocuments() 
    {
        return this.documents;
    }
    
    public void setDocuments(Set<Document> documents) 
    {
        this.documents = documents;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="shipmentPackage")
    @JsonIgnore
    public Set<PackagingContent> getPackagingContents() 
    {
        return this.packagingContents;
    }
    
    public void setPackagingContents(Set<PackagingContent> packagingContents) 
    {
        this.packagingContents = packagingContents;
    }			
		
    

}
