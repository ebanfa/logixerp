/**
 *  Business Logic.
 */
package com.qurion.businesslogic.workeffort.model;

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
import com.qurion.businesslogic.workeffort.model.WorkEffortType;
import com.qurion.businesslogic.fixedasset.model.FixedAssetType;

import java.math.BigDecimal;

/**
 * WorkEffortFixedAssetStandard 
 * @author Edward Banfa
 */
@Entity
@Table(name="WORK_EFFORT_FIXED_ASSET_STANDARD"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class WorkEffortFixedAssetStandard  extends BaseEntity implements java.io.Serializable {
	private WorkEffortType workEffortType;
	private FixedAssetType fixedAssetType;
	private Integer estQuantity;
	private Integer estDuration;
	private BigDecimal estCost;

    public WorkEffortFixedAssetStandard() {
    }

    public WorkEffortFixedAssetStandard(Integer estQuantity, Integer estDuration, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.estQuantity = estQuantity;
        this.estDuration = estDuration;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public WorkEffortFixedAssetStandard(WorkEffortType workEffortType, FixedAssetType fixedAssetType, Integer estQuantity, Integer estDuration, BigDecimal estCost, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.workEffortType = workEffortType;
        this.fixedAssetType = fixedAssetType;
        this.estQuantity = estQuantity;
        this.estDuration = estDuration;
        this.estCost = estCost;
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
    @JoinColumn(name="WRK_EFFORT_TY_ID")
    @JsonIgnore
    public WorkEffortType getWorkEffortType() 
    {
        return this.workEffortType;
    }
    
    public void setWorkEffortType(WorkEffortType workEffortType)
    {
        this.workEffortType = workEffortType;
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
    
    @Column(name="EST_QUANTITY", nullable=false)
    public Integer getEstQuantity() 
    {
        return this.estQuantity;
    }
    
    public void setEstQuantity(Integer estQuantity) 
    {
        this.estQuantity = estQuantity;
    }
	
    @Column(name="EST_DURATION", nullable=false)
    public Integer getEstDuration() 
    {
        return this.estDuration;
    }
    
    public void setEstDuration(Integer estDuration) 
    {
        this.estDuration = estDuration;
    }
	
    @Column(name="EST_COST")
    public BigDecimal getEstCost() 
    {
        return this.estCost;
    }
    
    public void setEstCost(BigDecimal estCost) 
    {
        this.estCost = estCost;
    }
	
    

}
