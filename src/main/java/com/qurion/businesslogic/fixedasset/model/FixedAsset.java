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

import com.qurion.businesslogic.businessdata.model.Uom;
import com.qurion.businesslogic.fixedasset.model.FixedAssetType;

import java.util.Date;
import java.util.Date;
import java.util.Date;
import java.util.Set;

import com.qurion.businesslogic.accounting.model.AccountingTransaction;

import java.util.Set;

import com.qurion.businesslogic.workeffort.model.WorkEffortAssetAssignment;

import java.util.Set;

import com.qurion.businesslogic.accounting.model.FixedAssetDepreciationMethod;
import com.qurion.businesslogic.application.model.BaseEntity;

import java.util.Set;

import com.qurion.businesslogic.ordering.model.Requirement;

import java.util.Set;

import com.qurion.businesslogic.shipment.model.ShipmentRouteSegment;

/**
 * FixedAsset 
 * @author Edward Banfa
 */
@Entity
@Table(name="FIXED_ASSET"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class FixedAsset  extends BaseEntity implements java.io.Serializable {
	private Uom uom;
	private FixedAssetType fixedAssetType;
	private String name;
	private String description;
	private Date aquiredDt;
	private Date lastServicedDt;
	private Date nextServicedDt;
	private Integer productionCapacity;
	private Set<AccountingTransaction> accountingTransactions;
	private Set<WorkEffortAssetAssignment> workEffortAssetAssignments;
	private Set<FixedAssetDepreciationMethod> fixedAssetDepreciationMethods;
	private Set<Requirement> requirements;
	private Set<ShipmentRouteSegment> shipmentRouteSegments;

    public FixedAsset() {
    }

    public FixedAsset(String name, Date aquiredDt, Integer productionCapacity, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.name = name;
        this.aquiredDt = aquiredDt;
        this.productionCapacity = productionCapacity;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public FixedAsset(Uom uom, FixedAssetType fixedAssetType, String name, String description, Date aquiredDt, Date lastServicedDt, Date nextServicedDt, Integer productionCapacity, Set accountingTransactions, Set workEffortAssetAssignments, Set fixedAssetDepreciationMethods, Set requirements, Set shipmentRouteSegments, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.uom = uom;
        this.fixedAssetType = fixedAssetType;
        this.name = name;
        this.description = description;
        this.aquiredDt = aquiredDt;
        this.lastServicedDt = lastServicedDt;
        this.nextServicedDt = nextServicedDt;
        this.productionCapacity = productionCapacity;
        this.accountingTransactions = accountingTransactions;
        this.workEffortAssetAssignments = workEffortAssetAssignments;
        this.fixedAssetDepreciationMethods = fixedAssetDepreciationMethods;
        this.requirements = requirements;
        this.shipmentRouteSegments = shipmentRouteSegments;
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
    @JoinColumn(name="UOM_ID")
    @JsonIgnore
    public Uom getUom() 
    {
        return this.uom;
    }
    
    public void setUom(Uom uom)
    {
        this.uom = uom;
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FIXED_ASSET_TY_ID")
    @JsonIgnore
    public FixedAssetType getFixedAssetType() 
    {
        return this.fixedAssetType;
    }
    
    public void setFixedAssetType(FixedAssetType fixedAssetType)
    {
        this.fixedAssetType = fixedAssetType;
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
	
    @Column(name="AQUIRED_DT", nullable=false, length=10)
    public Date getAquiredDt() 
    {
        return this.aquiredDt;
    }
    
    public void setAquiredDt(Date aquiredDt) 
    {
        this.aquiredDt = aquiredDt;
    }
	
    @Column(name="LAST_SERVICED_DT", length=10)
    public Date getLastServicedDt() 
    {
        return this.lastServicedDt;
    }
    
    public void setLastServicedDt(Date lastServicedDt) 
    {
        this.lastServicedDt = lastServicedDt;
    }
	
    @Column(name="NEXT_SERVICED_DT", length=10)
    public Date getNextServicedDt() 
    {
        return this.nextServicedDt;
    }
    
    public void setNextServicedDt(Date nextServicedDt) 
    {
        this.nextServicedDt = nextServicedDt;
    }
	
    @Column(name="PRODUCTION_CAPACITY", nullable=false)
    public Integer getProductionCapacity() 
    {
        return this.productionCapacity;
    }
    
    public void setProductionCapacity(Integer productionCapacity) 
    {
        this.productionCapacity = productionCapacity;
    }
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="fixedAsset")
    @JsonIgnore
    public Set<AccountingTransaction> getAccountingTransactions() 
    {
        return this.accountingTransactions;
    }
    
    public void setAccountingTransactions(Set<AccountingTransaction> accountingTransactions) 
    {
        this.accountingTransactions = accountingTransactions;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="fixedAsset")
    @JsonIgnore
    public Set<WorkEffortAssetAssignment> getWorkEffortAssetAssignments() 
    {
        return this.workEffortAssetAssignments;
    }
    
    public void setWorkEffortAssetAssignments(Set<WorkEffortAssetAssignment> workEffortAssetAssignments) 
    {
        this.workEffortAssetAssignments = workEffortAssetAssignments;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="fixedAsset")
    @JsonIgnore
    public Set<FixedAssetDepreciationMethod> getFixedAssetDepreciationMethods() 
    {
        return this.fixedAssetDepreciationMethods;
    }
    
    public void setFixedAssetDepreciationMethods(Set<FixedAssetDepreciationMethod> fixedAssetDepreciationMethods) 
    {
        this.fixedAssetDepreciationMethods = fixedAssetDepreciationMethods;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="fixedAsset")
    @JsonIgnore
    public Set<Requirement> getRequirements() 
    {
        return this.requirements;
    }
    
    public void setRequirements(Set<Requirement> requirements) 
    {
        this.requirements = requirements;
    }			
		
    @OneToMany(fetch=FetchType.LAZY, mappedBy="fixedAsset")
    @JsonIgnore
    public Set<ShipmentRouteSegment> getShipmentRouteSegments() 
    {
        return this.shipmentRouteSegments;
    }
    
    public void setShipmentRouteSegments(Set<ShipmentRouteSegment> shipmentRouteSegments) 
    {
        this.shipmentRouteSegments = shipmentRouteSegments;
    }			
		
    

}
