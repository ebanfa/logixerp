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
import com.qurion.businesslogic.fixedasset.model.FixedAsset;
import com.qurion.businesslogic.workeffort.model.WorkEffort;

import java.util.Date;
import java.util.Date;
import java.math.BigDecimal;

/**
 * WorkEffortAssetAssignment 
 * @author Edward Banfa
 */
@Entity
@Table(name="WORK_EFFORT_ASSET_ASSIGNMENT"
    ,catalog="businesslogic"
    ,uniqueConstraints = @UniqueConstraint(columnNames="CODE") 
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class WorkEffortAssetAssignment  extends BaseEntity implements java.io.Serializable {
	private FixedAsset fixedAsset;
	private WorkEffort workEffort;
	private Date fromDt;
	private Date toDt;
	private BigDecimal allocCost;
	private String comment;

    public WorkEffortAssetAssignment() {
    }

    public WorkEffortAssetAssignment(Date fromDt, BigDecimal allocCost, Integer id, String code, Date effectiveDt, Character recSt, Date createdDt, String createdByUsr) 
    {
        this.fromDt = fromDt;
        this.allocCost = allocCost;
        this.id = id;
        this.code = code;
        this.effectiveDt = effectiveDt;
        this.recSt = recSt;
        this.createdDt = createdDt;
        this.createdByUsr = createdByUsr;
    }

    public WorkEffortAssetAssignment(FixedAsset fixedAsset, WorkEffort workEffort, Date fromDt, Date toDt, BigDecimal allocCost, String comment, Integer id, String code, Date effectiveDt, Character recSt, Integer versionNo, Date rowTs, Date createdDt, String createdByUsr, Date lastModifiedDt, String lastModifiedUsr) 
    {
        this.fixedAsset = fixedAsset;
        this.workEffort = workEffort;
        this.fromDt = fromDt;
        this.toDt = toDt;
        this.allocCost = allocCost;
        this.comment = comment;
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
    @JoinColumn(name="WRK_EFFORT_ID")
    @JsonIgnore
    public WorkEffort getWorkEffort() 
    {
        return this.workEffort;
    }
    
    public void setWorkEffort(WorkEffort workEffort)
    {
        this.workEffort = workEffort;
    }
    
    @Column(name="FROM_DT", nullable=false, length=10)
    public Date getFromDt() 
    {
        return this.fromDt;
    }
    
    public void setFromDt(Date fromDt) 
    {
        this.fromDt = fromDt;
    }
	
    @Column(name="TO_DT", length=10)
    public Date getToDt() 
    {
        return this.toDt;
    }
    
    public void setToDt(Date toDt) 
    {
        this.toDt = toDt;
    }
	
    @Column(name="ALLOC_COST", nullable=false)
    public BigDecimal getAllocCost() 
    {
        return this.allocCost;
    }
    
    public void setAllocCost(BigDecimal allocCost) 
    {
        this.allocCost = allocCost;
    }
	
    @Column(name="COMMENT", length=150)
    public String getComment() 
    {
        return this.comment;
    }
    
    public void setComment(String comment) 
    {
        this.comment = comment;
    }
	
    

}
